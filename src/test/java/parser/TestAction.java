package parser;

import org.junit.Assert;

import parser.helper.IAfterParsingAction;
import parser.helper.IParserResult;

public class TestAction implements IAfterParsingAction {

	public void start(IParserResult result) {
		Assert.assertEquals(((TestParserResult)result).getResult(), "test");

	}

}
