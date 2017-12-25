package plane;

import java.net.URISyntaxException;

import javax.swing.JFrame;

public class PanelFrame {

	static MainPanel e1;
	static PlaySound p;
	
	public static void main(String[] args) {
		
		e1 = new MainPanel();
		
		e1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		e1.setVisible(true);
		
		e1.setResizable(false);
		if(p == null) {
			
			p = new PlaySound();


				p.open("sounds/OPSound.mid");	
				p.play();
				p.loop();
				p.start();




			
		}
	}
	
}
