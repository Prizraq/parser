package parser;

import parser.helper.IParserResult;

public class TestParserResult implements IParserResult {

	private String result;
	private String result2;

	public TestParserResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}
}
