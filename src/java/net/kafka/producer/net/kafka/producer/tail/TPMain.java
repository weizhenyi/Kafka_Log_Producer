package net.kafka.producer.tail;
import Util.ConfigProperties;
import Util.ConfigFactory;

public class TPMain {

	public TPMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ConfigProperties configProp = ConfigFactory.getInstance()
				.getConfigProperties(ConfigFactory.LOG4J_CONFIG_PATH);
		
		configProp = ConfigFactory.getInstance().getConfigProperties(
				ConfigFactory.KAFKA_CONFIG_PATH);
		
		DirectoryMonitor dm = new DirectoryMonitor(configProp);
		Thread t = new Thread(dm);
		t.start();
		
		
	}

}
