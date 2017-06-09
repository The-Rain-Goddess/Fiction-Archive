/**
 * 
 */
package com.rain.fiction_archive.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rain.fiction_archive.files.Fiction;
import com.rain.fiction_archive.files.FictionAttributes;
import com.rain.fiction_archive.files.FictionIdentificationAttributes;

/**
 * @author Ryan May
 *
 */
public class FictionSearch implements Callable<Fiction> {
	private FictionIdentificationAttributes fiction;
	
	/**
	 *  Standard Constructor
	 */
	public FictionSearch(FictionIdentificationAttributes fiction) {
		this.fiction = fiction;
	}
	
	private String getSearchURL(){
		return fiction.getDomain().getSearchURL() 
						+ fiction.getTitle().replaceAll(" ", "+") + "+" 
						+ fiction.getAuthor().replaceAll(" ", "+");
	}
	
	private String getDomainURL(){
		return fiction.getDomain().getDomainURL();
	}
	
	@Override
	public Fiction call() throws Exception {
		return new Fiction( 
					getFictionAttributes()
						.setName(fiction.getTitle())
							.setAuthor(fiction.getAuthor())		
								);
	}
	
	private FictionAttributes getFictionAttributes() throws IOException{
		int wordCount = 0, chapterCount = 0, follows = 0, favorites = 0, reviews = 0;
		String publishDate = "", updateDate = "";
		for(Element e : getStatsElementsList()){
			if(e.attr("class").contains("published"))
				publishDate = e.text();
			else if(e.attr("class").contains("status"))
				updateDate = e.text();
			else if(e.attr("class").contains("words"))
				wordCount = Integer.parseInt(e.text());
			else if(e.attr("class").contains("chapters"))
				chapterCount = Integer.parseInt(e.text().split("/")[0]);
			else if(e.attr("class").contains("comments"))
				reviews = Integer.parseInt(e.text());
			else if(e.attr("class").contains("kudos"))
				favorites = Integer.parseInt(e.text());
			else if(e.attr("class").contains("bookmarks"))
				follows = Integer.parseInt(e.text());
		}
		return new FictionAttributes()
					.setWordCount(wordCount)
						.setChapterCount(chapterCount)
							.setFollowCount(follows)
								.setFavoriteCount(favorites)
									.setReviewCount(reviews)
										.setPublishDate(publishDate)
											.setUpdateDate(updateDate);
	}
	
	private List<Element> getStatsElementsList() throws IOException{
		List<Element> stats = new ArrayList<>();
		for(Element e : getStatsSearch()){
			if(e.attr("class").equals("stats")){
				stats.addAll(e.select("dd"));
			}
		} stats.remove(0);
		return stats;
	}
	
	private Elements getStatsSearch() throws IOException{
		
		return getFictionHTMLPage().select("dd");
	}
	
	private Document getFictionHTMLPage() throws IOException{
		return Jsoup.connect(getDomainURL()+ getFilteredLinks().attr("href")).get();
	}
	
	private Element getFilteredLinks() throws IOException{
		List<Element> filteredLinks = new ArrayList<>();
		for(Element e : getSearchLinks()){
			if(e.attr("href").contains("/works/") && !e.attr("href").contains("/search"))
				filteredLinks.add(e);
		} return filteredLinks.get(0);
	}
	
	private Elements getSearchLinks() throws IOException{
		return Jsoup.connect(getSearchURL()).get().select("a[href]");
	}

	
	@Override
	public String toString(){
		return "Trying to retrieve: " + fiction;
	}

}
