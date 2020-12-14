package cn.bookspf.model.Dao;

import java.io.Serializable;
import lombok.Data;

/**
 * book
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String accesstoken;
    private Integer role;
    private String email;
    private Double balance;
    private String realname;
    private String phone;
    private String address;
    private String captcha;
    private static final long serialVersionUID = 1L;
}