package parser.service.impl;

import parser.helper.IParserResult;
import parser.helper.Task;

public class ParserRunnable implements Runnable {

	private Task task;
	
	public ParserRunnable(Task task) {
		this.task = task;
	}

	public void run() {
		try
		{
			String page = task.getDownloader().downloadPage(task.getUrl());
			IParserResult result = task.getParser().parse(page);
			task.getAction().start(result);
		}
		catch(Exception e)
		{
			System.err.println("Can't parse page " + task.getUrl());
		}
	}

}
