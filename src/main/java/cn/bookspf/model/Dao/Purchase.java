package cn.bookspf.model.Dao;

import java.io.Serializable;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class Purchase implements Serializable {
    private Long id;
    private Long purchaseid;
    private Integer bid;
    private String bookname;
    private String isbn;
    private Integer number;
    private Double purchaseprice;
    private Double total;
    private Integer operator;
    private String purchasetime;

    private static final long serialVersionUID = 1L;
}