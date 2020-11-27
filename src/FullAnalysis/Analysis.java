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
}
