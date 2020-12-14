package cn.bookspf.controller;

import javax.annotation.Resource;
import cn.bookspf.model.Dto.*;
import cn.bookspf.model.RO.*;
import cn.bookspf.service.ManagerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 */
@RestController
public class ManagerController {
	@Resource
	private ManagerService managerService;
	
	@PostMapping("/manager")
	public Response manager () {
		return managerService.manager();
	}

	/**
	 * 获取图书信息列表
	 * @return
	 */
	@GetMapping("/getBookList")
	public Response getBookList() {
		return managerService.getBookList();
	}

	/**
	 * 添加图书
	 * @param file
	 * @param bid
	 * @param bookname
	 * @param sortid
	 * @param author
	 * @param description
	 * @param bookprice
	 * @param added
	 * @return
	 */
	@PostMapping("/addBook")
	public Response addBook(@RequestParam("file") MultipartFile file,
							@RequestParam("bid") Integer bid,
							@RequestParam("bookname") String bookname,
							@RequestParam("sortid") Integer sortid,
							@RequestParam("author") String author,
							@RequestParam("description") String description,
							@RequestParam("bookprice") Double bookprice,
							@RequestParam("added") Integer added) {
		return managerService.addBook(file,bid,bookname,sortid,author,description,bookprice,added);
	}

	/**
	 * 修改图书
	 * @param isUpImg
	 * @param file
	 * @param bid
	 * @param bookname
	 * @param sortid
	 * @param author
	 * @param description
	 * @param bookprice
	 * @param added
	 * @return
	 */
	@PostMapping("/alterBook")
	public Response alterBook(@RequestParam("isUpImg") Boolean isUpImg,
							@RequestParam("file") MultipartFile file,
							@RequestParam("bid") Integer bid,
							@RequestParam("bookname") String bookname,
							@RequestParam("sortid") Integer sortid,
							@RequestParam("author") String author,
							@RequestParam("description") String description,
							@RequestParam("bookprice") Double bookprice,
							@RequestParam("added") Integer added) {
		return managerService.alterBook(isUpImg,file,bid,bookname,sortid,author,description,bookprice,added);
	}

	/**
	 * 删除图书
	 * @param request
	 * @return
	 */
	@PostMapping("/deleteBook")
	public Response deleteBook(@RequestBody BookDTO request) {
		return managerService.deleteBook(request);
	}

	/**
	 * 获取分类信息列表
	 * @return
	 */
	@GetMapping("/getSortList")
	public Response getSortList() {
		return managerService.getSortList();
	}

	/**
	 * 添加分类
	 * @param request
	 * @return
	 */
	@PostMapping("/addSort")
	public Response addSort(@RequestBody SortDTO request) {
		return managerService.addSort(request);
	}

	/**
	 * 修改分类
	 * @param request
	 * @return
	 */
	@PostMapping("/alterSort")
	public Response alterSort(@RequestBody SortDTO request) {
		return managerService.alterSort(request);
	}

	/**
	 * 删除分类
	 * @param request
	 * @return
	 */
	@PostMapping("/deleteSort")
	public Response deleteSort(@RequestBody SortDTO request) {
		return managerService.deleteSort(request);
	}

	/**
	 * 获取订单信息列表
	 * @param request
	 * @return
	 */
	@PostMapping("/getOrderListOfAdmin")
	public Response getOrderListOfAdmin(@RequestBody String request) {
		return managerService.getOrderListOfAdmin(request);
	}

	/**
	 * 获取订单详情列表
	 * @param request
	 * @return
	 */
	@PostMapping("/checkOrderOfAdmin")
	public Response checkOrderOfAdmin(@RequestBody OrderDTO request) {
		return managerService.checkOrderOfAdmin(request);
	}


	/**
	 * 获取销售信息列表
	 * @param request
	 * @return
	 */
	@PostMapping("/getSaleList")
	public Response getSaleList(@RequestBody String request) {
		return managerService.getSaleList(request);
	}

	/**
	 * 获取销售详情列表
	 * @param request
	 * @return
	 */
	@PostMapping("/checkSale")
	public Response checkSale(@RequestBody SaleDTO request) {
		return managerService.checkSale(request);
	}

	/**
	 * 获取进货信息列表
	 * @param request
	 * @return
	 */
	@PostMapping("/getPurchaseList")
	public Response getPurchaseList(@RequestBody String request) {
		return managerService.getPurchaseList(request);
	}

	/**
	 * 获取进货详情列表
	 * @param request
	 * @return
	 */
	@PostMapping("/checkPurchase")
	public Response checkPurchase(@RequestBody PurchaseDTO request) {
		return managerService.checkPurchase(request);
	}

	/**
	 * 添加进货记录
	 * @param request
	 * @return
	 */
	@PostMapping("/addPurchase")
	public Response addPurchase(@RequestBody PurchaseDTO request) {
		return managerService.addPurchase(request);
	}

	/**
	 * 获取库存信息列表
	 * @param request
	 * @return
	 */
	@PostMapping("/getStockList")
	public Response getStockList(@RequestBody String request) {
		return managerService.getStockList(request);
	}

	/**
	 * 获取库存详情列表
	 * @param request
	 * @return
	 */
	@PostMapping("/checkStock")
	public Response checkStock(@RequestBody StockDTO request) {
		return managerService.checkStock(request);
	}
}
