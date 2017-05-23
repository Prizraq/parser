package parser.database;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.JdbcUtils;
import parser.helper.IParserResult;

public class AbstractDao {
	
	private Connection conn;
	private JdbcUtils jdbcUtils;
	private String tableName;

	public AbstractDao(Connection conn) {
		jdbcUtils = new JdbcUtils(conn);
	}
	
	public AbstractDao(String dbPath) throws IOException, SQLException {
		if (!dbPath.isEmpty())
			jdbcUtils = new JdbcUtils(dbPath);
	}
	
	public void addInDb(IParserResult result)
	{
		String sql = null;
		List<Object> params = new ArrayList<Object>();  
		try
		{
			Class<? extends IParserResult> c = result.getClass();
			StringBuilder sb = new StringBuilder();
			sb.append("INSER INTO ");

			if (tableName != null)
			{
				sb.append(tableName);
			}
			else
			{
				sb.append(c.getSimpleName());
			}
			
			sb.append(" (");
			
			for (int i=0; i<c.getDeclaredFields().length; i++)
			{
				Field field = c.getDeclaredFields()[i];
				sb.append(field.getName());
				field.setAccessible(true);
				params.add(field.get(result));
				if (i<c.getDeclaredFields().length-1)
				{
					sb.append(", ");
				}
			}
			sb.append(") VALUES (");
			
			for (int i=0; i<c.getDeclaredFields().length; i++)
			{
				sb.append("?");
				if (i<c.getDeclaredFields().length-1)
				{
					sb.append(", ");
				}
			}
			sb.append(");");
			sql = sb.toString();
			System.out.println(sql);
			System.out.println(params);
			jdbcUtils.update(sql, params.toArray());
		}
		catch (Exception e)
		{
			System.err.println("Can't add in database " + sql);
			System.err.println(e);
		}
	}

	public Connection getConn() {
		return conn;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
