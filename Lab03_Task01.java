package lab03;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab03_Task01 implements GLEventListener {

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab03_Task01 l = new Lab03_Task01();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(800, 800);
        //creating frame
        final JFrame frame = new JFrame("Five circles");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

    private GLU glu;

    @Override
    public void init(GLAutoDrawable gld) {

        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        Circle(gl, 0, 0, 120);    //larger 
        Circle(gl, 60, 0, 60);    //right 
        Circle(gl, 0, 60, 60);     //upper 
        Circle(gl, -60, 0, 60);    //left
        Circle(gl, 0, -60, 60);    //lower
    }
private void FivePoint(GL2 gl, int x, int y, int x1, int y1) {
        gl.glBegin(GL2.GL_POINTS);

        gl.glVertex2d(x + x1, y + y1);
        gl.glVertex2d(y + x1, x + y1);

        gl.glVertex2d(-x + x1, y + y1);
        gl.glVertex2d(-y + x1, x + y1);

        gl.glVertex2d(-x + x1, -y + y1);
        gl.glVertex2d(-y + x1, -x + y1);

        gl.glVertex2d(x + x1, -y + y1);
        gl.glVertex2d(y + x1, -x + y1);

        gl.glEnd();
    }
    private void Circle(GL2 gl, int x1, int y1, int r) {

        gl.glPointSize(2f);
        gl.glColor3d(1, 1, 1);
        int x = 0;
        int y = r;
        int d = 1 - y;
        FivePoint(gl, x, y, x1, y1);
        while (x < y) {
            if (d < 0) {
                d = d + 2 * x + 3;
                ++x;
            } else {
                d = d + 2 * x - 2 * y + 5;
                ++x;
                --y;
            }
            FivePoint(gl, x, y, x1, y1);

        }
    }


    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    public void dispose(GLAutoDrawable arg0) {

    }

}
