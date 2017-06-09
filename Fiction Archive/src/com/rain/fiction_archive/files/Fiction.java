package com.rain.fiction_archive.files;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fiction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5185781113396566196L;
	private String name, author, path, url;
	private String publishDate, updateDate;
	private int bookmarkChapter, wordCount, chapterCount, rating;
	private int favoriteCount, followCount, reviewCount;
	

	public Fiction(FictionAttributes fa) {
		this.name = fa.getName();
		this.author = fa.getAuthor();
		this.path = fa.getPath();
		this.url = fa.getUrl();
		this.bookmarkChapter = fa.getBookmarkChapter();
		this.wordCount = fa.getWordCount();
		this.chapterCount = fa.getChapterCount();
		this.favoriteCount = fa.getFavoriteCount();
		this.followCount = fa.getFollowCount();
		this.reviewCount = fa.getReviewCount();
		this.publishDate = fa.getPublishDate();
		this.updateDate = fa.getUpdateDate();
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * @return the rating
	 */
	public IntegerProperty getRatingProperty() {
		return new SimpleIntegerProperty(rating);
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the chapterCount
	 */
	public int getChapterCount() {
		return chapterCount;
	}
	
	/**
	 * @return the chapterCount
	 */
	public IntegerProperty getChapterCountProperty() {
		return new SimpleIntegerProperty(chapterCount);
	}

	/**
	 * @param chapterCount the chapterCount to set
	 */
	public void setChapterCount(int chapterCount) {
		this.chapterCount = chapterCount;
	}

	/**
	 * @return the wordCount
	 */
	public int getWordCount() {
		return wordCount;
	}
	
	/**
	 * @return the wordCount
	 */
	public IntegerProperty getWordCountProperty() {
		return new SimpleIntegerProperty(wordCount);
	}

	/**
	 * @param wordCount the wordCount to set
	 */
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the name
	 */
	public StringProperty getNameProperty() {
		return new SimpleStringProperty(name);
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * @return the author
	 */
	public StringProperty getAuthorProperty() {
		return new SimpleStringProperty(author);
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the bookmarkChapter
	 */
	public int getBookmarkChapter() {
		return bookmarkChapter;
	}
	
	/**
	 * @return the bookmarkChapter
	 */
	public IntegerProperty getBookmarkChapterProperty() {
		return new SimpleIntegerProperty(bookmarkChapter);
	}

	/**
	 * @return the publishDate
	 */
	public String getPublishDate() {
		return publishDate;
	}

	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @return the favoriteCount
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}
	
	/**
	 * @return the favoriteCount
	 */
	public IntegerProperty getFavoriteCountProperty() {
		return new SimpleIntegerProperty(favoriteCount);
	}

	/**
	 * @return the followCount
	 */
	public int getFollowCount() {
		return followCount;
	}
	
	/**
	 * @return the followCount
	 */
	public IntegerProperty getFollowCountProperty() {
		return new SimpleIntegerProperty(followCount);
	}
	
	/**
	 * @return the reviewCount
	 */
	public int getReviewCount() {
		return reviewCount;
	}
	
	/**
	 * @return the reviewCount
	 */
	public IntegerProperty getReviewCountProperty() {
		return new SimpleIntegerProperty(reviewCount);
	}

	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @param favoriteCount the favoriteCount to set
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	/**
	 * @param followCount the followCount to set
	 */
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	/**
	 * @param reviewCount the reviewCount to set
	 */
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param bookmarkChapter the bookmarkChapter to set
	 */
	public void setBookmarkChapter(int bookmarkChapter) {
		this.bookmarkChapter = bookmarkChapter;
	}
	
	@Override
	public String toString(){
		return "Name: " + this.name
				+ "\n Author: " + this.author
				+ "\n Date Created: " + this.publishDate
				+ "\n Date Updated: " + this.updateDate
				+ "\n Word Count: " + this.wordCount
				+ "\n Chapter Count: " + this.chapterCount
				+ "\n Bookmarked Chapter: " + this.bookmarkChapter
				+ "\n Favorite Count: " + this.favoriteCount
				+ "\n Follower Count: " + this.followCount
				+ "\n Review Count: " + this.reviewCount
				+ "\n Personal Rating: " + this.rating;
				
	}

}
