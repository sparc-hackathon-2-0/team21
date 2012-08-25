package com.example.team21;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		next();
	}

	private void initView() {
	  TableRow imageRow = (TableRow) findViewById(R.id.imageRow);
	  int mainWidth = imageRow.getWidth();
	  for (int id : (new int[]{ R.id.lhsImage, R.id.rhsImage})) {
	    ImageView v = (ImageView) findViewById(id);
	    v.setMaxWidth(mainWidth/2);
	    v.setMinimumWidth(mainWidth/2);
	  }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	String leftUrl = "http://www.google.com/";
	String rightUrl = "";

	String leftId = "";
	String rightId = "";

	private String getServerUrl() {
	  return getResources().getText(R.string.serverUrl).toString();
	}
	
	public void next() {
	  try {
	    Rpc rpc = CRPC.getRPC(getServerUrl());
	    String[] left = rpc.getRandomArticle();
	    String[] right = rpc.getRandomArticle();
	    updateLeftArticle(left[2], left[0], left[3]);
	    updateRightArticle(right[2], right[0], right[3]);
	    //initView();
	  } catch (Exception e) {
	    shortAnnounce(e.getMessage());
	    e.printStackTrace();
	  }
	}

	private void updateLeftArticle(
	    String articleUri,
	    String articleId, 
	    String imageUri) {
	  this.leftUrl = articleUri;
	  this.leftId = articleId;
	  ImageView button = ((ImageView) findViewById(R.id.lhsImage));
	  Bitmap image = getImageBitmap(imageUri);
	  button.setImageBitmap(image);
	}
	
	private void updateRightArticle(
	    String articleUri,
	    String articleId, 
	    String imageUri) {
	  this.rightUrl = articleUri;
	  this.rightId = articleId;
	  ImageView button = ((ImageView) findViewById(R.id.rhsImage));
	  Bitmap image = getImageBitmap(imageUri);
	  button.setImageBitmap(image);
	}	    
	
	private void openUrl(String url) {
	  Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	  startActivity(webIntent);
	}
	
	public void related(View view) {
		try {
      CRPC.getRPC(getServerUrl()).relateorunrelate(Long.parseLong(leftId), Long.parseLong(rightId), true);
      next();
    } catch (NumberFormatException e) {
      shortAnnounce("The id's don't make sense. Go away.");
      e.printStackTrace();
    } catch (Exception e) {
      shortAnnounce(e.getMessage());
      e.printStackTrace();
    }
	}

	public void openLeftUrl(View view) {
	  openUrl(leftUrl);
	}

	public void notrelated(View view) {
		try {
      CRPC.getRPC(getServerUrl()).relateorunrelate(Long.parseLong(leftId), Long.parseLong(rightId), true);
      next();
		} catch (NumberFormatException e) {
      shortAnnounce("The id's don't make sense. Go away.");
      e.printStackTrace();
    } catch (Exception e) {
      shortAnnounce(e.getMessage());
      e.printStackTrace();
    }
		
	}
	public void openRightUrl(View view) {
	  openUrl(rightUrl);
	}

	private void shortAnnounce(String msg) {
	  Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	private static Bitmap getImageBitmap(String url) {
	  Bitmap bm = null;
	  try {
	    URL aURL = new URL(url);
	    URLConnection conn = aURL.openConnection();
	    conn.connect();
	    InputStream is = conn.getInputStream();
	    BufferedInputStream bis = new BufferedInputStream(is);
	    bm = BitmapFactory.decodeStream(bis);
	    bis.close();
	    is.close();
	  } catch (IOException e) {
	    e.printStackTrace();
	  }
	  return bm;
	} 
}
