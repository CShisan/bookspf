package cn.bookspf.model.Dto;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class OrderDTO {
	private Long id;
	private Long orderid;
	private Integer uid;
	private Integer bid;
	private String isbn;
	private Double bookprice;
	private String createtime;
}
