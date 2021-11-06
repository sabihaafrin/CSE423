import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.util.Random;

public class cse423_lab01 implements GLEventListener{

   static GLProfile profile = GLProfile.get(GLProfile.GL2);
   static GLCapabilities capabilities = new GLCapabilities(profile);

   static GLCanvas glcanvas = new GLCanvas(capabilities);

   public static void main(String[] args) {

       cse423_lab01 l = new cse423_lab01();

       glcanvas.addGLEventListener((GLEventListener) l);
       glcanvas.setSize(800, 800);

       final JFrame frame = new JFrame ("straight Line");

       frame.getContentPane().add(glcanvas);
       frame.setSize(frame.getContentPane().getPreferredSize());
       frame.setVisible(true);

   }
   public void display(GLAutoDrawable drawable) {
       final GL2 gl = drawable.getGL().getGL2();
       gl.glColor3d(70,70,70);
       Random r = new Random();
       double low = -1.0;
       double high = 1.0;
       gl.glBegin(GL2.GL_POINTS);

       for(int i = 0; i<100; i++) {
           double x = low + (high - low) * r.nextDouble();
           double y = low + (high - low) * r.nextDouble();

           gl.glVertex2d(x, y);
       }

       gl.glEnd();


   }

   public void dispose(GLAutoDrawable arg0) {

   }


   public void init(GLAutoDrawable drawable) {

   }

   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

   }
}