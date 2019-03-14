/**
 * @author xiao xinyu 2019Äê3ÔÂ7ÈÕ
 * @description:
 */
package domain;

public class User {
	
	@Override
	public String toString() {
		return "News [id=" + id + ", preferences=" + preferences + "]";
	}
	
	private long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the preferences
	 */
	public String getPreferences() {
		return preferences;
	}

	/**
	 * @param preferences
	 *            the preferences to set
	 */
	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	private String preferences;
}
