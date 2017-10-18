/**
 * 
 */
package com.nonesole.example4;

/**
 * 本示例中包含下列内容：<br>
 * 异常：系统异常、自定义异常<br>
 * @author jack lee
 * @version 1.0
 */
public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Example ex = new Example();
		//由于异常抛出后，后续代码不再执行，因此将部分方法是放到独立的线程中执行
		Thread t = new Thread(){
			public void run(){
				ex.e1();
			}
		};
		t.start();
		
		Thread t2 = new Thread(){
			public void run(){
				ex.e2();
			}
		};
		t2.start();
		
		Thread t5 = new Thread(){
			public void run(){
				ex.e5();
			}
		};
		t5.start();
	}

	/**
	 * 系统异常、自定义异常
	 */
	@SuppressWarnings({ "null", "unused" })
	public void e1(){
		/*
		 * 以提高程序的健壮性为目标，明确的回答：什么出了错?在哪出的错?为什么出错?
		 * 异常的使用原则：具体明确，提早抛出，延迟捕获
		 * 
		 * 以击鼓传花来解释说明异常使用。
		 * 
		 * Throwable： 有两个重要的子类：Exception（异常）和 Error（错误），二者都是 Java 
		 * 异常处理的重要子类，各自都包含大量子类。
		 * 
		 * Error（错误）:是程序无法处理的错误，表示运行应用程序中较严重问题。例如，
		 * Java虚拟机运行错误（Virtual MachineError），当 JVM 不再有继续执行操作所需的内存资源时，
		 * 将出现 OutOfMemoryError。这些异常发生时，Java虚拟机（JVM）一般会选择线程终止。
		 * 
		 * Exception（异常）：这种异常分两大类运行时异常和非运行时异常(编译异常)。程序中应当尽可能去处理这些异常。
		 * 运行时异常：都是RuntimeException类及其子类异常，如NullPointerException(空指针异常)、
		 * IndexOutOfBoundsException(下标越界异常)等，这些异常是不检查异常，程序中可以选择捕获处理，
		 * 也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。
		 * 
		 * 运行时异常的特点是Java编译器不会检查它，也就是说，当程序中可能出现这类异常，即使没有用
		 * try-catch语句捕获它，也没有用throws子句声明抛出它，也会编译通过。
		 * 
		 * 非运行时异常 （编译异常）：是RuntimeException以外的异常，类型上都属于Exception类及其子类。
		 * 从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。如IOException、
		 * SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。
		 */
		
		//运行时异常示例，下面的方式虽然能够抛出异常，但由于没有提早抛出，造成信息不明确。
		String a1 = null;
		String a2 = a1;
		String a3 = null;
		System.out.println(a2.equals(a3.equals(a1)));//异常抛出后，后续代码不再执行
	}
	
	/**
	 * 异常类
	 */
	public void e2() {
		//运行时异常示例，下面的方式提早抛出，且明确了异常信息。
		String a1 = null;
		String a2 = a1;
		if ( a2 == null ) {
			throw new NullPointerException("a2 of e2 is null");//异常抛出后，后续代码不再执行
		}
		//下面的代码实是多余的，永远不会执行
		@SuppressWarnings("unused")
		String a3 = null;
		System.out.println(a2.equals(a3));
	}
	
	/**
	 * 自定义异常
	 * @throws StringException 
	 */
	public void e3() throws StringException {//方法中存在throw语句，应抛出指定异常
		String a1 = null;
		String a2 = a1;
		if ( a2 == null ) {
			throw new StringException("a2 of e3 is null");
		}
	}
	
	/**
	 * 自定义异常
	 * @throws Exception 
	 */
	public void e4() throws Exception {
		//注意这里抛出了IntegerException的父类Exception，从语法上来说这是允许的，
		//异常的信息就不甚明确
		Integer a1 = null;
		Integer a2 = a1;
		if ( a2 == null ) {
			throw new IntegerException("a2 of e4 is null");
		}
	}
	
	/**
	 * 自定义异常
	 * @throws Exception 
	 */
	public void e5(){
		try {//捕获异常
			e3();
			e4();
		} catch ( StringException e ) {//处理异常
			e.printStackTrace();//在控制台输出异常
		} catch (Exception e) {//注意异常捕获顺序
			e.printStackTrace();
		} finally {//注意finally的用法
			System.out.println("finally a1 is null");
		}
	}
}

/**
 * 继承了Throwable的异常类
 * @author jack lee
 * @version 1.0
 */
class StringException extends Throwable {

	private static final long serialVersionUID = 7896411745624485594L;
	
	public StringException() {
		this(null);
	}
	
	/**
	 * 构造带指定详细消息的新 throwable。 
	 * @param message
	 */
	public StringException(String message) {
		this(message,null);
	}
    
	/**
	 * 构造带指定详细消息的新 throwable。 
	 * @param message
	 */
	public StringException(String message, Throwable cause) {
		super(message,cause);
	}
		
}

/**
 * 继承了Exception的异常类
 * @author jack lee
 * @version 1.0
 */
class IntegerException extends Exception {

	private static final long serialVersionUID = -3737953890225969219L;

	public IntegerException() {
		this(null);
	}
	
	/**
	 * 构造带指定详细消息的新 throwable。 
	 * @param message
	 */
	public IntegerException(String message) {
		this(message,null);
	}
    
	/**
	 * 构造带指定详细消息的新 throwable。 
	 * @param message
	 */
	public IntegerException(String message, Throwable cause) {
		super(message,cause);
	}
	
}
