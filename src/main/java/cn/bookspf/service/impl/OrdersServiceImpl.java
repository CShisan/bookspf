package cn.bookspf.service.impl;

import cn.bookspf.mapper.BookMapper;
import cn.bookspf.mapper.OrderMapper;
import cn.bookspf.mapper.UserMapper;
import cn.bookspf.model.Dao.Order;
import cn.bookspf.model.Dto.OrderDTO;
import cn.bookspf.model.Dto.UserDTO;
import cn.bookspf.model.RO.OrderResponse;
import cn.bookspf.model.RO.Response;
import cn.bookspf.service.OrdersService;
import cn.bookspf.utils.Operator;
import cn.bookspf.utils.Validator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static cn.bookspf.constant.RoleConstant.NORMAL;

/**
 * @author Administrator
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    HttpSession httpSession;
    Validator validator;
    Operator operator;
    UserMapper userMapper;
    BookMapper bookMapper;
    OrderMapper orderMapper;

    @Autowired
    public OrdersServiceImpl(HttpSession httpSession, UserMapper userMapper, BookMapper bookMapper, OrderMapper orderMapper){
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.operator=new Operator();
        this.userMapper=userMapper;
        this.bookMapper=bookMapper;
        this.orderMapper=orderMapper;
    }

    /**
     * 获取订单列表
     * @param request
     * @return
     */
    @Override
    public Response getOrderList(String request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        OrderResponse orderResponse=new OrderResponse();
        UserDTO user = (UserDTO) httpSession.getAttribute("user");
        Integer uid= user.getUid();

        JSONObject Obj=JSONObject.parseObject(request);
        int index = Obj.getInteger("index");
        if(index==0){
            ArrayList<Order> order = orderMapper.getOrderOfUid(uid);
            ArrayList<Double> price = orderMapper.getOrderPriceOfUid(uid);
            orderResponse.setOrders(operator.getOrders(order,price));
        }else if(index==1){
            long orderid=Obj.getLong("str");
            ArrayList<Order> order = orderMapper.getOrderOfOrderidAndUid(orderid,uid);
            if (order==null) {
                return new Response(false,"请输入正确订单号");
            }
            ArrayList<Double> price = orderMapper.getOrderPriceOfOrderid(orderid);
            orderResponse.setOrders(operator.getOrders(order,price));
        }else if(index==2){
            String createtime=Obj.getString("str");
            createtime=createtime.replace("T"," ");
            ArrayList<Order> order = orderMapper.getOrderOfCreatetimeAndUid(createtime,uid);
            if (order==null) {
                return new Response(false,"没有该时间订单记录");
            }
            ArrayList<Double> price = orderMapper.getOrderPriceOfCreatetime(createtime);
            orderResponse.setOrders(operator.getOrders(order,price));
        }
        return orderResponse;
    }

    /**
     * 获取订单详情
     * @param request
     * @return
     */
    @Override
    public Response checkOrder(OrderDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,NORMAL);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Long orderid=request.getOrderid();
        ArrayList<Order> orders = orderMapper.getOrderinfoOfOrderid(orderid);
        ArrayList<Integer> bids =  orderMapper.getBidsOfOrderid(orderid);
        for(int i=0;i<orders.size();i++){
            String bookname=bookMapper.getBookname(bids.get(i));
            orders.get(i).setBookname(bookname);
        }
        return new OrderResponse(orders);
    }
}
