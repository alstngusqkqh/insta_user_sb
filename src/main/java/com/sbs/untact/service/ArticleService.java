package com.sbs.untact.service;

import java.util.ArrayList;
import java.util.List;

import com.sbs.untact.dto.Article;
import com.sbs.untact.util.Util;

public class ArticleService {
	private List<Article> articles = new ArrayList<>();
	private int articleLastId;
	
	public ArticleService() {
		articles = new ArrayList<>();
		articleLastId = 0;
		makeTestData();
	}
	
	public boolean ModifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		
		if(article == null ) {
			return false;
		}
		
		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDateStr());
		return true;
	}
	
	public boolean deleteArticleById(int id) {
		
		Article article = getArticleById(id);
		
		if(article == null ) {
			return false;
		}
		
		articles.remove(article);
		return true;
	}

	public int writeArticle(String title, String body) {
		
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);
		
		articleLastId = id;
		
		return id;
	}

	

	public Article getArticleById(int id) {
		
		for (Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		
//		for(int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			
//			if(article.getId() == id) {
//				return article;
//			}
//		}
		
		
		return null;
	}
	
	public void makeTestData() {
		
		for(int i = 0; i < 10; i++ ) {
			writeArticle("제목1", "내용1");	
		}	
		
	}

}
