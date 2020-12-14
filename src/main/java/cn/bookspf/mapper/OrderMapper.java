package cn.bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.bookspf.model.Dao.Order;

@Mapper
@Repository
public interface OrderMapper {

	/**
	 * 插入一个订单
	 * @param orderid
	 * @param uid
	 * @param bid
	 * @param isbn
	 * @param bookprice
	 * @param createtime
	 */
	@Insert("insert into orders(orderid,uid,bid,isbn,bookprice,createtime) values(#{orderid},#{uid},#{bid},#{isbn},#{bookprice},#{createtime})")
	void insertOrder(Long orderid,Integer uid,Integer bid,String isbn,Double bookprice,String createtime);

	/**
	 * 查询所有订单的统计信息
	 * @return
	 */
	@Select("select distinct orderid,uid,createtime from orders")
	ArrayList<Order> getOrders();

	/**
	 * 查询某个订单信息
	 * @param orderid
	 * @return
	 */
	@Select("select distinct orderid,uid,createtime from orders where orderid=#{orderid}")
	ArrayList<Order> getOrderOfOrderid(Long orderid);

	/**
	 * 查询某个订单详情
	 * @param orderid
	 * @return
	 */
	@Select("select  orderid,bid,isbn,bookprice from orders where orderid=#{orderid}")
	ArrayList<Order> getOrderinfoOfOrderid(Long orderid);

	/**
	 * 查询某个订单信息
	 * @param uid
	 * @return
	 */
	@Select("select distinct orderid,uid,createtime from orders where uid=#{uid}")
	ArrayList<Order> getOrderOfUid(Integer uid);

	/**
	 * 查询某个订单信息
	 * @param createtime
	 * @return
	 */
	@Select("select distinct orderid,uid,createtime from orders where createtime=#{createtime}")
	ArrayList<Order> getOrderOfCreatetime(String createtime);

	/**
	 * 查询所有订单的总价
	 * @return
	 */
	@Select("select sum(bookprice) from orders group by orderid")
	ArrayList<Double> getOrderPrice();

	/**
	 * 查询所有订单的总价
	 * @param orderid
	 * @return
	 */
	@Select("select sum(bookprice) from orders where orderid=#{orderid} group by orderid")
	ArrayList<Double> getOrderPriceOfOrderid(Long orderid);

	/**
	 * 查询所有订单的总价
	 * @param uid
	 * @return
	 */
	@Select("select sum(bookprice) from orders where uid=#{uid} group by orderid")
	ArrayList<Double> getOrderPriceOfUid(Integer uid);

	/**
	 * 查询所有订单的总价
	 * @param createtime
	 * @return
	 */
	@Select("select sum(bookprice) from orders where createtime=#{createtime} group by orderid")
	ArrayList<Double> getOrderPriceOfCreatetime(String createtime);

	/**
	 * 查询bid
	 * @param orderid
	 * @return
	 */
	@Select("select bid from orders where orderid=#{orderid}")
	ArrayList<Integer> getBidsOfOrderid(Long orderid);


	//用户

	/**
	 * 查询某个订单信息
	 * @param orderid
	 * @param uid
	 * @return
	 */
	@Select("select distinct orderid,uid,createtime from orders where orderid=#{orderid} and uid=#{uid}")
	ArrayList<Order> getOrderOfOrderidAndUid(Long orderid, Integer uid);

	/**
	 * 查询某个订单信息
	 * @param createtime
	 * @param uid
	 * @return
	 */
	@Select("select distinct orderid,uid,createtime from orders where createtime=#{createtime} and uid=#{uid}")
	ArrayList<Order> getOrderOfCreatetimeAndUid(String createtime, Integer uid);

}
