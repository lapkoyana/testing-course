package ru.addressbook.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

public class DbConnectionTest {
	@Test
	public void testDbConnection() {
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
		    Statement statement = conn.createStatement();
		    ResultSet rs = statement.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
		    Groups groups = new Groups();
		    while(rs.next()) {
		    	groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
		    		.withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
		    }
		    
		    rs.close();
		    statement.close();
		    conn.close();
		    
		    System.out.println(groups);
		    
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
