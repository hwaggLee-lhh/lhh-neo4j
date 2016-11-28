package com.lhh.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Neo4jJdbcUtils {

	// 数据库用户名
	private static String USERNAME = "neo4j";
	// 数据库密码
	private static String PASSWORD = "a123456";
	// 驱动信息
	private static String DRIVER = "org.neo4j.jdbc.Driver";
	public static int JDBCPOOLINITSIZE  = 10;	
	// 数据库地址
	private static String URL = "jdbc:neo4j:http://61.152.154.22:7474";
	
//    static{
//        InputStream in = Neo4jJdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        Properties prop = new Properties();
//        try {
//            prop.load(in);
//            DRIVER = prop.getProperty("neo4j.connection.driver_class");
//            URL = prop.getProperty("neo4j.db.connection.url");
//            USERNAME = prop.getProperty("neo4j.db.connection.username");
//            PASSWORD = prop.getProperty("neo4j.db.connection.password");
//            JDBCPOOLINITSIZE =Integer.parseInt(prop.getProperty("neo4j.jdbcPoolInitSize"));//数据库连接池的初始化连接数大小
//            Class.forName(DRIVER);
//        } catch (Exception e) {
//            throw new ExceptionInInitializerError(e);
//        }
//    }
    
	
	public Neo4jJdbcUtils() {
	}
	public Connection getConnection(){
		return getCreateConnection();
	}
	
	private Connection conn;
	/**
	 * 调用这个连接将不允许被关闭
	 * @return
	 */
	public synchronized Connection getFreeConnection(){
		if( conn == null ){ 
			conn = getCreateConnection();
			return conn;
		}
		try {
			if( conn.isClosed() ){
				conn = getCreateConnection();
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Neo4jJdbcUtils(String username, String password, String driver, String url) {
		USERNAME = username;
		PASSWORD = password;
		DRIVER = driver;
		URL = url;
	}

	/**
	 * 获得数据库的连接
	 * 
	 * @return
	 */
	private Connection getCreateConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public static void closeResultSett(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void testJDBC() throws SQLException {
		Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		// Querying
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("MATCH (n) RETURN n");
			while (rs.next()) {
				System.out.println(rs.getString("n"));
			}
		}
	}

	
	public static void main(String[] args) {
		Neo4jJdbcUtils utils = new Neo4jJdbcUtils();
		try {
			utils.testJDBC();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
