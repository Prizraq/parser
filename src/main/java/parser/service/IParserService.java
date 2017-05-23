package parser.service;

import parser.helper.Task;

public interface IParserService {

	public void start();
	
	public void addTask(Task task);
	
	public void shutdownExecutor();
}
