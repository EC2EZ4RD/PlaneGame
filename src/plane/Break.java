package plane;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;

/**
 * @author EC2EZ4RD
 *
 */
public class Break {

	private Image enemy_b[];
	private int x, y;
	JDialog f;

	public Break(int x, int y) {
		enemy_b = new Image[6];
		for (int i = 0; i < enemy_b.length; i++) {
			enemy_b[i] = Toolkit.getDefaultToolkit()
					.getImage(getClass().getResource("/images/bomb_enemy_" + i + ".png"));
		}

		this.x = x;
		this.y = y;
	}

	void enemy_break(Graphics g, Canvas c, int i) {
		g.drawImage(enemy_b[i / 5], x, y, GamePanel.ENEMY_SIZE, GamePanel.ENEMY_SIZE, c);
	}

	void plane_break(Graphics g, Canvas c, int i) {
		if (i < 30)
			g.drawImage(enemy_b[i / 5], x, y, GamePanel.PLANE_SIZE, GamePanel.PLANE_SIZE, c);
	}

	void boss_break(Graphics g, Canvas c, int i) {
		if (i < 30)
			g.drawImage(enemy_b[i / 5], x, y, GamePanel.BOSS_WIDTH, GamePanel.BOSS_HEIGHT, c);
	}

}
