/**
 * 
 */
package com.rain.fiction_archive.files;

/**
 * @author Ryan May
 *
 */
public class FictionIdentificationAttributes {
	private String title, author;
	private Domain domain;
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		return domain;
	}
	/**
	 * @param title the title to set
	 */
	public FictionIdentificationAttributes setTitle(String title) {
		this.title = title;
		return this;
	}
	/**
	 * @param author the author to set
	 */
	public FictionIdentificationAttributes setAuthor(String author) {
		this.author = author;
		return this;
	}
	/**
	 * @param domain the domain to set
	 */
	public FictionIdentificationAttributes setDomain(Domain domain) {
		this.domain = domain;
		return this;
	}
	
	@Override
	public String toString(){
		return "Title: " + title
				+ "\n Author: " + author
				+ "\n Domain: " + domain.name;
	}
}
