package cn.bookspf.service;

import cn.bookspf.model.Dto.*;
import cn.bookspf.model.RO.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 */
public interface ManagerService {
    Response manager ();

    /**
     * 获取图书信息列表
     * @return
     */
    Response getBookList();

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
    Response addBook(@RequestParam("file") MultipartFile file,
                     @RequestParam("bid") Integer bid,
                     @RequestParam("bookname") String bookname,
                     @RequestParam("sortid") Integer sortid,
                     @RequestParam("author") String author,
                     @RequestParam("description") String description,
                     @RequestParam("bookprice") Double bookprice,
                     @RequestParam("added") Integer added);

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
    Response alterBook(@RequestParam("isUpImg") Boolean isUpImg,
                       @RequestParam("file") MultipartFile file,
                       @RequestParam("bid") Integer bid,
                       @RequestParam("bookname") String bookname,
                       @RequestParam("sortid") Integer sortid,
                       @RequestParam("author") String author,
                       @RequestParam("description") String description,
                       @RequestParam("bookprice") Double bookprice,
                       @RequestParam("added") Integer added);

    /**
     * 删除图书
     * @param request
     * @return
     */
    Response deleteBook(@RequestBody BookDTO request);

    /**
     * 获取分类信息列表
     * @return
     */
    Response getSortList();

    /**
     * 添加分类
     * @param request
     * @return
     */
    Response addSort(@RequestBody SortDTO request);

    /**
     * 修改分类
     * @param request
     * @return
     */
    Response alterSort(@RequestBody SortDTO request);

    /**
     * 删除分类
     * @param request
     * @return
     */
    Response deleteSort(@RequestBody SortDTO request);

    /**
     * 获取订单信息列表
     * @param request
     * @return
     */
    Response getOrderListOfAdmin(@RequestBody String request);

    /**
     * 获取订单详情列表
     * @param request
     * @return
     */
    Response checkOrderOfAdmin(@RequestBody OrderDTO request);

    /**
     * 获取销售信息列表
     * @param request
     * @return
     */
    Response getSaleList(@RequestBody String request);

    /**
     * 获取销售详情列表
     * @param request
     * @return
     */
    Response checkSale(@RequestBody SaleDTO request);

    /**
     * 获取进货信息列表
     * @param request
     * @return
     */
    Response getPurchaseList(@RequestBody String request);

    /**
     * 获取进货详情列表
     * @param request
     * @return
     */
    Response checkPurchase(@RequestBody PurchaseDTO request);

    /**
     * 添加进货记录
     * @param request
     * @return
     */
    Response addPurchase(@RequestBody PurchaseDTO request);

    /**
     * 获取库存信息列表
     * @param request
     * @return
     */
    Response getStockList(@RequestBody String request);

    /**
     * 获取库存详情列表
     * @param request
     * @return
     */
    Response checkStock(@RequestBody StockDTO request);
}
