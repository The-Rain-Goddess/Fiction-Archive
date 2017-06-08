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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.rain.fiction_archive.files.Fandom;
import com.rain.fiction_archive.gui.MainWindow;

/**
 * @author Ryan May
 *
 */
public class Main {
	private static final String home = "C:\\Fiction\\";
	private static final Map<Long, Fandom> masterArchiveData = Collections.synchronizedMap(new TreeMap<Long, Fandom>());
	private static long UUID = 0;
	private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			createHomeDirectory();
			readFromDisk();
			
			/*
			Fandom f1 = new Fandom(new FandomAttributes()
											.setName("Clexa")
											.setPath("C:\\Fandom\\Clexa.data"),
											getNextUUID() );
			f1.setFictions(Arrays.asList(new Fiction(new FictionAttributes()
																.setName("Hedatu")
																.setAuthor("Red Hope")
																.setWordCount(100000)),
										 new Fiction(new FictionAttributes()
												 				.setName("Before Words, Beyond Silence")
												 				.setAuthor("natkate")
										 						.setWordCount(389000))
										 ));
			masterArchiveData.put(f1.getUUID(), f1);
			
			Fandom f2 = new Fandom(new FandomAttributes()
					.setName("Bellice")
					.setPath("C:\\Fandom\\Bellice.data"),
					getNextUUID() );
			masterArchiveData.put(f2.getUUID(), f2); */
			
			MainWindow app = new MainWindow();
			app.begin(args);
			
			writeToDisk();
			
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
	
	private static void writeToDisk() throws IOException{
		List<Fandom> list = getMasterDataAsList();
		for(int i = 0; i<list.size(); i++){
			list.get(i).writeToDisk();
		}
	}
	
	private static void readFromDisk() throws FileNotFoundException, ClassNotFoundException, IOException{
		loadArchiveData();
	}
	
	private static void loadArchiveData() throws FileNotFoundException, IOException, ClassNotFoundException{
		List<File> files = getFilesToLoad();
		if(files!=null){	
			for (File file : files) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				Fandom fandom = (Fandom) ois.readObject();
				fandom.setUUID(getNextUUID());
				System.out.println("New fandom read from disk: \n" + fandom);
				if(fandom.getFictions()==null)
					fandom.setFictions(new ArrayList<>());
				masterArchiveData.put(fandom.getUUID(), fandom);
				ois.close();
				file.delete();
			}
		}
	}
	
	private static List<File> getFilesToLoad(){
		File mainDirectory = new File(home);
		File[] files = mainDirectory.listFiles();
		//System.out.println(files);
		if(files!=null){
			List<File> mainDir = Arrays.asList(files);
			List<File> allData = new ArrayList<>();
			for (int k = 0; k < mainDir.size(); k++) {
				if(mainDir.get(k).isDirectory()){
					List<File> fandomDir = Arrays.asList(mainDir.get(k).listFiles());
					for(int i = 0; i<fandomDir.size(); i++){
						if(fandomDir.get(i).getAbsolutePath().contains(".data"))
							allData.add(fandomDir.get(i));
					}
				}
				
			} System.out.println("Files to be read from disk into ram : \n" + allData + "\n"); 
			return allData; 
		} else{
			return new ArrayList<File>();
		}
	}
	
//non-private accessors / mutators	
	public static ExecutorService getThreadPool(){ return threadPool; }
	
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
