/**
 * 
 */
package com.nonesole.example2;

/**
 * 本示例中包含下列内容：<br>
 * 运算：优先级、赋值、算数运算符、自动递增递减、关系运算符、逻辑运算符、按位运算符、
 * 移位运算符、逗号运算符、字符串运算符、强制类型转换、三元if-else运算（？：）
 * @author jack lee
 * @version 1.0
 */
public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Example ex = new Example();
		ex.e1();
		ex.e2();
		ex.e3();
		ex.e4();
		ex.e5();
		ex.e6();
	}

	/**
	 * 1、算数运算符
　　　　* + :加法
　　　　* - ：减法
　　　　* * ：乘法
　　　　* / ：除法
　　　　* % ：取余运算
	 */
	public void e1(){
		
		int a = 10;
		float b = 10.1f;
		double c = 10.2d;
		short d = 11;
		long e = 12l;
		
		/*
		 * 数据类型的转换：
		 * 向上转换：
		 * boolean 类型是不可以转换为其他基本数据类型。
		 * 整型，字符型，浮点型的数据在混合运算中相互转换，转换时遵循以下原则：
		 * 值域小的类型可自动转换为值域大的数据类型；
		 * byte,short,char → int → long → float → double 
		 * byte，short，char之间不会相互转换，他们在计算时首先会转换为int类型。
		 * 向下转换：
		 * 值域大的数据类型转换为值域小的数据类型时，要加上强制转换符，但可能造成精度的降低或溢出，使用时要格外注意。
		 * 有很多种类型的数据混合运算时，系统首先自动的将所有数据转换成容器最大的哪一种数据类型，再计算。
		 * 表达式的数据类型自动提升 所有的byte型、short型和char的值将被提升到int型。
		 */
		float f = a + b;//结果可以是float,double类型
		int g = a + d;//结果可以是int,long,float,double类型
		double h = a + b * c / d % e;//结果只能为double型
		
		System.out.println(f + "," + g + "," + h);
	
	}
	
	/**
	 * 2、关系运算符
	 * < ：只能比较基本类型数据之间的关系，不能比较对象之间的关系。
	 * > : (同关系运算符“<”)
	 * <=: (同关系运算符“<”)
	 * >=: (同关系运算符“<”)
	 * == ：若使用该运算符比较两个对象的引用（变量），则实质上是比较两个变量是否引用了相同的对象。所谓相同的对象是指，是否是在堆栈（Heap）中开辟的同一块儿内存单元中存放的对象。
	 * 若比较两个对象的引用(变量)所引用的对象的内容是否相同，则应该使用equals()方法，该方法的返回值类型是布尔值。需要注意的是：若用类库中的类创建对象，则对象的引用调用equals()方法比较的是对象的内容；若用自定义的类来创建对象，则对象的引用调用equals()方法比较的是两个引用是否引用了同一个对象，因为第二种情况equals()方法默认的是比较引用。
	 * != ：(同关系运算符“==”)
	 */
	public void e2(){
		
		int a = 10;
		float b = 10.1f;
		double c = 10d;
		short d = 11;
		long e = 12l;
		float f = 10.1f;
		double g = 10d;
		
		System.out.println("a > b : " + (a > b));
		System.out.println("c < b : " + (c < b));
		System.out.println("d >= b : " + (d >= b));
		System.out.println("e <= b : " + (e <= b));
		System.out.println("f == b : " + (f == b));
		System.out.println("f != b : " + (f != b));
		System.out.println("g == c : " + (g == c));
		System.out.println("g != c : " + (g != c));
		System.out.println("g - c = 0 : " + (g - c == 0));
				
	}
	
	/**
	 * 3、逻辑运算符 （操作符只能是布尔类型的）
	 * && ： 逻辑与
	 * || ：逻辑或
	 * ! ： 逻辑非，不可以与=联用，因为!是一元操作符；不可以对布尔类型的数据进行按位非运算
	 */
	public void e3(){
		
		int a = 10;
		float b = 10.1f;
		double c = 10d;
		short d = 11;
		
		System.out.println("a > b and  c > b : " + (a > b && c > b ));
		System.out.println("a > b or  c > b : " + (a > b || c > b ));
		System.out.println("a > b and !( c > d ): " + (a > b && !(c > d)));
		
		//非的示例：
		boolean r = false;
		if( !r && !false ) {//右侧的非运算 !false 虽然没有实际意义，但是符合语法要求
			System.out.println("true");
		}
		
	}
	
	/**
	 * 4、位运算符
	 * & ：与
	 * | ：或
	 * ^ ：异或
	 */
	public void e4() {
		
		int a = 10;
		short b = 11;
		
		System.out.println("a & b = " + (a & b));
		System.out.println("a | b = " + (a | b));
		System.out.println("a ^ b = " + (a ^ b));
		System.out.println("~b = " + (~b));
		
	}
	
	/**
	 * 5、移位运算符（只能处理整数运算符）
	 * Char、byte、short类型，在进行移位之前，都将被转换成int类型，移位后的结果也是int类型；
	 * 移位符号右边的操作数只截取其二进制的后5位（目的是防止因为移位操作而超出int类型的表示范围：2的5次方是32，
	 * int类型的最大范围是32位）；对long类型进行移位，结果仍然是long类型，移位符号右边的操作符只截取其二进制的后6位。
	 * << ：
	 * >> ：若符号位为正，则在最高位插入0；若符号位为负，则在最高位插入1
	 * >>> ：无论正负，都在最高位插入0
	 */
	public void e5(){
		// 5 ： 0000 0000 0000 0101 向右移一位变成  2： 0000 0000 0000 0010
        System.out.println(5>>1);//2

        System.out.println(8>>1);//4
        System.out.println(3+5>>1);//4
        System.out.println(3+(5>>1));//5
        
        //其他运算大家自行尝试
	}
	
	/**
	 * 5、其他
	 * instanceof运算符
	 * ?:运算符
	 * +=,-=,/=,%=,*=,&=,|=,^=,>>=,<<=,>>>=运算符
	 * ++,--运算符，自动递增递减
	 * 强制类型转换
	 */
	public void e6(){
		//instanceof该运算符是用来在运行时指出对象是否是特定类的一个实例。instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例。
        System.out.println("This is " + ((this instanceof Example)?"Example object":"not Example object"));
        
        //?:
        int a = 8;
        short b = 9;
        System.out.println(a>b?100:-100);//-100
        
        //+=,-=,/=,%=,*=,&=,|=,^=,>>=,<<=,>>>=运算符
        //以+=运算符为例，其他运算符大家自行尝试
        System.out.println("a+=a+10 : " + (a+=a+10) );//相当于a=a+a+10
        a = 8;
        System.out.println("a+(a+=10) : " + (a+(a+=10)) );//相当于a=a+a+10
        
        //++,--
        a = 8;
        System.out.println("a++ : " + a++ );//相当于a=a+1，但是输出的却是运算前的a值8
        System.out.println("a : " + a );//真正的结果9
        System.out.println("a-- : " + a-- );//相当于a=a-1，但是输出的却是运算前的a值9
        System.out.println("a : " + a );//真正的结果8
        
        System.out.println("++a : " + ++a );//相当于a=a+1，但是输出的却是运算前的a值8
        System.out.println("a : " + a );//真正的结果9
        System.out.println("--a : " + --a );//相当于a=a-1，但是输出的却是运算前的a值9
        System.out.println("a : " + a );//真正的结果8
        
        System.out.println("a++ + ++a : " + (a++ + ++a) );//相当于a=a+(a+1+1),18
        
        
        //强制类型转换
        //每个基础数据类型都有一个与之对应的类
        //int与Integer, short与Short, long与Long
        //float与Float, double与Double
        //boolean与Boolean, byte与Byte, char与Char
        
        Integer i = 10;
        i = a;//JVM自动完成转换
        Long j = 20l;
        j = a * 1l;//通过运算完成转换
        j = Long.valueOf(String.valueOf(a));//通过String完成转换
        i = j.intValue();//调用Long的方法
        long c = a;//JVM自动转换
        c = i;//JVM自动转换
        i = (int) c;//强制转换
        a = 10000000;
        b = (short)a;//强制转换，数据异常
        System.out.println(" a = 10000000 转换为short类型的结果  : " + b);
        
        
        //非基础数据类型的转换
        Object obj = new Example();
        Example exa = (Example)obj;//强制转换，被转换的对象必须是Example，否则会报错
        
	}
	
	
	
}
