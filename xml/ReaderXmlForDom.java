package com.xmlStr;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 * java 自带DOM方式解析XML格式字符串
 * @author pccw
 *
 */
public class ReaderXmlForDom {
	
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
	
	/**
	 * DOM解析
	 * @param protocolXml
	 */
	public static void parse(String protocolXml){
		try{
			NodeList books = getXmlNodeList(protocolXml);
			if(books!=null){
				for(int i=0;i<books.getLength();i++){
					Node book = books.item(i);
					System.out.println("节点：" + book.getNodeName() + "\t文本值：" + book.getFirstChild().getNodeValue());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 解析XML格式字符串，获得节点集合
	 * @param protocolXml
	 * @return
	 */
	public static NodeList getXmlNodeList(String protocolXml){
		NodeList books = null;
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = factory.newDocumentBuilder();
			Document doc = build.parse(new InputSource(new StringReader(protocolXml)));
			Element root = doc.getDocumentElement();
			books = root.getChildNodes();
		}catch(Exception e){
			e.printStackTrace();
		}
		return books;
	}
	
	/**
	 * 根据节点名称返回节点文本值
	 * @param eleName
	 * @return
	 */
	public static String getElementValueByName(String eleName,String xml){
		String value = "";
		try{
			NodeList list = getXmlNodeList(xml);
			if(list!=null && list.getLength() != 0){
				for(int i=0;i<list.getLength();i++){
					Node node = list.item(i);
					if(eleName.equalsIgnoreCase(node.getNodeName())){
						value = node.getFirstChild().getNodeValue();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		parse(xmlStr);
		long after = System.currentTimeMillis();
		System.out.println("DOM用时："+ (after - begin) + " 毫秒");
		//获取具体的节点值
		long beginTime = System.currentTimeMillis();
		String textValue = getElementValueByName("status", xmlStr);
		long endTime = System.currentTimeMillis();
		System.out.println("status:" + textValue);
		System.out.println("获取节点值耗时：" + (endTime - beginTime) + "毫秒");
	}
}
