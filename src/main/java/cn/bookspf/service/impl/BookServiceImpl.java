package cn.bookspf.service.impl;

import cn.bookspf.mapper.*;
import cn.bookspf.model.Dao.Book;
import cn.bookspf.model.Dao.Stock;
import cn.bookspf.model.Dao.User;
import cn.bookspf.model.Dto.BookDTO;
import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.Response;
import cn.bookspf.service.BookService;
import cn.bookspf.utils.Generator;
import cn.bookspf.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;

import static cn.bookspf.constant.RoleConstant.NORMAL;

/**
 * @author Administrator
 */
@Service
public class BookServiceImpl implements BookService {
    HttpSession httpSession;
    Validator validator;
    UserMapper userMapper;
    BookMapper bookMapper;
    SortMapper sortMapper;
    OrderMapper orderMapper;
    SaleMapper saleMapper;
    StockMapper stockMapper;
    ShopcarMapper shopcarMapper;

    @Autowired
    public BookServiceImpl(HttpSession httpSession, UserMapper userMapper, BookMapper bookMapper,
                          SortMapper sortMapper, OrderMapper orderMapper, SaleMapper saleMapper,
                          StockMapper stockMapper, ShopcarMapper shopcarMapper){
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.userMapper=userMapper;
        this.bookMapper=bookMapper;
        this.sortMapper=sortMapper;
        this.orderMapper=orderMapper;
        this.saleMapper=saleMapper;
        this.stockMapper=stockMapper;
        this.shopcarMapper=shopcarMapper;
    }

    @Transactional
    @Override
    public Response buyBook(BookDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Integer bid=request.getBid();
        if(bookMapper.getBookNumber(bid)==0) {
            return new Response(false,"抱歉,该图书已售罄");
        }
        User user=(User)httpSession.getAttribute("user");
        Integer uid = user.getUid();
        Book book=bookMapper.getBook(bid);
        Double balance=userMapper.getBalance(uid);
        Double bookprice=book.getBookprice();
        if(bookprice>balance) {
            return new Response(false,"余额不足,请充值");
        }

        //扣钱
        userMapper.updateBalance(uid, balance-bookprice);

        //减少书籍数量
        bookMapper.updateBookNumber(bid,book.getNumber()-1);

        //增加热度
        Integer hot =  bookMapper.getHot(bid);
        bookMapper.updateHot(bid,hot+1);

        //出库
        String time= Generator.generateTime();
        Stock stock = stockMapper.getComeStock(bid);
        stockMapper.updateOutStock(stock.getStockid(),time);
        String isbn=stock.getIsbn();

        //生成订单
        Long orderid = Generator.generateId();
        if(orderMapper.getOrderOfOrderid(orderid).size()!=0) {
            orderid+=123;
        }
        orderMapper.insertOrder(orderid,uid,bid,isbn,bookprice,time);

        //生成销售记录
        Long saleid = Generator.generateId();
        if(saleMapper.getSaleOfSaleid(saleid).size()!=0) {
            saleid+=123;
        }
        saleMapper.insertSale(saleid,bid,isbn,time);


        return new Response(true,"购买成功,书本将会加急送到您手中");
    }

    @Override
    public Response addShopcar(BookDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Integer bid=request.getBid();
        if(bookMapper.getBookNumber(bid)==0) {
            return new Response(false,"抱歉,该图书已售罄");
        }
        User user=(User)httpSession.getAttribute("user");
        Integer uid = user.getUid();
        Long carid = shopcarMapper.getShopcaridOfUid(uid);
        if(carid==null) {
            //生成购物车记录
            carid = Generator.generateId();
            if(shopcarMapper.getShopcarOfCarid(carid).size()!=0) {
                carid+=123;
            }
            shopcarMapper.insertShopcar(carid,uid,bid,1);
        }else{
            if(shopcarMapper.findBid(carid,bid)==null){
                shopcarMapper.insertShopcar(carid,uid,bid,1);
            }else{
                Integer booknumber = shopcarMapper.getBooknumber(carid,bid);
                shopcarMapper.updateShopcar(carid,bid,booknumber+1);
            }
        }


        return new Response(true,"添加成功");
    }
}
