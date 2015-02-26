package com.erevmax.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	public final static String CONNECTION_URL = "";
	public final static String SERVER_NAME = "";
	public final static String PORT_NUMBER = "";
	public final static String DATABASE_USERNAME = "";
	public final static String DATABASE_PASSWORD = "";
	public final static String DATABASE_NAME = "";
	private static final String selectMethod = "cursor";
	public static Connection con=null;
	public static PreparedStatement pst=null;
	public static ResultSet rs=null;
	public static Statement st=null;


	static {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String rateTigerConnectionUrl() {
		return CONNECTION_URL + SERVER_NAME + ":" + PORT_NUMBER
				+ ";databaseName=" + DATABASE_NAME + ";selectMethod="
				+ selectMethod + ";";

	}


	public static Connection getRateTigerDataBaseConnection() {
		con = null;
		try {
			con = java.sql.DriverManager.getConnection(
					rateTigerConnectionUrl(), DATABASE_USERNAME,
					DATABASE_PASSWORD);
			//if (con != null)
			//	System.out.println("RateTiger database Connection Successful!");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("RateTiger database connection is not successful");
		} 
		return con;
	}

	public static ResultSet executeQuery(String query, Connection con) {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void closeConnection(Connection con, PreparedStatement pst,ResultSet rs) {
		try {
			if (con != null) {
				con.close();
				System.out.println("Closing the connection after test");
			}
			if (pst != null) {
				pst.close();
				System.out.println("Closing the preparedStatement after test");
			}
			if (rs != null) {
				rs.close();
				System.out.println("Closing the resultset after test");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public static void closeConnection(Connection con, Statement st,ResultSet rs) {
		try {
			if (con != null) {
				con.close();
				System.out.println("Closing the connection after test");
			}
			if (rs != null) {
				rs.close();
				System.out.println("Closing the resultset after test");
			}if (st != null) {
				st.close();
				System.out.println("Closing the Statement after test");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void closeConnection(Connection con, Statement st,ResultSet rs,PreparedStatement pst) {
		try {
			if (con != null) {
				con.close();
				System.out.println("Closing the connection after test");

			}if (pst != null) {
				pst.close();
				System.out.println("Closing the preparedStatement after test");
			}
			if (rs != null) {
				rs.close();
				System.out.println("Closing the resultset after test");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}



	//public static final String ARI_LogsDB="jdbc:sqlserver://176.74.173.38;databaseName=RTMONITOR; username=rtctlog; password=rtctlog";


	public Connection getDBConnection(String dbName){
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = java.sql.DriverManager.getConnection(dbName);
			return con;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}



	public void executeUpdateDBQuery(String query, String dbName){
		Connection con =getDBConnection(dbName);
		Statement statement = null;
		try {
			con.setAutoCommit(true);
			int i = 0;
			//Thread.sleep(2000);
			statement = con.createStatement();
			//System.out.println(query);
			i=statement.executeUpdate(query);
			//System.out.println(i);
			if (i==0){
				System.out.println("Nothing is been updated on database");
			}
			else if (i>0){
				System.out.println("Updated "+i+" row");
			}
		} catch (Exception e) {
			System.out.println("Error in the query OR DB connection....Please check intput query & DB connection string");
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Closing error");
				e.printStackTrace();
			}
		}
	}

	public void executeBatchUpdateDBQuery(String dbName, String... query){
		Connection con =getDBConnection(dbName);
		Statement statement = null;
		try {
			con.setAutoCommit(false);
			statement = con.createStatement();
			for(String quer:query){
				statement.addBatch(quer);
			}
			statement.executeBatch();
			con.commit();
			System.out.println("Batch of sql queries updated Successfully");
		} catch (Exception e) {
			System.out.println("Error in the query OR DB connection....Please check intput query & DB connection string");
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Closing error");
				e.printStackTrace();
			}
		}
	}

	public ResultSet executeQuery(String query, String dbName) {
		Connection con =getDBConnection(dbName);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
