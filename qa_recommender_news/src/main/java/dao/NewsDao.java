/**
 * @author xiao xinyu 2019年3月7日
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
	 * 按id获取新闻.
	 * 
	 * @param null
	 * @return 某个新闻
	 */
	public News getNewsByID(int id) {
		String statement = "mapper.newsMapper.getNews";
		News news = session.selectOne(statement, id);
		return news;
	}

	/**
	 * 获取所有新闻.
	 * 
	 * @param null
	 * @return 所有新闻
	 */
	public List<News> getAllNews() {
		String statement = "mapper.newsMapper.getAllNews";
		List<News> newslist = session.selectList(statement);
		return newslist;
	}

	/**
	 * 获取经过排序的新闻，如果该新闻在首页，则由它的热度排序；若新闻不在首页，则由它的发布时间排序.
	 * 
	 * @param null
	 * @return 经过排序的新闻
	 */
	public List<News> getRankNews() {
		String statement = "mapper.newsMapper.getRankNews";
		List<News> newslist = session.selectList(statement);
		return newslist;
	}
}
