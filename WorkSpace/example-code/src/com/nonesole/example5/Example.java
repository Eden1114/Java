/**
 * 
 */
package com.nonesole.example5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.omg.CORBA.Environment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 本示例中包含下列内容：<br>
 * Java IO：properties、xml、文本、网络数据的输入和输出
 * 多线程
 * @author jack lee
 * @version 1.0
 */
public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Example ex = new Example();
		ex.e1();
		ex.e2();
		ex.e3();
		ex.e4();
	}

	/**
	 * properties文件读写
	 */
	public void e1(){
		//读取Properties文件
		Properties prop = this.readProperties();
		
		URL url = Example.class.getClassLoader().getResource("example.properties");
		System.out.println("example.properties URL : " + url.getFile());
		
		prop.setProperty("age", "20");//演示时修改年龄，可以验证数据是否写入文件
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(url.getFile(), false);//false表示擦除文件旧的数据并重写，true表示接着写入新数据，不擦除原有数据
			prop.store(fos, "No comment");
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		prop = this.readProperties();
		
	}
	
	/**
	 * 从文件中读取Properties
	 * @return Properties对象
	 */
	public Properties readProperties() {
		InputStream is = Example.class.getClassLoader().getResourceAsStream("example.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			is.close();//关闭输入流
		} catch (IOException e) {
			e.printStackTrace();
		}
		//使用StringBuilder动态拼接字符串
		StringBuilder sbd = new StringBuilder();
		sbd.append(prop.getProperty("name")).append(",")
		   .append(prop.getProperty("age")).append(",")
		   .append(prop.getProperty("father")).append(",")
		   .append(prop.getProperty("mother"));
		System.out.println(sbd.toString());
		return prop;
	}
	
	/**
	 * 读取XML文件
	 * @param filePath
	 * @return document
	 * @throws Exception 
	 * */
	public final Document loadStreamFile(String resource) 
	throws Exception{
		InputStream is = getResourceAsStream(resource);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = null;
		doc = db.parse(is);
		return doc;
	}

	/**
	 * 在包中定位指定名称的文件并将其读入InputStream中
	 * @param resource
	 * @return InputStream
	 * @throws Exception 
	 */
	public InputStream getResourceAsStream(String resource) throws Exception {
		String stripped = resource.startsWith("/") ? resource.substring(1)
				: resource;
		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		if (stream == null) {
			stream = Environment.class.getResourceAsStream(resource);
		}
		if (stream == null) {
			stream = Environment.class.getClassLoader().getResourceAsStream(
					stripped);
		}
		if (stream == null) {
			throw new Exception(resource + " not found");
		}
		return stream;
	}
	
	/**
	 * 遍历子节点，将名称为user的节点保存到集合中
	 * @param fatherNode
	 * @param l
	 */
	public void findChildNode(Node fatherNode, List<Node> l) {
		try {
			NodeList children = fatherNode.getChildNodes();
			if ( children != null && children.getLength() > 0 ) {
				Node n = null;
				if ( "user".equals(fatherNode.getNodeName()) ) {//对名称为Node的节点特殊处理
					for ( int i = 0 ; i < children.getLength() ; i++ ) {
						n = children.item(i);
						if ( !"#text".equals(n.getNodeName()) ) {
							System.out.println(n.getNodeName()+" : " + n.getTextContent());
						}
					}
					l.add(n);
				} else {
					for ( int i = 0 ; i < children.getLength() ; i++ ) {
						n = children.item(i);
						this.findChildNode(n,l);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Element createUser(Document document, int userAge, String userName, String userFather, String userMother) {
		Element user = document.createElement("user"); 
        
        Element age = document.createElement("age"); 
        age.appendChild(document.createTextNode(String.valueOf(userAge))); 
        user.appendChild(age); 
        
        Element name = document.createElement("name"); 
        name.appendChild(document.createTextNode(userName)); 
        user.appendChild(name); 
        
        Element father = document.createElement("father"); 
        father.appendChild(document.createTextNode(userFather)); 
        user.appendChild(father); 
        
        Element mother = document.createElement("mother"); 
        mother.appendChild(document.createTextNode(userMother)); 
        user.appendChild(mother); 
        return user;
	}
	
	/**
	 * 保存文件
	 * @param fileName
	 */
	public void saveXML(String fileName){
		DocumentBuilderFactory factory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder;
        Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
        if ( document == null ) {
        	return;
        }
		
		Element root = document.createElement("config"); 
        document.appendChild(root); 
        root.appendChild(this.createUser(document, 11, "Tom", "Mike", "Lucy"));
        root.appendChild(this.createUser(document, 15, "Jerry", "Mike", "Lucy"));
        root.appendChild(this.createUser(document, 18, "Dug", "Mike", "Lucy"));
        
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            System.out.println("XML文件保存成功!");
            pw.close();
        } catch (TransformerConfigurationException e) {
        	e.printStackTrace();
        } catch (IllegalArgumentException e) {
        	e.printStackTrace();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (TransformerException e) {
        	e.printStackTrace();
        }
	}
	
	/**
	 * xml文件读写
	 */
	public void e2(){
		URL url = Example.class.getClassLoader().getResource("config.xml");
		this.saveXML(url.getFile());
		
		try {
			//读取XML文件
			Document doc = this.loadStreamFile("config.xml");
			NodeList l = doc.getChildNodes();
			if ( l != null && l.getLength() > 0 ) {
				List<Node> list = new ArrayList<Node>();
				this.findChildNode(doc,list);
				System.out.println("User对象数量：" + list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存文本
	 * @param path
	 * @param fileName
	 * @param content
	 * @throws Exception
	 */
	public static void saveFileFromString(String path, String fileName, String content)
			throws Exception {
		File file = new File(path);
		if ( !file.exists() ) {
			file.mkdirs();
		}
		path = path  + fileName;
		file = new File(path);
		if ( !file.exists() ) {
			file.createNewFile();
		}

		OutputStream os = new FileOutputStream(file,true);
		OutputStreamWriter ow = new OutputStreamWriter(os,"UTF-8");
		BufferedWriter out = new BufferedWriter(ow);
        out.write(content);
       	out.close();
        ow.close();
        os.close();
		   
	}
	
	/**
	 * 从文件中读取文本
	 * @param path
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public String loadFileToString(String path,String charset) throws IOException {
		File file = new File(path);
		InputStream is = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(is,charset);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while (null != (line = br.readLine()))
			sb.append(line).append("\r\n");
		br.close();
		isr.close();
		is.close();
		isr = null;
		is = null;		
		br = null;
		return sb.toString();
	}
	 
	/**
	 * txt文件读写
	 */
	public void e3(){
		String book = "明月几时有？把酒问青天。不知天上宫阙，今夕是何年。我欲乘风归去，又恐琼楼玉宇，高处不胜寒。起舞弄清影，何似在人间？转朱阁，;低绮户，照无眠。不应有恨，何事长向别时圆？人有悲欢离合，月有阴晴圆缺，此事古难全。但愿人长久，千里共婵娟。";
		URL url = Example.class.getClassLoader().getResource(".");
		try {
			Example.saveFileFromString(url.getFile(), "book.txt", book);
			System.out.println("TXT文本保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		url = Example.class.getClassLoader().getResource("book.txt");
		try {
			String content = this.loadFileToString(url.getFile(), "UTF-8");
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 网络数据读写
	 */
	public void e4(){
		//读取网站的数据
		try {
			URL url =new URL("http://www.baidu.com");
			InputStream is = url.openStream();//通过openStream方法获取资源的字节输入流
			InputStreamReader isr = new InputStreamReader(is,"GBK");//将字节输入流转换为字符输入流,中文网站一般字符编码一般为UTF-8,GBK,GB2312
			BufferedReader br =new BufferedReader(isr);//为字符输入流添加缓冲，提高读取效率
			String data = null;
			while((data=br.readLine())!=null){
				System.out.println(data);//输出数据
			}
			br.close();
			isr.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Socket通信
		Thread t1 = new Thread(){
			public void run() {
				try {
					Example.this.startServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		
		Thread t2 = new Thread(){
			public void run() {
				try {
					Example.this.startClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t2.start();
		
	}
	
	/**
	 * 启动服务端
	 * @throws IOException
	 */
	public void startServer() throws IOException{
		/**
		 * 基于TCP协议的Socket通信，实现用户登录，服务端
		*/
		//1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket =new ServerSocket(10086);//1024-65535的某个端口
		//2、调用accept()方法开始监听，等待客户端的连接
		Socket socket = serverSocket.accept();
		//3、获取输入流，并读取客户端信息
		InputStream is = socket.getInputStream();
		InputStreamReader isr =new InputStreamReader(is);
		BufferedReader br =new BufferedReader(isr);
		String info =null;
		while((info=br.readLine())!=null){
			System.out.println("我是服务器，客户端说："+info);
		}
		socket.shutdownInput();//关闭输入流
		//4、获取输出流，响应客户端的请求
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("欢迎您！");
		pw.flush();

		//5、关闭资源
		pw.close();
		os.close();
		br.close();
		isr.close();
		is.close();
		socket.close();
		serverSocket.close();
	}
	
	/**
	 * 启动客户端
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void startClient() throws UnknownHostException, IOException{
		//客户端
		//1、创建客户端Socket，指定服务器地址和端口
		Socket socket =new Socket("localhost",10086);
		//2、获取输出流，向服务器端发送信息
		OutputStream os = socket.getOutputStream();//字节输出流
		PrintWriter pw =new PrintWriter(os);//将输出流包装成打印流
		pw.write("用户名：admin；密码：123");
		pw.flush();
		socket.shutdownOutput();
		//3、获取输入流，并读取服务器端的响应信息
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while((info=br.readLine())!=null){
			System.out.println("我是客户端，服务器说："+info);
		}

		//4、关闭资源
		br.close();
		is.close();
		pw.close();
		os.close();
		socket.close();
	}
}
