package com.rain.fiction_archive.files;

public class FictionAttributes {
	private String name, author, path, url;
	private int bookmarkChapter, wordCount;
	
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
