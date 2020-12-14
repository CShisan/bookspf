package cn.bookspf.service;

import cn.bookspf.model.Dto.BookDTO;
import cn.bookspf.model.RO.Response;

/**
 * @author Administrator
 */
public interface IndexService {
    /**
     * 获取排行榜
     * @return
     */
    Response getRankList ();

    /**
     * 获取书籍
     * @return
     */
    Response getBook ();

    /**
     * 搜索书籍
     * @param request
     * @return
     */
    Response search (BookDTO request);
}
