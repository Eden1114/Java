import java.util.*;
public class StringTokenizer{
	public static void main(String[] args)
	{
		/**
			public StringTokenizer(String str);
			构造一个字符串且采用默认分隔符的词法构造器
			public StringTokenizer(String str,String delim);
			构造一个指定字符串和分隔符的词法分析器
			public StringTokenizer(String str,Str delim,boolean returnDelims);
			构造一个指定字符串和分隔符且返回分隔符的词法分析器
		*/
		StringTokenizer b = new StringTokenizer("Today is a lucky day. What I want most is your love.",".",true);
		while(b.hasMoreElements())
		{
			System.out.println("Token:" + b.nextToken());
		}
	}
}
