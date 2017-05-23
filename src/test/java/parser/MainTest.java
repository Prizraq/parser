package parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Test;

import parser.database.AbstractDao;
import parser.helper.Task;
import parser.service.IParserService;
import parser.service.impl.AbstractParserService;

public class MainTest {
	
	@Test
	public void mainTest()
	{
		Task task = new Task("http://www.google.com", new TestParser(), new TestDownloader(), new TestAction());
		IParserService service = new AbstractParserService(1, Arrays.asList(task));
		service.start();
		service.shutdownExecutor();
	}
	
	@Test
	public void dbTest() throws IOException, SQLException
	{
		AbstractDao dao = new AbstractDao("");
		TestParserResult result = new TestParserResult("result1");
		result.setResult2("result2");
		dao.addInDb(result);
	}

}
