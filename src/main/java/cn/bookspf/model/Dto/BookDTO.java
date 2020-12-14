package cn.bookspf.model.Dto;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class BookDTO {
	private Integer bid;
	private String bookname;
	private Integer hot;
	private Integer sortid;
	private String author;
	private String description;
	private Double bookprice;
	private Integer added;
	private Integer number;

	public BookDTO(){}

	public BookDTO(Integer bid, String bookname, Integer hot, Integer sortid, String author, String description, Double bookprice, Integer added, Integer number) {
		this.bid = bid;
		this.bookname = bookname;
		this.hot = hot;
		this.sortid = sortid;
		this.author = author;
		this.description = description;
		this.bookprice = bookprice;
		this.added = added;
		this.number = number;
	}
}
