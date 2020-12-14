package cn.bookspf.model.Dao;

import java.io.Serializable;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class Book implements Serializable {
    private Integer bid;
    private String bookname;
    private Integer hot;
    private Integer sortid;
    private String sortname;
    private String author;
    private String description;
    private Double bookprice;
    private Integer added;
    private Integer number;

    private static final long serialVersionUID = 1L;
}