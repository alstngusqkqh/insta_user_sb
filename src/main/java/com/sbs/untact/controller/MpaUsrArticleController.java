package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Controller
public class MpaUsrArticleController {
	private List<Article> articles = new ArrayList<>();
	private int articleLastId;

	public MpaUsrArticleController() {
		articles = new ArrayList<>();
		articleLastId = 0;
		makeTestData();
	}
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		
		if(Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 입력해주세요");
		}
		
		if(Util.isEmpty(body)) {
			return new ResultData("F-2", "내용을 입력해주세요");
		}
		
		int id = writeArticle(title, body);
		Article article = getArticleById(id);
		
		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);
		
	}
	
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {
		
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해주세요");
		}
		
		if(Util.isEmpty(title)) {
			return new ResultData("F-2", "제목을 입력해주세요");
		}
		
		if(Util.isEmpty(body)) {
			return new ResultData("F-3", "내용을 입력해주세요");
		}
		
		boolean modified = ModifyArticle(id, title, body);
		
		if(modified == false) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id" , id);
		}
		
		return new ResultData("S-1", id + "번 글이 수정되었습니다.", "article", getArticleById(id));
		
	}
	


	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		boolean deleted = deleteArticleById(id);
		
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해주세요");
		}
		
		if(deleted == false) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id" , id);
		}
		
		return new ResultData("S-1", id + "번 글이 삭제되었습니다.", "id", id);
		
	}
	
	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {
		
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "번호를 입력해주세요");
		}
		
		Article article = getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id" , id);
		}
		return new ResultData("S-1", article.getId() + "번 글입니다.", "article", article);
	}
	
	// 내부
	
	private boolean ModifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		
		if(article == null ) {
			return false;
		}
		
		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDateStr());
		return true;
	}
	
	private boolean deleteArticleById(int id) {
		
		Article article = getArticleById(id);
		
		if(article == null ) {
			return false;
		}
		
		articles.remove(article);
		return true;
	}

	private int writeArticle(String title, String body) {
		
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);
		
		articleLastId = id;
		
		return id;
	}

	

	private Article getArticleById(int id) {
		
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
	
	private void makeTestData() {
		
		for(int i = 0; i < 10; i++ ) {
			writeArticle("제목1", "내용1");	
		}	
		
	}

}

