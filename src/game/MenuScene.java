package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class MenuScene extends Scene {

	public KL keyListener;
	public ML mouseListener;
	public BufferedImage title, play, playPressed, exit, exitPressed;
	public Rect playRect, exitRect, titleRect; // Renamed ExitRect to exitRect
	public BufferedImage playCurrentImage, exitCurrentImage;

	// Constructor for the MenuScene class
	public MenuScene(KL keyListener, ML mouseListener) {
		this.keyListener = keyListener;
		this.mouseListener = mouseListener;
		try {
			BufferedImage spritesheet = ImageIO.read(new File("assets/menuSprite2.png"));
			title = spritesheet.getSubimage(0, 242, 960, 240);
			play = spritesheet.getSubimage(0, 121, 261, 121);
			playPressed = spritesheet.getSubimage(264, 121, 261, 121);
			exit = spritesheet.getSubimage(0, 0, 233, 93);
			exitPressed = spritesheet.getSubimage(264, 0, 233, 93);

		} catch (Exception e) {
			e.printStackTrace();
		}

		playCurrentImage = play;
		exitCurrentImage = exit;

		titleRect = new Rect(240, 100, 300, 100);
		playRect = new Rect(310, 280, 150, 70);
		exitRect = new Rect(318, 355, 130, 55);
	}

	@Override
	public void update(double dt) {
		if (mouseListener.getX() >= playRect.x && mouseListener.getX() <= playRect.x + playRect.width
				&& mouseListener.getY() >= playRect.y && mouseListener.getY() <= playRect.y + playRect.height) {
			playCurrentImage = playPressed;
			if(mouseListener.isPressed()) {
				Window.getWindow().changeState(1);
			}
		} else {
			playCurrentImage = play;
		}

		if (mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width
			    && mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height) {
			    exitCurrentImage = exitPressed;
			    if(mouseListener.isPressed()) {
			    	Window.getWindow().close();
			    }
			} else {
			    exitCurrentImage = exit;
			}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

		g.drawImage(title, (int) titleRect.x, (int) titleRect.y, (int) titleRect.width, (int) titleRect.height, null);
		g.drawImage(playCurrentImage, (int) playRect.x, (int) playRect.y, (int) playRect.width, (int) playRect.height,
				null);
		g.drawImage(exitCurrentImage, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width, (int) exitRect.height,
				null);
	}
}
