package cn.bookspf.controller;

import cn.bookspf.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
 * @author Administrator
 */
@Controller
public class PagesController {
	@Resource
	private PageService pageService;

	/**
	 * 主页
	 * @param model
	 * @return
	 */
	@RequestMapping({"/","/index"})
	public String index (Model model) {
		return pageService.index(model);
	}

	/**
	 * 注册页
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public String register (Model model)  {
		return pageService.register(model);
	}

	/**
	 * 登录页
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login (Model model) {
		return pageService.login(model);
	}

	/**
	 * 超级管理员
	 * @param model
	 * @return
	 */
	@RequestMapping("/superManager")
	public String superManager(Model model) {
		return pageService.superManager(model);
	}

	/**
	 * 图书管理员
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(Model model) {
		return pageService.manager(model);
	}

	/**
	 * 账户页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/account/{id}")
	public String account (@PathVariable("id") Integer id,Model model) {
		return pageService.account(id, model);
	}

	/**
	 * 订单页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/orders/{id}")
	public String orders (@PathVariable("id") Integer id,Model model) {
		return pageService.orders(id, model);
	}

	/**
	 * 购物车页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/shopcar/{id}")
	public String shopcar (@PathVariable("id") Integer id,Model model) {
		return pageService.shopcar(id, model);
	}

	/**
	 * 图书页
	 * @param bid
	 * @param model
	 * @return
	 */
	@RequestMapping("/book/{bid}")
	public String book (@PathVariable("bid") Integer bid,Model model) {
		return pageService.book(bid, model);
	}
}
