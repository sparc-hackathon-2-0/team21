package com.example.team21;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		next();
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
	  button.setImageURI(Uri.parse(imageUri));
	}
	
	private void updateRightArticle(
	    String articleUri,
	    String articleId, 
	    String imageUri) {
	  this.rightUrl = articleUri;
	  this.rightId = articleId;
	  ImageView button = ((ImageView) findViewById(R.id.rhsImage));
	  button.setImageURI(Uri.parse(imageUri));
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
}
