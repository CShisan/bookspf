package cn.bookspf.model.Dto;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class SaleDTO {
	private Long id;
	private Long saleid;
	private Integer bid;
	private String isbn;
	private String saletime;

}
