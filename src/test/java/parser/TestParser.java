package parser;

import org.junit.Assert;

import parser.helper.IParserResult;
import parser.parsers.IParser;

public class TestParser implements IParser {

	public IParserResult parse(String page) {
		Assert.assertEquals(page, "test");
		return new TestParserResult("test");
	}

}
