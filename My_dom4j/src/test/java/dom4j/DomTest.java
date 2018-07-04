package dom4j;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class DomTest {

	@Test
	public void testReadFile() {
		SAXReader reader=new SAXReader();
		Document doc=null;
		try{
			doc=reader.read("src/main/resources/aaa.xml");
		}catch(DocumentException e){
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		System.out.println(root);
		List<Element> list = root.elements();
		for(Element e:list){
			System.out.println("姓名："+e.element("name").getText());
			System.out.println("性别："+e.element("sex").getText());
		}
	}
	//1、写一个xml文件
	@Test
	public void testWriteXmlFile(){
		Document doc = DocumentHelper.createDocument();
		Element root= doc.addElement("beans");
		root.addElement("bean").addAttribute("id", "userDao").addAttribute("class", "com.hc.dao.UserDaoImpl");
		root.addElement("bean").addAttribute("id", "itemsDao").addAttribute("class", "com.hc.dao.ItemsDaoImpl");
		root.addElement("bean").addAttribute("id", "itemsDao").addAttribute("class", "汉字测试");
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer =null;
		try {
			writer = new XMLWriter(new FileWriter("src/main/resources/applicationContext.xml"),format);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.write(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//2、增加一个节点一个xml文件(添加到末尾)
	@Test
	public void testAddElementEnd(){
		SAXReader reader=new SAXReader();
		Document doc=null;
		try {
			doc=reader.read("src/main/resources/applicationContext.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		root.addElement("bean").addAttribute("id", "personDao").addAttribute("class", "com.hc.dao.PersonDaoImpl");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer=null;
		try {
			writer=new XMLWriter(new FileWriter("src/main/resources/applicationContext.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				writer.write(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//增加一个节点一个xml文件(添加到中间)
	@Test
	public void testAddElementMiddle(){
		SAXReader reader = new SAXReader();
		Document doc=null;
		try {
			doc=reader.read("src/main/resources/applicationContext.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		Element insert = DocumentHelper.createElement("bean");
		insert.addAttribute("id", "personDao");
		list.add(2, insert);
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer=null;
		try {
			writer=new XMLWriter(new FileWriter("src/main/resources/applicationContext.xml"),format);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.write(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
