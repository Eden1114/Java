public class test {
	public static void main(String[] args)
	{
		man m = new man("yangyu",18);
		m.say();
	}
}



class man {
	private String name;
	private int age;
	public void say() {
		System.out.println("My name is "+ name +"and my age is "+ age);
	}
	public man(String a,int b)
	{
		name = a;
		age = b;
	}

}



