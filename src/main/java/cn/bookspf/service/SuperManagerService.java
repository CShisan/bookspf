package cn.bookspf.service;

import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.Response;

/**
 * @author Administrator
 */
public interface SuperManagerService {
    Response superManager ();

    /**
     * 获取图书管理员列表
     * @return
     */
    Response getManagerList();

    /**
     * 添加图书管理员
     * @param request
     * @return
     */
    Response addAdmin(UserDTO request);

    /**
     * 修改图书管理员
     * @param request
     * @return
     */
    Response alterAdmin(UserDTO request);

    /**
     * 删除图书管理员
     * @param request
     * @return
     */
    Response deleteAdmin(UserDTO request);
}
