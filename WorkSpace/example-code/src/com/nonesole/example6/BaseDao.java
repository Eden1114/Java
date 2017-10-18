/**
 * 
 */
package com.nonesole.example6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * @author jack
 */
public class BaseDao {
		
	public static Integer INT = 0;
	
	public static Integer VARCHAR = 1;
	
	public static Integer BIGINT = 2;
		
	/**
	 * 获得Connection对象
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws Exception { 
		return JDBCInfo.getInstance().getConnection();
	} 
	
	/**
	 * 预编译执行SQL
	 * @param sql
	 * @throws Exception
	 */
	public static void preparedExecute(String sql,PreparedMap<Integer,Object> params) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BaseDao.getConnection();
			pstmt = conn.prepareStatement(sql); 
			if ( params != null ) {
				Integer intValue ;
				Long longValue;
				String strValue;
				List<Integer> keys = params.keys();
				int key;
				Object value;
				int seq;
				for ( int i = 0 ; i < keys.size() ; i++ ) {
					key = keys.get(i);
					value = params.getValue(i);
					seq = i + 1;
					//System.out.println(i+","+ key +","+value);
					if ( key == BaseDao.INT ) {
						intValue = value==null||"".equals(value)?0:Integer.valueOf(value.toString());	
						pstmt.setInt(seq, intValue);
					} else if ( key == BaseDao.VARCHAR ) {
						strValue = value==null?"":String.valueOf(value);
						pstmt.setString(seq, strValue);
					} else if ( key == BaseDao.BIGINT ) {
						longValue = value==null||"".equals(value)?0:Long.valueOf(value.toString());
						pstmt.setLong(seq, longValue);
					} 
				}
			}
			
			pstmt.executeUpdate(); 
			if ( !conn.getAutoCommit() )
				conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			if ( null != conn ) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 预编译执行SQL，返回查询结果
	 * @param sql
	 * @throws Exception
	 */
	public static ResultSet preparedQuery(String sql,PreparedMap<Integer,Object> params) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BaseDao.getConnection();
			pstmt = conn.prepareStatement(sql); 
			if ( params != null ) {
				Integer intValue ;
				Long longValue;
				String strValue;
				List<Integer> keys = params.keys();
				int key;
				Object value;
				int seq;
				for ( int i = 0 ; i < keys.size() ; i++ ) {
					key = keys.get(i);
					value = params.getValue(i);
					seq = i + 1;
					//System.out.println(i+","+ key +","+value);
					if ( key == BaseDao.INT ) {
						intValue = value==null?0:(Integer)value;	
						pstmt.setInt(seq, intValue);
					} else if ( key == BaseDao.VARCHAR ) {
						strValue = value==null?"":String.valueOf(value);
						pstmt.setString(seq, strValue);
					} else if ( key == BaseDao.BIGINT ) {
						longValue = value==null?0:(Long)value;
						pstmt.setLong(seq, longValue);
					} 
				}
			}
			return pstmt.executeQuery(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	/**
	 * 执行不带返回值的SQL，即执行非查询的SQL
	 * @param sql
	 * @throws Exception
	 */
	public static void executeNotAutoCommit(Connection conn,String sql) throws Exception{
		conn = BaseDao.getConnection();
		conn.setAutoCommit(false);
		conn.createStatement().execute(sql);
	}
	
	/**
	 * 执行不带返回值的SQL，即执行非查询的SQL
	 * @param sql
	 * @throws Exception
	 */
	public static void execute(Connection conn,String sql) throws Exception{
		conn = BaseDao.getConnection();
		conn.createStatement().execute(sql);
		if ( !conn.getAutoCommit() ){
			conn.commit();
		}
	}
	
	/**
	 * 执行不带返回值的SQL，即执行非查询的SQL
	 * @param sql
	 * @throws Exception
	 */
	public static void execute(String sql) throws Exception{
		Connection conn = null;
		try {
			conn = BaseDao.getConnection();
			conn.createStatement().execute(sql);
			if ( !conn.getAutoCommit() )
				conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( null != conn ) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 查询数据，需手动关闭rs和connection，可以调用release方法完成
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static ResultSet query(String sql) throws Exception{
		return BaseDao.getConnection().createStatement().executeQuery(sql);
	}
	
	/**
	 * 关闭rs和connection
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static void release(ResultSet rs) throws Exception{
		if ( rs != null ) {
			Connection conn = rs.getStatement().getConnection();
			rs.close();
			conn.close();
			rs = null;
		}
	}
	
	public static void main(String[] args) throws Exception{
//		String sql = "select * from home_user";
//		try {
//			ResultSet rs = BaseDao.query(sql);
//			System.out.println(rs.next());
//			BaseDao.release(rs);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		sql = "update home_user set pwd = ? where id = ?";
//		PreparedMap<Integer,Object> params = new PreparedMap<Integer,Object>();
//		params.put(BaseDao.VARCHAR, "100");
//		params.put(BaseDao.INT, 1);
//		BaseDao.preparedExecute(sql, params);
		
	}
	
}
