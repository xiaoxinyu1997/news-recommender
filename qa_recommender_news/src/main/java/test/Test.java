/**
 * @author xiao xinyu 2019��3��7��
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
		// // ʹ�������������mybatis�������ļ�
		// InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
		// // ����sqlSession�Ĺ���
		// SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		// // ������ִ��ӳ���ļ���sql��sqlSession
		// SqlSession session = sessionFactory.openSession();
		// // ӳ��sql�ı�ʶ�ַ���
		// String statement = "mapper.newsMapper.getNews";
		// // ִ�в�ѯ����һ��Ψһuser�����sql
		// News news = session.selectOne(statement, 54);
		// System.out.println(news);
		// String statement2 = "mapper.newsMapper.getAllNews";
		// List<News> news2 = session.selectList(statement2);
		// System.out.println(news2);
		// ByClass.order();
		// String[] request = {"��ƶ",};
		// System.out.println(ByKeyWords.search(request));
		ByClass.sort();

	}
}
