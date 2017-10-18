/**
 * 
 */
package com.nonesole.example1;

/**
 * 接口
 * @author jack
 * @version 1.0
 */
public interface IPerson {
	
	/**
	 * 男性常量
	 */
	short MALE = 1;
	
	/**
	 * 女性常量
	 */
	short FEMALE = 2;
	
	/**
	 * 根据基础数据判断是否过胖
	 * @return
	 * @throws Exception
	 */
	public boolean isFat() throws Exception;

}
