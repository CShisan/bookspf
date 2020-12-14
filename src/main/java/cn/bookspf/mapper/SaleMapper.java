package cn.bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.bookspf.model.Dao.Sale;

@Mapper
@Repository
public interface SaleMapper {

	/**
	 * 插入销售记录
	 * @param saleid
	 * @param bid
	 * @param isbn
	 * @param saletime
	 */
	@Insert("insert into sale(saleid,bid,isbn,saletime) values(#{saleid},#{bid},#{isbn},#{saletime})")
	void insertSale(Long saleid, Integer bid, String isbn, String  saletime);

	/**
	 * 查询所有销售记录
	 * @return
	 */
	@Select("select * from sale")
	ArrayList<Sale> getSales();

	/**
	 * 查询某个销售记录
	 * @param saleid
	 * @return
	 */
	@Select("select * from sale where saleid=#{saleid}")
	ArrayList<Sale> getSaleOfSaleid(Long saleid);

	/**
	 * 查询某个销售记录
	 * @return
	 */
	@Select("select distinct saleid,saletime from sale")
	ArrayList<Sale> getSalesinfo();

	/**
	 * 查询某个销售记录
	 * @param saleid
	 * @return
	 */
	@Select("select distinct saleid,saletime from sale where saleid=#{saleid}")
	ArrayList<Sale> getSalesinfoOfSaleid(Long saleid);

	/**
	 * 查询某个销售记录
	 * @param isbn
	 * @return
	 */
	@Select("select distinct saleid,saletime from sale where isbn=#{isbn}")
	ArrayList<Sale> getSalesinfoOfISBN(String isbn);

	/**
	 * 查询某个销售记录
	 * @param saletime
	 * @return
	 */
	@Select("select distinct saleid,saletime from sale where saletime=#{saletime}")
	ArrayList<Sale> getSalesinfoOfSaletime(String saletime);


}
