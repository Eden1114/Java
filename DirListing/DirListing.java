/**
 * 实现简单的ls命令
 * 两个参数-R -verbose
 */

import java.io.*;
import java.rmi.server.*;

class DirListing {
	public static void main(String[] args) {
		//status0 表示递归输出子目录
		//status1 表示打印文件状态和文件名
		boolean[] status = {false, false};
		//用于保存目录的名称
		String dir = null;
		
		//分析命令行参数
		for(String item : args)
		{
			if(item.equals("-R")) {
				status[0] = true;
			} else if (item.equals("-verbose")) {
				status[1] = true;
				
			}
		}
		
		if(args.length != 0)
		{
			dir = args[args.length-1];
		}
		//取dir
		
		
		/**
		//默认只显示文件名
		args[0]  "-R"//递归搜索子目录，并注意缩进
		args[1]  "-verbose" //显示文件的大小和最后修改
		args[end] "dirctory"
		*/
		try {
			File t = new File(dir);
			list(status[0], status[1], t, 0);
		} catch (java.lang.NullPointerException e) {
			System.out.println("Parameter Error,Need Directory.");
		}
	}
	
	//打印函数
	static void list(boolean status0, boolean status1, File t, int indent)
	{
		File[] files = t.listFiles();
		for(File item : files)
		{
			if (item.isDirectory()) {
				pr(status1, item, indent);
				
				if(status0 == true) 
				{
					list(status0, status1, item, indent + 1);
				}
			}
			if (item.isFile()) {
				pr(status1, item, indent);
			}
		}
	}
	
	static void pr(boolean status1,File file,int indent)
	{
		if(indent != 0)
		{
			for (int i = 0;i < indent;i++) {
				System.out.print("    ");
			}
			System.out.print("|-");
		}
		
		if(file.exists())
		{
			if(status1 == false)
			{
				System.out.println(file.getName());
			}
			else
			{
				System.out.print(file.getName() + " Size:" + file.length());
				
				String temp = "";
				if (file.canRead()) {
					temp += " R";
				}
				if (file.canWrite()) {
					temp += " W";
				}
				if(file.canExecute())
				{
					temp += " E";
				}
				
				System.out.println(temp);
			}
		}
	}

}

