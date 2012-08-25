package com.example.team21;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

  private final String rpcUrl = getResources().getText(R.string.serverUrl).toString();
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

	public void next() {
		ImageButton choose = ((ImageButton) findViewById(R.id.lhsImage));
		String[] sta = CRPC.getRPC(rpcUrl).getRandomArticle();
		choose.setImageURI(Uri.parse(sta[3]));
		leftUrl = sta[2];
		leftid = sta[0];
		//maybe make sta[1] a text display below

	}

	private void openUrl(String url) {
	  Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	  startActivity(webIntent);
	}
	
	public void related(View view) {
		CRPC.getRPC(rpcUrl).relateorunrelate(Long.parseLong(leftid), Long.parseLong(rightid), true);
	  next();
	}

	public void openLeftUrl(View view) {
	  openUrl(leftUrl);
	}

	public void notrelated(View view) {
		CRPC.getRPC(rpcUrl).relateorunrelate(Long.parseLong(leftid), Long.parseLong(rightid), true);
		
	}
	public void openRightUrl(View view) {
	  openUrl(rightUrl);
	}

}
