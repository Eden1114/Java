/**
 * 
 */
package com.nonesole.example2;

/**
 * 本示例中包含下列内容：<br>
 * 执行控制：真假、if-else、反复、do-while、for、中断和继续、开关
 * @author jack lee
 * @version 1.0
 */
public class Example2 {

	public static void main(String[] args) {
		Example2 ex = new Example2();
		ex.e1(null, null);
		ex.e1("1", null);
		ex.e1("root", null);
		ex.e1("root", "1");
		ex.e1("root", "root");
		ex.e2();
		ex.e3();
	}

	/**
	 * if-else示例
	 */
	public void e1(String name, String password) {
		String rootName = "root";
		String rootPassword = "root";
		
		if ( name == null ) {
			System.out.println("Name is null.");
			return;//终止运行
		} 
		
		if ( !rootName.equals(name) ) {//字符串之间比较是否相等不能使用==，必须使用equals，使用该方法要注意字符串对象不得为null
			System.out.println("Wrong name.");
			return;//终止运行
		} else {
			if ( password == null ) {
				System.out.println("Password is null.");
				return;//终止运行
			} else if ( !rootPassword.equals(password) ) {
				System.out.println("Wrong password.");
				return;//终止运行
			} else {
				System.out.println("成功!");
				return;//终止运行
			}
		}		
	}
	
	/**
	 * do-while,for、中断和继续
	 */
	public void e2() {
		int i = 10;
		while ( i > 0 ) {
			for ( int j = 0 ; j <= i ; j++ ) {
				if ( j == i ) {
					break;//中断，跳出for循环，继续执行while循环
				}
				if ( j == 2 ) {
					continue;//中断本次循环，continue的代码不执行，继续执行后续的for循环
				}
				System.out.println(" j = " + j);
			}
			--i;
			System.out.println(" i = " + i);
		}
		
	}
	
	/**
	 * switch
	 */
	public void e3(){
		int i = 9;
		String message = null;
		switch ( i ) {
		case 10 : message = "ten";break;//break不能取消
		case 9 : message = "nine";break;
		case 8 : message = "eight";break;
		default : message = "small number";
		}
		System.out.println(message);
		
		switch ( message ) {
		case "ten" : message = "10";break;
		case "nine" : message = "9";break;
		case "small number" : message = "1";
		}
		System.out.println(message);
	}
}
