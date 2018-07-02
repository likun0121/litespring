package org.litespring.core.io;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;

public class FileSystemXmlApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory;
	
	public FileSystemXmlApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new FileSystemResource(configFile);
		reader.loadBeanDefinitions(resource);
	}
	
	@Override
	public Object getBean(String beanId) {
		return factory.getBean(beanId);
	}

}
