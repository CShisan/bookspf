package cn.bookspf.model.Dto;


import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class UserDTO {
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
}
