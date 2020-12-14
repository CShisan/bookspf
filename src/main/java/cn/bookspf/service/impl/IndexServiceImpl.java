package cn.bookspf.service.impl;

import cn.bookspf.mapper.BookMapper;
import cn.bookspf.model.Dao.Book;
import cn.bookspf.model.Dto.BookDTO;
import cn.bookspf.model.RO.BookResponse;
import cn.bookspf.model.RO.Response;
import cn.bookspf.service.IndexService;
import cn.bookspf.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author Administrator
 */
@Service
public class IndexServiceImpl implements IndexService {
    HttpSession httpSession;
    BookMapper bookMapper;
    Validator validator;

    @Autowired
    public IndexServiceImpl(HttpSession httpSession, BookMapper bookMapper) {
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.bookMapper=bookMapper;
    }

    @Override
    public Response getRankList() {
        ArrayList<Book> rankList = bookMapper.getRankList();
        return new BookResponse(rankList);
    }

    @Override
    public Response getBook() {
        ArrayList<Book> bookList = bookMapper.getPublishBook();
        return new BookResponse(bookList);
    }

    @Override
    public Response search(BookDTO request) {
        Integer bid = bookMapper.getBid(request.getBookname());
        if(bid==null) {
            return new Response(false,"对不起,没有找到您需要的图书");
        }
        return new Response(true,"/book/"+bid);
    }
}
