package plane;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * @author EC2EZ4RD
 *
 */
public class BossPlane {

	private int bossPlane_x, bossPlane_y;
	private Image ima;
	private final int step = 1;
	boolean stayed;
	private int position = -1, point;
	private Break b;
	int id;

	BossPlane() {
		bossPlane_x = 175;
		bossPlane_y = -500;
		ima = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/boss.png"));
		stayed = true;
	}

	void drawBoss(Graphics g, Canvas c) {
		if (stayed)
			g.drawImage(ima, bossPlane_x, bossPlane_y, GamePanel.BOSS_WIDTH, GamePanel.BOSS_HEIGHT, c);
		else if (id == 0) {
			b = new Break(bossPlane_x, bossPlane_y);
			b.boss_break(g, c, id);
			id++;
		} else {
			b.boss_break(g, c, id);
			id++;
		}
	}

	void bossMove() {
		if (bossPlane_y < 80)
			bossPlane_y += step;
		else
			switch (position) {
			case 0:
				bossPlane_y += step;
				bossPlane_x += step;
				if (bossPlane_x == GamePanel.MAP_WIDTH - GamePanel.BOSS_WIDTH)
					position++;
				break;
			case 1:
				if ((point / 700) % 2 == 0) {
					bossPlane_y += 3;
					bossPlane_x -= step;
				} else {
					bossPlane_y += 2;
					bossPlane_x -= 3;
				}
				if (bossPlane_y >= GamePanel.MAP_HEIGHT - GamePanel.BOSS_HEIGHT)
					position++;
				break;
			case 2:
				if ((point / 700) % 2 == 0) {
					bossPlane_y -= step;
					bossPlane_x -= step;
				} else {
					bossPlane_y -= 3;
					bossPlane_x -= step;
				}
				if (bossPlane_x <= 0)
					position++;
				break;
			case 3:
				bossPlane_x += step;
				if (bossPlane_x == 175)
					position = -1;
				break;
			default:
				point++;
				if (point % 300 == 0)
					position = 0;
				break;
			}
	}

	Point getX_Y() {

		return new Point(bossPlane_x, bossPlane_y);

	}

}
