package com.yxt;

import com.github.pagehelper.Page;
import com.yxt.domain.entity.PageData;
import com.yxt.domain.entity.Person;
import com.yxt.page.PageInfo;
import com.yxt.service.BuildService;
import com.yxt.service.PersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonMapperTests {

	private Logger logger = LoggerFactory.getLogger(PersonMapperTests.class);

	//@Autowired
	//private PersonService personService;
	@Autowired
	private BuildService buildService;

	@Before
	public void testInsert() {
		Person person = new Person();
		person.setName("测试");
		person.setAddress("address");
		person.setAge(10);
		//personService.insert(person);

		//Assert.assertNotNull(person.getId());
		logger.debug(JSON.toJSONString(person));
	}

	/*@Test
	public void testFindAll() {
		List<Person> persons = personService.findAll();

		Assert.assertNotNull(persons);
		logger.debug(JSON.toJSONString(persons));
	}*/

	@Test
	public void testFindByPage() throws Exception{
		PageData pd = null;
		Page<PageData> builds = buildService.findByPage(1, 10, pd);
		// 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
		PageInfo<PageData> pageInfo = new PageInfo<>(builds);
		Assert.assertNotNull(builds);
		logger.debug(pageInfo.toString());
		logger.debug(JSON.toJSONString(pageInfo));
	}

	/*@Test
	public void testCacheByPage() {
		long begin = System.currentTimeMillis();
		List<Person> persons = personService.findAll();
		long ing = System.currentTimeMillis();
		personService.findAll();
		long end = System.currentTimeMillis();
		logger.debug("第一次请求时间：" + (ing - begin) + "ms");
		logger.debug("第二次请求时间:" + (end - ing) + "ms");

		Assert.assertNotNull(persons);
		logger.debug(persons.toString());
		logger.debug(JSON.toJSONString(persons));
	}*/

}
