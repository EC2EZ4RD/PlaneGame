package plane;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyPlane {

	private int myPlane_x = 230, myPlane_y = 450;
	private Image planePic[];
	private final int STEP = 7;
	private boolean isPress01, isPress02, isPress03, isPress04;
	boolean stayed = true;
	private Break b;
	int id;
	static int planeID;
	
	public MyPlane() {
		
		planePic = new Image[5];
		for(int i = 1; i <= planePic.length; i++) {
			planePic[i-1] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Plane0" + i + ".png"));
		}
		
	}

	void drawMyPlane(Graphics g, Canvas c) {
		if(stayed)
			g.drawImage(planePic[planeID], myPlane_x, myPlane_y, GamePanel.PLANE_SIZE, GamePanel.PLANE_SIZE, c);
		else if(id == 0) {
			b = new Break(myPlane_x, myPlane_y);
			b.plane_break(g, c, id);
			id++;
		} else {
			b.plane_break(g, c, id);
			id++;
		}
			
	}

	void adapter(Canvas c) {
		
		c.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_UP)
					isPress01 = true;

				if(key == KeyEvent.VK_DOWN)
					isPress02 = true;

				if(key == KeyEvent.VK_LEFT)
					isPress03 = true;

				if(key == KeyEvent.VK_RIGHT)
					isPress04 = true;
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_UP)
					isPress01 = false;

				if(key == KeyEvent.VK_DOWN)
					isPress02 = false;

				if(key == KeyEvent.VK_LEFT)
					isPress03 = false;

				if(key == KeyEvent.VK_RIGHT)
					isPress04 = false;
			}
			
		});
		
	}
	/**
	 * 控制飞机移动方法
	 */
	void planeMove() {
				
		if(isPress01)
			if(myPlane_y > 0)
				myPlane_y -= STEP;

		if(isPress02)
			if(myPlane_y < GamePanel.MAP_HEIGHT-GamePanel.PLANE_SIZE)
				myPlane_y += STEP;

		if(isPress03)
			if(myPlane_x > 0)
				myPlane_x -= STEP;

		if(isPress04)
			if(myPlane_x < GamePanel.MAP_HEIGHT-GamePanel.PLANE_SIZE)
				myPlane_x += STEP;

	}

	Point getX_Y() {
		
		return new Point(myPlane_x, myPlane_y);
		
	}
}
