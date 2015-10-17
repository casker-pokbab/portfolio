package com.casker.portfolio.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Recently;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-root.xml"})
@Transactional
public class RecentlyMapperTest {
	
	@Autowired
	private RecentlyMapper projectMapper;

	@Test
	public void testSelectProjectList() throws Exception {
		BaseSearch<Recently> search = new BaseSearch<>();
		Recently domain = new Recently();
		domain.setDisplayYN("Y");
		search.setDomain(domain);
		search.setPageNum(0);
		search.setPageSize(10);
		
		List<Recently> actual = projectMapper.selectRecentlyList(search);

		assertFalse(CollectionUtils.isEmpty(actual));
	}
	
}
