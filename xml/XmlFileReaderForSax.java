package com.xmlFile;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * sax方式
 * @author pccw
 *
 */
public class XmlFileReaderForSax extends DefaultHandler { 

	java.util.Stack tags = new java.util.Stack(); 
	public XmlFileReaderForSax() { 
		super(); 
	} 

	public static void main(String args[]) { 
		long lasting = System.currentTimeMillis(); 
		try { 
			SAXParserFactory sf = SAXParserFactory.newInstance(); 
			SAXParser sp = sf.newSAXParser(); 
			XmlFileReaderForSax reader = new XmlFileReaderForSax();
			InputStream is = XmlFileReaderForDom.class.getResourceAsStream("/readXmlDemo.xml");
			sp.parse(is, reader);
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		
		System.out.println("运行时间：" + (System.currentTimeMillis() - lasting) + "毫秒");
	}
	
	public void characters(char ch[], int start, int length) throws SAXException { 
		String tag = (String) tags.peek(); 
		System.out.print(new String(ch, start, length));
	} 

	public void startElement(String uri,String localName,String qName,Attributes attrs) { 
		tags.push(qName);
	} 

}
