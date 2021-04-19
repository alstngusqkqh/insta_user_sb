package com.sbs.untact.service;

import java.util.ArrayList;
import java.util.List;

import com.sbs.untact.dao.ArticleDao;
import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ResultData ModifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", "존재하지 않는 게시물입니다.");
		}
		
		articleDao.ModifyArticle(id, title, body);
		
		return new ResultData("S-1", "게시물이 수정되었습니다." , "id", id);
	}
	
	public ResultData deleteArticleById(int id) {
		
		Article article = getArticleById(id);
		
		if(article == null ) {
			
			return new ResultData("F-1", "게시물이 존재하지 않습니다.", "id", id);
		}
		
		articleDao.deleteArticleById(id);
		return new ResultData("S-1", id +"번 게시물이 삭제되었습니다." , "id", id);
	}

	public ResultData writeArticle(String title, String body) {
		
		int id = articleDao.writeArticle(title, body);
		
		return new ResultData("S-1", id +"번 게시물이 작되었습니다." , "id", id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);			
	}
}
