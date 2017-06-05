package com.rain.fiction_archive.files;

public class FandomAttributes {
	private String name, path;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param name the name to set
	 */
	public FandomAttributes setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param path the path to set
	 */
	public FandomAttributes setPath(String path) {
		this.path = path;
		return this;
	}
}
