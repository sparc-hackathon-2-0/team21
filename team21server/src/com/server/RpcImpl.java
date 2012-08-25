package com.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.appengine.api.datastore.EntityNotFoundException;

public class RpcImpl implements Rpc{

	@Override
	public void relateorunrelate(long idrelater,long idrelatee, boolean relate) {
		try {
			NewsArticle na=SDao.getNewsArticleDao().get(idrelater);
			Relates rtc = null;
			for(Relates rl :na.relates){
				if(rl.newsArticle.getId() == idrelatee){
					rtc = rl;
					
				}
			}
			if(rtc == null){
				rtc = new Relates(idrelatee);
				na.relates.add(rtc);
			}
			if(relate){
				rtc.positiveRelates++;
			} else {
				rtc.negativeRelates++;
			}
			SDao.getNewsArticleDao().put(na);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static int last;
	@Override
	public String[] getRandomArticle() {
		ArticleList al = SDao.getArticleListDao().getByProperty("name", "first");
		int rand=new Random().nextInt(al.nalist.size());
		int count = 10;
		while(rand == last){
			rand=new Random().nextInt(al.nalist.size());
			if(count <= 0){
				break;
			}
			count--;
		}
		last = rand;
		NewsArticle na = null;
		try {
			na = SDao.getNewsArticleDao().get(al.nalist.get(rand));
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] ret = new String[]{""+na.id,na.displayName,na.imgUrl,na.url};
		return ret;
	}

	@Override
	public String[] getList() {
		ArticleList al = SDao.getArticleListDao().getByProperty("name", "first");
		int rand=new Random().nextInt(al.nalist.size());
		NewsArticle na = null;
		try {
			na = SDao.getNewsArticleDao().get(al.nalist.get(rand));
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> l = new ArrayList();
		for(Relates r :na.relates){
			if(r.positiveRelates <= 0){
				continue;
			}
			NewsArticle n = null;
			try {
				n = SDao.getNewsArticleDao().get(r.newsArticle);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			l.add(n.imgUrl);
		}
		
		return l.toArray(new String[0]);
	}

}
