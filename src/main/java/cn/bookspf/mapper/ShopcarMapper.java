package cn.bookspf.mapper;

import cn.bookspf.model.Dao.Shopcar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ShopcarMapper {

    /**
     * 插入记录
     * @param carid
     * @param uid
     * @param bid
     * @param booknumber
     */
    @Insert("insert into shopcar(carid,uid,bid,booknumber) values(#{carid},#{uid},#{bid},#{booknumber})")
    void insertShopcar(Long carid,Integer uid,Integer bid,Integer booknumber);

    /**
     * 获得记录的某书数量
     * @param carid
     * @param bid
     * @return
     */
    @Select("select booknumber from shopcar where carid=#{carid} and bid=#{bid}")
    Integer getBooknumber(Long carid,Integer bid);

    /**
     * 更新购物车
     * @param carid
     * @param bid
     * @param booknumber
     */
    @Update("update shopcar set booknumber=#{booknumber} where carid=#{carid} and bid=#{bid}")
    void updateShopcar(Long carid,Integer bid,Integer booknumber);

    /**
     * 根据购物车id查询所有
     * @param carid
     * @return
     */
    @Select("select * from shopcar where carid=#{carid}")
    ArrayList<Shopcar> getShopcarOfCarid(Long carid);

    /**
     * 查询某用户的购物车
     * @param uid
     * @return
     */
    @Select("select * from shopcar where uid=#{uid}")
    ArrayList<Shopcar> getShopcarOfUid(Integer uid);

    /**
     * 查询某用户购物车id
     * @param uid
     * @return
     */
    @Select("select distinct carid from shopcar where uid=#{uid}")
    Long getShopcaridOfUid(Integer uid);

    /**
     * 查询用户购物车是否存在某书
     * @param carid
     * @param bid
     * @return
     */
    @Select("select bid from shopcar where carid=#{carid} and bid=#{bid}")
    Integer findBid(Long carid,Integer bid);

    /**
     * 删除某用户整个购物车
     * @param uid
     */
    @Delete("delete from shopcar where uid=#{uid}")
    void deleteAllShopcar(Integer uid);

    /**
     * 删除某用户购物车的某本书
     * @param uid
     * @param bid
     */
    @Delete("delete from shopcar where uid=#{uid} and bid=#{bid}")
    void deleteShopcarOfBid(Integer uid,Integer bid);

    /**
     * 修改购物车书籍数量
     * @param uid
     * @param bid
     * @param booknumber
     */
    @Update("update shopcar set booknumber=#{booknumber} where uid=#{uid} and bid=#{bid}")
    void updateBooknumber(Integer uid,Integer bid,Integer booknumber);
}
