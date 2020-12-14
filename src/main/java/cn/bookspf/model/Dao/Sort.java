package cn.bookspf.model.Dao;

import java.io.Serializable;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class Sort implements Serializable {
    private Integer sortid;
    private String sortname;

    private static final long serialVersionUID = 1L;
}