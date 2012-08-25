package com.server;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.Key;



public class ArticleList {
	public ArticleList() {
	}
	public List<Key<NewsArticle>> nalist = new ArrayList();
	@Id
	public Long id;
		public Key<ArticleList> getKey(){
		return new Key(ArticleList.class,id);
	}
	String name = "first";
}
