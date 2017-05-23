package parser.parsers.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.helper.IParserResult;
import parser.parsers.IParser;

public class LentaRuParser implements IParser {

	public IParserResult parse(String page) {
		LentaRuParserResult result = new LentaRuParserResult();
		Pattern p = Pattern.compile("<a href=\"/news/([^\"]+)\"><time[^>]+>(.*?)</time>([^\"]+)</a>");
		Matcher m = p.matcher(page);
		if(m.find())
		{
			result.setName(m.group(3));
			result.setLink(m.group(1));
		}
		return result;
	}

}
