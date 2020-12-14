package cn.bookspf.controller;

import cn.bookspf.model.Dto.ShopcarDTO;
import cn.bookspf.model.RO.Response;
import cn.bookspf.service.ShopcarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
public class ShopcarController {
    @Resource
    private ShopcarService shopcarService;

    /**
     * 获取购物车列表
     * @return
     */
    @GetMapping("/getShopcarList")
    public Response getShopcarList(){
        return shopcarService.getShopcarList();
    }


    /**
     * 购物车item增加/减少数量
     * @param request
     * @return
     */
    @PostMapping("/changeNum")
    public Response changeNum(@RequestBody ShopcarDTO request){
        return shopcarService.changeNum(request);
    }


    /**
     * 结算购物车
     * @return
     */
    @PostMapping("/settlement")
    public Response settlement(){
        return shopcarService.getShopcarList();
    }

    /**
     * 删除购物车item
     * @param request
     * @return
     */
    @PostMapping("/deleteShopcarOfBid")
    public Response deleteShopcarOfBid(@RequestBody ShopcarDTO request){
        return shopcarService.deleteShopcarOfBid(request);
    }
}
