/**
 * 
 */
package com.nonesole.example6;

import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource; 

/**
 * JDBC数据
 * @author jack
 */
public class JDBCInfo {
	
	private static final String MYSQL_CONFIG = "?useServerPrepStmts=true&cachePrepStmts=true&prepStmtCacheSize=25&prepStmtCacheSqlLimit=256&useUnicode=true&characterEncoding=utf8";
	
	private static ComboPooledDataSource pooledDataSource = new ComboPooledDataSource(); 
	
	private String jdbcUrl = "";
	private String userName = "";
	private String password = "";
	private String driverClass = "";
	private boolean useC3P0 = false;
	
	
	private static JDBCInfo info;
	static {
		info = new JDBCInfo();
	}
	private JDBCInfo(){
		Properties prop = new Properties();
		try {
			prop.load(JDBCInfo.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.jdbcUrl = prop.getProperty("jdbcUrl");
		this.userName = prop.getProperty("user");
		this.password = prop.getProperty("password");
		this.driverClass = prop.getProperty("driverClass");
		String useC3P0 = prop.getProperty("useC3PO");
		if ( useC3P0 != null && "true".contentEquals(useC3P0) ) {
			this.useC3P0 = true;
		} else {
			this.useC3P0 = false;
		}
		//System.out.println(jdbcUrl);
	}
	
	public static JDBCInfo getInstance(){
		return info;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		if ( this.useC3P0 ) {
			return pooledDataSource.getConnection(); 
		} else {
			//System.out.println(this.getDriverClass());
			Class.forName(this.getDriverClass());
			return DriverManager.getConnection(
					this.getJdbcUrl(),
					this.getUserName(), 
					this.getPassword());
		}
	}

	/**
	 * 数据库地址
	 * @return
	 */
	public String getJdbcUrl() {
		//System.out.println(jdbcUrl);
		return jdbcUrl;
	}

	/**
	 * 用户名
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 驱动
	 * @return
	 */
	public String getDriverClass() {
		return driverClass;
	}
	
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	/**
	 * 获取带有全部配置的config
	 * @return
	 */
	public String addConfig2jdbcUrl(){
		return cutStringEnd(this.getJdbcUrl(), "?") + MYSQL_CONFIG;
	}
	
	/**
	 * 将内存中的配置信息写入jdbc.properties和c3p0.properties文件中
	 * 并将数据源文件的设置修改为true
	 * @throws Exception 
	 */
	public void saveJDBCConfig() throws Exception{
		///保存属性到jdbc.properties文件
		//true表示追加打开，false重新生成
		FileOutputStream oFile = new FileOutputStream(
				JDBCInfo.class.getClassLoader().getResource("jdbc.properties").getPath(), 
				false);
		Properties prop = new Properties();
		prop.setProperty("driverClass", this.getDriverClass());
		prop.setProperty("user", this.getUserName());
		prop.setProperty("password", this.getPassword());
		prop.setProperty("useC3PO", "true");
		prop.setProperty("jdbcUrl", this.addConfig2jdbcUrl());
		prop.store(oFile, "must use " + MYSQL_CONFIG);	
		
		//System.out.println("saveJDBCConfig");
		oFile.close();
	}
	
	/**
	 * 将内存中的配置信息写入jdbc.properties和c3p0.properties文件中
	 * 并将数据源文件的设置修改为true
	 * @throws Exception 
	 */
	public void saveC3P0Config() throws Exception{
		///保存属性到jdbc.properties文件
		//true表示追加打开，false重新生成
		FileOutputStream oFile = new FileOutputStream(
				JDBCInfo.class.getClassLoader().getResource("c3p0.properties").getPath(), 
				false);
		Properties prop = new Properties();
		prop.setProperty("c3p0.driverClass", this.getDriverClass());
		prop.setProperty("c3p0.user", this.getUserName());
		prop.setProperty("c3p0.password", this.getPassword());
		prop.setProperty("c3p0.initialPoolSize", "10");
		prop.setProperty("c3p0.maxIdleTime", "30");
		prop.setProperty("c3p0.maxPoolSize", "100");
		prop.setProperty("c3p0.minPoolSize", "10");	
		prop.setProperty("c3p0.jdbcUrl", this.addConfig2jdbcUrl());
		prop.store(oFile, "must use " + MYSQL_CONFIG);	
		//System.out.println("saveC3P0Config");
		oFile.close();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JDBCInfo.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 剪切字符串中指定字符后面的字符串，将前面的字符串返回
	 * @param input
	 * @param keyword
	 * @return
	 */
	public static String cutStringEnd(String input,String keyword){
		if ( input == null || keyword == null ) {
			return "";
		}
		if ( input.indexOf(keyword) < 0 ) {
			return input;
		}
		return input.substring(0, input.indexOf(keyword));
	}
	
}