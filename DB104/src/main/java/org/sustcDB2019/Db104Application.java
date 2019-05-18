package org.sustcDB2019;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.sustcDB2019.dao.DelivererMapper;
import org.sustcDB2019.dao.UserMapper;
import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Db104Application {

	public static void main(String[] args){
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			SqlSession session = sqlSessionFactory.openSession();

//			UserMapper mapper = session.getMapper(UserMapper.class);
//			User user = mapper.selectByPrimaryKey(2000006);

			DelivererMapper mapper = session.getMapper(DelivererMapper.class);

			Deliverer deliverer = new Deliverer();
			deliverer.setStatusOn("Y");
			deliverer.setWarehouseWarehouseId(6);
//			Deliverer user = mapper.selectByCase(deliverer);
			ArrayList<Deliverer> arrayList = mapper.selectByCase(deliverer);

			for(Deliverer x: arrayList){
				System.out.println(x.getUserId().toString());
			}
//
//			if(user!=null){
//				System.out.println("false"+user.getStatusOn());
//			}else {
//				System.out.println("true");
//			}
		}catch (IOException e){
			System.out.println(e.getMessage());
		}

	}
}
