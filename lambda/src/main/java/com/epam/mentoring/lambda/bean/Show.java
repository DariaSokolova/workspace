package com.epam.mentoring.lambda.bean;

import java.util.Set;

public class Show extends AbstractBean {

	private long showID;
	private String title;
	private String summary;
	private Set<Perfomance> perfomances;
	
	@Override
	public long getID() {
		return getShowID();
	}

	public long getShowID() {
		return showID;
	}

	public void setShowID(long showID) {
		this.showID = showID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Set<Perfomance> getPerfomances() {
		return perfomances;
	}

	public void setPerfomances(Set<Perfomance> perfomances) {
		this.perfomances = perfomances;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Show other = (Show) obj;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
