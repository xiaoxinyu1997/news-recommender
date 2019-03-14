/**
 * @author xiao xinyu 2019年3月6日
 * @description:v 按关键词搜索.
 */
package searcher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import dao.NewsDao;
import domain.News;

public class ByKeyWords {
	private static NewsDao newsDao = new NewsDao();
	private static Map<String, List<Integer>> invertedlist = new HashMap<String, List<Integer>>();

	/**
	 * 建立关键词-新闻倒排索引.
	 * 
	 * @param null
	 * @return null
	 */
	public static void invert() {
		invertedlist.clear();
		for (News news : newsDao.getAllNews()) {
			String keywords = news.getKeywords();
			for (String keyword : keywords.split(",")) {
				if (invertedlist.containsKey(keyword)) {
					invertedlist.get(keyword).add(news.getId());
				} else {
					List<Integer> nulllist = new ArrayList<Integer>();
					nulllist.clear();
					nulllist.add(news.getId());
					invertedlist.put(keyword, nulllist);
				}
			}
		}
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String TimeString = time.format(new java.util.Date());
		System.out.println(TimeString);
	}

	/**
	 * 根据关键词对索引进行搜索.
	 * 
	 * @param keywords
	 *            关键词集合
	 * @return 新闻id表
	 */
	public static List<Integer> search(String[] keywords) {
		Map<Integer, Integer> resultsmap = new TreeMap<Integer, Integer>();
		resultsmap.clear();
		List<Integer> results = new ArrayList<Integer>();
		for (String keyword : keywords) {
			if (invertedlist.containsKey(keyword)) {
				List<Integer> newslist = invertedlist.get(keyword);
				for (int news : newslist) {
					if (null != resultsmap.get(news)) {
						resultsmap.replace(news, resultsmap.get(news) + 1);
					} else {
						resultsmap.put(news, 1);
					}
				}
			} else {
				// 若索引中不含这个关键词
				continue;
			}
		}
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(resultsmap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		for (Entry<Integer, Integer> temp : list) {
			results.add(temp.getKey());
		}
		if (results.isEmpty()) {
			// 搜索结果为空
			return null;
		} else {
			return results;
		}
	}
}
