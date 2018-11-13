/* CS2150Coursework.java
 * robinss4, Samuel George Robinson
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *
 *  TODO: Provide a scene graph for your submission
 */
package Coursework;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import GraphicsLab.*;

/**
 * TODO: Briefly describe your submission here
 *
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * <li>Hold w, s, a, d to move the car forwards, backwards, left and right repsectively
 * </ul>
 * TODO: Add any additional controls for your sample to the list above
 *
 */
public class CS2150Coursework extends GraphicsLab
{
	private float rotx, roty, rotz;
	private float dx, dz;
	
    //TODO: Feel free to change the window title and default animation scale here
    public static void main(String args[])
    {   new CS2150Coursework().run(WINDOWED,"CS2150 Coursework Submission",0.01f);
    }

    protected void initScene() throws Exception
    {//TODO: Initialise your resources here - might well call other methods you write.
    	//global ambient light level
    	float globalAmbient[] = {0.2f, 0.2f, 0.2f ,1.0f};
    	//set global ambient lighting
    	GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
    	//enable lighting calculations
    	GL11.glEnable(GL11.GL_LIGHTING);
    	//ensure normals are correct
    	GL11.glEnable(GL11.GL_NORMALIZE);
    }
    protected void checkSceneInput()
    {//TODO: Check for keyboard and mouse input here
    	// Rotate the camera around the x axis
    	if(Keyboard.isKeyDown(Keyboard.KEY_X)) {
    		rotx += getAnimationScale();
    	}
    	// Rotate the camera around the y axis
    	if(Keyboard.isKeyDown(Keyboard.KEY_Y)) {
    		roty += getAnimationScale();
    	}
    	// Rotate the camera around the z axis
    	if(Keyboard.isKeyDown(Keyboard.KEY_Z)) {
    		rotz += getAnimationScale();
    	}
    	// Move the car forward
    	if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
    		dz -= 0.001f;
    	}
    	// Move the car back
    	if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
    		dz += 0.001f;
    	}
    	// Move the car left
    	if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
    		dx -= 0.001f;
    	}
    	// Move the car right
    	if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
    		dx += 0.001f;
    	}
    }
    protected void updateScene()
    {
        //TODO: Update your scene variables here - remember to use the current animation scale value
        //        (obtained via a call to getAnimationScale()) in your modifications so that your animations
        //        can be made faster or slower depending on the machine you are working on
    }
    protected void renderScene()
    {//TODO: Render your scene here - remember that a scene graph will help you write this method! 
     //      It will probably call a number of other methods you will write.
    	
    	/**
    	// position and draw the first rectangle
        GL11.glPushMatrix();
        {
	        GL11.glTranslatef(0.0f, -1.0f, -2.0f);
	        drawRectangle(Colour.BLUE,Colour.BLUE,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
        }
        GL11.glPopMatrix();
        
        // position and draw the second rectangle
        GL11.glPushMatrix();
        {
	        GL11.glTranslatef(6.0f, -1.0f, -2.0f);
	        drawRectangle(Colour.BLUE,Colour.BLUE,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
        }
        GL11.glPopMatrix();
        */
    	
    	// global ambient light level
        float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
        
        // the first light for the scene is white...
        float diffuse0[]  = { 0.6f,  0.6f, 0.6f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient0[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above and behind the viewpoint
        float position0[] = { 0.0f, 10.0f, 5.0f, 1.0f}; 

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
  		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);
        
    	// How shiny the car is
    	float carShininess = 10.0f;
    	// Specular reflection of the car
    	float carSpecular[] = {0.1f, 0.0f, 0.0f, 1.0f};
    	// Diffuse reflection of the car
    	float carDiffuse[] = {0.6f, 0.2f, 0.2f, 1.0f};
    	
    	// Set material properties for the car
    	GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, carShininess);
    	GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(carSpecular));
    	GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(carDiffuse));
    	GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(carDiffuse));
    	
    	GL11.glPushMatrix(); 
    	{
			// position and draw car
			GL11.glTranslatef(0.0f + dx, 1.0f, -2.0f + dz);
	        drawCar(Colour.BLUE,Colour.BLUE,Colour.RED,Colour.RED,Colour.GREEN,Colour.GREEN);
		} 
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		{
			//position and draw roads
			GL11.glTranslatef(0.0f, -1.0f, -2.0f);
			
		}
    }
    protected void setSceneCamera()
    {
        // call the default behaviour defined in GraphicsLab. This will set a default perspective projection
        // and default camera settings ready for some custom camera positioning below...  
        super.setSceneCamera();

        //TODO: If it is appropriate for your scene, modify the camera's position and orientation here
        //        using a call to GL11.gluLookAt(...)
        GLU.gluLookAt(2.0f + rotx, 15.0f + roty, 15.0f + rotz,   // viewer location        
  		      2.0f, 1.0f, -5.0f,    // view point location
  		      0.0f, 1.0f, 0.0f);   // view-up vector
   }

    protected void cleanupScene()
    {//TODO: Clean up your resources here
    }
    
    private void drawRectangle(Colour top, Colour bottom, Colour left, Colour right, Colour near, Colour far) {
    	//The vertices of the rectangle
    	Vertex v1 = new Vertex( 0.0f,  1.0f,  10.0f);
        Vertex v2 = new Vertex( 0.0f,  0.0f,  2.0f);
        Vertex v3 = new Vertex( 1.0f,  1.0f,  10.0f);
        Vertex v4 = new Vertex( 0.5f, -0.5f,  2.0f);
        Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v6 = new Vertex(-0.5f,  0.5f, -0.5f);
        Vertex v7 = new Vertex( 0.5f,  0.5f, -0.5f);
        Vertex v8 = new Vertex( 0.5f, -0.5f, -0.5f);
    	
        // draw the near face:
        near.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        left.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        right.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        top.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
       	bottom.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        far.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
    }
    
    private void drawPlane(Colour c) {
    	//The vertices of the plane
    	Vertex v1 = new Vertex(-0.5f, -0.5f,  2.0f);
        Vertex v2 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v3 = new Vertex( 0.5f, -0.5f, -0.5f);
        Vertex v4 = new Vertex( 0.5f, -0.5f,  2.0f);
        
        // draw the face:
        c.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
            v4.submit();
            v2.submit();
            v3.submit();
            v1.submit();
        }
        GL11.glEnd();
    }
    
    private void drawCar(Colour side, Colour top, Colour back, Colour front, Colour bottom, Colour window) {
    	//The vertices of the car
    	Vertex v1 = new Vertex( 0.0f,  0.0f,  5.0f);
        Vertex v2 = new Vertex( 4.0f,  0.0f,  5.0f);
        Vertex v3 = new Vertex( 0.0f,  2.0f,  5.0f);
        Vertex v4 = new Vertex( 4.0f,  2.0f,  5.0f);
        Vertex v5 = new Vertex( 0.0f,  3.0f,  4.0f);
        Vertex v6 = new Vertex( 4.0f,  3.0f,  4.0f);
        Vertex v7 = new Vertex( 0.0f,  3.0f,  2.0f);
        Vertex v8 = new Vertex( 4.0f,  3.0f,  2.0f);
        Vertex v9 = new Vertex( 0.0f,  2.0f,  0.0f);
        Vertex v10 = new Vertex(0.0f,  0.0f,  0.0f);
        Vertex v11 = new Vertex(4.0f,  2.0f,  0.0f);
        Vertex v12 = new Vertex(4.0f,  0.0f,  0.0f);
        
        // draw the front face:
        front.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v9.submit();
        	v10.submit();
        	v12.submit();
        	v11.submit();
        }
        GL11.glEnd();
        
        // draw the left side face:
        side.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v9.submit();
        	v7.submit();
        	v5.submit();
        	v3.submit();
        	v1.submit();
        	v10.submit();
        }
        GL11.glEnd();
        
        // draw the right side face:
        side.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v11.submit();
        	v12.submit();
        	v2.submit();
        	v4.submit();
        	v6.submit();
        	v8.submit();
        }
        GL11.glEnd();
        
        // draw the front window face:
        window.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v11.submit();
        	v9.submit();
        	v7.submit();
        	v8.submit();
        }
        GL11.glEnd();
        
	    // draw the rear window face:
        window.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v6.submit();
        	v5.submit();
        	v3.submit();
        	v4.submit();
        }
        GL11.glEnd();
        
	    // draw the rear face:
        back.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v4.submit();
        	v3.submit();
        	v1.submit();
        	v2.submit();
        }
        GL11.glEnd();
        
	    // draw the top face:
        top.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v8.submit();
        	v7.submit();
        	v5.submit();
        	v6.submit();
        }
        GL11.glEnd();
        
	    // draw the bottom face:
        bottom.submit();
        GL11.glBegin(GL11.GL_POLYGON);
        {
        	v2.submit();
        	v1.submit();
        	v10.submit();
        	v12.submit();
        }
        GL11.glEnd();
    }
}
