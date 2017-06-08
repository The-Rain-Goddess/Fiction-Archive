package com.rain.fiction_archive.files;

public enum Domain {
	AO3("https://archiveofourown.org/works/search?utf8=✓&work_search%5Bquery%5D=", "Archive Of Our Own"),
	FFNet("", "");
	
	String url, name;
	Domain(String url, String name){
		this.url = url;
		this.name = name;
	}
}
