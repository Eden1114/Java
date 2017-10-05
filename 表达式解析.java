import java.util.*;
import java.math.*;

class Main {
	public static void main(String[] args) {
		Scanner s =new Scanner(System.in);
		while(s.hasNext())
		{
			String readIn = s.next();
			
			if(readIn.equals("EOF"))
				break;
			
			
			String temp = readIn.substring(0,1);
			int a = Integer.parseInt(temp);
			
			temp = readIn.substring(2,3);
			int b = Integer.parseInt(temp);
			
			temp = readIn.substring(3,4);
			char ope = temp.charAt(0);
			
			temp = readIn.substring(4,5);
			int c = Integer.parseInt(temp);
			
			temp = readIn.substring(6,7);
			int d = Integer.parseInt(temp);
						
			if(ope == '+')
			{
				int fenmu = b*d;
				int fenzi = b*c + a*d;
				int t = gcd(fenmu, fenzi);
				fenmu /= t;
				fenzi /= t;
				if(fenmu == 1)
					System.out.println(fenzi);
				else
					System.out.println(fenzi+"/"+fenmu);
			}
			
			
			else if(ope == '-')
			{
				int fenmu = b*d;
				int fenzi = a*d - b*c;
				if(fenzi<0&&fenmu>0)
				{
					System.out.print('-');
					fenzi = -fenzi;
				}
				else if(fenmu<0&&fenzi>0)
				{
					System.out.print('-');
					fenmu = -fenmu;
				}
				int t = gcd(fenmu, fenzi);
				fenmu /= t;
				fenzi /= t;
				if(fenmu == 1)
					System.out.println(fenzi);
				else
					System.out.println(fenzi+"/"+fenmu);
			}
		}
	}
	
	public static int gcd(int x,int y)
	{
		return y == 0 ? x : gcd(y,x%y);
	}
}