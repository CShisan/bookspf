package cn.bookspf.model.Dto;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class PurchaseDTO {
	private Long id;
	private Long purchaseid;
	private Integer bid;
	private String isbn;
	private Integer purchaseprice;
	private Integer operator;
	private String purchasetime;
}
