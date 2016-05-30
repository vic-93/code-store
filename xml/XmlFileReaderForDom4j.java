package com.xmlFile;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * DOM4J性能最好，连Sun的JAXM也在用DOM4J.
 * 目前许多开源项目中大量采用DOM4J，例如大名鼎鼎的Hibernate也用DOM4J来读取XML配置文件
 * 如果不考虑可移植性，那就采用DOM4J.
 * @author pccw
 *
 */
public class XmlFileReaderForDom4j {
	
	public static void main(String[] args) {
		try{
			InputStream is = XmlFileReaderForDom4j.class.getResourceAsStream("/readXmlDemo.xml");
			SAXReader sax = new SAXReader();
			Document doc = sax.read(is);
			Element foo = doc.getRootElement();
			//不带参数，遍历所有；带参数，遍历所有特点节点名称；
			Iterator eleList = foo.elementIterator();
			//Iterator eleList = foo.elementIterator("status");
			while(eleList.hasNext()){
				Element ele = (Element) eleList.next();
				System.out.println(ele.getName() + " : " + ele.getText());
				//System.out.println(ele.elementText("status"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
