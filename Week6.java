import java.util.*;
class Weeek6 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Vector<String> vec = new Vector<String>();
		String line = s.nextLine();
		String [] inputStrArray = line.split(" ");
		
		for(String str : inputStrArray)
		{
			split(vec, str.trim() , 6);
		}
		
		for(String i : vec)
		{
			System.out.println(i);
		}

	}
	
	public static void split(Vector<String> vec,String str, int length) {
		int l = 0,r = length;
		
		int len = str.length();
		
		while(r <= len)
		{
			String st = str.substring(l,r);
			vec.add(st);
			//System.out.println(st);
			l += length;
			r += length;
		}
		
		if(l < len) 
		{
			String st = str.substring(l);
			String temp = "";
			for(int i = 0;i < length - st.length();i++)
			{
				temp += "0";
			}
			st += temp;
			//System.out.println(st);
			vec.add(st);
		}	
	
	}
}