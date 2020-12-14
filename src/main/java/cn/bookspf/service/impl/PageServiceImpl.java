package cn.bookspf.service.impl;

import cn.bookspf.mapper.BookMapper;
import cn.bookspf.mapper.SortMapper;
import cn.bookspf.mapper.UserMapper;
import cn.bookspf.model.Dao.Book;
import cn.bookspf.model.Dao.User;
import cn.bookspf.service.PageService;
import cn.bookspf.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static cn.bookspf.constant.AccountConstant.USERTOKEN;

/**
 * @author Administrator
 */
@Service
public class PageServiceImpl implements PageService {
    HttpSession httpSession;
    Validator validator;
    UserMapper userMapper;
    BookMapper bookMapper;
    SortMapper sortMapper;

    @Autowired
    public PageServiceImpl(HttpSession httpSession, UserMapper userMapper, BookMapper bookMapper, SortMapper sortMapper) {
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.userMapper=userMapper;
        this.bookMapper=bookMapper;
        this.sortMapper=sortMapper;
    }


    public void setModelUser(Model model) {
        String accesstoken = (String) httpSession.getAttribute(USERTOKEN);
        User user=userMapper.getUser(accesstoken);
        user.setAccesstoken(null);
        user.setPassword(null);
        model.addAttribute("user", user);
    }

    public void setModelBook(Model model,Integer bid) {
        Book book=bookMapper.getBook(bid);
        book.setSortname(sortMapper.getSortname(book.getSortid()));
        model.addAttribute("book", book);
    }

    /**
     * 主页
     *
     * @return
     */
    @Override
    public String index(Model model) {
        if(!validator.isLogin(userMapper)) {
            return "index";
        }
        setModelUser(model);
        return validator.isIdentity(userMapper, "index");
    }

    /**
     * 注册页
     *
     * @return
     */
    @Override
    public String register(Model model)  {
        if(!validator.isLogin(userMapper)) {
            return "register";
        }
        setModelUser(model);
        return validator.isIdentity(userMapper, "index");
    }

    /**
     * 登录页
     *
     * @return
     */
    @Override
    public String login(Model model) {
        if(!validator.isLogin(userMapper)) {
            return "login";
        }
        setModelUser(model);
        return validator.isIdentity(userMapper, "index");
    }

    /**
     * 超级管理员
     *
     * @return
     */
    @Override
    public String superManager(Model model) {
        if(!validator.isLogin(userMapper)) {
            return "login";
        }
        setModelUser(model);
        return validator.isIdentity(userMapper, "superManager");
    }

    /**
     * 图书管理员
     *
     * @return
     */
    @Override
    public String manager(Model model) {
        if(!validator.isLogin(userMapper)) {
            return "login";
        }
        setModelUser(model);
        return validator.isIdentity(userMapper, "manager");
    }

    /**
     * 账户页
     *
     * @return
     */
    @Override
    public String account(Integer id, Model model) {
        if(!validator.isLogin(userMapper)) {
            return "login";
        }
        if(!validator.isAccount(userMapper, id)) {
            return "404";
        }
        setModelUser(model);
        return validator.isIdentity(userMapper, "account");
    }

    /**
     * 订单页
     *
     * @return
     */
    @Override
    public String orders(Integer id,Model model) {
        if(!validator.isLogin(userMapper)) {
            return "login";
        }
        if(!validator.isAccount(userMapper, id)) {
            return "404";
        }
        setModelUser(model);
        return validator.isIdentity(userMapper, "orders");
    }

    /**
     * 购物车
     *
     * @return
     */
    @Override
    public String shopcar(Integer id, Model model) {
        if(!validator.isLogin(userMapper)) {
            return "login";
        }
        if(!validator.isAccount(userMapper, id)) {
            return "404";
        }
        setModelUser(model);
        return validator.isIdentity(userMapper, "shopcar");
    }

    /**
     * 书籍页
     *
     * @return
     */
    @Override
    public String book(Integer bid, Model model) {
        if(validator.isLogin(userMapper)) {
            setModelUser(model);
        }
        setModelBook(model,bid);
        //增加热度
        Integer hot =  bookMapper.getHot(bid);
        bookMapper.updateHot(bid,hot+1);

        if(httpSession.getAttribute(USERTOKEN)==null) {
            return "book";
        }
        return validator.isIdentity(userMapper, "book");
    }
}
