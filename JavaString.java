public class JavaString{
	public static void main(String[]  args)
	{
		String a = "helloworld!";
		String b = new String("Final double a = 1.1");
		String c = a + b;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);

		int alen = a.length();
		System.out.println(alen);
		/**
		 * 字符串长度
		 * public int length();
		 */
		
		char c_w = a.charAt(5);
		System.out.println(c_w);
		/**
		 * 返回指定位置的字符
		 * public char charAt(int index); 
		 */
		
		try
		{
			char[] s = new char[5];
			a.getChars(1,3,s,0);
		}
		catch(Exception ex)
		{
			System.out.println("触发异常");
		}
		/**
		 * 复制字符串的特定部分到另一个字符串（字符数组）
		 * public void getChars(int srcBegin,int srcEnd,char[] dst,int dstBegin);
		 * 没有返回值，但是会抛出异常
		 */
		
		System.out.println(a.equals(b));
		//:System.out.println(a.equals IgnorCase(b));
		/**
		 * 比较两个字符串是否相等
		 * public boolean equals(Object anObject);
		 * 可以用等号代替吧
		 * public boolean equals IgnorCase(Object anObject);
		 * 不区分大小写
		 */

		/**
		 * 其他的一些方法，用的时候才试一下吧
		 * 
		 * public int compareTo(Object anObject);
		 * 比较两个字符串的大小
		 * 
		 * public String startWith(String pre,int toffset);
		 * 返回指定范围内，是否有前缀内容
		 * 
		 * public String concat(String str);
		 * 连接两个字符串，相当于+
		 * 
		 * public int indexOf(char ch);
		 * public int indexOf(String str);
		 * 返回某个字符，或者字符串首次出现的下标
		 * 
		 * public String replace(char oldChar,char newChar);
		 * 使用指定字符替代其他字符
		 * 
		 * public String substring(int start,int end);
		 * 取子串
		 * 
		 * public String toUpperCase();
		 * 转化成大写
		 * public String toLowerCase();
		 * 转化成小写
		 * */


	
	}
}
