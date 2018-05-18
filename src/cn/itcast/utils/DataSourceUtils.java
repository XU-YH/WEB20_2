package cn.itcast.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	// ����һ��c3p0���ӳ�
	private static DataSource dataSource = new ComboPooledDataSource();

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	// ֱ�ӻ�ȡһ��c3p0���ӳ�
	public static DataSource getDataSource() {
		return dataSource;
	}

	// �����ӳ��л�ȡһ�����Ӷ���
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	// ��ȡ��ǰThreadLocal�е����Ӷ���
	public static Connection getCurrentConnection() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}
		return con;
	}
	
	//����
	//��������
	public static void startTransaction() throws SQLException {
		Connection con= getCurrentConnection();
		if(con!=null) {
			con.setAutoCommit(false);
		}
	}
	//����ع�
	public static void rollback() throws SQLException {
		Connection con= getCurrentConnection();
		if(con!=null) {
			con.rollback();
		}
	}
	//�����ύ���ͷ���Դ���Ƴ��̰߳�
	public static void commitAndRelease() throws SQLException {
		Connection con= getCurrentConnection();
		if(con!=null) {
			con.commit();
			con.close();
			tl.remove();
		}
	}
	
	//�ͷ���Դ
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
