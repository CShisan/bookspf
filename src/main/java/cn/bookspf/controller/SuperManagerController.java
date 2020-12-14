package cn.bookspf.controller;

import cn.bookspf.service.SuperManagerService;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.Response;

/**
 * @author Administrator
 */
@RestController
public class SuperManagerController {
	@Resource
	private SuperManagerService superManagerService;
	
	@PostMapping("/superManager")
	public Response superManager () {
		return superManagerService.superManager();
	}

	/**
	 * 获取图书管理员列表
	 * @return
	 */
	@GetMapping("/getManagerList")
	public Response getManagerList() {
		return superManagerService.getManagerList();
	}

	/**
	 * 添加图书管理员
	 * @param request
	 * @return
	 */
	@PostMapping("/addAdmin")
	public Response addAdmin(@RequestBody UserDTO request) {
		return superManagerService.addAdmin(request);
	}

	/**
	 * 修改图书管理员
	 * @param request
	 * @return
	 */
	@PostMapping("/alterAdmin")
	@Transactional
	public Response alterAdmin(@RequestBody UserDTO request) {
		return superManagerService.alterAdmin(request);
	}

	/**
	 * 删除图书管理员
	 * @param request
	 * @return
	 */
	@PostMapping("/deleteAdmin")
	public Response deleteAdmin(@RequestBody UserDTO request) {
		return superManagerService.deleteAdmin(request);
	}

}
