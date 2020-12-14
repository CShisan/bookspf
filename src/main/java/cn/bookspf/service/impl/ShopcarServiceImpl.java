package cn.bookspf.service.impl;

import cn.bookspf.mapper.*;
import cn.bookspf.model.Dao.Book;
import cn.bookspf.model.Dao.Shopcar;
import cn.bookspf.model.Dao.Stock;
import cn.bookspf.model.Dto.ShopcarDTO;
import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.Response;
import cn.bookspf.model.RO.ShopcarResponse;
import cn.bookspf.service.ShopcarService;
import cn.bookspf.utils.Generator;
import cn.bookspf.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static cn.bookspf.constant.RoleConstant.NORMAL;


/**
 * @author Administrator
 */
@Service
public class ShopcarServiceImpl implements ShopcarService {
    HttpSession httpSession;
    Validator validator;
    UserMapper userMapper;
    BookMapper bookMapper;
    ShopcarMapper shopcarMapper;
    OrderMapper orderMapper;
    SaleMapper saleMapper;
    StockMapper stockMapper;

    @Autowired
    public ShopcarServiceImpl(HttpSession httpSession, UserMapper userMapper, BookMapper bookMapper,
                             ShopcarMapper shopcarMapper, OrderMapper orderMapper, SaleMapper saleMapper,
                             StockMapper stockMapper){
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.userMapper=userMapper;
        this.bookMapper=bookMapper;
        this.shopcarMapper=shopcarMapper;
        this.orderMapper=orderMapper;
        this.saleMapper=saleMapper;
        this.stockMapper=stockMapper;
    }

    /**
     * 获取购物车列表
     *
     * @return
     */
    @Override
    public Response getShopcarList() {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();
        ArrayList<Shopcar> shopcars= shopcarMapper.getShopcarOfUid(uid);
        if (shopcars.size()==0) {
            return new Response(false,"");
        }

        Double total=0.0;
        for(int i=0;i<shopcars.size();i++){
            total+=bookMapper.getBookprice(shopcars.get(i).getBid())*shopcars.get(i).getBooknumber();
        }

        for (int i=0;i<shopcars.size();i++){
            Book book = bookMapper.getBook(shopcars.get(i).getBid());
            shopcars.get(i).setBookname(book.getBookname());
            shopcars.get(i).setBookprice(book.getBookprice());
            shopcars.get(i).setTotal(total);
        }
        return new ShopcarResponse(shopcars);
    }

    /**
     * 购物车item增加/减少数量
     *
     * @param request
     * @return
     */
    @Override
    public Response changeNum(ShopcarDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();
        shopcarMapper.updateBooknumber(uid,request.getBid(),request.getBooknumber());
        return  new Response(true,"修改成功");
    }

    /**
     * 结算购物车
     *
     * @return
     */
    @Transactional
    @Override
    public Response settlement() {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();

        ArrayList<Shopcar> shopcars = shopcarMapper.getShopcarOfUid(uid);
        if(shopcars==null) {
            return new Response(false,"购物车暂时还空无一物");
        }

        Double total=0.0;
        for(int i=0;i<shopcars.size();i++){
            total+=bookMapper.getBookprice(shopcars.get(i).getBid())*shopcars.get(i).getBooknumber();
        }
        Double balance = userMapper.getBalance(uid);
        if(balance<total) {
            return new Response(false,"余额不足,请充值");
        }
        userMapper.updateBalance(uid, balance-total);

        //生成订单
        Long orderid = Generator.generateId();
        if(orderMapper.getOrderOfOrderid(orderid).size()!=0) {
            orderid+=123;
        }

        //生成销售记录
        Long saleid = Generator.generateId();
        if(saleMapper.getSaleOfSaleid(saleid).size()!=0) {
            saleid+=123;
        }

        for(int i=0;i<shopcars.size();i++){
            Integer bid = shopcars.get(i).getBid();
            Integer booknumber = shopcars.get(i).getBooknumber();
            Book book = bookMapper.getBook(bid);
            Double bookprice=book.getBookprice();
            if(booknumber>book.getNumber()) {
                return new Response(false,"该书库存不足"+booknumber+"本,如需购买请联系客服");
            }

            bookMapper.updateBookNumber(bid,book.getNumber()-booknumber);

            String time= Generator.generateTime();
            for(int j=0;j<booknumber;j++){
                Stock stock = stockMapper.getComeStock(bid);
                stockMapper.updateOutStock(stock.getStockid(),time);
                String isbn=stock.getIsbn();

                orderMapper.insertOrder(orderid,uid,bid,isbn,bookprice,time);

                saleMapper.insertSale(saleid,bid,isbn,time);
            }
        }

        shopcarMapper.deleteAllShopcar(uid);

        return new Response(true,"购买成功,书本将会加急送到您手中");
    }

    /**
     * 删除购物车item
     *
     * @param request
     * @return
     */
    @Override
    public Response deleteShopcarOfBid(ShopcarDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();
        shopcarMapper.deleteShopcarOfBid(uid,request.getBid());
        return new Response(true,"删除成功");
    }
}
