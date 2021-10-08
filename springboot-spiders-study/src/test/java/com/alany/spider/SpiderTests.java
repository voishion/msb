package com.alany.spider;

import com.alany.spider.entity.Article;
import com.alany.spider.core.proxy.ProxyFetchFactory;
import com.alany.spider.core.task.AsyncProcessTask;
import com.alany.spider.service.ArticleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderTests {

	@Autowired
	private AsyncProcessTask asyncProcessTask;

	@Autowired
	private ProxyFetchFactory proxyFetchFactory;

	@Autowired
	private ArticleService articleService;

	@Test
	public void testProxyFetch() {
		proxyFetchFactory.buildProxy();
	}

	@Test
	public void testArticleCouldDBSeervice(){
		List<Article> articles = new ArrayList<>();

		for (int i = 0; i < 2000; i++) {
			Article article = new Article();
			article.setArticleId("test12345678");
			article.setTitle("insert test");
			article.setSourceName("测试数据");
			articles.add(article);
			articleService.save(article);
		}

		articles.clear();

		articles = articleService.list(Wrappers.<Article>lambdaQuery().eq(Article::getArticleId, "test12345678"));
		System.out.println(articles);
	}
}
