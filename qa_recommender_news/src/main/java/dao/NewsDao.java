/**
 * @author xiao xinyu 2019��3��7��
 * @description:
 */
package dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.News;
import test.Test;

public class NewsDao {
	// mybatis config file
	private static String resource = "conf.xml";
	// use calss loader to load mybatis config file
	private static InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
	// build sqlSession factory
	private SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
	private SqlSession session = sessionFactory.openSession();

	/**
	 * ��id��ȡ����.
	 * 
	 * @param null
	 * @return ĳ������
	 */
	public News getNewsByID(int id) {
		String statement = "mapper.newsMapper.getNews";
		News news = session.selectOne(statement, id);
		return news;
	}

	/**
	 * ��ȡ��������.
	 * 
	 * @param null
	 * @return ��������
	 */
	public List<News> getAllNews() {
		String statement = "mapper.newsMapper.getAllNews";
		List<News> newslist = session.selectList(statement);
		return newslist;
	}

	/**
	 * ��ȡ������������ţ��������������ҳ�����������ȶ����������Ų�����ҳ���������ķ���ʱ������.
	 * 
	 * @param null
	 * @return �������������
	 */
	public List<News> getRankNews() {
		String statement = "mapper.newsMapper.getRankNews";
		List<News> newslist = session.selectList(statement);
		return newslist;
	}
}
