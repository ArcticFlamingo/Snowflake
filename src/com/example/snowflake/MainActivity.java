package com.example.snowflake;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private MyRenderer mGLRenderer;
	private GLSurfaceView mGLView;
	int depth = 2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		mGLRenderer = new MyRenderer(depth);
		mGLView = new GLSurfaceView(this);
		mGLView.setRenderer(mGLRenderer);
		setContentView(mGLView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.mymenu, menu);
		    menu.setGroupCheckable(R.id.group1, true, true);
		    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.item1:  //Depth 1
	    	item.setChecked(true);
	        mGLRenderer.changeDepth(2);
	    	mGLView.requestRender();
	    	return true;
	    case R.id.item2:  //Depth 2
	    	item.setChecked(true);
	    	mGLRenderer.changeDepth(3);
	    	mGLView.requestRender();
	        return true;
	    case R.id.item3:  //Depth 3
	    	item.setChecked(true);
	    	mGLRenderer.changeDepth(4);
	    	mGLView.requestRender();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	
	
	
	
	
	
}
