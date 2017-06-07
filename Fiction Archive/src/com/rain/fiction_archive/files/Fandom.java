package com.rain.fiction_archive.files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.rain.fiction_archive.Main;

public class Fandom implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5453372685173030717L;
	private String name, path;
	private List<Fiction> fictions;
	private long UUID;

	public Fandom(FandomAttributes fa, long UUID) {
		this.name = fa.getName();
		this.path = fa.getPath();
		this.UUID = UUID;
	}
	
	public void writeToDisk() throws IOException{
		FileOutputStream f_out = new FileOutputStream(Main.getHomeDir() + "Fandom\\" + name + ".data");
		ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
		obj_out.writeObject(this);
		obj_out.close();
		f_out.close();		
	}

	/**
	 * @return the UUID
	 */
	public long getUUID() {
		return UUID;
	}

	/**
	 * @param UUID the UUID to set
	 */
	public void setUUID(long uUID) {
		UUID = uUID;
	}

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
	 * @return the fictions
	 */
	public List<Fiction> getFictions() {
		return fictions;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @param fictions the fictions to set
	 */
	public void setFictions(List<Fiction> fictions) {
		this.fictions = fictions;
	}
	
	@Override
	public String toString(){
		return "Name: " + name + " \nPath: " + path + "\nFictions: " + fictions + "\n";
	}

}
