package net.kafka.producer.tail;

import java.util.Iterator;
import java.util.Properties;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import Util.ConfigProperties;
public class DirectoryMonitor implements Runnable{
	
	private Set<File> subDirectorys;
	private File baseDirectory;
	private Properties p;
	private int type; // 1:one logfile to one topic; 2:one logfile to multitopic
	private String topic;
	private Map<String, String> topicMapping;
	private Set<String> doingFiles;
	private long modifyInterval = 60000000L;// 10∑÷÷”
	
	
	
	String[] filenamefilters;
	public DirectoryMonitor(Properties prop) throws Exception{
		// TODO Auto-generated constructor stub
		this.p = prop;
		this.baseDirectory = new File(this.p.getProperty("baseDirectory"));
		if(!this.baseDirectory.isDirectory())
		{
			throw new RuntimeException(" Monitor path must be a Directory");
		}
		this.subDirectorys = new HashSet<File>();
		this.doingFiles = new HashSet<String>();
		this.type = Integer.valueOf(this.p.getProperty("type"));
		if (this.type == 1) 
		{
			this.topic = this.p.getProperty("topic");
		}
		this.filenamefilters = this.p.getProperty("filenamefilters").split(",");
	}
	
	
	private void createTailer(String filename) {
		if (this.type == 1) {
			@SuppressWarnings("unused")
			TailProducer tailer = new TailProducer(filename,
					this.p.getProperty("zkconnect"), this.topic,
					Integer.valueOf(this.p.getProperty("runtime")),
					Long.valueOf(this.p.getProperty("interval")));
			// this.tailerSet.add(tailer);
		} else if (this.type == 2) {
			/*@SuppressWarnings("unused")
			MutliTailProducer mtailer = new MutliTailProducer(filename,
					this.p.getProperty("zkconnect"), this.topicMapping,
					Integer.valueOf(this.p.getProperty("runtime")),
					Long.valueOf(this.p.getProperty("interval")));
			// this.tailerSet.add(mtailer);
*/		}
	}
	
	private boolean fileNameMatches(String filename) 
	{
		//return true;
		 if (this.filenamefilters.length == 0) 
		 {
			 return true;
		 }
		 
		 for (String filter : this.filenamefilters)
		 {
			 if (filter.length() == 0)
			 {
				 return true;
			 }
			 if (filename.matches(filter))
			 {
				 return true;
			 }
		 }
		 return false;
	}
	
	private void createTailerFromDir(File file) throws NotDirectoryException
	{
		if(file.isDirectory())
		{
			for(File f : file.listFiles())
			{
				//boolean b = this.doingFiles.contains(f);
				
				if(f.isFile() && !this.doingFiles.contains(f.toString()) && fileNameMatches(f.getName()))
				{
					long lastModify = f.lastModified();
					long curtime = System.currentTimeMillis();
					if (curtime - lastModify < modifyInterval) { // long modifyInterval = 600000L;
						
						this.createTailer(f.getAbsolutePath());
						this.doingFiles.add(f.getAbsolutePath());
					}
				}
				else if(f.isDirectory() && !this.subDirectorys.contains(f))
				{
					this.subDirectorys.add(f);
					createTailerFromDir(f);	
				}
			}
		}
		else 
		{
			throw new NotDirectoryException();
		}
	}
	
	
	@Override
	public void run() 
	{
		//long modifyInterval = 600000L;// 1hour
		/*Set<File> tmp = new HashSet<File>();
		Set<File> deleted = new HashSet<File>();*/
		while (true) {
			try {

				TimeUnit.MILLISECONDS.sleep(1000);
				
				createTailerFromDir(this.baseDirectory);
				/*for (File f : this.baseDirectory.listFiles()) {
					if (f.isDirectory()) 
					{
						if (!this.subDirectorys.contains(f)) {
							this.subDirectorys.add(f);
						}
					} 
					else
					{
						if (f.isFile()) {
							if (!this.doingFiles.contains(f.getAbsolutePath())) {
								long lastModify = f.lastModified();
								long curtime = System.currentTimeMillis();
								if (curtime - lastModify < modifyInterval) { // long modifyInterval = 600000L;
									
									this.createTailer(f.getAbsolutePath());
									this.doingFiles.add(f.getAbsolutePath());
								}
							}
						}
					}
				}

				for (File f : this.subDirectorys) {
					if (!f.exists()) {
						deleted.add(f);
						continue;
					}
					for (File sf : f.listFiles()) {
						if (sf.isDirectory()) {
							if (!this.subDirectorys.contains(sf)) {
								// this.subDirectorys.add(sf);
								tmp.add(sf);
							}
						}
					}
				}
				this.subDirectorys.removeAll(deleted);
				this.subDirectorys.addAll(tmp);
				tmp.clear();
				deleted.clear();

				for (File f : this.subDirectorys) {
					for (File sf : f.listFiles()) {
						if (sf.isFile() && this.fileNameMatches(sf.getName())) {
							if (!this.doingFiles.contains(sf.getAbsolutePath())) {
								long lastModify = sf.lastModified();
								long curtime = System.currentTimeMillis();
								if (curtime - lastModify < modifyInterval) {
									this.createTailer(sf.getAbsolutePath());
									this.doingFiles.add(sf.getAbsolutePath());				
									
								}
							}
						}
					}
				}*/

			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			} 
			catch (NotDirectoryException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.exit(-1);
			} 
		}
	}


	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
*/
}
