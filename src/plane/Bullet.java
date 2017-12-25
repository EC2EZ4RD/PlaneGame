package plane;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 * @author EC2EZ4RD
 *
 */
public class Bullet {

	private int bullet_x, bullet_y;
	private final int STEP = 5;
	private final int STEP1 = 5;
	private Image bullet01, bullet02;
	private final int TIME = 200;
	private final int TIME1 = 5000;
	static long before_time;
	static long[] before_time1 = new long[10];
	boolean stayed = true;

	public Bullet(int x, int y) {

		bullet_x = x;
		bullet_y = y;

		bullet01 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bullet_01.png"));
		bullet02 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bullet_02.png"));

	}

	void drawBullet(Graphics g, Canvas c, int i) {
		if (i == 1)
			g.drawImage(bullet01, bullet_x, bullet_y, GamePanel.BULLET_WIDTH, GamePanel.BULLET_HEIGHT, c);
		else
			g.drawImage(bullet02, bullet_x, bullet_y, GamePanel.BULLET_WIDTH, GamePanel.BULLET_WIDTH, c);

	}

	void drawBullet_1(ArrayList<Bullet> array, Graphics g, Canvas c) {
		long now_time = System.currentTimeMillis();
		if (now_time - before_time >= TIME) {
			drawBullet(g, c, 1);
			array.add(this);
			before_time = now_time;

		}
	}

	void drawBullet_2(ArrayList<Bullet> array, Graphics g, Canvas c, int i) {
		long now_time = System.currentTimeMillis();
		if (now_time - before_time1[i] >= TIME1) {
			drawBullet(g, c, 2);
			array.add(this);
			before_time1[i] = now_time;

		}
	}

	void drawBullet_3(Graphics g, Canvas c) {
		drawBullet(g, c, 2);
	}

	void bulletMove(ArrayList<Bullet> array, int i) {

		if (bullet_y < -GamePanel.BULLET_HEIGHT || stayed == false)
			array.remove(i);
		else
			bullet_y -= STEP;
	}

	void bulletMove1(ArrayList<Bullet> array, int i) {

		if (bullet_y > GamePanel.MAP_HEIGHT || stayed == false)
			array.remove(i);
		else
			bullet_y += STEP1;
	}

	void bulletMove2(ArrayList<Bullet[]> arr, int i, int j) {
		if (stayed)
			switch (j) {
			case 0:
				bullet_x -= 2;
				bullet_y += 2;
				break;
			case 1:
				bullet_x -= 1;
				bullet_y += 2;
				break;
			case 2:
				bullet_y += 2;
				break;
			case 3:
				bullet_x += 1;
				bullet_y += 2;
				break;
			case 4:
				bullet_x += 2;
				bullet_y += 2;
				break;
			default:
				break;
			}
		else
			arr.get(i)[j] = null;
	}

	Point getX_Y() {

		return new Point(bullet_x, bullet_y);

	}
}
