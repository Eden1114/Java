/**
 *Objective:
 *In this exercise you will use random access files to implement a simple database program.<br>
 *Directions
 *Create an application called DBTest.java that emulates a small database program that 
 *stores and retrieves records relating to products. 存取数据<br>
 *Use theRandomAccessFile class and a flat file.<br>
 *Records in the database should consist of string names and integer quantities. 包含两个字段<br>
 *Your program should allow the user to display a record, update a record, and add a new record.
 */
import java.io.*;
class DBTest {
	public static void main(String[] args) throws IOException {
		try {
			RandomAccessFile randomaccessfile = new RandomAccessFile("test.dat", "rw");
		}
		catch (IOException e) {
			
		}
		
		
		
		
		
	}
	
	static void add(RandomAccessFile t)
	{
		
	}

}
