/**
 * @author xiao xinyu 2019��3��6��
 * @description: ��������.
 */
package searcher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ClassDao;
import dao.NewsDao;
import domain.Classification;
import domain.News;

public class ByClass {

	private static NewsDao newsDao = new NewsDao();
	private static ClassDao classDao = new ClassDao();
	// ���ఴ�ȶ����������id�б�
	private static Map<Integer, List<Integer>> classNewsList = new HashMap<Integer, List<Integer>>();
	// �����б�
	private static List<Classification> classlist = new ArrayList<Classification>();

	/**
	 * ���ಢ����.
	 * 
	 * @param null
	 * @return null
	 */
	public static void sort() {
		classNewsList.clear();
		classlist.clear();
		List<News> templist = newsDao.getRankNews();
		classlist = classDao.getAllClass();
		classlist.remove(0);
		for (Classification classification : classlist) {
			List<Integer> newslist = new ArrayList<Integer>();
			classNewsList.put(classification.getId(), newslist);
		}
		for (News news : templist) {
			classNewsList.get(news.getClass_id()).add(news.getId());
		}
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String TimeString = time.format(new java.util.Date());
		System.out.println(TimeString);
	}

	/**
	 * �������з�������.
	 * 
	 * @param null
	 * @return �������ֱ�
	 */
	public static List<String> getClassNames() {
		List<String> classnames = new ArrayList<String>();
		for (Classification classification : classlist) {
			classnames.add(classification.getClass_name());
		}
		return classnames;
	}

	/**
	 * �������з�������.
	 * 
	 * @param classid
	 *            ����id
	 * @param userID
	 *            �û�id
	 * @return ����id��
	 */
	public static List<Integer> search(int classid, long userID) {
		return classNewsList.get(classid);
	}
}
