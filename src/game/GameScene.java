package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {
	Rect background, foreground;
	Snake snake;
	KL keyListener;
	
	public Food food;
	
	public GameScene(KL keyListener) {
		background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		foreground = new Rect(24, 48, Constants.TILE_WIDTH *31 , Constants.TILE_WIDTH*22);
		snake = new Snake(3, 48, 48 + 24, 24, 24); //Criando a cobrinha com 3 quadrados
		this.keyListener = keyListener;
		food = new Food(foreground, snake, 12, 12, Color.green);
		food.spawn();
	}
	
	@Override
	public void update(double dt) {
		if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
			snake.changeDirection(Direction.UP);
		} else if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
			snake.changeDirection(Direction.DOWN);
		} else if(keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
			snake.changeDirection(Direction.RIGHT);
		} else if(keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
			snake.changeDirection(Direction.LEFT);
		}
		
		if(!food.isSpawned) food.spawn();
		food.update(dt);
		snake.update(dt);
		
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));
		
		g2.setColor(Color.PINK);
		g2.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height ));
		
		snake.draw(g2);
		food.draw(g2);
	}

}
