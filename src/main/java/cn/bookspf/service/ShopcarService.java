package cn.bookspf.service;

import cn.bookspf.model.Dto.ShopcarDTO;
import cn.bookspf.model.RO.Response;

public interface ShopcarService {
    /**
     * 获取购物车列表
     * @return
     */
    Response getShopcarList();

    /**
     * 购物车item增加/减少数量
     * @param request
     * @return
     */
    Response changeNum(ShopcarDTO request);

    /**
     * 结算购物车
     * @return
     */
    Response settlement();

    /**
     * 删除购物车item
     * @param request
     * @return
     */
    Response deleteShopcarOfBid(ShopcarDTO request);

}
