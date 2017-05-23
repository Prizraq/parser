package parser.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import parser.helper.Task;
import parser.service.IParserService;

public class AbstractParserService implements IParserService {
	
	private List<Task> tasks;
	private ExecutorService executor;
	
	
	public AbstractParserService(int threadsCount, List<Task> tasks) {
		this.tasks = tasks;
		this.executor = Executors.newFixedThreadPool(threadsCount);
	}

	public void start()
	{
		for (Task task : tasks)
		{
			addTask(task);
		}
	}
	
	public void addTask(Task task)
	{
		executor.execute(new ParserRunnable(task));
	}

	public ExecutorService getExecutor() {
		return executor;
	}
	
	public void shutdownExecutor()
	{
		executor.shutdown();
	}

}
