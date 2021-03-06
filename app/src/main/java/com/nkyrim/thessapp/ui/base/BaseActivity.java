package com.nkyrim.thessapp.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nkyrim.thessapp.R;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Base Activity that extends AppCompatActivity and provides basic configuration
 */
public abstract class BaseActivity extends AppCompatActivity {
	// UI elements
	protected Toolbar toolbar;

	protected abstract int getLayoutResource();

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// make back/up simply terminate current activity
		if(item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Icepick.restoreInstanceState(this, savedInstanceState);
		setContentView(getLayoutResource());
		ButterKnife.bind(this);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if(toolbar != null) {
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Icepick.saveInstanceState(this, outState);
	}

	// Override start and finish methods to add transition animations
	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	@Override
	public void startActivityForResult(Intent intent, int code) {
		super.startActivityForResult(intent, code);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
	}
}
