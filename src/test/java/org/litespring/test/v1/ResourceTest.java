package org.litespring.test.v1;

import java.io.InputStream;

import org.junit.Assert.*;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

public class ResourceTest {

	@Test
	public void testClassPathResource() throws Exception {
		Resource r = new ClassPathResource("petStore-v1.xml");
		InputStream is = null;
		try {
			is = r.getInputStream();
			// 注意：这个测试用例并不充分
			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
	
	@Test
	public void testFileSystemResource() throws Exception {
		Resource r = new FileSystemResource("D:\\project\\litespring\\src\\test\\resources\\petStore-v1.xml");
		InputStream is = null;
		try {
			is = r.getInputStream();
			// 注意：这个测试用例并不充分
			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

}
