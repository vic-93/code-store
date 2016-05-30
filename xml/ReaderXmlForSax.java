package com.xmlStr;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 使用java自带的sax解析XML格式字符串
 * @author pccw
 *
 */
public class ReaderXmlForSax {

	private static final String xmlStr = "<WfMessage>"
			+ "<id>001</id>"
			+ "<userId>test_qt</userId>"
			+ "<appid>SYS_OA</appid>"
			+ "<wfid>001</wfid>"
			+ "<title>测试标题后修改</title>"
			+ "<startname>yangzhong</startname>"
			+ "<starttime class='sql-timestamp'>2008-08-11 00:00:00</starttime>"
			+ "<endtime class='sql-timestamp'>2008-08-11 00:00:00</endtime>"
			+ "<wfstatus>公司收文</wfstatus>" + "<wfurl>news.sina.com.cn</wfurl>"
			+ "<status>0</status>" + "</WfMessage>";
	
	public static void parse(String xml){
		try{
			SAXParserFactory fac = SAXParserFactory.newInstance();
			SAXParser sp = fac.newSAXParser();
			TestSax ts = new TestSax();
			sp.parse(new InputSource(new StringReader(xml)), ts);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		parse(xmlStr);
	}
}

class TestSax extends DefaultHandler{
	private StringBuffer buf;
	private String str;
	
	public TestSax(){
		super();
	}
	
	public void startDocument(){
		buf = new StringBuffer();
		System.out.println("*********开始解析XML***********");
	}
	
	public void endDocument(){
		System.out.println("*********解析XML结束***********");
	}
	
	public void endElement(String namespaceURI,String localName,String fullName){
		str = buf.toString();
		System.out.println("节点："+fullName +"\tvalue="+buf+" 长度="+buf.length());
		System.out.println();
		buf.delete(0, buf.length());
	}
	
	public void characters(char[] chars,int start,int length){
		buf.append(chars,start,length);
	}
}
