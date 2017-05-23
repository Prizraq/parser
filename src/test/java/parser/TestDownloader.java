package parser;

import parser.downloader.IDownloader;

public class TestDownloader implements IDownloader {

	public String downloadPage(String url) {
		return "test";
	}

}
