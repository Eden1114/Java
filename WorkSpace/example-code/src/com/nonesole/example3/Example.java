/**
 * 
 */
package com.nonesole.example3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 本示例中包含下列内容：<br>
 * 对象容器：数组、集合、枚举、哈希表、克隆、序列化
 * @author jack lee
 * @version 1.0
 */
public class Example {

	public static void main(String[] args) {
		Example ex = new Example();
		ex.e1();
		ex.e2();
		ex.e3();
		ex.e4();
		ex.e5();
	}

	/**
	 * 数组示例
	 */
	public void e1() {
		String[] names = new String[10];
		names[0] = "Tom";
		names[1] = "Jerry";
		
		for ( int i = 0 ; i < names.length ; i++ ) {//数组的大小的属性是length
			System.out.println(names[i]);//如果输出为null表示该对象为空
		}
	}
	
	/**
	 * List（集合）,Vector示例
	 */
	public void e2() {
		String[] names = new String[10];
		names[0] = "Tom";
		names[1] = "Jerry";
		
		//ArrayList和LinkedList是List接口的两个常用实现类，他们主要区别是数据结构不同
		//ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构
		List<String> array = new ArrayList<String>();
		List<String> link = new LinkedList<String>();
		
		for ( int i = names.length - 1 ; i > -1 ; i-- ) {
			if ( names[i] != null ) {
				array.add(names[i]);
				link.add(names[i]);
			}
		}
		
		//实验：增加100万条数据，看数据对比
		array.clear();
		long t1 = System.currentTimeMillis();
		for ( int  i = 0 ; i < 1000000 ; i++ ) {
			array.add("Tom");
		}
		long t2 = System.currentTimeMillis();
		System.out.println("ArrayList添加100万数据用时：" + (t2-t1) + "毫秒");
		
		link.clear();
		t1 = System.currentTimeMillis();
		for ( int  i = 0 ; i < 1000000 ; i++ ) {
			link.add("Tom");
		}
		t2 = System.currentTimeMillis();
		System.out.println("LinkedList添加100万数据用时：" + (t2-t1) + "毫秒");
		
		/*
		 * 测试结果：
		 * ArrayList添加100万数据用时：18毫秒
		 * LinkedList添加100万数据用时：75毫秒   <----- 注意这里（与下面的实验数据对比）
		 */
		
		
		// 实验：增加100万条数据，看数据对比
		array.clear();
		t1 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			array.add(i, "Tom");
		}
		t2 = System.currentTimeMillis();
		System.out.println("ArrayList插入100万数据用时：" + (t2 - t1) + "毫秒");

		link.clear();
		t1 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			link.add(i, "Tom");
		}
		t2 = System.currentTimeMillis();
		System.out.println("LinkedList插入100万数据用时：" + (t2 - t1) + "毫秒");
	
	
		/*
		 * 测试结果：
		 * ArrayList添加100万数据用时：18毫秒
		 * LinkedList添加100万数据用时：15毫秒   <----- 注意这里（与上面的实验数据对比）
		 */
		
		// 实验：增加10万条数据，看数据对比
		array.clear();
		t1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			if ( i > 0 ) {
				array.add(1, "Tom");
			} else {
				array.add(i, "Tom");
			}
		}
		t2 = System.currentTimeMillis();
		System.out.println("ArrayList插入10万数据用时：" + (t2 - t1) + "毫秒");

		link.clear();
		t1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			if ( i > 0 ) {
				link.add(1, "Tom");
			} else {
				link.add(i, "Tom");
			}
		}
		t2 = System.currentTimeMillis();
		System.out.println("LinkedList插入10万数据用时：" + (t2 - t1) + "毫秒");

		/*
		 * 测试结果：
		 * ArrayList插入10万数据用时：949毫秒
		 * LinkedList插入10万数据用时：9毫秒   
		 */
		
		//参照上述示例请独立完成修改、删除等实验
		
		//强调：根据需求选择实现类！
		
		//Vector类实现了一个动态数组。和ArrayList和相似，但Vector是线程安全的，用于多线程的场景。
		
		Vector<String> v = new Vector<String>();
		t1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			if ( i > 0 ) {
				v.add(1, "Tom");
			} else {
				v.add(i, "Tom");
			}
		}
		t2 = System.currentTimeMillis();
		System.out.println("Vector插入10万数据用时：" + (t2 - t1) + "毫秒");

	}
	
	/**
	 * 枚举
	 * 这段代码实际上调用了7次 Enum<Week>(String name, int ordinal)：
	 * new Enum<Week>("MON",0);
	 * new Enum<Week>("TUE",1);
	 */
	public enum Week {
	    MON, TUE, WED, THU, FRI, SAT, SUN;
	    
	    /**
	     * 重写toString方法
	     */
	    public String toString(){
	    	return this.name() + "," + this.ordinal();
	    }
	}
	
	/**
	 * 枚举示例
	 */
	public void e3(){
		for (Week we : Week.values()) {
            System.out.println(we.toString());
        }
		
		Week wk = Week.FRI;
		switch ( wk ) {
			case SUN : ;
			case SAT : System.out.println("今天休息");break;
			default :  System.out.println("今天不休息");
		}
		
	}
	
	/**
	 * 哈希示例
	 * HashTable（多线程使用）,HashMap(常用),TreeMap(较少使用)
	 */
	public void e4(){
		//HashTable与HashMap最大的区别就是HashTable是线程安全的，HashMap是线程不安全的
		//下面的方法可以创建线程安全的HashMap，但极少使用
		Map<String,String> hashMap = new HashMap<String,String>();
		Map<String,String> m = Collections.synchronizedMap(hashMap);

		hashMap.put("leader", "David");
		hashMap.put("mate1", "Lily");
		hashMap.put("mate2", "Tom");
		hashMap.put("mate3", "Jerry");
		
		System.out.println(hashMap.get("leader"));
		hashMap.put("leader", "Jasson");
		System.out.println(hashMap.get("leader"));
		
		//哈希实例的特点是通过Key-Value的对应关系快速访问数据
		//本方法中以HashMap为例，HashTable在一般场景的使用上与之并无不同，不再赘述
		//学员可参考上述实例编写相关代码测试HashMap和HashTable性能
		
	}
	
	/**
	 * 克隆,序列化
	 */
	public void e5(){
		String name = "Tom";
		String name2 = name;//复制变量，本质上是指针复制
		int num1 = 1;
		int num2 = num1;
		
		User u = new User();
		u.id=1;
		u.name="Tom";
		System.out.println("修改前User：" + u.id);
		
		//直接克隆
		User u1 = u;
		System.out.println("修改前复制对象" + u1.id);
		
		u1.id = 2;
		System.out.println("修改后User：" + u.id);
		System.out.println("修改后复制对象" + u1.id);
		
		//由于user和user1变量使用了同一块内存空间，其实是同一个对象，不是两个独立的对象
		
		//克隆
		u1 = (User) u.clone();
		u1.id = 3;
		u1.name = "David";
		System.out.println("User id：" + u.id);
		System.out.println("克隆的User对象 id：" + u1.id);
		System.out.println("User name：" + u.name);
		System.out.println("克隆的User对象 name：" + u1.name);
		
		List<User> l = new ArrayList<User>();
		l.add(u);
		
		//克隆，完全是两个独立的对象
		List<User> l1 = l;
		l1 = new ArrayList<User>();
		l1.add((User)u.clone());
		l1.get(0).id = 4;
		l1.get(0).name = "Jerry";
		System.out.println(" List : " + u.id + " " + u.name);
		System.out.println("调用父类克隆方法： List克隆 : " + l1.get(0).id + " " + l1.get(0).name);
		
		//在Java语言中，如果需要实现深克隆，可以通过覆盖Object类的clone()方法实现，也可以通过序列化(Serialization)等方式来实现。
		l1 = new ArrayList<User>();
		l1.add((User)u.clone2());//使用序列化进行克隆
		l1.get(0).id = 5;
		l1.get(0).name = "Robert";
		System.out.println(" List : " + u.id + " " + u.name);
		System.out.println("序列化克隆：  List克隆 : " + l1.get(0).id + " " + l1.get(0).name);
	}
	
	
}

/**
 * 该类实现了克隆接口(Cloneable)和序列化接口(Serializable)
 * @author jack lee
 * @version 1.0
 */
class User implements Cloneable,Serializable {

	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
	 * JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，
	 * 如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。
	 * serialVersionUID就是类的唯一标志
	 */
	private static final long serialVersionUID = 6677093178972751792L;
	
	public int id;
	public String name;
	
	@Override  
    public Object clone() {  
		User u = null;  
        try{  
            u = (User)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return u;  
    } 
	
	/**
	 * 利用序列化实现克隆
	 * @return
	 */
	public Object clone2(){
		User u = null;
	      try {
	    	  // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
	          ByteArrayOutputStream baos = new ByteArrayOutputStream();
	          ObjectOutputStream oos = new ObjectOutputStream(baos);
	          oos.writeObject(this);
	          // 将流序列化成对象
	          ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	          ObjectInputStream ois = new ObjectInputStream(bais);
	          u = (User) ois.readObject();
	          ois.close();
	          bais.close();
	      } catch (IOException e) {
	          e.printStackTrace();
	      } catch (ClassNotFoundException e) {
	          e.printStackTrace();
	      }
	      return u;
	}
}
