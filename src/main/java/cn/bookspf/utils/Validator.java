package cn.bookspf.utils;

import javax.servlet.http.HttpSession;

import cn.bookspf.mapper.UserMapper;
import cn.bookspf.model.Dao.User;
import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.Response;

import static cn.bookspf.constant.AccountConstant.USERTOKEN;
import static cn.bookspf.constant.RoleConstant.*;

/**
 * @author Administrator
 */
public class Validator {
	HttpSession httpSession;

	public Validator(HttpSession httpSession) {
		this.httpSession=httpSession;
	}

	/**
	 * 验证登录状态
	 * @return Status
	 */
	public boolean isLogin(UserMapper userMapper) {
		String userToken = (String)httpSession.getAttribute(USERTOKEN);
		if(userToken == null) {
			return false;
		}
		User user = userMapper.getUser(userToken);
		return user != null ? true : false;
	}

	/**
	 * 验证身份
	 * @param userMapper userMapper
	 * @return 身份标识
	 */
	public Response isIdentity(UserMapper userMapper,Integer role) {
		User user = userMapper.getUser((String)httpSession.getAttribute(USERTOKEN));
		if(user == null) {
			return new Response(false,"请登录再操作");
		}
		if(!role.equals(user.getRole())){
			return new Response(false,"权限不足");
		}
		return new Response(true,"成功");
	}

	/**
	 * 验证身份
	 * @param userMapper userMapper
	 * @param page page
	 * @return 页面
	 */
	public String isIdentity(UserMapper userMapper,String page) {
		User user = userMapper.getUser((String)httpSession.getAttribute(USERTOKEN));
		Integer role = user.getRole();
		if(NORMAL.equals(role)) {
			return page;
		} else if(MANAGER.equals(role)) {
			return "manager";
		} else {
			return "superManager";
		}
	}

	/**
	 * 验证用户
	 * @param id id
	 * @return Status
	 */
	public boolean isAccount(UserMapper userMapper,int id){
		User user = userMapper.getUser((String)httpSession.getAttribute(USERTOKEN));
		return user.getUid() == id;
	}

	/**
	 * 验证数据库是否有相同用户
	 * @param userMapper userMapper
	 * @param request user
	 * @return
	 */
	public String isSame(UserMapper userMapper, UserDTO request) {
		if(request.getUid()!=null) {			
			if(userMapper.findUid(request.getUid())!=null) {
				return "UID已存在";
			}
		}
		if(userMapper.findUsername(request.getUsername())!=null) {
			return "用户名已存在";
		}
		if(userMapper.findEmail(request.getEmail())!=null) {
			return "邮箱已存在";
		}
		return "成功";
	}
}
