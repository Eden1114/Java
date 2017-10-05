class Untitled {
	public static void main(String[] args) {
		Student[] s = new Student[5];
		s[0] = new Student("16281237", "杨宇", "1608");
		s[1] = new Student("16281238", "杨宇1", "16081");
		s[2] = new Student("16281239", "杨宇2", "16082");
		s[3] = new Student("16281240", "杨宇3", "16083");
		s[4] = new Student("16281241", "杨宇4", "16084");
		for(int i= 0;i < 5;i++)
		{
			System.out.println(s[i].getInfo());
		}
		Person[] p = new Student2[3];
		p[0] = new Student2("1231", "yangyu1","111");
		p[1] = new Student2("1232", "yangyu2","222");
		p[2] = new Student2("1233", "yangyu3","333");
		
		p[0].setDoing("chifan");
		p[1].setDoing("shuijiao");
		p[2].setDoing("dafeiji");

		for(int i = 0;i < 3;i++)
		{
			System.out.println(p[i].getInfo());
		}
	}
}


class Student {
	String sID;
	String sName;
	static String sClass;
	private String sDoing;
	
	public Student(String sid,String sname)
	{
		this.sID = sid;
		this.sName = sname;
	}
	
	public Student(String sid,String sname,String sclass)
	{
		this.sID = sid;
		this.sName = sname;
		this.sClass = sclass;
	}
	
	public void setDoing(String sdoing)
	{
		this.sDoing = sdoing;
	}
	
	public String getDoing()
	{
		return this.sDoing;
	}
	
	public String getInfo()
	{
		return (this.sName+"("+this.sClass+", "+this.sID+")");
	}
	
}

abstract class Person {
	String sName;
	String sDoing;
	public Person(String name)
	{
		this.sName = name;
	}
	abstract public void setDoing(String sdoing);
	abstract public String getDoing();
	abstract public String getInfo();
}



class Student2 extends Person {
	String sID;
	static String sClass;
	
	public Student2(String sid,String sname,String sclass)
	{
		super(sname);
		this.sID = sid;
		this.sClass = sclass;
	}
	
	public void setDoing(String sdoing)
	{
		this.sDoing = sdoing;
	}
	
	public String getDoing()
	{
		return this.sDoing;
	}
	
	public String getInfo()
	{
		return (this.sName+" "+this.getDoing());
	}
}