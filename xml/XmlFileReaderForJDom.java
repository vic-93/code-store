package com.xmlFile;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * JDOM仅使用具体类而不使用接口。这在某些方面简化了API，但是也限制了灵活性
 * API大量使用了Collections类，简化了那些已经熟悉这些类的Java开发者的使用
 * @author pccw
 *
 */
public class XmlFileReaderForJDom {

	public static void main(String[] args) {
		try{
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(XmlFileReaderForJDom.class.getResourceAsStream("/readXmlDemo.xml"));
			Element foo = doc.getRootElement();
			List child = foo.getChildren();
			for(int i=0;i<child.size();i++){
				Element ele = (Element) child.get(i);
				System.out.println(ele.getName() + " : " + ele.getText());
				//System.out.println(ele.getChild("status").getText());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
