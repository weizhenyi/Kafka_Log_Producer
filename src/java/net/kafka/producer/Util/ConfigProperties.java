package Util;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
public class ConfigProperties extends Properties{
	
private static final long serialVersionUID = -1851097272122935390L;
	
	public static final String CONFIG_NAME_MAPRED_QUEUE_NAME = "mapred.job.queue.name";
	public static final String CONFIG_NAME_HBASE_MASTER = "hbase.master";
	public static final String CONFIG_NAME_HBASE_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
	public static final String KAFKA_CONFIG_PATH = "/kafka.properties";
	public static final String LOG4J_CONFIG_PATH = "/log4j.properties";
	/**
	 * @Constructor
	 * @param filePath is the class path to the config file
	 */
	protected ConfigProperties(final String filePath) {
		final InputStream inStream = ConfigProperties.class.getResourceAsStream(filePath);
		try {
			load(inStream);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new NullPointerException("Failed to load config file: " +
					filePath + ", error: " + e.getMessage());
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (final IOException e) {
					// do nothing
				}
			}

		}
	}

	public int getInt(final String propertyName, final int defaultValue) {
		int propertyValue = defaultValue;

		final String valueStr = getProperty(propertyName);
		try {
			propertyValue = Integer.parseInt(valueStr);
		} catch (final Exception e) {
			// do nothing, just return the default value;
		}

		return propertyValue;
	}

	public float getFloat(final String propertyName, final float defaultValue) {
		float propertyValue = defaultValue;

		final String valueStr = getProperty(propertyName);
		try {
			propertyValue = Float.parseFloat(valueStr);
		} catch (final Exception e) {
			// do nothing, just return the default value;
		}

		return propertyValue;
	}

	public double getDouble(final String propertyName, final double defaultValue) {
		double propertyValue = defaultValue;

		final String valueStr = getProperty(propertyName);
		try {
			propertyValue = Double.parseDouble(valueStr);
		} catch (final Exception e) {
			// do nothing, just return the default value;
		}

		return propertyValue;
	}
	
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigProperties config = new ConfigProperties(KAFKA_CONFIG_PATH);
		//config.getDouble(propertyName, "1.0");
		int runtime = config.getInt("runtime", -1);
		String zkConnect = config.getProperty("zkconnect");
	}*/

<<<<<<< HEAD
	public static void sayHello()
	{
		system.out.println("user2 add code1");
=======
	public static void main(String[] args)
	{
		System.out.println("user1 commit code2");
>>>>>>> 47af3eba6011af3c4143d710bde46c6b5ee6b614
	}

}
