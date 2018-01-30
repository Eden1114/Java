/**
 * 使用randomAccessFile类实现简单的数据库
 */

import java.io.*;
import java.util.*;
import javax.activation.*;

class DBTest {
	RandomAccessFile randomAcessFile;
	ArrayList<String> strings;
	ArrayList<Integer> nums;

	public static void main(String[] args) throws FileNotFoundException {
		DBTest dbt = new DBTest("test.dat", "rw");
		dbt.addRecord("1233", 22222);
		dbt.updateByName("111111", 1231);
		dbt.updateByNum("111111", 22222);
		dbt.desplay();
		
	}

	public DBTest(String filename, String mode) throws FileNotFoundException {
		this.strings = new ArrayList<String>();
		this.nums = new ArrayList<Integer>();
		this.strings.clear();
		this.nums.clear();

		try {
			randomAcessFile = new RandomAccessFile(filename, mode);
			readRecord();
		} catch (FileNotFoundException e) {
			;
		}

	}

	private void readRecord() {
		try {
			randomAcessFile.seek(0);
			String s;
			Integer n;
			while (true) {
				s = randomAcessFile.readUTF();
				n = randomAcessFile.readInt();
				this.strings.add(s);
				this.nums.add(n);
			}
		} catch (EOFException e) {
			;
		} catch (IOException e) {
			// System.out.println(2);
			;
		}

	}
	
	public void removeRecordByName(String s) {
		
	}
	
	public void removeRecordByNum(int n) {
		
	}


	public void desplay() {
		try {
			randomAcessFile.seek(0);
			while (true) {
				System.out.print(this.randomAcessFile.readUTF() + " ");
				System.out.println(this.randomAcessFile.readInt());
			}
		} catch (EOFException e) {
			;
		} catch (IOException e) {
			;
		}
	}

	public void updateByName(String s, int n) {
		int size = this.strings.size();
		for (int i = 0; i < size; i++) {
			if (this.strings.get(i).equals(s)) {
				this.nums.set(i, n);
			}
		}
		
		writeRecord();
	}
	
	public void addRecord(String s,int n) {
		this.strings.add(s);
		this.nums.add(n);
		
		writeRecord();
	}

	public void updateByNum(String s, int n) {
		int size = this.nums.size();
		for (int i = 0; i < size; i++) {
			if (this.nums.get(i).equals(n)) {
				this.strings.set(i, s);
			}
		}
		
		writeRecord();
	}

	public void writeRecord() {
		try {
			this.randomAcessFile.seek(0);
			int l1 = this.strings.size();

			for (int i = 0; i < l1; i++) {
				this.randomAcessFile.writeUTF(this.strings.get(i));
				this.randomAcessFile.writeInt(this.nums.get(i));
			}

		} catch (Exception e) {
			;
		}
	}

}
