package parser.helper;

import parser.downloader.IDownloader;
import parser.parsers.IParser;

public class Task {
	
	private String url;
	private IParser parser;
	private IDownloader downloader;
	private IAfterParsingAction action;
	
	public Task() {
	}
	
	public Task(String url, IParser parser, IDownloader downloader, IAfterParsingAction action) {
		this.url = url;
		this.parser = parser;
		this.downloader = downloader;
		this.action = action;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public IParser getParser() {
		return parser;
	}
	
	public void setParser(IParser parser) {
		this.parser = parser;
	}
	
	public IDownloader getDownloader() {
		return downloader;
	}
	
	public void setDownloader(IDownloader downloader) {
		this.downloader = downloader;
	}

	public IAfterParsingAction getAction() {
		return action;
	}

	public void setAction(IAfterParsingAction action) {
		this.action = action;
	}
}
