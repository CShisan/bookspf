package cn.bookspf.controller;

import javax.annotation.Resource;
import cn.bookspf.model.Dto.BookDTO;
import cn.bookspf.service.IndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.bookspf.model.RO.Response;


/**
 * @author Administrator
 */
@RestController
public class IndexController {
	@Resource
	private IndexService indexService;

	/**
	 * 获取排行榜
	 * @return
	 */
	@GetMapping("/getRankList")
	public Response getRankList () {
		return indexService.getRankList();
	}

	/**
	 * 获取书籍(未禁用书籍)
	 * @return
	 */
	@GetMapping("/getPublishBook")
	public Response getBook () {
		return indexService.getBook();
	}

	/**
	 * 搜索
	 * @param request
	 * @return
	 */
	@PostMapping("/search")
	public Response search(@RequestBody BookDTO request){
		return indexService.search(request);
	}
	
}
