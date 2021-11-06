package cse423_lab02;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab02_Task01 implements GLEventListener {

    private GLU glu;

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        //("ID : 19101050, have to draw 50");        
        
        mid_point(gl, -80, 90, -35, 90);
        mid_point(gl, -80, 50, -80, 90);
        mid_point(gl, -80, 50, -35, 50);      //for 5
        mid_point(gl, -35, 10, -35, 50);
        mid_point(gl, -80, 10, -35, 10);

        mid_point(gl, -15, 90, 40, 90);
        mid_point(gl, -15, 10, -15, 90);
        mid_point(gl, -15, 10, 40, 10);      //for 0
        mid_point(gl, 40, 10, 40, 90);

        gl.glEnd();

    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
    }

    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-100, -50, 50, 100);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    public void mid_point(GL2 gl, int x1, int y1, int x2, int y2) {
        int new_x1 = 0, new_y1 = 0, new_x2 = 0, new_y2 = 0, dx, dy, zone = -1;
        gl.glPointSize(3.0f);
        gl.glColor3d(1, 1, 1);
        dy = y2 - y1;
        dx = x2 - x1;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0 && dy >= 0) {
                zone = 0;
                new_x1 = x1;
                new_y1 = y1;
                new_x2 = x2;
                new_y2 = y2;
            } else if (dx < 0 && dy > 0) {
                zone = 3;
                new_x1 = -x1;
                new_y1 = y1;
                new_x2 = -x2;
                new_y2 = y2;
            } else if (dx < 0 && dy < 0) {
                zone = 4;
                new_x1 = -x1;
                new_y1 = -y1;
                new_x2 = -x2;
                new_y2 = -y2;
            } else if (dx > 0 && dy < 0) {
                zone = 7;
                new_x1 = x1;
                new_y1 = -y1;
                new_x2 = x2;
                new_y2 = -y2;
            }
        } else {
            if (dx >= 0 && dy > 0) {
                zone = 1;
                new_x1 = y1;
                new_y1 = x1;
                new_x2 = y2;
                new_y2 = x2;
            } else if (dx < 0 && dy > 0) {
                zone = 2;
                new_x1 = y1;
                new_y1 = -x1;
                new_x2 = y2;
                new_y2 = -x2;
            } else if (dx < 0 && dy < 0) {
                zone = 5;
                new_x1 = -y1;
                new_y1 = -x1;
                new_x2 = -x2;
                new_y2 = -y2;
            } else if (dx > 0 && dy < 0) {
                zone = 6;
                new_x1 = -y1;
                new_y1 = x1;
                new_x2 = -y2;
                new_y2 = x2;
            }
        }
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x1, y1);
        dy = new_y2 - new_y1;
        dx = new_x2 - new_x1;
        int d_init = (2 * dy) - dx;
        while (new_x1 <= new_x2) {
            int[] coordinate = back_to_main_zone(new_x1, new_y1, zone);
            gl.glVertex2f(coordinate[0], coordinate[1]);
            new_x1++;
            if (d_init > 0) {
                new_y1 += 1;
                d_init += (2 * dy) - (2 * dx);
            } else {
                d_init += 2 * dy;
            }
        }

    }

    static int[] back_to_main_zone(int x1, int y1, int zone) {
        int[] coordinate = new int[2];
        switch (zone) {
            case 0:
                coordinate[0] = x1;
                coordinate[1] = y1;
                break;
            case 3:
                coordinate[0] = -x1;
                coordinate[1] = y1;
                break;
            case 4:
                coordinate[0] = -x1;
                coordinate[1] = -y1;
                break;
            case 7:
                coordinate[0] = x1;
                coordinate[1] = -y1;
                break;
            case 1:
                coordinate[0] = y1;
                coordinate[1] = x1;
                break;
            case 2:
                coordinate[0] = -y1;
                coordinate[1] = x1;
                break;
            case 5:
                coordinate[0] = -y1;
                coordinate[1] = -x1;
                break;
            case 6:
                coordinate[0] = y1;
                coordinate[1] = -x1;
                break;
            default:
                break;
        }
        return coordinate;
    }

    public static void main(String[] args) {
        // getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab02_Task01 l = new Lab02_Task01();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(700, 700);
        // creating frame
        final JFrame frame = new JFrame("Five and Zero");
        // adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}
