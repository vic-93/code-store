package com.yitai.front.web.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @ClassName FreeMakerHelper
 * @Description TODO
 * @author andy.hu
 * @Date 2016年3月15日
 */
public class FreeMakerHelper {
	
	private static FreeMakerHelper helper = new FreeMakerHelper();
	private Configuration config = new Configuration();
	
	public static FreeMakerHelper getHelper() {
		return helper;
	}
	private FreeMakerHelper() {
		
		URL url = FreeMakerHelper.class.getClassLoader().getResource("");
		String RealPath = url.getFile();
		File file = new File(RealPath + "ftl");
		try {
			config.setDirectoryForTemplateLoading(file);
			
			System.out.println(RealPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.setObjectWrapper(new DefaultObjectWrapper());
	}
	public static void main(String args[]) {
		
		File file = new File("templates");
		System.out.println(System.getProperty("user.dir"));
		System.out.println(file.getAbsolutePath());
		
		Map<String, Object> root = new HashMap<String, Object>();
        root.put("xx_A1001", "xxx");
        root.put("A1002", "3432");
	}
	
	public void processTemplate(String templateName, Map<?, ?> mapping, OutputStream out) {
		try {
			Template template = config.getTemplate(templateName,"utf-8");
			Writer outWriter = new OutputStreamWriter(out);
			template.process(mapping, outWriter);
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	public String processHtmlTemplate(String templateName, Map<?, ?> dataMapping) {
		try {
			Template template = config.getTemplate(templateName,"utf-8");
			
			String names[] = template.getCustomAttributeNames();
            StringWriter writer = new StringWriter();  
			template.process(dataMapping, writer);
			return writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
