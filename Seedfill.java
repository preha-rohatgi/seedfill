
package org.yourorghere;
import static java.lang.Integer.signum;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Stack;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
//import javax.media.opengl.GLCapabilities;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
class ThirdGLEventListener implements GLEventListener {
ArrayList<String> bv=new ArrayList<String>();
ArrayList<String> color=new ArrayList<String>();
Stack<String> st=new Stack();
private GLU glu;

public void init(GLAutoDrawable gld) {
    GL gl = gld.getGL();
    glu = new GLU();
    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    gl.glViewport(0,0,640,480);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluOrtho2D(0,640,0,480);
}
/**
 * Take care of drawing here.
 */
public void display(GLAutoDrawable drawable) {
    GL gl = drawable.getGL();

    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    /*
     * put your code here
     */
    //drawLine(gl, 0, 0, 100, 100);
    gl.glColor3f(1.0f, 0.0f, 0.0f );
    
    Bresenhams(gl,300,100,330,100);
    Bresenhams(gl,330,100,330,130);
    Bresenhams(gl,330,130,360,130);
    Bresenhams(gl,360,130,360,100);
    Bresenhams(gl,360,100,400,100);
    Bresenhams(gl,400,100,400,200);
    Bresenhams(gl,400,200,300,200);
    Bresenhams(gl,300,200,300,100);
    System.out.println(bv.size());
    System.out.println(color.size());
    gl.glColor3f(0.0f, 0.0f, 1.0f );
    fill(gl,320,110);
 
    
    
    
}
public void reshape(GLAutoDrawable drawable, int x, int y, int width,
        int height) {
}
public void displayChanged(GLAutoDrawable drawable,
        boolean modeChanged, boolean deviceChanged) {
}


private void Bresenhams(GL gl,int x1,int y1,int x2,int y2){
    int x=x1;
    int y=y1;
    int dx=abs(x2-x1);
    int dy=abs(y2-y1);
    int s1=signum(x2-x1);
    int s2=signum(y2-y1);
    int interchange=0;
    int i;
    if(dy>dx){
        int temp=dx;
        dx=dy;
        dy=temp;
        interchange=1;
    }
    int e1=2*dy-dx;
    for( i=1;i<=dx;i++){
        gl.glBegin(GL.GL_POINTS);// begin plotting points
        String s="";
        gl.glVertex2i(x,y);
        s=s+Integer.toString(x)+","+Integer.toString(y);
        bv.add(s);
        
    gl.glEnd();
    while(e1>0){
        if(interchange==1){
            x=x+s1;
        }
        else{
            y=y+s2;
        }
        e1=e1-2*dx;
    }
    if(interchange==1){
        y=y+s2;   
        
    }
    else{
        x=x+s1;
        
    }
    e1=e1+2*dy;

    }
    
}
public void fill(GL gl,int x,int y){
    st.push(Integer.toString(x)+","+Integer.toString(y));
    while(!(st.empty())){   
    String a=st.pop();
    x=Integer.parseInt(a.split(",")[0]);
    y=Integer.parseInt(a.split(",")[1]);
    gl.glBegin(GL.GL_POINTS);
    String s;
    gl.glVertex2d(x, y);
   // s=Integer.toString(x)+","+Integer.toString(y);
    System.out.println(a);
    color.add(a);
    gl.glEnd();
    //String s1=Integer.toString(x)+","+Integer.toString(y);
    if(!(bv.contains(Integer.toString(x+1)+","+Integer.toString(y)) || color.contains(Integer.toString(x+1)+","+Integer.toString(y)))){
        st.push(Integer.toString(x+1)+","+Integer.toString(y));
    
}
    if(!(bv.contains(Integer.toString(x)+","+Integer.toString(y+1)) || color.contains(Integer.toString(x)+","+Integer.toString(y+1)))){
        st.push(Integer.toString(x)+","+Integer.toString(y+1));
    
}
    if(!(bv.contains(Integer.toString(x-1)+","+Integer.toString(y)) || color.contains(Integer.toString(x-1)+","+Integer.toString(y)))){
        st.push(Integer.toString(x-1)+","+Integer.toString(y));
    
}
    if(!(bv.contains(Integer.toString(x)+","+Integer.toString(y-1)) || color.contains(Integer.toString(x)+","+Integer.toString(y-1)))){
        st.push(Integer.toString(x)+","+Integer.toString(y-1));
    
}}}
    
  







public void dispose(GLAutoDrawable arg0)
{
 }
}
public class Seedfill
{
public static void main(String args[])
{      //getting the capabilities object of GL2 profile
    //final GLProfile profile=GLProfile.get(GLProfile.GL);
    GLCapabilities capabilities=new GLCapabilities();
    // The canvas
    final GLCanvas glcanvas=new GLCanvas(capabilities);
    ThirdGLEventListener b=new ThirdGLEventListener();
    glcanvas.addGLEventListener(b);
    glcanvas.setSize(400, 400);
    //creating frame
    final JFrame frame=new JFrame("Basic frame");
    //adding canvas to frame
    frame.add(glcanvas);
    frame.setSize(1000,1000);
    frame.setVisible(true); }
}

