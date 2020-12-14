package cn.bookspf.mapper;

import cn.bookspf.model.Dao.Stock;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface StockMapper {

    /**
     * 插入入库记录
     * @param stockid
     * @param bid
     * @param isbn
     * @param cometime
     */
    @Insert("insert into stock(stockid,bid,isbn,cometime,comeout) values(#{stockid},#{bid},#{isbn},#{cometime},0)")
    void insertComeStock(Long stockid,Integer bid,String isbn,String cometime);

    /**
     * 获取Bids
     * @param stockid
     * @return
     */
    @Select("select bid from stock where stockid=#{stockid}")
    ArrayList<Integer> getBidsOfStockid(Long stockid);

    /**
     * 获取所有库存记录
     * @return
     */
    @Select("select distinct stockid,comeout,cometime,outtime from stock")
    ArrayList<Stock> getStocks();

    /**
     * 获取某个库存记录
     * @param stockid
     * @return
     */
    @Select("select distinct stockid,comeout,cometime,outtime from stock where stockid=#{stockid}")
    ArrayList<Stock> getStockOfStockid(long stockid);

    /**
     * 获取某个库存记录
     * @param stocktime
     * @return
     */
    @Select("select distinct stockid,comeout,cometime,outtime from stock where stocktime=#{stocktime}")
    ArrayList<Stock> getStockOfStocktime(String stocktime);

    /**
     * 获取某个库存记录
     * @param stockid
     * @return
     */
    @Select("select stockid,bid,isbn from stock where stockid=#{stockid}")
    ArrayList<Stock> getStockinfoOfStockid(long stockid);

    /**
     * 获取入库信息
     * @param bid
     * @return
     */
    @Select("select * from stock where comeout=0 and bid=#{bid} limit 0,1")
    Stock getComeStock(Integer bid);

    /**
     * 修改出库状态
     * @param stockid
     * @param outtime
     */
    @Update("update stock set outtime=#{outtime},comeout=1 where stockid=#{stockid}")
    void updateOutStock(Long stockid,String outtime);

    /**
     * 删除记录
     * @param isbn
     */
    @Delete("delete from stock where isbn=#{isbn}")
    void deleteStock(String isbn);
}
