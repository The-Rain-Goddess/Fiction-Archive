/**
 * 
 */
package com.rain.fiction_archive.gui;

import java.util.concurrent.Callable;

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

	@Override
	public Fiction call() throws Exception {
		
		return new Fiction( 
					new FictionAttributes()
						.setName(fiction.getTitle())
							.setAuthor(fiction.getAuthor())
								.setWordCount(10_000)
										);
	}
	
	@Override
	public String toString(){
		return "Trying to retrieve: " + fiction;
	}

}
