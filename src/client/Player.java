package client;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Player {
	protected Circle player;
	protected double x;
	protected double y;
	protected double offSetX;
	protected double offSetY;
	
	
	public Player(double x, double y, int radius, Color color) {
		this.player = new Circle(x, y, radius);
		this.x = x;
		this.y = y;
		this.player.setFill(color);
	}
	




	protected abstract void movePlayer(double newX, double newY);
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public Circle getPlayer() {
		return this.player;
	}
	public void setPosition(double x, double y) {
		this.x = x; 
		this.y = y;
		player.setCenterX(x);
		player.setCenterY(y);
	}
}
