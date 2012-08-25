package com.server;


public class SDao {

	
	public static Dao<NewsArticle> getNewsArticleDao(){
		return new Dao<NewsArticle>(NewsArticle.class);
	}
	
	public static Dao<Relates> getRelateDao(){
		return new Dao<Relates>(Relates.class);
	}
	
	public static Dao<ArticleList> getArticleListDao(){
		return new Dao<ArticleList>(ArticleList.class);
	}
	
}
