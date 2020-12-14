package cn.bookspf.mapper;

import java.util.ArrayList;

import cn.bookspf.model.Dto.BookDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.bookspf.model.Dao.Book;

@Mapper
@Repository
 public interface BookMapper {

	//管理员****************************************

	/**
	 * 查询所有图书
	 * @return
	 */
	@Select("select * from book")
	ArrayList<Book> getBooks();

	/**
	 * 插入图书记录
	 * @param book
	 */
	@Insert("insert into book values(#{bid},#{bookname},#{hot},#{sortid},#{author}," +
			"#{description},#{bookprice},#{added},#{number})")
	void addBook(BookDTO book);

	/**
	 * 更新图书记录
	 * @param book
	 */
	@Update("update book set bookname=#{bookname}, sortid=#{sortid}, author=#{author}," +
			"description=#{description}, bookprice=#{bookprice}, added=#{added} where bid=#{bid}")
	void alterBook(BookDTO book);

	/**
	 * 删除图书
	 * @param bid
	 */
	@Delete("delete from book where bid=#{bid}")
	void deleteBook(Integer bid);

	/**
	 * 查Bid
	 * @param bid
	 * @return
	 */
	@Select("select bid from book where bid=#{bid}")
	String findBid(Integer bid);

	/**
	 * 查询某书的数量
	 * @param bid
	 * @return
	 */
	@Select("select number from book where bid=#{bid}")
	Integer getBookNumberOfAdmin(Integer bid);

	/**
	 * 修改某书的数量
	 * @param bid
	 * @param number
	 * @return
	 */
	@Update("update book set number=#{number} where bid=#{bid}")
	Integer updateBookNumberOfAdmin(Integer bid,Integer number);

	/**
	 * 查询书籍名称
	 * @param bid
	 * @return
	 */
	@Select("select bookname from book where bid=#{bid}")
	String getBooknameOfAdmin(Integer bid);
	//*************************************************************







	//用户******************************************

	/**
	 * 查询某图书
	 * @param bid
	 * @return
	 */
	@Select("select * from book where bid=#{bid} and added = 1")
	Book getBook(Integer bid);

	/**
	 * 获取bid
	 * @param bookname
	 * @return
	 */
	@Select("select bid from book where bookname=#{bookname} and added = 1")
	Integer getBid(String bookname);

	/**
	 * 查询某书价格
	 * @param bid
	 * @return
	 */
	@Select("select bookprice from book where bid=#{bid} and added = 1")
	Double getBookprice(Integer bid);

	/**
	 * 查询某书的数量
	 * @param bid
	 * @return
	 */
	@Select("select number from book where bid=#{bid} and added = 1")
	Integer getBookNumber(Integer bid);

	/**
	 * 修改某书的数量
	 * @param bid
	 * @param number
	 * @return
	 */
	@Update("update book set number=#{number} where bid=#{bid} and added = 1")
	Integer updateBookNumber(Integer bid,Integer number);

	/**
	 * 查询书籍名称
	 * @param bid
	 * @return
	 */
	@Select("select bookname from book where bid=#{bid} and added = 1")
	String getBookname(Integer bid);

	/**
	 * 查询热度
	 * @param bid
	 * @return
	 */
	@Select("select hot from book where bid=#{bid} and added = 1")
	Integer getHot(Integer bid);

	/**
	 * 更新热度
	 * @param bid
	 * @param hot
	 */
	@Update("update book set hot=#{hot} where bid=#{bid} and added = 1")
	void updateHot(Integer bid,Integer hot);

	/**
	 * 查询排行榜
	 * @return
	 */
	@Select("select * from book where added=1 order by hot desc limit 0,10 ")
	ArrayList<Book> getRankList();

	/**
	 * 获取发布的图书
	 * @return
	 */
	@Select("select * from book where added=1 limit 0,5")
	ArrayList<Book> getPublishBook();
	
}
