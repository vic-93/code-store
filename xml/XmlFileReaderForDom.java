package com.xmlFile;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Dom 方式解析XML文件，DOM与平台和语言无关
 * @author pccw
 *
 */
public class XmlFileReaderForDom {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = factory.newDocumentBuilder();
			//读取XML文件(相对路径方式，不需要斜杠)
			/*File file = new File("src/readXmlDemo.xml");
			Document doc = build.parse(file);*/
			//读取XML文件（classpath路径方式读取，可靠方式）
			InputStream is = XmlFileReaderForDom.class.getResourceAsStream("/readXmlDemo.xml");
			Document doc = build.parse(is);
			//根据节点名称查
			NodeList nodeList = doc.getElementsByTagName("WfMessage");
			for(int i=0;i<nodeList.getLength();i++){
				System.out.println("status:"+doc.getElementsByTagName("status").item(i).getFirstChild().getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
