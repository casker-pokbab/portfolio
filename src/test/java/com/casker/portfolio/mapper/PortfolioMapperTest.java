package com.casker.portfolio.mapper;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-root.xml"})
@Transactional
public class PortfolioMapperTest {

	@Autowired
	private PortfolioMapper portfolioMapper;

	@Test
	public void testSelectProjectList() throws Exception {
		List<Portfolio> actual = portfolioMapper.selectPortfolioList();

		assertFalse(CollectionUtils.isEmpty(actual));
	}
	
	@Test
	public void testInsertPortfoilo() throws Exception {
		Portfolio portfolio = new Portfolio();
		portfolio.setPortfolioNo(2);
		portfolio.setPortfolioName("한국관광공사, 관광투자 웹사이트");
		portfolio.setUrl("");
		portfolio.setWorkDate(new DateFormatter("yyyy.MM.dd").parse("2015.01.01", Locale.KOREA));
		
		portfolioMapper.insertPortfoilo(portfolio);
	}
}
