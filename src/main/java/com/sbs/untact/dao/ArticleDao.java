package com.sbs.untact.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.sbs.untact.dto.Article;
import com.sbs.untact.util.Util;

@Mapper
public interface ArticleDao {
	
	boolean ModifyArticle(@Param("id")int id, @Param("title")String title, @Param("body")String body);

	void writeArticle(@Param("boardId")int boardId, @Param("memberId")int memberId ,@Param("title")String title, @Param("body")String body); 

	Article getArticleById(@Param("id")int id); 

	void deleteArticleById(@Param("id")int id);
}
