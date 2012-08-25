package com.server;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SubmitServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		if(req.getParameter("submit") != null){
			ArticleList al = SDao.getArticleListDao().getByProperty("name", "first");
			if(al == null){
				al = new ArticleList();
			}
			
			NewsArticle na = new NewsArticle(req.getParameter("display"), req.getParameter("link"), req.getParameter("image"));
			resp.getWriter().append("submitted");
			
			al.nalist.add(SDao.getNewsArticleDao().put(na));
			SDao.getArticleListDao().put(al);
		}
		resp.getWriter().println("<form action=/submit >\r\n" + 
				"  link<input type=text name=link><br>\r\n" + 
				"  image<input type=text name=image><br>\r\n" + 
				"  display<input type=text name=display><br>\r\n" + 
				"  <input type=submit name=submit value=Submit><br>\r\n" + 
				"  \r\n" + 
				"  \r\n" + 
				"  </form>\r\n" + 
				"  ");
	}
}
