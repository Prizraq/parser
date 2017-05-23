package parser.helper;

import java.io.IOException;
import java.sql.SQLException;

import parser.database.AbstractDao;

public class SaveInDbAction implements IAfterParsingAction {

	private AbstractDao dao;
	
	public SaveInDbAction(String dbPath) throws IOException, SQLException {
		dao = new AbstractDao(dbPath);
	}

	public void start(IParserResult result) {
		dao.addInDb(result);
	}

}
