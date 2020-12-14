package cn.bookspf.controller;
import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.Response;
import cn.bookspf.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


/**
 * @author Administrator
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 修改余额
     * @param request
     * @return
     */
    @PostMapping("/changeBalance")
    public Response changeBalance(@RequestBody UserDTO request){
        return userService.changeBalance(request);
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @PostMapping("/changePassword")
    public Response changePassword(@RequestBody UserDTO request){
        return userService.changePassword(request);
    }

    /**
     * 修改手机号码
     * @param request
     * @return
     */
    @PostMapping("/changePhone")
    public Response changePhone(@RequestBody UserDTO request){
        return userService.changePhone(request);
    }

    /**
     * 修改真实姓名
     * @param request
     * @return
     */
    @PostMapping("/changeRealname")
    public Response changeRealname(@RequestBody UserDTO request){
        return userService.changeRealname(request);
    }

    /**
     * 修改地址
     * @param request
     * @return
     */
    @PostMapping("/changeAddress")
    public Response changeAddress(@RequestBody UserDTO request){
        return userService.changeAddress(request);
    }

    /**
     * 注册
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Response register (@RequestBody UserDTO request) {
        return userService.register(request);
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Response login (@RequestBody UserDTO request) {
        return userService.login(request);
    }

    /**
     * 请求验证码
     * @return
     */
    @PostMapping("/changeCode")
    public String changeCode(){
        return userService.changeCode();
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/logout")
    public void logout () {
        userService.logout();
    }


}
