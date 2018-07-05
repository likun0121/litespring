# litespring
BeanFactoryTest：
	第一个测试用例：(BeanFactoryTest:testGetBean)
		给定一个xml配置文件（内含bean的定义），能够从中获取：
		1.bean的定义
		2.bean的实例

	第二个测试用例：(BeanFactoryTest:testInvalidBean)
		测试BeanCreationException是否可用

	第三个测试用例：(BeanFactoryTest:testInvalidXML)
		测试BeanDefinitionStoreException是否可用

	第四个测试用例：(BeanFactoryTest:testGetBean)
		修改第一个测试用例，因为认为BeanDefinition这是一个内部定义，不应该暴露给用户，同时，依据单一职责原则(SRP:single responbility principle)，
		我们认为读取xml文件的职责不应该放在DefaultBeanFactory中，
		所以需要创建XmlBeanDefinitionReader用于读取xml，同时定义一个接口BeanDefinitionRegistry用于隐藏获取并注册BeanDefinition，
		使DefaultBeanFactory实现BeanFacoty和BeanDefinitionRegistry。


在实际使用过程中，我们很少使用BeanFactory或者XmlBeanDefinitionReader等类，而是使用ApplicationContext来实现BeanFactory等的功能。下面是ApplicationContext的测试用例。
ApplicationContextTest：
	第一个测试用例：(testGetBean)
		测试ApplicationContext的getBean
		实现类是ClassPathXmlApplicationContext
		
	第二个测试用例：(testGetBeanFromFileSystemContext)
		测试FileSystemXmlApplicationContext的getBean
		

除了在classpath下的xml文件读取之外，还有在文件系统下(file system)查找xml文件的方式，建立关于resource的测试用例
ResourceTest：
	第一个测试用例：(testClassPathResource)
		测试classPath下的xml文件读取
	
	第二个测试用例：(testFileSystemResource)
		测试文件系统下的xml文件读取
	修改原有的测试用例中XmlBeanDefinitionReader的loadBeanDefinitions方法的参数为Resource接口类型，修改ApplicationContext的两个实现类。

在ClassPathXmlApplicationContext和FileSystemXmlApplicationContext中有重复的代码，需要将他们提取出来，用模板方法设计模式，用一个抽象类AbstractApplicationContext来实现骨架方法，
通过继承该抽象类重写自身的特殊方法。

抽象一个ConfigurableBeanFactory，来对ClassLoader进行setter和getter。

抽象出singleton Scope的接口。
