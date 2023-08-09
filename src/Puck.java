import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class Puck extends JPanel {
	static boolean asar1;
	static boolean asar2;
	static boolean large;
	static boolean fire;
	static boolean mirror;
	static boolean neshan;
	static boolean ghabli = false;
	int init = -1;
	int X = -1000;
	int Y = -1000;
	Pads pad = new Pads();
	private static int size = 60;
	private static int x = GamePanel.WIDTH / 2;
	private static int y = GamePanel.HEIGHT / 2;
	private static int vx = 2;
	private static int vy = 2;
	int player1_score = 0;
	int player2_score = 0;
	private Image puck;
	public void pScore_1(){
		player1_score += 1;
	}
	public void pScore_2(){
		player2_score += 1;
	}
	public int regX(int d){
		vx = d;
		return vx;
	}
	public int regY(int d){
		vy = d;
		return vy;
	}
	public int up(){
		return vy = -2;
	}
	public int down(){
		return vy = 2;
	}
	public int left(){
	     int es1 = (int)(Math.random() * -10);
	     if(es1 >= -4){
	    	 es1 = -5;
	     }
	     
		return vx = es1;
	}
	public int right(){
	     int es1 = (int)(Math.random() * 10);
	     if(es1 <= 4){
	    	 es1 = 5;
	     }
		return vx = es1;
	}
	public void move(){
		if (vx > 5){
			vx = 5;
		}
		if (vy > 5){
			vy = 5;
		}
		setY(getY() + vy);
		setX(getX() + vx);
		puck = new ImageIcon("puck.png").getImage();
		if (!large) {
			if (getX() >= GamePanel.WIDTH) {
				if (getY() >= 300 && getY() <= 500) {
					pScore_1();
					ghabli = false;
					fire = false;
					large = false;
					mirror = false;
					setY(GamePanel.HEIGHT / 2);
					setX(GamePanel.WIDTH / 2 + 100);
					int es1 = (int)(Math.random() * -3);
					int es2 = (int)(Math.random() * -3);

					if(es1 >= 0){
						es1 = -1;
					}else if(es2 >= 0){
						es2 = -1;
					}

					regX(es1);
					regY(es2);
					pad.reset();
				} else {
					setX(getX() + left());
				}
			} else if (getX() <= 0) {
				if (getY() >= 300 && getY() <= 500) {
					pScore_2();
					fire = false;
					ghabli = false;
					large = false;
					mirror = false;
					setY(GamePanel.HEIGHT / 2);
					setX(GamePanel.WIDTH / 2 - 100);
					int es1 = (int)(Math.random() * 3);
					int es2 = (int)(Math.random() * 3);
					if(es1 <= 0){
						es1 = 1;
					}else if(es2 <= 0){
						es2 = 1;
					}

					regX(es1);
					regY(es2);
					pad.reset();
				} else {
					setX(getX() + right());
				}
			}
		}
		else {
			if (whatIsFalse(asar1 , asar2) == 1){
				if(getX() <= 0) {
					if (getY() >= 200 && getY() <= 600) {
						pScore_2();
						ghabli = false;
						fire = false;
						large = false;
						mirror = false;
						setY(GamePanel.HEIGHT / 2);
						setX(GamePanel.WIDTH / 2 - 100);
						int es1 = (int)(Math.random() * 3);
						int es2 = (int)(Math.random() * 3);
						if(es1 <= 0){
							es1 = 1;
						}else if(es2 <= 0){
							es2 = 1;
						}
						regX(es1);
						regY(es2);
						pad.reset();
					} else {
						setX(getX() + right());
					}
				}
				else if (getX() >= GamePanel.WIDTH){
					if (getY() >= 300 && getY() <= 500) {
						pScore_1();
						ghabli = false;
						fire = false;
						large = false;
						mirror = false;
						setY(GamePanel.HEIGHT / 2);
						setX(GamePanel.WIDTH / 2 + 100);
						int es1 = (int)(Math.random() * -3);
						int es2 = (int)(Math.random() * -3);

						if(es1 >= 0){
							es1 = -1;
						}else if(es2 >= 0){
							es2 = -1;
						}

						regX(es1);
						regY(es2);
						pad.reset();
					} else {
						setX(getX() + left());
					}
				}
			}
			else {
				if(getX() >= GamePanel.WIDTH) {
					if (getY() >= 200 && getY() <= 600) {
						pScore_1();
						ghabli = false;
						fire = false;
						large = false;
						mirror = false;
						setY(GamePanel.HEIGHT / 2);
						setX(GamePanel.WIDTH / 2 + 100);
						int es1 = (int)(Math.random() * -3);
						int es2 = (int)(Math.random() * -3);

						if(es1 >= 0){
							es1 = -1;
						}else if(es2 >= 0){
							es2 = -1;
						}

						regX(es1);
						regY(es2);
						pad.reset();
					} else {
						setX(getX() + left());
					}
				}
				else if (getX() <= 0){
					if (getY() >= 300 && getY() <= 500) {
						pScore_2();
						ghabli = false;
						fire = false;
						large = false;
						mirror = false;
						setY(GamePanel.HEIGHT / 2);
						setX(GamePanel.WIDTH / 2 - 100);
						int es1 = (int)(Math.random() * 3);
						int es2 = (int)(Math.random() * 3);
						if(es1 <= 0){
							es1 = 1;
						}else if(es2 <= 0){
							es2 = 1;
						}
						regX(es1);
						regY(es2);
						pad.reset();
					} else {
						setX(getX() + right());
					}
				}
			}
		}
		if (getX() >= X  - 60 && getX() <= X + 60 && getY() >= Y  - 60 && getY() <= Y + 60){
			ghabli = false;
			if (init == 0){
				neshan = false;
				large = true;
				fire = false;
				mirror = false;
			}
			else if (init == 1){
				neshan = false;
				fire = true;
				mirror = false;
				large = false;
			}
			else if (init == 2){
				neshan = false;
				mirror = true;
				large = false;
				fire = false;
			}
		}
		if (!fire){
			collision1();
			collision2();
		}
		else {
			vx *= 1.1;
			vy *= 1.1;
			if (whatIsFalse(asar1 , asar2) == 1){
				collision2();
			}
			else {
				collision1();
			}
		}
		if (!mirror){
			if(getY() >= GamePanel.HEIGHT - size){
				setY(getY() + up());
			}else if(getY() <= 0){
				setY(getY() + down());
			}
		}
		else {
			if(getY() >= GamePanel.HEIGHT - size){
				setY(0);
			}else if(getY() <= 0){
				setY(GamePanel.HEIGHT - size);
			}
		}
	}
	public int whatIsFalse(boolean asar1 , boolean asar2){
		if (!asar1){
			return 1;
		}
		else if (!asar2){
			return 2;
		}
		return 0;
	}
	public void collision1(){
		if(thepuck().intersects(pad.getPad1())){
			int es1 = (int)(Math.random() * -2);

			if(es1 >= 0){
				es1 = -1;
			}

			setX(getX() + right());
			setY(getY() + es1);
			asar1 = true;
			asar2 = false;
		}
	}
	public void collision2(){
		if(thepuck().intersects(pad.getPad2())){

			int es2 = (int)(Math.random() * 2);

			if(es2 <= 0){
				es2 = 1;
			}

			setX(getX() + left());
			setY(getY() + es2);
			asar2 = true;
			asar1 = false;
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		g.setFont(new Font("Arial", Font.BOLD, 48));
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(player1_score), 50, 750);
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(player2_score), 1450, 750);
		if (Main.time % 10000 == 0 && !ghabli) {
			init = Main.random.nextInt(3);
			X = Main.random.nextInt(1500);
			Y = Main.random.nextInt(800);
			neshan = true;
			ghabli = true;
		}
		if (Main.timeInit % 5000 == 0 && ghabli){
			if (neshan){
				if (init == 0) {
					g.drawImage(new ImageIcon("large2.png").getImage(), X, Y, null);
				} else if (init == 1) {
					g.drawImage(new ImageIcon("fire2.png").getImage(), X, Y, null);

				} else {
					g.drawImage(new ImageIcon("mirror2.png").getImage(), X, Y, null);

				}
			}
		}
		if (neshan) {
			if (init == 0) {
				g.drawImage(new ImageIcon("large.png").getImage(), X, Y, null);
			} else if (init == 1) {
				g.drawImage(new ImageIcon("fire.png").getImage(), X, Y, null);

			} else {
				g.drawImage(new ImageIcon("mirror.png").getImage(), X, Y, null);

			}
		}
		g.drawImage(puck, getX(), getY(), null);
	}
	public int getY() {
		return y;
	}
	public static void setY(int ay) {
		y = ay;
	}
	public int getX() {
		return x;
	}
	public static void setX(int ax) {
		x = ax;
	}
	public Rectangle thepuck(){
		return new Rectangle(getX(), getY(), size, size);
	}
}
