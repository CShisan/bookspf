package cn.bookspf.controller;
import cn.bookspf.model.Dto.BookDTO;
import cn.bookspf.model.RO.Response;
import cn.bookspf.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author Administrator
 */
@RestController
public class BookController {
    @Resource
    private BookService bookService;

    /**
     * 购买书籍
     * @param request
     * @return
     */
    @PostMapping("/buyBook")
    public Response buyBook (@RequestBody BookDTO request){
        return bookService.buyBook(request);
    }

    /**
     * 添加到购物车
     * @param request
     * @return
     */
    @PostMapping("/addShopcar")
    public Response addShopcar(@RequestBody BookDTO request){
        return bookService.addShopcar(request);
    }
}
