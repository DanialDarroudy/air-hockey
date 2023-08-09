import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	static final int WIDTH = 1500;
	static final int HEIGHT = 800;
	static Image present;
	
	Pads pads = new Pads();
	Puck puck = new Puck();
	
	public GamePanel(){
		this.setFocusable(true);
		this.addKeyListener(
				new KeyListener(){
					public void keyPressed(KeyEvent e){
						switch(e.getKeyCode()){
						case KeyEvent.VK_UP:
								Pads.pad2_up();
								break;
						case KeyEvent.VK_DOWN:
								Pads.pad2_down();
								break;
						case KeyEvent.VK_LEFT:
								Pads.pad2_left();
								break;
						case KeyEvent.VK_RIGHT:
								Pads.pad2_right();
								break;
						case KeyEvent.VK_W:
								Pads.pad1_up();
								break;
						case KeyEvent.VK_S:
								Pads.pad1_down();
								break;
						case KeyEvent.VK_A:
 							    Pads.pad1_left();
								break;
						case KeyEvent.VK_D:
								Pads.pad1_right();
						        break;
						default:
								break;
						}
					}
					public void keyReleased(KeyEvent e) {
						switch (e.getKeyCode()){
							case KeyEvent.VK_UP:
							case KeyEvent.VK_DOWN:
								Pads.pad2_release();
								break;
							case KeyEvent.VK_LEFT:
							case KeyEvent.VK_RIGHT:
								Pads.pad2_release2();
								break;
							case KeyEvent.VK_W:
							case KeyEvent.VK_S:
								Pads.pad1_release();
								break;
							case KeyEvent.VK_A:
							case KeyEvent.VK_D:
								Pads.pad1_release1();
								break;
							default:
								break;
						}
					}
					public void keyTyped(KeyEvent arg0) {}
				}
		);
	}
	public void paint(Graphics g){
			pads.paint(g);
			puck.paint(g);
	}
	public void move() {
		Main.timeInit += 5;
			pads.move_pads();
			puck.move();
	}
}
