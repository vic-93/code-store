package com.xmlStr;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Dom4j方式解析XML格式字符串
 * @author pccw
 *
 */
public class ReaderXmlForDom4j {

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
	
	public static void parse(String xmlStr){
		try{
			Document doc = DocumentHelper.parseText(xmlStr);
			Element element = doc.getRootElement();
			System.out.println("root:"+element.getName());
			Iterator elements = element.elementIterator();
			while(elements.hasNext()){
				Element e = (Element) elements.next();
				System.out.println("节点："+e.getName()+"\t节点文本值："+e.getText());
				//子元素
				List subElement = e.elements();
				//取指定节点名称的集合
				//List subNameElement = e.elements("status");
				for (int i = 0; i < subElement.size(); i++) {
					Element se = (Element) subElement.get(i);
					System.out.println("节点："+se.getName()+"\t节点文本值："+se.getText());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		long beginDate = System.currentTimeMillis();
		parse(xmlStr);
		long endDate = System.currentTimeMillis();
		System.out.println("耗时："+(endDate - beginDate));
	}
}
