package cn.bookspf;

import cn.bookspf.mapper.UserMapper;
import cn.bookspf.model.Dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BookspfApplicationTests {
	@Resource
	UserMapper userMapper;

	@Test
	void contextLoads() {

	}

}
