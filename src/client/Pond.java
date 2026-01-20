package client;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class 'Pond' defines a circle pond. Inside circle pond they will be a player.
 */
public class Pond extends Pane {
	private int radius = 50;
	final static double PI = 3.14;
	private int height = 500;
	private int width = 600;

	public Pond() {
		setUpGUI();
	}
	public Pond(int radius) {
		this.radius = radius;
		setUpGUI();
	}

	public void setUpGUI() {
		Pane canvas = new Pane();
		canvas.setStyle("-fx-background-color: black;");
		canvas.setPrefSize(width, height);

		Circle pond = new Circle(300, 250, 100);
		pond.setFill(Color.LIGHTBLUE);
		pond.setStroke(Color.DARKBLUE);
		pond.setStrokeWidth(3);
		//pond.relocate(175, 50);

		Human human = new Human(300, 250, pond);
		Goblin goblin = new Goblin(300, 80, pond);
		goblin.setTarget(human);


		canvas.getChildren().addAll(pond, human.getPlayer(), goblin.getPlayer());

		// Game loop - goblin constantly chases human
		AnimationTimer gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				goblin.chaseHuman();

				// Check if goblin caught human
				if (goblin.caughtHuman()) {
					System.out.println("The goblin caught the human! Game Over!");
					human.getPlayer().setFill(Color.DARKRED);
					this.stop();
				}
			}
		};
		gameLoop.start();

		VBox root = new VBox();//Creating a VBox
		root.getChildren().add(canvas);//Adding the BorderPane to the VBox
		this.getChildren().add(root);//Adding the VBox to the StackPane

	}

	//Accessor methods
	public int getRadius() {
		return this.radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
