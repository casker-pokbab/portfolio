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

import com.casker.portfolio.domain.Project;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-root.xml"})
@Transactional
public class ProjectMapperTest {
	
	@Autowired
	private ProjectMapper projectMapper;

	@Test
	public void testSelectProjectList() throws Exception {
		List<Project> actual = projectMapper.selectProjectList();

		assertFalse(CollectionUtils.isEmpty(actual));
	}
	
}
