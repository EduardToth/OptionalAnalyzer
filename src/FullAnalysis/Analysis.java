package FullAnalysis;

public class Analysis {
	private String ruleName;
	private String essentialInfo;
	
	public Analysis(String ruleName, String essentialInfo) {
		super();
		this.ruleName = ruleName;
		this.essentialInfo = essentialInfo;
	}
	
	
	@Override
	public String toString() {
		return ruleName;
	}
	
	public String getRuleName() {
		return ruleName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((essentialInfo == null) ? 0 : essentialInfo.hashCode());
		result = prime * result + ((ruleName == null) ? 0 : ruleName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Analysis other = (Analysis) obj;
		if (essentialInfo == null) {
			if (other.essentialInfo != null)
				return false;
		} else if (!essentialInfo.equals(other.essentialInfo))
			return false;
		if (ruleName == null) {
			if (other.ruleName != null)
				return false;
		} else if (!ruleName.equals(other.ruleName))
			return false;
		return true;
	}
	
	
}

