import com.lisz.bean.User;
import com.lisz.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class MyTest {
	private static final String RESOURCE = "mybatis-config.xml";
	private InputStream inputStream;
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setup() {
		try {
			inputStream = Resources.getResourceAsStream(RESOURCE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testSelect() throws Exception{
		try (SqlSession session = sqlSessionFactory.openSession()) {
			UserDao dao = session.getMapper(UserDao.class);
			User user = dao.findById(1);
			System.out.println(user);
			// 动态代理类：org.apache.ibatis.binding.MapperProxy@5178056b
			System.out.println(dao);
		}
	}

	@Test
	public void testSave() throws Exception{
		try (SqlSession session = sqlSessionFactory.openSession()) {// sqlSessionFactory.openSession(true) 自动提交
			UserDao dao = session.getMapper(UserDao.class);
			User user = new User();
			user.setName("yijing");
			user.setJob("SDE");
			user.setBirthDate(new Date("1991/09/15"));
			user.setEmail("yijing@gmail.com");
			user.setScore(100.00);
			user.setCreatedAt(new Date("2020/12/13"));
			user.setModifiedAt(new Date("2020/12/13"));
			Integer res = dao.save(user);
			session.commit();
			System.out.println("Result: " + res);
			System.out.println(user);
		}
	}

	@Test
	public void testUpdate() throws Exception{
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			UserDao dao = session.getMapper(UserDao.class);
			User user = new User();
			user.setId(1);
			user.setScore(120.00);
			dao.update(user);
			System.out.println(user);
		}
	}

	@Test
	public void testDelete() throws Exception{
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			UserDao dao = session.getMapper(UserDao.class);
			Integer delete = dao.deleteById(9);
			System.out.println(delete);
		}
	}
}
