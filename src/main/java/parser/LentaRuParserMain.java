package parser;

import java.util.Arrays;

import parser.downloader.impl.PageDownloader;
import parser.helper.EmptyAfterParsingAction;
import parser.helper.Task;
import parser.parsers.impl.LentaRuParser;
import parser.service.IParserService;
import parser.service.impl.AbstractParserService;

public class LentaRuParserMain {

	public static void main(String[] args) {
		IParserService service = new AbstractParserService(1, Arrays.asList(new Task("https://lenta.ru", new LentaRuParser(), new PageDownloader(PageDownloader.UTF8, null, null), new EmptyAfterParsingAction())));
		service.start();
	}

}
