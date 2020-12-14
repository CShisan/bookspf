package cn.bookspf.model.Dao;

import java.io.Serializable;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class Shopcar implements Serializable {
    private Long id;
    private Long carid;
    private Integer uid;
    private Integer bid;
    private String bookname;
    private Double bookprice;
    private Integer booknumber;
    private Double total;

    private static final long serialVersionUID = 1L;
}