package com.rain.fiction_archive.files;

public enum Domain {
	  	AO3("http://archiveofourown.org",
			"http://archiveofourown.org/works/search?utf8=✓&work_search%5Bquery%5D=", 
			"Archive Of Our Own",
			Filter.AO3),
	  FFNet("http://fanfiction.net",
			"https://www.fanfiction.net/search.php?keywords=%22SEARCHTERM%22&type=story&match=title&formatid=any&sort=0&genreid1=0&genreid2=0&characterid1=0&characterid2=0&characterid3=0&characterid4=0&words=0&ready=1&categoryid=0",
			"Fanfiction.net",
			Filter.FFNet);
	
	String searchURL, domainURL, name;
	Filter filter;
	Domain(String domainURL, String searchURL, String name, Filter filter){
		this.domainURL = domainURL;
		this.searchURL = searchURL;
		this.name = name;
		this.filter = filter;
	}
	
	public Filter getHTMLFilter(){
		return filter;
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
