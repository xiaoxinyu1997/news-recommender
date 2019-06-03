/**
 * @author xiao xinyu 2019��4��8��
 * @description:
 */
package com.meibanlu.qa.service.entity;

public class InvertedIndex {
	private String keyword;
	private String news_id;
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "[keyword=" + keyword + ", news_id=" + news_id + "]";
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the news_id
	 */
	public String getNews_id() {
		return news_id;
	}

	/**
	 * @param news_id
	 *            the news_id to set
	 */
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
}
