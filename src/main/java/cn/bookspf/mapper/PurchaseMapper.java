package cn.bookspf.mapper;

import cn.bookspf.model.Dao.Purchase;
import cn.bookspf.model.Dto.PurchaseDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface PurchaseMapper {

    /**
     * 插入记录
     * @param purchase
     */
    @Insert("insert into purchase(purchaseid,bid,isbn,purchaseprice,purchasetime,operator) "+
            "values(#{purchaseid},#{bid},#{isbn},#{purchaseprice},#{purchasetime},#{operator})")
    void insertPurchase(PurchaseDTO purchase);

    /**
     * 删除记录
     * @param isbn
     */
    @Delete("delete from purchase where isbn=#{isbn}")
    void deletePurchase(String isbn);

    /**
     * 查询进货统计
     * @return
     */
    @Select("select purchaseid,count(purchaseid) number,sum(purchaseprice) total,purchasetime,operator " +
            "from purchase group by purchaseid,purchasetime,operator")
    ArrayList<Purchase> getPurchases();

    /**
     * 查询某个进货统计
     * @param purchaseid
     * @return
     */
    @Select("select purchaseid,count(purchaseid) number,sum(purchaseprice) total,purchasetime,operator " +
            "from purchase where purchaseid=#{purchaseid} group by purchaseid,purchasetime,operator")
    ArrayList<Purchase> getPurchaseOfPurchaseid(long purchaseid);

    /**
     * 查询某个进货统计
     * @param purchasetime
     * @return
     */
    @Select("select purchaseid,count(purchaseid) number,sum(purchaseprice) total,purchasetime,operator " +
            "from purchase where purchasetime=#{purchasetime} group by purchaseid,purchasetime,operator")
    ArrayList<Purchase> getPurchaseOfPurchasetime(String purchasetime);

    /**
     * 查询某个进货信息
     * @param purchaseid
     * @return
     */
    @Select("select purchaseid,bid,isbn,purchaseprice from purchase where purchaseid=#{purchaseid}")
    ArrayList<Purchase> getPurchasesinfo(long purchaseid);

    /**
     * 获取Purchaseid Of Purchaseid
     * @param purchaseid
     * @return
     */
    @Select("select purchaseid from purchase where purchaseid=#{purchaseid}")
    ArrayList<Long> findPurchaseid (long purchaseid);

    /**
     * 获取isbn Of isbn
     * @param isbn
     * @return
     */
    @Select("select isbn from purchase where isbn=#{isbn}")
    String findIsbn (String isbn);

    /**
     * 获取Bid(根据某个purchase)
     * @param purchaseid
     * @return
     */
    @Select("select bid from purchase where purchaseid=#{purchaseid}")
    ArrayList<Integer> getBidsOfPurchase(Long purchaseid);
}
