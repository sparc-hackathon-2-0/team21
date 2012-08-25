package com.example.team21;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
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

	
	String leftid = "";
	String rightid = "";

	private String getServerUrl() {
	  return getResources().getText(R.string.serverUrl).toString();
	}
	
	public void next() {
	  try {
	    String[] sta = CRPC.getRPC(getServerUrl()).getRandomArticle();
	    ImageButton choose = ((ImageButton) findViewById(R.id.lhsImage));
	    choose.setImageURI(Uri.parse(sta[3]));
	    leftUrl = sta[2];
	    leftid = sta[0];
	  } catch (Exception e) {
	    shortAnnounce(e.getMessage());
	    e.printStackTrace();
	  }

	}

	private void openUrl(String url) {
	  Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	  startActivity(webIntent);
	}
	
	public void related(View view) {
		try {
      CRPC.getRPC(getServerUrl()).relateorunrelate(Long.parseLong(leftid), Long.parseLong(rightid), true);
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
      CRPC.getRPC(getServerUrl()).relateorunrelate(Long.parseLong(leftid), Long.parseLong(rightid), true);
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
