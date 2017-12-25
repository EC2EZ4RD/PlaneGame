package plane;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author EC2EZ4RD
 *
 */
public class GamePanel extends JPanel {

	private Image bg1, bg2;
	private final int SCREEN_WIDTH = 800;
	private final int SCREEN_HEIGHT = 600;
	static final int MAP_WIDTH = 600;
	static final int MAP_HEIGHT = 600;
	static final int BULLET_WIDTH = 15;
	static final int BULLET_HEIGHT = 30;
	static final int PLANE_SIZE = 100;
	static final int ENEMY_SIZE = 80;
	static final int BOSS_WIDTH = 250;
	static final int BOSS_HEIGHT = 180;
	private int bg1_y;
	private int bg2_y;
	private boolean isRunning = false;
	private Canvas jp;
	private JButton jb1, jb2, jb3;
	private JLabel jl;
	private MainPanel m;
	static long sum;
	static int live;
	static int live1;

	static int time;
	static long before_time2 = System.currentTimeMillis();

	private Image iBuffer;
	private Graphics gBuffer;

	public GamePanel(MainPanel m) {

		bg1_y = 0;
		bg2_y = -SCREEN_HEIGHT;

		setLayout(null);
		setBackground(new Color(83, 163, 238));

		showPanel();

		this.m = m;

		live = 100;
		live1 = 2000;

	}

	/**
	 * 此方法作用是显示界面
	 */
	private void showPanel() {

		sum = 0;

		jp = new MapPanel();
		jp.setBounds(200, 0, 600, 600);
		add(jp);

		jb1 = new JButton("�?�?(P)");
		jb1.setBounds(50, 60, 100, 50);
		add(jb1);
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				stop_start();

			}
		});

		jb2 = new JButton("重新�?�?");
		jb2.setBounds(50, 140, 100, 50);
		add(jb2);
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				remove(jp);
				jp = new MapPanel();
				jp.setBounds(200, 0, 600, 600);
				add(jp);

				isRunning = false;
				jb1.setText("�?�?(P)");
				Bullet.before_time = System.currentTimeMillis();

				jb1.setEnabled(true);
				sum = 0;

				live = 100;
				live1 = 2000;

				time = 0;

			}
		});

		jb3 = new JButton("返回主菜�?");
		jb3.setBounds(50, 220, 100, 50);
		add(jb3);
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Point p = PanelFrame.e1.getLocation();
				PanelFrame.e1.dispose();
				isRunning = false;
				PanelFrame.main(null);
				PanelFrame.e1.setLocation(p);

			}
		});

		jl = new JLabel("0", JLabel.CENTER);
		jl.setBounds(40, 300, 120, 50);
		jl.setFont(new Font("acefont-family", Font.BOLD, 30));
		jl.setForeground(Color.red);
		add(jl);
	}

	private class MapPanel extends Canvas implements Runnable {

		private MyPlane mp;
		private BossPlane bp;
		private Bullet b;
		private Thread th;
		private ArrayList<Bullet> array;
		private ArrayList<Bullet> array1;
		private Enemy[] e_array;
		private Collide c;
		private Image x;
		private ArrayList<Bullet[]> arr;

		MapPanel() {

			requestFocusInWindow();
			bg1 = bg2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/mapback.png"));

			mp = new MyPlane();
			mp.adapter(this);

			bp = new BossPlane();

			array = new ArrayList<Bullet>();
			array1 = new ArrayList<Bullet>();
			arr = new ArrayList<Bullet[]>();

			e_array = new Enemy[8];

			c = new Collide();

			adapter();

			for (int i = 0; i < e_array.length; i++)
				e_array[i] = new Enemy((-i - 1) * ENEMY_SIZE - BULLET_HEIGHT);

			th = new Thread(this);
			th.start();

		}

		public void paint(Graphics g) {

			jl.setText("" + sum);

			if (iBuffer == null) {
				iBuffer = createImage(MAP_WIDTH, MAP_HEIGHT);
				gBuffer = iBuffer.getGraphics();
			}
			gBuffer.fillRect(0, 0, MAP_WIDTH, MAP_HEIGHT);

			gBuffer.drawImage(bg1, 0, bg1_y, 600, 600, this);
			gBuffer.drawImage(bg2, 0, bg2_y, 600, 600, this);

			mp.drawMyPlane(gBuffer, this);

			for (int i = 0; i < arr.size() && arr.size() > 0; i++)
				for (int j = 0; j < 5; j++)
					if (arr.get(i)[j] != null)
						arr.get(i)[j].drawBullet(gBuffer, this, 2);

			if (time >= 2500) {
				if (bp.getX_Y().getY() >= 80 && System.currentTimeMillis() - before_time2 >= 2500 && bp.stayed) {

					Bullet[] bt = new Bullet[5];
					for (int i = 0; i < 5; i++) {
						bt[i] = new Bullet((int) (bp.getX_Y().getX()) + BOSS_WIDTH / 2 - 5,
								(int) (bp.getX_Y().getY()) + BOSS_HEIGHT - 30);
						bt[i].drawBullet_3(gBuffer, this);
					}
					arr.add(bt);
					before_time2 = System.currentTimeMillis();
				}
				if (!bp.stayed && bp.id == 30) {
					jb1.setEnabled(false);
					isRunning = false;
					new Dialog(m, 2);
				}
				bp.drawBoss(gBuffer, this);
			}

			if (mp.stayed) {

				b = new Bullet((int) (mp.getX_Y().getX()) + PLANE_SIZE / 2 - BULLET_WIDTH / 2,
						(int) (mp.getX_Y().getY()) - BULLET_HEIGHT);
				b.drawBullet_1(array, gBuffer, this);
				for (int i = 0; i < array.size() && array.size() > 1; i++) {
					array.get(i).drawBullet(gBuffer, this, 1);
				}
			} else if (mp.id == 30) {
				jb1.setEnabled(false);
				isRunning = false;
				new Dialog(m, 1);
			}

			for (int i = 0; i < e_array.length; i++) {
				e_array[i].drawEnemy(gBuffer, this, i % 5);
				if (e_array[i].stayed && e_array[i].getX_Y().getY() > 0) {
					int t = (int) (e_array[i].getX_Y().getY()) + ENEMY_SIZE;
					if (t < MAP_HEIGHT) {

						b = new Bullet((int) (e_array[i].getX_Y().getX()) + ENEMY_SIZE / 2 - BULLET_WIDTH / 2, t);
						b.drawBullet_2(array1, gBuffer, this, i);
					}

				}
				for (int j = 0; j < array1.size(); j++) {
					array1.get(j).drawBullet(gBuffer, this, 2);
				}
			}

			if (live1 >= 0 && bp.stayed) {

				x = Toolkit.getDefaultToolkit()
						.getImage(getClass().getResource("/images/xue_" + ((2000 - live1) / 100 + 1) + ".png"));
				gBuffer.drawImage(x, (int) (bp.getX_Y().getX()), (int) (bp.getX_Y().getY() - 10), 250, 10, this);
			}

			if (live >= 0) {

				x = Toolkit.getDefaultToolkit()
						.getImage(getClass().getResource("/images/xue_" + ((100 - live) / 5 + 1) + ".png"));
				gBuffer.drawImage(x, 7, 7, 200, 20, this);
			}

			g.drawImage(iBuffer, 0, 0, null);
		}

		public void update(Graphics g) {
			paint(g);
		}

		@Override
		public void run() {

			while (isRunning) {

				draw();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				time += 1;
			}
		}

		private void draw() {

			mapMove();

			mp.planeMove();

			if (time >= 2500) {
				c.boss_plane(bp, mp);
				bp.bossMove();
			}

			for (int i = 0; i < array.size(); i++)
				array.get(i).bulletMove(array, i);

			for (int i = 0; i < array1.size(); i++) {
				c.bullet_plane(array1.get(i), mp);
				array1.get(i).bulletMove1(array1, i);

			}

			for (int i = 0; i < e_array.length; i++) {
				e_array[i].enemyMove();
				c.plane_enemy(mp, e_array[i]);
			}

			for (int i = 0; i < array.size(); i++)
				for (int j = 0; j < e_array.length; j++) {
					c.bullet_enemy(array.get(i), e_array[j]);
					c.bullet_boss(array.get(i), bp);
				}

			for (int i = 0; i < arr.size(); i++)
				for (int j = 0; j < 5; j++) {
					if (arr.get(i)[j] != null) {
						c.bullet_plane(arr.get(i)[j], mp);
						arr.get(i)[j].bulletMove2(arr, i, j);
					}
				}

			repaint();
		}

		private void mapMove() {

			bg1_y += 1;
			bg2_y += 1;
			if (bg1_y == SCREEN_HEIGHT)
				bg1_y = -SCREEN_HEIGHT;
			if (bg2_y == SCREEN_HEIGHT)
				bg2_y = -SCREEN_HEIGHT;

		}

		private void adapter() {
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {

					if (e.getKeyCode() == KeyEvent.VK_P)
						stop_start();
				}
			});
		}

	}

	private void stop_start() {
		if (isRunning == true) {
			isRunning = false;
			jb1.setText("�?�?(P)");

		} else {
			isRunning = true;
			Thread d = new Thread((Runnable) jp);
			d.start();
			jb1.setText("暂停(P)");
		}
	}

}
