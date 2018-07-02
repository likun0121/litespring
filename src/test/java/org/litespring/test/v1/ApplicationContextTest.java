package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.core.io.FileSystemXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {

	@Test
	public void testGetBean() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
		PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");
		Assert.assertNotNull(petStore);
	}
	
	@Test
	public void testGetBeanFromFileSystemContext() {
		//注意，使用hardcode一个本地路径，这是个不好的实践
		ApplicationContext ctx = new FileSystemXmlApplicationContext("D:\\project\\litespring\\src\\test\\resources\\petStore-v1.xml");
		PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");
		Assert.assertNotNull(petStore);		
	}

}
