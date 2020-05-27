package cn.Bookspf.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import cn.Bookspf.model.DO.DBSale;

@Mapper
@Repository
public interface SaleMapper {
	
	//查询所有销售记录
	@Select("select * from sale")
	public ArrayList<DBSale> getSales();

	//查询某个销售记录
	@Select("select * from sale where saleid=#{saleid}")
	public ArrayList<DBSale> getSaleOfSaleid(long saleid);

	//查询某个销售记录
	@Select("select * from sale where isbn=#{isbn}")
	public ArrayList<DBSale> getSaleOfISBN(String isbn);

	//查询某个销售记录
	@Select("select * from sale where saletime=#{saletime}")
	public ArrayList<DBSale> getSaleOfSaletime(String saletime);
	
}