package com.server;
public interface Rpc {
	void relateorunrelate(long idrelater, long idrelatee, boolean relate);
	String[] getRandomArticle();
}