package client;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Human extends Player {
	private Circle pond;
	public Human(double x, double y, Circle pond) {
		super(x, y, 15, Color.RED);
		// TODO Auto-generated constructor stub
		this.pond = pond;
		setUpDragging();
	}

	private void setUpDragging() {
		// TODO Auto-generated method stub
		player.setOnMousePressed(event -> {
			offSetX = event.getSceneX() - player.getCenterX();
			offSetY = event.getSceneY() - player.getCenterY();
		});

		player.setOnMouseDragged(event -> {
			double newX = event.getSceneX() - offSetX;
			double newY = event.getSceneY() - offSetY;
			movePlayer(newX, newY);
		});
	}

	@Override
	protected void movePlayer(double newX, double newY) {
		// TODO Auto-generated method stub
		//Check if the new position is inside the pond
		double dx = newX - pond.getCenterX();
		double dy = newY - pond.getCenterY();
		double distanceFromCenter = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		
        // Keep human inside pond (subtract human radius to keep it fully inside)
		double maxDistance = pond.getRadius() - player.getRadius();
		
		if(distanceFromCenter <= maxDistance) {
			//Inside pond can move freely
			this.x = newX;
			this.y = newY;
			player.setCenterX(newX);
			player.setCenterY(newY);
		}else {
			// Outside pond, constrain to edge
            double angle = Math.atan2(dy, dx);
            this.x = pond.getCenterX() + maxDistance * Math.cos(angle);
            this.y = pond.getCenterY() + maxDistance * Math.sin(angle);
            player.setCenterX(this.x);
            player.setCenterY(this.y);
		}
	}
	
    public double getDistanceFromPondEdge() {
        double dx = x - pond.getCenterX();
        double dy = y - pond.getCenterY();
        double distanceFromCenter = Math.sqrt(dx * dx + dy * dy);
        return pond.getRadius() - distanceFromCenter;
    }


}
















