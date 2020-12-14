package cn.bookspf.model.Dao;

import java.io.Serializable;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class Sale implements Serializable {
    private Long id;
    private Long saleid;
    private Integer bid;
    private String bookname;
    private String isbn;
    private String saletime;

    private static final long serialVersionUID = 1L;
}