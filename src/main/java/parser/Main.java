package parser;

import parser.downloader.IDownloader;
import parser.downloader.impl.PageDownloader;

public class Main {

	public static void main(String[] args) {
		IDownloader downloader = new PageDownloader(PageDownloader.UTF8, null, null);
		System.out.println(downloader.downloadPage("https://www.yandex.ru"));
	}

}
