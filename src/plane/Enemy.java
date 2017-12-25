package plane;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Enemy {
	
	private int enemy_x, enemy_y;
	private int enemy_y0;
	private Image ememyPic[];
	private final int STEP = 2;
	boolean stayed = true;
	private Break b;
	private int id;

	public Enemy(int y) {
		
		enemy_x = (int) (Math.random()*500);
		enemy_y0 = enemy_y = y;
				
		ememyPic = new Image[5];
		for(int i = 1; i <= ememyPic.length; i++) {
			ememyPic[i-1] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bullet0" + i + ".png"));
		}
	}

	void drawEnemy(Graphics g, Canvas c, int i) {
		if(stayed)
			g.drawImage(ememyPic[i], enemy_x, enemy_y, GamePanel.ENEMY_SIZE, GamePanel.ENEMY_SIZE, c);
		else if(id == 0) {
				b = new Break(enemy_x, enemy_y);
				b.enemy_break(g, c, id);
				id++;
			}

		if(b != null && id != 0)
			if(id == 29){
				b.enemy_break(g, c, id);
				id = 0;
			} else {
				b.enemy_break(g, c, id);
				id++;
			}
	}

	void enemyMove() {
		if(enemy_y > GamePanel.MAP_HEIGHT || stayed == false)
		{
			
			if(GamePanel.time >= 2500) {
				enemy_x = 0;
				enemy_y = GamePanel.MAP_HEIGHT+GamePanel.PLANE_SIZE;
			} else {
				enemy_x = (int) (Math.random()*500);
				enemy_y = enemy_y0;
				stayed = true;
			}
		} else
			enemy_y += STEP;
	}

	Point getX_Y() {
		
		return new Point(enemy_x, enemy_y);
		
	}
	
}
