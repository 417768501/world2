package dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class JaxenTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testJaxen() {
		System.out.println("进入testJaxen");
		// 1. 创建 对象 SAXreader();
		SAXReader reader = new SAXReader();
		// 2. read xml 文件
		Document doc = null;
		try {
			doc = reader.read("src/main/resources/aaa.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		List<Node> nodes = doc.selectNodes("//name");
		System.out.println("nodes1:" + nodes.size());
		for (Node n : nodes) {
			System.out.println(n.getText());
		}

		nodes = doc.selectNodes("//teacher[@id]");
		System.out.println("nodes2:" + nodes.size());
		for (Node n : nodes) {
			System.out.println(n.valueOf("@id"));
		}

		Node node = doc.selectSingleNode("/teachers/teacher[last()]/name");
		System.out.println("node:" + node.getText());

	}

}
