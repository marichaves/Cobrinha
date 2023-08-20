package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {
	// Declaração de objetos e variáveis da cena do jogo
	Rect background, foreground; // Declaração de retângulos de fundo e primeiro plano
	Snake snake; // Declaração da cobra
	KL keyListener; // Declaração do listener de teclado

	public Food food; // Declaração de objeto de comida

	public GameScene(KL keyListener) {
		// Construtor da cena do jogo
		background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT); // Criando retângulo de fundo
		foreground = new Rect(24, 48, Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH * 22); // Criando retângulo de
																								// primeiro plano
		snake = new Snake(3, 48, 48 + 24, 24, 24); // Criando a cobra com 3 segmentos
		this.keyListener = keyListener; // Inicializando o listener de teclado
		food = new Food(foreground, snake, 12, 12, Color.RED); // Criando comida na área do primeiro plano
		food.spawn(); // Gerando a posição inicial da comida
	}

	@Override
	public void update(double dt) {
		// Atualização lógica da cena do jogo
		if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
			snake.changeDirection(Direction.UP); // Mudando direção da cobra para cima
		} else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
			snake.changeDirection(Direction.DOWN); // Mudando direção da cobra para baixo
		} else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
			snake.changeDirection(Direction.RIGHT); // Mudando direção da cobra para direita
		} else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
			snake.changeDirection(Direction.LEFT); // Mudando direção da cobra para esquerda
		}

		if (!food.isSpawned)
			food.spawn(); // Se a comida não estiver gerada, gerar comida
		food.update(dt); // Atualizar estado da comida
		snake.update(dt); // Atualizar estado da cobra
	}

	@Override
	public void draw(Graphics g) {
		// Renderização gráfica da cena do jogo
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK); 
		g2.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height)); // Preenchendo
																											// retângulo
																											// de fundo

		g2.setColor(Color.PINK); 
		g2.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height)); // Preenchendo
																											// retângulo
																											// de
																											// primeiro
																											// plano

		snake.draw(g2); // Desenhando a cobra
		food.draw(g2); // Desenhando a comida
	}
}
