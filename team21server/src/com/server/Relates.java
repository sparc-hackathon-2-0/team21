package com.server;


import java.util.ArrayList;
import java.util.List;


import javax.persistence.Embedded;
import javax.persistence.Id;

import com.googlecode.objectify.Key;



public class Relates {
	public Relates() {
	}
	
	public Relates(long idrelatee) {
		newsArticle = new Key(NewsArticle.class,idrelatee);
	}

	@Id
	public Long id;
	Key<NewsArticle> newsArticle;
	int positiveRelates;
	int negativeRelates;
	public Key<Relates> getKey(){
		return new Key(Relates.class,id);
	}
	
}
