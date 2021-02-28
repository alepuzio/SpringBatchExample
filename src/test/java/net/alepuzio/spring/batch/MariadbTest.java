package net.alepuzio.spring.batch;

import static org.junit.Assert.assertFalse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MariadbTest {

	private Connection conn = null;
	private Statement st = null;

	@Before
	public void before() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection( MariaDbEnum.DB_URL. value(), MariaDbEnum.USER. value(), MariaDbEnum.PASS. value());
			System.out.println("Connected database successfully...");
		} catch (Exception e) {
			error(e);
		}
	
	}

	@Test
	public void testCountRows() {
		try {
			st = conn.createStatement();
			System.out.println("Create statement successfully...");
			ResultSet rs = null;
			rs = st.executeQuery("SELECT COUNT(*) FROM REPORT");
			rs.next();
			System.out.println(String.format("Read %d rows successfully...", rs.getInt(1)));
		} catch (Exception e) {
			error(e);
		}
	}

	private void error(Exception e) {
		System.err.println(String.format("Problem %s", e.getMessage()));
		e.printStackTrace();
		assertFalse(true);
	}

	@After
	public void after() throws SQLException {
		if (null != st) {
			st.close();
		}
		if (null != conn) {
			conn.close();
		}
	}
}

enum MariaDbEnum{
 JDBC_DRIVER ("org.mariadb.jdbc.Driver"),
 DB_URL ("jdbc:mariadb://127.0.0.1:3306/springbatch"),
 USER ("guest"),
 PASS ( "guest2021");

	private MariaDbEnum(String newValue) {
		this.value = newValue;
	}
	
	private final String value;
	
	public String value(){
		return this.value;
	}
}