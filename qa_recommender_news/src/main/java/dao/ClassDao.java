/**
 * @author xiao xinyu 2019��3��8��
 * @description:
 */
package dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Classification;
import test.Test;

public class ClassDao {
	// mybatis config file
	private static String resource = "conf.xml";
	// use calss loader to load mybatis config file
	private static InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
	// build sqlSession factory
	private SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
	private SqlSession session = sessionFactory.openSession();

	/**
	 * ��ȡ���з���.
	 * 
	 * @param null
	 * @return ���з���
	 */
	public List<Classification> getAllClass() {
		String statement = "mapper.classificationMapper.getAllClasses";
		List<Classification> classlist = session.selectList(statement);
		return classlist;
	}
}
