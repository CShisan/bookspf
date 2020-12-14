package cn.bookspf.service;

import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.Response;

public interface UserService {

    /**
     * 修改余额
     * @param request
     * @return
     */
    Response changeBalance(UserDTO request);

    /**
     * 修改密码
     * @param request
     * @return
     */
    Response changePassword(UserDTO request);

    /**
     * 修改手机号码
     * @param request
     * @return
     */
    Response changePhone(UserDTO request);

    /**
     * 修改真实姓名
     * @param request
     * @return
     */
    Response changeRealname(UserDTO request);

    /**
     * 修改地址
     * @param request
     * @return
     */
    Response changeAddress(UserDTO request);

    /**
     * 登录
     * @param request
     * @return
     */
    Response login (UserDTO request);

    /**
     * 登出
     */
    void logout ();

    /**
     * 请求验证码
     * @return
     */
    String changeCode();

    /**
     * 注册
     * @param request
     * @return
     */
    Response register(UserDTO request);
}
