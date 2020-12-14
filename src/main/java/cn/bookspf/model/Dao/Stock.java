package cn.bookspf.model.Dao;

import java.io.Serializable;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class Stock implements Serializable {
    private Long id;
    private Long stockid;
    private Integer bid;
    private String bookname;
    private String isbn;
    private Integer comeout;
    private String cometime;
    private String outtime;

    private static final long serialVersionUID = 1L;
}