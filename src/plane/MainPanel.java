package plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author EC2EZ4RD
 *
 */
public class MainPanel extends JFrame {

	private JLabel back, label01, label02, label03, label04;
	private PlaySound p;
	private MainPanel m;

	public MainPanel() {
		
		super("雷电");

		setSize(800, 600);

		setLayout(null);

		showBackground();

		showLabel();

		adapter();
		m = this;
	}

	public void adapter() {

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (PlaySound.b[1]) {

					p = new PlaySound();

					p.open("sounds/ClickSound.wav");
					p.play();
					p.start();
				}

				int key = e.getKeyCode();

				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP) {
					label03.setVisible(!label03.isVisible());
					label04.setVisible(!label04.isVisible());

					if (label03.isVisible()) {
						label01.setForeground(Color.blue);
						label02.setForeground(Color.black);
					} else {
						label01.setForeground(Color.black);
						label02.setForeground(Color.blue);
					}
				}

				if (key == KeyEvent.VK_ENTER && label03.isVisible()) {

					GamePanel game = new GamePanel(m);
					add(game);
					game.setSize(800, 600);

					remove(label01);
					remove(label02);
					remove(label03);
					remove(label04);
					Bullet.before_time = System.currentTimeMillis();
				}

				if (key == KeyEvent.VK_ENTER && label04.isVisible()) {

					new Dialog(m, 0);
				}

			}

		});

	}

	private void showBackground() {

		ImageIcon background = new ImageIcon(getClass().getResource("/images/mainback.png"));

		back = new JLabel(background);

		back.setBounds(0, 0, getWidth(), getHeight());

		JPanel j = (JPanel) getContentPane();
		j.setOpaque(false);

		getLayeredPane().add(back, new Integer(Integer.MIN_VALUE));
	}

	private void showLabel() {

		ImageIcon icon = new ImageIcon(getClass().getResource("/images/point.png"));

		label01 = new JLabel("�?始游�?");
		label01.setFont(new Font("acefont-family", Font.BOLD, 50));
		label01.setForeground(Color.blue);
		label01.setBounds(220, 340, 400, 120);

		label02 = new JLabel("设置");
		label02.setFont(new Font("acefont-family", Font.BOLD, 50));
		label02.setBounds(320, 430, 200, 120);

		label03 = new JLabel(icon);
		label03.setBounds(40, 340, 250, 120);

		label04 = new JLabel(icon);
		label04.setBounds(130, 435, 250, 120);
		label04.setVisible(false);

		add(label01);
		add(label02);
		add(label03);
		add(label04);
	}

}
