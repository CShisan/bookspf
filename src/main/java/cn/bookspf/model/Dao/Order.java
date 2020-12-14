package cn.bookspf.model.Dao;

import java.io.Serializable;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class Order implements Serializable {
    private Long id;
    private Long orderid;
    private Integer uid;
    private Integer bid;
    private String bookname;
    private String isbn;
    private Double bookprice;
    private Double total;
    private String createtime;


    private static final long serialVersionUID = 1L;
}