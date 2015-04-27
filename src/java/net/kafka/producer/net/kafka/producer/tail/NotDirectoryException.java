package net.kafka.producer.tail;

public class NotDirectoryException extends Exception{

	public NotDirectoryException() {
		// TODO Auto-generated constructor stub
		super("the monitored root dir not a Driectory!!");
	}

}
