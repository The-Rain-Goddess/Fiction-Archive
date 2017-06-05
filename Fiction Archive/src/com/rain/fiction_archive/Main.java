/**
 * 
 */
package com.rain.fiction_archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.rain.fiction_archive.files.Fandom;
import com.rain.fiction_archive.files.FandomAttributes;
import com.rain.fiction_archive.files.Fiction;
import com.rain.fiction_archive.files.FictionAttributes;
import com.rain.fiction_archive.gui.MainWindow;

/**
 * @author Ryan May
 *
 */
public class Main {
	private static final String home = "C:\\Fiction\\";
	private static final Map<Long, Fandom> masterArchiveData = Collections.synchronizedMap(new TreeMap<Long, Fandom>());
	private static long UUID = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			createHomeDirectory();
			loadArchiveData();
			
			Fandom f1 = new Fandom(new FandomAttributes()
											.setName("Clexa")
											.setPath("C:\\Fandom\\Clexa.data"),
											getNextUUID() );
			f1.setFictions(Arrays.asList(new Fiction(new FictionAttributes()
																.setName("Hedatu")
																.setAuthor("Red Hope")),
										 new Fiction(new FictionAttributes()
												 				.setName("Before Words, Beyond Silence")
												 				.setAuthor("natkate"))));
			masterArchiveData.put(f1.getUUID(), f1);
			
			Fandom f2 = new Fandom(new FandomAttributes()
					.setName("Bellice")
					.setPath("C:\\Fandom\\Bellice.data"),
					getNextUUID() );
			masterArchiveData.put(f2.getUUID(), f2);
			
			MainWindow app = new MainWindow();
			app.begin(args);
			
		} catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}

//private mutators	
	private static void createHomeDirectory(){
		File homeDir = new File(home + "Fandom\\");
		if (!homeDir.exists())
			homeDir.mkdirs();
	}
	
	private static void loadArchiveData() throws FileNotFoundException, IOException, ClassNotFoundException{
		List<File> files = getFilesToLoad();
		if(files!=null){	
			for (File file : files) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				Fandom fandom = (Fandom) ois.readObject();
				fandom.setUUID(getNextUUID());
				System.out.println("New media read from disk: \n" + fandom);
				masterArchiveData.put(fandom.getUUID(), fandom);
				ois.close();
			}
		}
	}
	
	private static List<File> getFilesToLoad(){
		File mainDirectory = new File(home + "Fandoms\\");
		File[] files = mainDirectory.listFiles();
		if(files!=null){
			List<File> fandomDir = Arrays.asList(files);
			List<File> allData = new ArrayList<>();
			for (int k = 0; k < fandomDir.size(); k++) {
				if(fandomDir.get(k).getAbsolutePath().contains(".data"))
					allData.add(fandomDir.get(k));
			} System.out.println("Files to be read into ram : \n" + allData); 
			return allData; 
		} else{
			return new ArrayList<File>();
		}
	}
	
//non-private accessors / mutators	
	public static Map<Long, Fandom> getMasterArchiveDataMap(){
		return masterArchiveData;
	}
	
	/**
	 * Returns the Master Archive Data map as a list; a synchronized List<@link com.rain.fiction_archive.files.Fandom>.
	 * 
	 * @return theMasterArchiveData
	 */
	public static List<Fandom> getMasterDataAsList() {
		List<Fandom> list = new ArrayList<>();
		for (Map.Entry<Long, Fandom> entry : masterArchiveData.entrySet()) {
			list.add(entry.getValue());
		} return list;
	}
	
	public static long getNextUUID(){
		return ++UUID;
	}
	
	public static String getHomeDir(){
		return home;
	}

}
