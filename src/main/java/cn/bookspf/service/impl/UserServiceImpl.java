package cn.bookspf.service.impl;

import cn.bookspf.mapper.UserMapper;
import cn.bookspf.model.Dao.User;
import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.CaptchaResponse;
import cn.bookspf.model.RO.Response;
import cn.bookspf.service.UserService;
import cn.bookspf.utils.Generator;
import cn.bookspf.utils.Operator;
import cn.bookspf.utils.Validator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static cn.bookspf.constant.AccountConstant.USERTOKEN;
import static cn.bookspf.constant.RoleConstant.NORMAL;
import static cn.bookspf.constant.StatusConstant.SUCCESS;


/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {
    HttpSession httpSession;
    Validator validator;
    Operator operator;
    UserMapper userMapper;

    @Autowired
    public UserServiceImpl(HttpSession httpSession, UserMapper userMapper){
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.operator=new Operator();
        this.userMapper=userMapper;
    }

    @Override
    public Response changeBalance(UserDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();
        Double userBalance = userMapper.getBalance(uid);
        userBalance = request.getBalance()+userBalance;
        userMapper.updateBalance(uid,userBalance);
        return new Response(true,String.valueOf(userMapper.getBalance(uid)));
    }

    @Override
    public Response changePassword(UserDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();
        String newPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        if(newPassword.equals(userMapper.getPasswordOfUid(uid))) {
            return new Response(false,"新密码与旧密码一致");
        }
        userMapper.updatePassword(uid,newPassword);
        return new Response(true,"修改成功");
    }

    @Override
    public Response changePhone(UserDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();
        String phone = request.getPhone();
        String oldPhone = userMapper.getPhone(uid);
        if(oldPhone!=null){
            if(oldPhone.equals(phone)) {
                return new Response(false,"新旧手机号一致");
            }
        }
        userMapper.updatePhone(uid,phone);
        return new Response(true,userMapper.getPhone(uid));
    }

    @Override
    public Response changeRealname(UserDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();
        String realname = request.getRealname();
        String oldRealname = userMapper.getRealname(uid);
        if(oldRealname!=null){
            if(oldRealname.equals(realname)) {
                return new Response(false,"新旧姓名一致");
            }
        }
        userMapper.updateRealname(uid,realname);
        return new Response(true,userMapper.getRealname(uid));
    }

    @Override
    public Response changeAddress(UserDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();
        String address = request.getAddress();
        String oldAddress = userMapper.getAddress(uid);
        if(oldAddress!=null){
            if(oldAddress.equals(address)) {
                return new Response(false,"新旧地址一致");
            }
        }
        userMapper.updateAddress(uid,address);
        return new Response(true,userMapper.getAddress(uid));
    }

    @Override
    public Response login(UserDTO request) {
        String captcha= (String) httpSession.getAttribute("captcha");
        if(captcha==null) {
            return new Response(false,"验证码过期");
        }
        if (!captcha.equalsIgnoreCase(request.getCaptcha())) {
            return new Response(false,"验证码错误");
        }
        String username = request.getUsername();
        String password = request.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user=userMapper.getUserOfUsername(username);
        if(user==null) {
            return new Response(false,"用户名错误");
        }
        String userPassword = user.getPassword();
        if(!password.equals(userPassword)) {
            return new Response(false,"密码错误");
        }
        String userToken = Generator.genAccesstoken();
        user.setAccesstoken(userToken);
        UserDTO temp = new UserDTO();
        BeanUtils.copyProperties(user,temp);
        userMapper.updateAccesstoken(temp);
        httpSession.setAttribute(USERTOKEN, userToken);
        httpSession.setAttribute("username", user.getUsername());
        httpSession.setAttribute("uid", user.getUid());
        httpSession.setMaxInactiveInterval(60*60);
        return new Response(true,"登陆成功");
    }

    @Override
    public void logout() {
        UserDTO user=(UserDTO)httpSession.getAttribute("user");
        UserDTO temp=new UserDTO();
        BeanUtils.copyProperties(user,temp);
        temp.setAccesstoken("");
        userMapper.updateAccesstoken(temp);
        httpSession.removeAttribute(USERTOKEN);
        httpSession.invalidate();
    }

    @Override
    public String changeCode() {
        try {
            CaptchaResponse captcha = Generator.generateCaptchaImg();
            httpSession.setAttribute("captcha",captcha.getCaptcha());
            return captcha.getImgJSON();
        } catch (IOException e) {return null;}
    }

    @Override
    public Response register(UserDTO request) {
        String captcha= (String) httpSession.getAttribute("captcha");
        if (!captcha.equalsIgnoreCase(request.getCaptcha())) {
            return new Response(false,"验证码错误");
        }
        String status =validator.isSame(userMapper, request);
        if(!SUCCESS.equals(status)) {
            return new Response(false,status);
        }
        Integer uid = Generator.generateUid();
        if(userMapper.findUid(uid)!=null) {
            uid+=1;
        }
        String password = request.getPassword();
        request.setUid(uid);
        request.setRole(NORMAL);
        request.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userMapper.insertUser(request);
        return new Response(true,"注册成功");
    }
}
