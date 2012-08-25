package com.example.team21;

import com.server.trading.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	String lefturl = "";
	String righturl = "";

	
	String leftid = "";
	String rightid = "";

	public void next() {
		ImageButton choose = ((ImageButton) findViewById(R.id.lhsImage));
		String[] sta = CRPC.getRPC().getRandomArticle();
		choose.setImageURI(Uri.parse(sta[3]));
		lefturl = sta[2];
		leftid = sta[0];
		//maybe make sta[1] a text display below

	}

	public void related(View view) {
		CRPC.getRPC().relateorunrelate(idrelater, idrelatee, true);
	}

	public void lefturl(View view) {
		//take lefturl and open the browser
	}

	public void righturl(View view) {

	}

	public void notrelated(View view) {

	}
}
