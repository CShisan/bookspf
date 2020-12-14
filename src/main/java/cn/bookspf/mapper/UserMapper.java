package cn.bookspf.mapper;

import java.util.ArrayList;

import cn.bookspf.model.Dto.UserDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import cn.bookspf.model.Dao.User;

/**
 * @author Administrator
 */
@Mapper
@Repository
public interface UserMapper {

	/**
	 * 更新accesstoken Of Uid
	 * @param user user
	 */
	@Update("update user set accesstoken=#{accesstoken} where uid=#{uid}")
	void updateAccesstoken(UserDTO user);

	/**
	 * 获取User of accesstoken
	 * @param accesstoken accesstoken
	 * @return user
	 */
	@Select("select * from user where accesstoken=#{accesstoken}")
	User getUser(String accesstoken);

	/**
	 * 获取User Of Uid
	 * @param uid
	 * @return
	 */
	@Select("select * from user where uid=#{uid}")
	User getUserOfUid(Integer uid);

	/**
	 * 获取User Of Username
	 * @param username
	 * @return
	 */
	@Select("select * from user where username=#{username}")
	User getUserOfUsername(String username);

	/**
	 * 获取User Of Email
	 * @param email
	 * @return
	 */
	@Select("select * from user where email=#{email}")
	User getUserOfEmail(String email);

	/**
	 * 获取UserNoPass Of Uid
	 * @param role
	 * @return
	 */
	@Select("select uid,username,password,role,email,balance,realname,phone,address from user where role=#{role}")
	ArrayList<User> getUserNoPasswordOfRole(Integer role);

	/**
	 * 插入用户
	 * @param user
	 */
	@Insert("insert into user(uid,username,password,email,role) values(#{uid},#{username},#{password},#{email},#{role})")
	void insertUser(UserDTO user);

	/**
	 * 插入管理员
	 * @param user
	 */
	@Insert("insert into user(uid,username,password,email,role,realname) values(#{uid},#{username},#{password},#{email},#{role},#{realname})")
	void insertManager(UserDTO user);

	/**
	 * 获取Uid Of Username
	 * @param username
	 * @return
	 */
	@Select("select uid from user where username=#{username}")
	int getUid(String username);

	/**
	 * 获取Uid Of Uid
	 * @param uid
	 * @return
	 */
	@Select("select uid from user where uid=#{uid}")
	Integer findUid(Integer uid);

	/**
	 * 获取Username Of Username
	 * @param username
	 * @return
	 */
	@Select("select username from user where username=#{username}")
	String findUsername(String username);

	/**
	 * 获取Email Of Email
	 * @param email
	 * @return
	 */
	@Select("select email from user where email=#{email}")
	String findEmail(String email);

	/**
	 * 获取Password Of Username
	 * @param username
	 * @return
	 */
	@Select("select password from user where username=#{username}")
	String getPasswordOfUsername(String username);

	/**
	 * 获取Admin Of Uid
	 * @param uid
	 * @return
	 */
	@Select("select admin from user where uid=#{uid}")
	int getAdmin(int uid);

	/**
	 * 删除用户
	 * @param uid
	 * @return
	 */
	@Delete("delete from user where uid=#{uid}")
	int deleteUser(int uid);

	/**
	 * 查询用户余额
	 * @param uid
	 * @return
	 */
	@Select("select balance from user where uid=#{uid}")
	Double getBalance(Integer uid);

	/**
	 * 查询用户密码
	 * @param uid
	 * @return
	 */
	@Select("select password from user where uid=#{uid}")
	String getPasswordOfUid(Integer uid);

	/**
	 * 修改用户余额
	 * @param uid
	 * @param balance
	 */
	@Update("update user set balance=#{balance} where uid=#{uid}")
	void updateBalance(Integer uid,Double balance);

	/**
	 * 修改用户名字
	 * @param uid
	 * @param username
	 */
	@Update("update user set username=#{username} where uid=#{uid}")
	void updateUsername(Integer uid,String username);

	/**
	 * 修改用户密码
	 * @param uid
	 * @param password
	 */
	@Update("update user set password=#{password} where uid=#{uid}")
	void updatePassword(Integer uid,String password);

	/**
	 * 修改用户密码
	 * @param uid
	 * @param email
	 */
	@Update("update user set email=#{email} where uid=#{uid}")
	void updateEmail(Integer uid,String email);

	/**
	 * 修改用户手机
	 * @param uid
	 * @param phone
	 */
	@Update("update user set phone=#{phone} where uid=#{uid}")
	void updatePhone(Integer uid,String phone);

	/**
	 * 修改用户真实名字
	 * @param uid
	 * @param realname
	 */
	@Update("update user set realname=#{realname} where uid=#{uid}")
	void updateRealname(Integer uid,String realname);

	/**
	 * 修改用户地址
	 * @param uid
	 * @param address
	 */
	@Update("update user set address=#{address} where uid=#{uid}")
	void updateAddress(Integer uid,String address);

	/**
	 * 查手机
	 * @param uid
	 * @return
	 */
	@Select("select phone from user where uid=#{uid}")
	String getPhone(Integer uid);

	/**
	 * 查真实名字
	 * @param uid
	 * @return
	 */
	@Select("select realname from user where uid=#{uid}")
	String getRealname(Integer uid);

	/**
	 * 查地址
	 * @param uid
	 * @return
	 */
	@Select("select address from user where uid=#{uid}")
	String getAddress(Integer uid);

}
