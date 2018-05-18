package cn.itcast.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	// 定义一个c3p0连接池
	private static DataSource dataSource = new ComboPooledDataSource();

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	// 直接获取一个c3p0连接池
	public static DataSource getDataSource() {
		return dataSource;
	}

	// 从连接池中获取一个连接对象
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	// 获取当前ThreadLocal中的连接对象
	public static Connection getCurrentConnection() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}
		return con;
	}
	
	//事务
	//开启事务
	public static void startTransaction() throws SQLException {
		Connection con= getCurrentConnection();
		if(con!=null) {
			con.setAutoCommit(false);
		}
	}
	//事务回滚
	public static void rollback() throws SQLException {
		Connection con= getCurrentConnection();
		if(con!=null) {
			con.rollback();
		}
	}
	//事务提交，释放资源，移除线程绑定
	public static void commitAndRelease() throws SQLException {
		Connection con= getCurrentConnection();
		if(con!=null) {
			con.commit();
			con.close();
			tl.remove();
		}
	}
	
	//释放资源
	public static void closeConnection() throws SQLException {
		Connection con= getCurrentConnection();
		if(con!= null) {
			con.close();
		}
	}
	public static void closeStatement(Statement st) throws SQLException {
		if(st!= null) {
			st.close();
		}
	}
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if(rs!=null) {
			rs.close();
		}
	}
}
