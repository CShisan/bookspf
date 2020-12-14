package cn.bookspf.service.impl;

import cn.bookspf.mapper.UserMapper;
import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.Response;
import cn.bookspf.model.RO.UserResponse;
import cn.bookspf.service.SuperManagerService;
import cn.bookspf.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;

import static cn.bookspf.constant.RoleConstant.*;
import static cn.bookspf.constant.StatusConstant.SUCCESS;


/**
 * @author Administrator
 */
@Service
public class SuperManagerServiceImpl implements SuperManagerService {
    HttpSession httpSession;
    Validator validator;
    UserMapper userMapper;

    @Autowired
    public SuperManagerServiceImpl(HttpSession httpSession, UserMapper userMapper) {
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.userMapper=userMapper;
    }

    @Override
    public Response superManager() {
        Response loginStatus = validator.isIdentity(userMapper,SUPER_MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        return new Response(true,"success");
    }

    /**
     * 获取图书管理员列表
     *
     * @return
     */
    @Override
    public Response getManagerList() {
        Response loginStatus = validator.isIdentity(userMapper,SUPER_MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        return new UserResponse(userMapper.getUserNoPasswordOfRole(MANAGER));
    }

    /**
     * 添加图书管理员
     *
     * @param request
     * @return
     */
    @Override
    public Response addAdmin(UserDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,SUPER_MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        String status =validator.isSame(userMapper, request);
        String password = request.getPassword();
        if(!SUCCESS.equals(status)) {
            return new Response(false,status);
        }
        request.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        request.setRole(MANAGER);
        userMapper.insertManager(request);
        return new Response(true,"添加成功");
    }

    /**
     * 修改图书管理员
     *
     * @param request
     * @return
     */
    @Override
    public Response alterAdmin(UserDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,SUPER_MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Integer uid = request.getUid();
        String username = request.getUsername();
        String email = request.getEmail();
        String password = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        if(userMapper.findUsername(username)!=null) {
            return new Response(false,"用户名已存在");
        }
        if(userMapper.findEmail(email)!=null) {
            return new Response(false,"邮箱已存在");
        }

        userMapper.updateUsername(uid,username);
        userMapper.updatePassword(uid,password);
        userMapper.updateEmail(uid,email);
        userMapper.updateRealname(uid,request.getRealname());

        return new Response(true,"添加成功");
    }

    /**
     * 删除图书管理员
     *
     * @param request
     * @return
     */
    @Override
    public Response deleteAdmin(UserDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,SUPER_MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        userMapper.deleteUser(request.getUid());
        return new Response(true,"删除成功");
    }
}
