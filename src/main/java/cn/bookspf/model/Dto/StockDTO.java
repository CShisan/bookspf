package cn.bookspf.model.Dto;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class StockDTO {
	private Long id;
	private Long stockid;
	private Integer bid;
	private String isbn;
	private Integer comeout;
	private String cmoetime;
	private String outtime;
}
