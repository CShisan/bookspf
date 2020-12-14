package cn.bookspf.service;

import cn.bookspf.model.Dto.OrderDTO;
import cn.bookspf.model.RO.Response;

/**
 * @author Administrator
 */
public interface OrdersService {
    /**
     * 获取订单列表
     * @param request
     * @return
     */
    Response getOrderList(String request);

    /**
     * 获取订单详情
     * @param request
     * @return
     */
    Response checkOrder(OrderDTO request);
}
