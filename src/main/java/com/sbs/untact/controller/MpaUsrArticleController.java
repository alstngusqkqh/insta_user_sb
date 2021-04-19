package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.service.ArticleService;
import com.sbs.untact.util.Util;

@Controller
public class MpaUsrArticleController {
	
	@Autowired
	private ArticleService articleservice;

	
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		
		if(Util.isEmpty(title)) {
			return new ResultData("F-1", "제목을 입력해주세요");
		}
		
		if(Util.isEmpty(body)) {
			return new ResultData("F-2", "내용을 입력해주세요");
		}
		
		int id = articleservice.writeArticle(title, body);
		Article article = articleservice.getArticleById(id);
		
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
		
		boolean modified = articleservice.ModifyArticle(id, title, body);
		
		if(modified == false) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id" , id);
		}
		
		return new ResultData("S-1", id + "번 글이 수정되었습니다.", "article", articleservice.getArticleById(id));
		
	}
	


	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id) {
		boolean deleted = articleservice.deleteArticleById(id);
		
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
		
		Article article = articleservice.getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id" , id);
		}
		return new ResultData("S-1", article.getId() + "번 글입니다.", "article", article);
	}
	
	// 내부
	
	
}

