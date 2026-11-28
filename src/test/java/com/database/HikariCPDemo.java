package com.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {
	public static void main(String[] args) throws SQLException {
		
		HikariConfig hikariconfig= new HikariConfig();
		hikariconfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariconfig.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
		hikariconfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariconfig.setMaximumPoolSize(10);
		hikariconfig.setMinimumIdle(2);
		hikariconfig.setConnectionTimeout(10000);
		hikariconfig.setIdleTimeout(10000);
		hikariconfig.setMaxLifetime(180000);
		hikariconfig.setPoolName("Phoenix Test Automation Framework Pool");
		
		HikariDataSource ds = new HikariDataSource(hikariconfig);
		Connection conn=ds.getConnection();
		System.out.println(conn);
		
		Statement statement= conn.createStatement();
	ResultSet rs=	statement.executeQuery("SELECT first_name,last_name,mobile_number from tr_customer;");
	while(rs.next()) {
		String first_name=rs.getString("first_name");
		String last_name=rs.getString("last_name");
		String mobile_number=rs.getString("mobile_number");
		System.out.println(first_name+"|"+last_name+"|"+mobile_number);
	}
	ds.close();	
	}
      
}
