package plane;

import java.net.URISyntaxException;

public class Collide {

	private PlaySound p;

	/**
	 * @param b
	 * @param e
	 */
	void bullet_enemy(Bullet b, Enemy e) {
		if (b.getX_Y().getX() >= e.getX_Y().getX() - GamePanel.BULLET_WIDTH
				&& b.getX_Y().getX() <= e.getX_Y().getX() + GamePanel.ENEMY_SIZE
				&& b.getX_Y().getY() >= e.getX_Y().getY() - GamePanel.BULLET_HEIGHT
				&& b.getX_Y().getY() <= e.getX_Y().getY() + GamePanel.ENEMY_SIZE) {
			b.stayed = false;
			e.stayed = false;
			if (PlaySound.b[2]) {
				p = new PlaySound();
				p.open("sounds/Break.wav");
				p.play();
				p.start();
			}
			GamePanel.sum += 100;
		}
	}

	void plane_enemy(MyPlane m, Enemy e) {
		if (m.getX_Y().getX() >= e.getX_Y().getX() - GamePanel.PLANE_SIZE
				&& m.getX_Y().getX() <= e.getX_Y().getX() + GamePanel.ENEMY_SIZE
				&& m.getX_Y().getY() >= e.getX_Y().getY() - GamePanel.PLANE_SIZE
				&& m.getX_Y().getY() <= e.getX_Y().getY() + GamePanel.ENEMY_SIZE) {
			e.stayed = false;
			if (GamePanel.live <= 50) {
				m.stayed = false;
				if (PlaySound.b[3]) {
					p = new PlaySound();
					p.open("sounds/HeroBrustSound.wav");
					p.play();
					p.start();
				}
				GamePanel.live = 0;
			} else
				GamePanel.live -= 50;
		}
	}

	void bullet_plane(Bullet b, MyPlane m) {
		if (b.getX_Y().getX() >= m.getX_Y().getX()
				&& b.getX_Y().getX() <= m.getX_Y().getX() + GamePanel.PLANE_SIZE - GamePanel.BULLET_WIDTH
				&& b.getX_Y().getY() >= m.getX_Y().getY()
				&& b.getX_Y().getY() <= m.getX_Y().getY() + GamePanel.PLANE_SIZE) {
			b.stayed = false;
			if (GamePanel.live <= 5) {
				m.stayed = false;
				if (PlaySound.b[3]) {
					p = new PlaySound();
					p.open("sounds/HeroBrustSound.wav");
					p.play();
					p.start();
				}
				GamePanel.live = 0;

			} else
				GamePanel.live -= 5;
		}
	}

	void boss_plane(BossPlane b, MyPlane m) {
		if (b.getX_Y().getX() >= m.getX_Y().getX() - GamePanel.BOSS_WIDTH
				&& b.getX_Y().getX() <= m.getX_Y().getX() + GamePanel.PLANE_SIZE
				&& b.getX_Y().getY() >= m.getX_Y().getY() - GamePanel.BOSS_HEIGHT
				&& b.getX_Y().getY() <= m.getX_Y().getY() + GamePanel.PLANE_SIZE) {
			m.stayed = false;
			if (PlaySound.b[3]) {
				p = new PlaySound();
				p.open("sounds/HeroBrustSound.wav");
				p.play();
				p.start();
			}
			GamePanel.live = 0;
		}
	}

	void bullet_boss(Bullet b, BossPlane m) {
		if (b.getX_Y().getX() >= m.getX_Y().getX()
				&& b.getX_Y().getX() <= m.getX_Y().getX() + GamePanel.BOSS_WIDTH - GamePanel.BULLET_WIDTH
				&& b.getX_Y().getY() >= m.getX_Y().getY()
				&& b.getX_Y().getY() <= m.getX_Y().getY() + GamePanel.BOSS_HEIGHT && m.getX_Y().getY() >= 0) {
			b.stayed = false;
			if (GamePanel.live1 <= 1) {
				m.stayed = false;
				if (PlaySound.b[3]) {
					p = new PlaySound();
					p.open("sounds/HeroBrustSound.wav");
					p.play();
					p.start();
				}
				GamePanel.live1 = 0;
				GamePanel.sum += 500;

			} else
				GamePanel.live1 -= 1;
		}
	}

}
