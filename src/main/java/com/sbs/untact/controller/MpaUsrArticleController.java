package com.sbs.untact.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Controller
public class MpaUsrArticleController {
	private int articleLastId = 0;
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	
	public ResultData doWrite(String title, String body) {
		int id = articleLastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		Article article = new Article(id, regDate, updateDate, title, body);
		
		articleLastId = id;
		
		Map<String, Object> rsData = new HashMap<>();
		
		rsData.put("resultcode", "S-1");
		rsData.put("msg", id + "번 글이 작성되었습니다.");
		rsData.put("article", article);
		
		
		return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);
		
	}
}

