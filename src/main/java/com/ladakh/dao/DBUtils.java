package com.ladakh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	
	private static Connection connection;
	
	public static Connection fetchDBConnection() throws ClassNotFoundException, SQLException
	{
		System.out.println("Inside fetchDBConnection ");
	
		//local
//		connection = DriverManager.getConnection("jdbc:postgresql://10.210.0.173:42022/stag_jkoffice_dev","jkoffice", "jkof%87#");
		
		//production
//		connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.30:5433/jkbo_sl_db","jkoffice", "db@jkbo76");
		
		//staging
		connection = DriverManager.getConnection("jdbc:postgresql://192.168.13.126:42022/stag_jkoffice_dev","laservice", "laservice");
		
	    return connection;

	}

}
