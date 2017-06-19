package com.rain.fiction_archive.files;

public enum Filter {
	AO3(	"/works/",
			"dd",
			"stats",
			"dd"),
	FFNet(	"/s/",
			"span",
			"xgray xcontrast_txt",
			"");
	
	String linkFilter, statFilter, statSearch, statSearchFilter;
	Filter(String linkFilter, String statFilter, String statSearch, String statSearchFilter){
		this.linkFilter = linkFilter;
		this.statFilter = statFilter;
		this.statSearch = statSearch;
		this.statSearchFilter = statSearchFilter;
	}
	
	public String getStatSearchFilter(){
		return statSearchFilter;
	}
	
	public String getLinkFilter(){
		return linkFilter;
	}
	
	public String getStatFilter(){
		return statFilter;
	}
	
	public String getStatSearch(){
		return statSearch;
	}
}
