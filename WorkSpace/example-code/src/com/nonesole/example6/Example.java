/**
 * 
 */
package com.nonesole.example6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;

/** 
 * 本示例中包含下列内容：<br>
 * JDBC：主键、外键、级联删除、联合主键、表空间、建表、建字段，SQL脚本导入与导出、DML、DQL、预编译、非预编译、数据集、增删改查、批量操作、Blob与Clob、数据源和连接池
 * @author jack lee
 * @version 1.0
 */
public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserAction.dropTable();
		UserAction.createTable();
		IUser action = new UserAction();
		
		User user = new User();
		user.setAge(19);
		user.setMale(true);
		user.setName("Jerry");
		
		try {
			//新增对象
			action.add(user);
			
			User u = action.find(1);
			System.out.println(u.toString());
			
			//修改对象
			user.setId(1);
			user.setAge(20);
			action.update(user);
			
			u = action.find(1);
			System.out.println(u.toString());
			
			//删除对象
			action.delete(u);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

/**
 * User业务接口
 * @author jack
 *
 */
interface IUser {
	/**
	 * 新增用户
	 * @param u
	 * @throws Exception
	 */
	public void add(User u) throws Exception;
	
	/**
	 * 修改用户
	 * @param u
	 * @throws Exception
	 */
	public void update(User u) throws Exception;
	
	/**
	 * 删除用户
	 * @param u
	 * @throws Exception
	 */
	public void delete(User u) throws Exception;
	
	/**
	 * 检索用户
	 * @param id
	 * @throws Exception
	 */
	public User find(int id) throws Exception;
}

/**
 * User业务实现类
 * @author jack
 *
 */
class UserAction implements IUser {

	/**
	 * 是否使用预编译Action
	 */
	public static boolean PREPARED = true;
	
	public static void createTable(){
		URL url = Example.class.getClassLoader().getResource("create_table.sql");
		try {
			String content = loadFileToString(url.getFile(), "UTF-8");
			System.out.println(content);
			Connection conn = BaseDao.getConnection();
			conn.createStatement().execute(content);
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void dropTable(){
		URL url = Example.class.getClassLoader().getResource("drop_table.sql");
		try {
			String content = loadFileToString(url.getFile(), "UTF-8");
			System.out.println(content);
			Connection conn = BaseDao.getConnection();
			conn.createStatement().execute(content);
			conn.close();
			conn = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void add(User u) throws Exception {
		String sql = "insert into test_user(age,name,male) values("+ u.getAge() +",'"+ u.getName() +"',"+ (u.isMale()?"1":"0") +")";
		String preparedSql = "insert into test_user(age,name,male) values(?,?,?)";
		PreparedMap<Integer,Object> params = new PreparedMap<Integer,Object>();
		params.put(BaseDao.INT, u.getAge());
		params.put(BaseDao.VARCHAR, u.getName());
		params.put(BaseDao.INT, u.isMale()?"1":"0");
		if ( PREPARED ) {
			this.execute(preparedSql, params);
		} else {
			this.execute(sql, null);
		}
		
	}

	@Override
	public void update(User u) throws Exception {
		String sql = null;
		PreparedMap<Integer,Object> params = null;
		if ( PREPARED ) {
			sql = "update test_user set age=?,name=?,male=? where id=?";
			params = new PreparedMap<Integer,Object>();
			params.put(BaseDao.INT, u.getAge());
			params.put(BaseDao.VARCHAR, u.getName());
			params.put(BaseDao.INT, u.isMale()?"1":"0");
			params.put(BaseDao.INT, u.getId());
		} else {
			sql = "update test_user set age="+ u.getAge() +",name='"+ u.getName() +"',male="+ (u.isMale()?"1":"0") + " where id="+u.getId();
		}
		this.execute(sql, params);
	}

	@Override
	public void delete(User u) throws Exception {
		String sql = null;
		PreparedMap<Integer,Object> params = null;
		if ( PREPARED ) {
			sql = "delete from test_user where id=?";
			params = new PreparedMap<Integer,Object>();
			params.put(BaseDao.INT, u.getId());
		} else {
			sql = "delete from test_user where id="+u.getId();
		}
		this.execute(sql, params);
		
	}

	@Override
	public User find(int id) throws Exception {
		String sql = "select * from test_user where id="+id;
		ResultSet rs = BaseDao.query(sql);
		User user = null;
		while ( rs.next() ) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setAge(rs.getInt("age"));
			user.setMale(rs.getInt("male")==1?true:false);
			System.out.println("ResultSet = " + user.toString());
		}
		return user;
	}
	
	private void execute(String sql,PreparedMap<Integer,Object> params) {
		try {
			if ( PREPARED ) {
				BaseDao.preparedExecute(sql, params);
			} else {
				BaseDao.execute(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从文件中读取文本
	 * @param path
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String loadFileToString(String path,String charset) throws IOException {
		File file = new File(path);
		InputStream is = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(is,charset);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while (null != (line = br.readLine()))
			sb.append(line).append("\r\n");
		br.close();
		isr.close();
		is.close();
		isr = null;
		is = null;		
		br = null;
		return sb.toString();
	}
}

class User {
	
	private int id;
	private int age;
	private String name;
	private boolean male = false;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the male
	 */
	public boolean isMale() {
		return male;
	}
	/**
	 * @param male the male to set
	 */
	public void setMale(boolean male) {
		this.male = male;
	}
	
	public String toString(){
		return this.id + ", " + this.name + ", " + this.age;
	}
}