package com.rain.fiction_archive.files;

public enum Domain {
	AO3("http://archiveofourown.org","http://archiveofourown.org/works/search?utf8=✓&work_search%5Bquery%5D=", "Archive Of Our Own"),
	FFNet("", "", "");
	
	String searchURL, domainURL, name;
	Domain(String domainURL, String searchURL, String name){
		this.domainURL = domainURL;
		this.searchURL = searchURL;
		this.name = name;
	}
	
	public String getDomainURL(){
		return domainURL;
	}
	
	public String getSearchURL(){
		return searchURL;
	}
	
	public String getName(){
		return name;
	}
}
