package cn.bookspf.controller;

import cn.bookspf.model.Dto.OrderDTO;
import cn.bookspf.model.RO.Response;
import cn.bookspf.service.OrdersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author Administrator
 */
@RestController
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    /**
     * 获取订单信息列表
     * @param request
     * @return
     */
    @PostMapping("/getOrderList")
    public Response getOrderList(@RequestBody String request) {
        return ordersService.getOrderList(request);
    }

    /**
     * 获取订单详情列表
     * @param request
     * @return
     */
    @PostMapping("/checkOrder")
    public Response checkOrder(@RequestBody OrderDTO request) {
        return ordersService.checkOrder(request);
    }
}


