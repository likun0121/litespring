package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry 
	implements ConfigurableBeanFactory, BeanDefinitionRegistry {

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);
	private ClassLoader beanClassLoader;
	
	public DefaultBeanFactory() {
		
	}

	public void registerBeanDefinition(String beanId, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanId, bd);
	}

	public BeanDefinition getBeanDefinition(String beanId) {
		return this.beanDefinitionMap.get(beanId);
	}

	public Object getBean(String beanId) {
		BeanDefinition bd = this.getBeanDefinition(beanId);
		if (bd == null) {
			throw new BeanCreationException("Bean Definition does not exist");
		}
		if (bd.isSingleton()) {
			Object bean = this.getSingleton(beanId);
			if (bean == null) {
				bean = createBean(bd);
				this.registerSingleton(beanId, bean);
			}
			return bean;
		}
		return createBean(bd);
	}

	private Object createBean(BeanDefinition bd) {
		ClassLoader cl = this.getBeanClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clazz = cl.loadClass(beanClassName);
			return clazz.newInstance();			
		} catch (Exception e) {
			throw new BeanCreationException("create bean for " + beanClassName + "failed", e);
		}
	}

	@Override
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
	}

	@Override
	public ClassLoader getBeanClassLoader() {
		return this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader();
	}

}
