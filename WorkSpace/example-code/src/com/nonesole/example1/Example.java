/**
 * 代码多行注释示例1：
 * 顶部注释常用于描述项目和版权内容
 */
/*
 * 多行注释示例2：
 * 顶部注释常用于描述项目和版权内容
 */
//单行注释示例：顶部注释常用于描述项目和版权内容
package com.nonesole.example1;//包名，与代码所在目录的路径一致


/**
 * 本示例中包含两部分内容：<br>
 * 对象入门：抽象、继承、接口、类、子类、抽象类、异常<br>
 * Java入门：包、接口、类、子类、抽象类、常量、变量、全局变量、基础数据类型、方法、静态方法、构造方法、控制台输出、注释<br>
 * 接口与类：方法的重载、垃圾回收、包规范、访问指示符、继承与合成、final关键字、只读类、内部类<br>
 * @author jack lee
 * @version 1.0
 */
public class Example {
	//class前的public关键字每个代码文件中只能存在一个，否则会报错
	//如果你在使用Eclipse编辑器，请将鼠标停留在BasicExample上，看看多行注释的效果。<br>是HTML的回车标签，保证生成的API文档（HTML格式）可以正确显示内容。
	
	/**
	 * 程序的入口，启动BasicExample，必调此方法
	 * @param args
	 */
	public static void main(String[] args) {
		IPerson p1 = new Person();//使用IPerson接口声明对象，无法使用Person类的自有方法，只能使用接口规定的方法。
		try {
			//在控制台输出执行结果
			//字符串可以通过“+”号进行连接拼成新的字符串
			System.out.println("First Person is " + (p1.isFat()?"fat.":"not fat."));//p1.isFat()一定要用括号括起来，否则就不正确了
			//如果打算输出人名，需要将对象强制类型转换为Person类，强制类型转换有风险，操作不当出现数据类型错误。
			System.out.println(((Person)p1).getName() + " is " + (p1.isFat()?"fat.":"not fat."));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Person p2 = new Person();//使用Person类声明对象，可以使用Person类的自有方法，包括接口规定的方法。
		p2.setHeight(1.7f);//设置身高
		p2.setWeight(60d);//设置体重
		p2.setSex(IPerson.MALE);//改成0可以看异常
		p2.setName("Second Person");//设置名字
		try {
			//在控制台输出执行结果，可以使用用户名
			System.out.println(p2.getName() + " is " + (p2.isFat()?"fat.":"not fat."));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//对于重复使用的变量可以统一声明
		double weight = 61d;
		float height = 1.7f;
		
		try {
			Male tom = new Male();//Male是Person的子类，可以使用Person的public方法和属性
			tom.setHeight(height);
			tom.setWeight(weight);
			tom.setName("Tom");
			
			Female lucy = new Female();//Female是Person的子类，可以使用Person的public方法和属性
			lucy.setHeight(height);
			lucy.setWeight(weight);
			lucy.setName("Lucy");
			
			//在控制台输出执行结果，可以使用用户名
			System.out.println(tom.getName() + " is " + (tom.isFat()?"fat.":"not fat."));
			System.out.println(lucy.getName() + " is " + (lucy.isFat()?"fat.":"not fat."));
			
			//垃圾回收
			System.gc();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//调用内部类：方法1
		Example ex = new Example();
		Example.InnerClass eic = ex.new InnerClass();
		
		//调用内部类：方法2
		InnerClass ic = new Example().getInnerClass();
		
		//返回内部类处理结果
		Person boy = ex.getBoy();
		
		//返回内部类处理结果
		Person girl = ex.getGirl();
		
		//调用静态内类的属性
		Person tallboy = Example.TallBoy.TALL_BOY;
		
		EnglishTeacher et = new EnglishTeacher();
		et.setHeight(height);
		et.setWeight(weight);
		et.setName("Kat");
		
		MathTeacher mt = new MathTeacher();
		mt.setHeight(height);
		mt.setWeight(weight);
		mt.setName("Jerry");
		try {
			//在控制台输出执行结果，可以使用用户名
			System.out.println(et.getStatus() + " " + et.getName() + " is " + (et.isFat()?"fat.":"not fat."));
			System.out.println(mt.getStatus() + " " + mt.getName() + " is " + (mt.isFat()?"fat.":"not fat."));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public InnerClass getInnerClass() {
		return new InnerClass();
	}
	
	/**
	 * 内部类<br>
	 * 内部类是指在一个外部类的内部再定义一个类。类名不需要和文件夹相同。内部类大量使用在富客户端和多线程<br>
	 * 1. 成员内部类
	 * 在外部类的内部，可以直接使用外部类的所有成员和方法。外部类可通过内部类对象获取其成员和方法。<br>
	 * 由于编译顺序的原因，成员内部类不能含有static的变量和方法，成员内部类通过outer.this来调用外部类对象；<br>
	 * <br>
	 * 2. 静态内部类<br>
	 * 静态内部类，就是修饰为static的内部类，该类的特点是可以直接为外部对象所调用，无需实例化外部类。<br>
	 * 静态内部类与其他内部类的区别：静态内部类可以有static属性和方法，可以包含嵌套类。静态内部类一般声明为public。<br>
	 * <br>
	 * 3. 匿名内部类<br>
	 * 有时候我为了免去给内部类命名，便倾向于使用匿名内部类，因为它没有名字。比如说多线程代码中就经常会出现匿名内部类<br>
	 * 4. 局部内部类<br>
	 * 在方法体或程序块内部定义的类。
	 * 5. 我们不推荐内部类的继承。
	 * <br>
	 * @author jack lee
	 * @version 1.0
	 */
	private class InnerClass extends Person {//此内部类为成员内部类
		public InnerClass(){
			Example.this.print("This message comes from InnerClass.");//这里对外部类的调用在某些特定需求下会经常用到
			this.setName("Inner class");
			this.setAge(10);
			this.setHeight(1.8f);
			this.setWeight(80d);
			try {
				System.out.println(this.getName() + " is " + (this.isFat()?"":"not") + " fat");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void print(String message) {
		System.out.println(message);
	}
	
	public Person getBoy(){//外部类方法
		class Boy extends Person {//局部内部类
			public Boy() {
				this.setSex(IPerson.MALE);
			}
		}
		return new Boy();
	}
	
	private Person girl = null;//外部类的属性
	{//程序块
		class Girl extends Person {//局部内部类
			public Girl() {
				this.setSex(IPerson.FEMALE);
			}
		}
		girl = new Girl();
	}
	
	public Person getGirl() {
		return this.girl;
	}
	
	public static class TallBoy {//静态内部类
		public static Person TALL_BOY;
		static {
			TALL_BOY = new Person();
			TALL_BOY.setSex(IPerson.MALE);
			TALL_BOY.setHeight(1.9f);
		}
	}
}

/**
 * Person类实现了IPerson接口，是IPerson接口的实现类<br>
 * 一个类可以实现多个接口，但只能继承一个类
 */
class Person implements IPerson {
	/*
	 * 由于生成API文档的需求，public关键字修饰的变量，通常使用多行注释
	 * 注意public,protected,private的使用范围，所有类变量都是全局变量，可以在整个类中被调用。
	 */
	
	/**
	 * 名字 
	 */
	public String name = null;//名字，字符型变量，默认值为空
	
	/**
	 * 年龄，默认值为1岁
	 */
	public int age = 1;//整型变量int：32 位，其对应的类是Integer
	
	/**
	 * 身高，默认值为1.8米
	 */
	private float height = 1.8f;//浮点型变量float：32 位，数字后加“f”，其对应的类是Float
	
	/**
	 * 体重，默认值为70公斤
	 */
	private double weight = 70d;//双精度浮点型变量double：64 位，数字后加“d”，其对应的类是Double
	
	/**
	 * 性别，1为男性，2为女性
	 */
	private short sex = IPerson.MALE;//短整型变量short：32 位，其对应的类是Short
	
	/**
	 * 出生日期，默认值为0
	 */
	private long birthday = 0l;//长整型变量long：64  位、常用于时间、金额等大数的表示，数字后加“l”，其对应的类是Long
	
	/**
	 * 呼入快捷键
	 */
	private byte key = 'a';//byte：8 位，用于表示最小数据单位，如文件中数据，值域：-128~127，其对应的类是Byte
	
	/**
	 * 单字昵称
	 */
	private char nickName = '人';//char：16 位，是整数类型，用单引号括起来的 1 个字符（可以是一个中文字符），使用 Unicode 码代表字符，0~2^16-1（65535），其对应的类是Char
	
	/**
	 * 是否为会员
	 */
	private boolean vip = false;//布尔型变量，其对应的类是Boolean
	
	/**
	 * 无参构造方法
	 */
	public Person(){
		this("Noname");//重载Person(name)构造方法
	}
	
	/**
	 * 构造方法
	 * @param name 名字
	 */
	public Person(String name){//重载Person(name,age)构造方法
		this(name,1);
	}
	
	/**
	 * 构造方法
	 * @param name 名字
	 * @param age 年龄
	 */
	public Person(String name, int age){
		//将传参的数据赋值给类变量
		this.name = name;
		this.age = age;
	}
	
	@Override
	public boolean isFat() throws Exception {//继承自IPerson的方法
		//if-else运算
		//“this”表示当前Person对象，this.sex表示当前Person对象的sex属性
		double standard = 0d;//标准体重
		if ( this.sex == MALE ) {//男性
			standard = ( this.height * 100 - 100 ) * 0.9d;	
		} else if ( this.sex == FEMALE ) {//女性
			standard = ( this.height * 100 - 100 ) * 0.9d - 2.5d;
		} else {
			/*
			 * Java中异常提供了一种识别及响应错误情况的一致性机制，有效地异常处理能使程序更加健壮、
			 * 易于调试。异常之所以是一种强大的调试手段，在于其回答了以下三个问题：
			 * 什么出了错?
			 * 在哪出的错?
			 * 为什么出错?
			 */
			throw new Exception("性别错误！");//抛出异常，同学可以将this.sex设置为0，就可以看到异常了
		}
		return toDecimal(weight - standard) > 0.01;//对于类内部的静态方法，也可以不注明类名称，直接使用方法名调用。
	}
	
	/**
	 * 将双精度浮点型变量截取小数点后两位，注意static修饰符，表示该方法是静态方法。静态方法可以被其他外部类直接调用，而不用创建所属类的实例。
	 * 静态方法在访问本类的成员时，只允许访问静态成员（即静态成员变量和静态方法），而不允许访问实例成员变量和实例方法；实例方法则无此限制。
	 * 本静态方法的调用示例：Person.toDecimal(...);
	 * @param d
	 * @return
	 */
	public static double toDecimal(double d){
		//格式化数据，如果不想在文件顶部使用import导入外部类，可以使用完整路径来构建对象。
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");   
		String s = df.format(d);
		//强制类型转换
		return new Double(s).doubleValue();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		//注意：此处的name是Person的类变量，也可以表示为this.name
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		//注意：此处的name是传参，this.name才是Person的类变量
		this.name = name;
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
	 * @return the tall
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param tall the tall to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the sex
	 */
	public short getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(short sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthday
	 */
	public long getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the key
	 */
	public byte getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(byte key) {
		this.key = key;
	}

	/**
	 * @return the nickName
	 */
	public char getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(char nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the vip
	 */
	public boolean isVip() {
		return vip;
	}

	/**
	 * @param vip the vip to set
	 */
	public void setVip(boolean vip) {
		this.vip = vip;
	}

}

/**
 * 继承示例：Male类继承了Person类，Male类是Person类的子类，Person类是Male类的父类
 * @author jack lee
 * @version 1.0
 */
final class Male extends Person {//使用final关键字的类属于不可变类，不可变类有很多好处，譬如它们的对象是只读的，能够提高性能，它们不能被其他类继承，可以在多线程环境下安全的共享，不用额外的同步开销等等。
	/**
	 * 构造方法
	 */
	public Male(){
		super();
		this.setSex(IPerson.MALE);
	}
}

/*
 * 多行注释示例3：
 */
final class Female extends Person {//继承示例：Person类继承了IPerson接口
	/**
	 * 构造方法
	 */
	public Female(){
		super();
		this.setSex(IPerson.FEMALE);
	}
}

/**
 * 抽象类<br>
 * 一个类可以实现多个接口，但是只能有父类。
 * @author jack lee
 * @version 1.0
 */
abstract class Teacher extends Person implements IStatus {
	
	protected String status = null;
	
	public Teacher(){
		super();//调用父类的构造方法
		status = "teacher";
	}
	
	/**
	 * 抽象方法，只在抽象类中存在
	 */
	public abstract String getStatus();
		
}

class EnglishTeacher extends Teacher {

	/**
	 * 构造方法
	 */
	public EnglishTeacher(){
		this.setSex(IPerson.FEMALE);
	}
	
	/**
	 * 子类必须实现父类的抽象方法，除非子类也是抽象类
	 */
	public String getStatus() {
		return "English Teacher";
	}
}

class MathTeacher extends Teacher {

	/**
	 * 构造方法
	 */
	public MathTeacher(){
		this.status = "Math Teacher";
		this.setSex(IPerson.MALE);
	}
	
	public String getStatus() {
		return status;
	}
	
}