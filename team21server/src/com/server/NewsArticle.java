package com.server;


import java.util.ArrayList;
import java.util.List;


import javax.persistence.Embedded;
import javax.persistence.Id;

import com.googlecode.objectify.Key;



public class NewsArticle {
	public NewsArticle() {
	}
	
	public NewsArticle(String displayName, String url, String imgUrl) {
		super();
		this.displayName = displayName;
		this.url = url;
		this.imgUrl = imgUrl;
	}

	@Id
	public Long id;
	public String displayName;
	public String url;
	public String imgUrl;
	@Embedded
	public List<Relates> relates = new ArrayList();
	public String getDisplayName() {
		return displayName;
	}
	public Key<NewsArticle> getKey(){
		return new Key(NewsArticle.class,id);
	}
	
}
