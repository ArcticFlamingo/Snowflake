package com.example.snowflake;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;


public class MyRenderer implements Renderer {
	
	Snowflake flake1;
	
	private float rotationAngle1 = 0;
	
	private float rotationSpeed1 = 1.0f;
	
	MyRenderer(int depth){
		flake1 = new Snowflake(depth);
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
	
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
	
		gl.glRotatef(rotationAngle1, 0.0f, 0.0f, 1.0f);
		flake1.draw(gl);
		flake1.changeColor();
		rotationAngle1 += rotationSpeed1;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);					
		gl.glEnable(GL10.GL_DEPTH_TEST);		
		gl.glDepthFunc(GL10.GL_LEQUAL);		
	}
	
	public void changeDepth(int newDepth){
		flake1 = new Snowflake(newDepth);
	}
	

	
}
