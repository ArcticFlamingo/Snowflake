package com.example.snowflake;

import javax.microedition.khronos.opengles.GL10;


	import java.nio.ByteBuffer;
	import java.nio.ByteOrder;
import java.nio.FloatBuffer;
	
	
public class Snowflake {
	
		int depth;
		private float vertexCoordinates[]; 
		private FloatBuffer vertexBuffer; 
		private int vSize; 
		private int vCount; 
		Vertex[] initialCoordinates;  
		int place = 0;
		int color = 0;
		
		public Snowflake(int depth){
			
			
			
			
			vSize = 3;
			this.depth = depth;
			vCount = 3*(int)Math.pow(4, depth);
			initialCoordinates = new Vertex[3];
			initialCoordinates[0]=new Vertex(-0.8f,-0.4f, 0.0f);
			initialCoordinates[1]= new Vertex(0.8f,-0.4f, 0.0f);
			initialCoordinates[2]= new Vertex(0.0f, 0.6f, 0.0f);
			
			vertexCoordinates = new float[10000];
			
			place = 0;
			
			
			side(depth, initialCoordinates[0], initialCoordinates[1]);
			side(depth, initialCoordinates[1], initialCoordinates[2]);
			side(depth, initialCoordinates[2], initialCoordinates[0]);
			
			ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertexCoordinates.length*4);
			byteBuf.order(ByteOrder.nativeOrder());
			vertexBuffer = byteBuf.asFloatBuffer();
			vertexBuffer.put(vertexCoordinates);
			
			
		}
		
		
		
		public void side(int level, Vertex p0, Vertex p1){
			
			
			 if (level == 0){
				 p0.write(vertexCoordinates);
			 }
			 else {
				 float deltax, deltay, x, y;
				 float factor;
				 
				 deltax = p1.x - p0.x;
				 deltay = p1.y - p0.y;
			 
				 
				 factor = 1f/3f;
				 x = p0.x +(deltax*factor);
				 y = p0.y +(deltay*factor); 
			
				 Vertex p13 = new Vertex(x, y, 1.0f);

				 factor = 2f/3f;
				 x = p0.x +(deltax*factor);
				 y = p0.y +(deltay*factor);
				 Vertex p23 = new Vertex(x, y, 0.5f);
			 
				 float distance = (float)Math.sqrt(3)/6f;
			 
				 x = (0.5f*(p0.x+p1.x) + distance *-(p0.y-p1.y));
				 y = (0.5f*(p0.y+p1.y) + distance *-(p1.x-p0.x));
			 
				 Vertex p12 = new Vertex(x, y , 0.0f);
				 
				 side(level-1, p0, p13);
				 side(level-1, p13, p12);
				 side(level-1, p12, p23);
				 side(level-1, p23, p1);
			 }
			 
		 }
		
		
		public void changeColor(){
			color++;
			if(color>300){
				color = 0;
			}
		}
		public void draw(GL10 gl){
			
			if(color == 0){
				gl.glColor4f(0.0f, 1.0f, 1.0f, 1.0f); //white
			}
			else if(color == 100){
				gl.glColor4f(0.0f, 0.6f, 1.0f, 1.0f); //light blue
			}
			else if(color == 200){
				gl.glColor4f(0.0f, 0.2f, 1.0f, 1.0f);//blue		
			}
			else if(color == 300){
				gl.glColor4f(0.0f, 0.0f, 0.8f, 1.0f);//dark blue
			}
			gl.glFrontFace(GL10.GL_CCW);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			vertexBuffer.position(0);
			
			gl.glVertexPointer(3, GL10.GL_FLOAT, vSize*4, vertexBuffer);
			gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, vCount);
			
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);		
		}

		
		class Vertex{
			float x;
			float y;
			float z;
			public Vertex(float x, float y, float z) {
				super();
				this.x = x;
				this.y = y;
				this.z = z;
			}
			/**
			 * prints the 3 components (x,y,z) to the array starting from place
			 */
			void write(float v[]){
				v[place++]=x;
				v[place++]=y;
				v[place++]=z;
			}
			
			void output(){
				System.out.println(x+" "+y+" "+z+" ");
			}

		}
		 
		
		 
	}
