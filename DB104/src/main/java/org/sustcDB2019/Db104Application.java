package org.sustcDB2019;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.sustcDB2019.dao.UserMapper;
import org.sustcDB2019.entity.User;

import java.io.IOException;
import java.io.InputStream;

public class Db104Application {

	public static void main(String[] args){
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//			sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);

			SqlSession session = sqlSessionFactory.openSession();
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.selectByPrimaryKey(2000006);
			//User user = (User) session.selectOne("org.sustcDB2019.dao.UserMapper.selectByPrimaryKey", new Integer(2000001));
			if(user!=null){
				System.out.println("false"+user.getPassword());
			}else {
				System.out.println("true");
			}
		}catch (IOException e){
			System.out.println(e.getMessage());
		}

	}
}
