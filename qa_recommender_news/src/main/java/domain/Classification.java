/**
 * @author xiao xinyu 2019Äê3ÔÂ7ÈÕ
 * @description:
 */
package domain;

public class Classification {

	@Override
	public String toString() {
		return "News [id=" + id + ", class_name=" + class_name + "]";
	}

	private int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the class_name
	 */
	public String getClass_name() {
		return class_name;
	}

	/**
	 * @param class_name
	 *            the class_name to set
	 */
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	private String class_name;
}
