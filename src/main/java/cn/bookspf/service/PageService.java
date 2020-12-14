package cn.bookspf.service;

import org.springframework.ui.Model;

public interface PageService {
    /**
     * 主页
     * @return
     */
    String index(Model model);

    /**
     * 注册页
     * @return
     */
    String register (Model model);

    /**
     * 登录页
     * @return
     */
    String login (Model model);

    /**
     * 超级管理员
     * @return
     */
    String superManager(Model model);

    /**
     * 图书管理员
     * @return
     */
    String manager(Model model);

    /**
     * 账户页
     * @return
     */
    String account (Integer id, Model model);

    /**
     * 订单页
     * @return
     */
    String orders (Integer id, Model model);

    /**
     * 购物车
     * @return
     */
    String shopcar (Integer id, Model model);

    /**
     * 书籍页
     * @return
     */
    String book (Integer bid, Model model);
}
