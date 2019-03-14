/**
 * @author xiao xinyu 2019年3月7日
 * @description:
 */
package test;

//import java.io.InputStream;
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import domain.News;
import searcher.ByClass;
//import searcher.ByKeyWords;

public class Test {
	public static void main(String[] args) {
		// // mybatis config file
		// String resource = "conf.xml";
		// // 使用类加载器加载mybatis的配置文件
		// InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
		// // 构建sqlSession的工厂
		// SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		// // 创建能执行映射文件中sql的sqlSession
		// SqlSession session = sessionFactory.openSession();
		// // 映射sql的标识字符串
		// String statement = "mapper.newsMapper.getNews";
		// // 执行查询返回一个唯一user对象的sql
		// News news = session.selectOne(statement, 54);
		// System.out.println(news);
		// String statement2 = "mapper.newsMapper.getAllNews";
		// List<News> news2 = session.selectList(statement2);
		// System.out.println(news2);
		// ByClass.order();
		// String[] request = {"扶贫",};
		// System.out.println(ByKeyWords.search(request));
		ByClass.sort();

	}
}
