package com.rain.fiction_archive.files;

import java.io.Serializable;

public class Fiction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5185781113396566196L;
	private String name, author, path, url;
	private int bookmarkChapter;

	public Fiction(FictionAttributes fa) {
		this.name = fa.getName();
		this.author = fa.getAuthor();
		this.path = fa.getPath();
		this.url = fa.getUrl();
		this.bookmarkChapter = fa.getBookmarkChapter();
	}

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

}
