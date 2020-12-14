package cn.bookspf.service;

import cn.bookspf.model.Dto.BookDTO;
import cn.bookspf.model.RO.Response;

/**
 * @author Administrator
 */
public interface BookService {

    /**
     * 购买书籍
     * @param request
     * @return
     */
    Response buyBook (BookDTO request);

    /**
     * 添加到购物车
     * @param request
     * @return
     */
    Response addShopcar(BookDTO request);
}
