package com.rain.fiction_archive.files;

public class FictionAttributes {
	private String name, author, path, url;
	private String publishDate, updateDate;
	private int bookmarkChapter, wordCount, chapterCount, favoriteCount,
				followCount, reviewCount;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
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
	 * @return the chapterCount
	 */
	public int getChapterCount() {
		return chapterCount;
	}
	/**
	 * @return the favoriteCount
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}
	/**
	 * @return the followCount
	 */
	public int getFollowCount() {
		return followCount;
	}
	/**
	 * @return the reviewCount
	 */
	public int getReviewCount() {
		return reviewCount;
	}
	/**
	 * @param publishDate the publishDate to set
	 */
	public FictionAttributes setPublishDate(String publishDate) {
		this.publishDate = publishDate;
		return this;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public FictionAttributes setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	/**
	 * @param chapterCount the chapterCount to set
	 */
	public FictionAttributes setChapterCount(int chapterCount) {
		this.chapterCount = chapterCount;
		return this;
	}
	/**
	 * @param favoriteCount the favoriteCount to set
	 */
	public FictionAttributes setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
		return this;
	}
	/**
	 * @param followCount the followCount to set
	 */
	public FictionAttributes setFollowCount(int followCount) {
		this.followCount = followCount;
		return this;
	}
	/**
	 * @param reviewCount the reviewCount to set
	 */
	public FictionAttributes setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
		return this;
	}
	/**
	 * @param name the name to set
	 */
	public FictionAttributes setName(String name) {
		this.name = name;
		return this;
	}
	/**
	 * @param author the author to set
	 */
	public FictionAttributes setAuthor(String author) {
		this.author = author;
		return this;
	}
	/**
	 * @param path the path to set
	 */
	public FictionAttributes setPath(String path) {
		this.path = path;
		return this;
	}
	/**
	 * @param url the url to set
	 */
	public FictionAttributes setUrl(String url) {
		this.url = url;
		return this;
	}
	/**
	 * @param bookmarkChapter the bookmarkChapter to set
	 */
	public FictionAttributes setBookmarkChapter(int bookmarkChapter) {
		this.bookmarkChapter = bookmarkChapter;
		return this;
	}
	/**
	 * @return the wordCount
	 */
	public int getWordCount() {
		return wordCount;
	}
	/**
	 * @param wordCount the wordCount to set
	 */
	public FictionAttributes setWordCount(int wordCount) {
		this.wordCount = wordCount;
		return this;
	}
}
