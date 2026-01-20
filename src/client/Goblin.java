package client;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Goblin extends Player {
	
	  private Circle pond;
	    private Human target;
	    private static final double CHASE_SPEED = 5.0;
	    private static final double TRIGGER_DISTANCE = 80.0; // Distance from edge to trigger chase
	    
	    public Goblin(double x, double y, Circle pond) {
	        super(x, y, 20, Color.GREEN);
	        this.pond = pond;
	    }
	    
	    public void setTarget(Human human) {
	        this.target = human;
	    }
	    
	    @Override
	    public void movePlayer(double newX, double newY) {
	        this.x = newX;
	        this.y = newY;
	        player.setCenterX(newX);
	        player.setCenterY(newY);
	    }
	    
	    public void chaseHuman() {
	        if (target == null) return;
	        
	        // Check if human is close to edge
	        double humanDistanceFromEdge = target.getDistanceFromPondEdge();
	        
	        if (humanDistanceFromEdge < TRIGGER_DISTANCE) {
	            // Human is close to edge, goblin chases!
	            
	            // Find closest point on pond edge to human
	            double humanX = target.getX();
	            double humanY = target.getY();
	            double pondCenterX = pond.getCenterX();
	            double pondCenterY = pond.getCenterY();
	            
	            double dx = humanX - pondCenterX;
	            double dy = humanY - pondCenterY;
	            double angle = Math.atan2(dy, dx);
	            
	            // Target point is just outside the pond edge, closest to human
	            double targetX = pondCenterX + (pond.getRadius() + player.getRadius() + 5) * Math.cos(angle);
	            double targetY = pondCenterY + (pond.getRadius() + player.getRadius() + 5) * Math.sin(angle);
	            
	            // Move goblin towards target point
	            double goblindx = targetX - x;
	            double goblindy = targetY - y;
	            double distance = Math.sqrt(goblindx * goblindx + goblindy * goblindy);
	            
	            if (distance > 1) {
	                double moveX = (goblindx / distance) * CHASE_SPEED;
	                double moveY = (goblindy / distance) * CHASE_SPEED;
	                double newX = x + moveX;
	                double newY = y + moveY;
	                
	                // Check if new position would be inside pond - if so, don't move there
	                double distFromPondCenter = Math.sqrt(
	                    Math.pow(newX - pondCenterX, 2) + Math.pow(newY - pondCenterY, 2)
	                );
	                
	                // Goblin must stay outside pond (with its radius considered)
	                if (distFromPondCenter >= pond.getRadius() + player.getRadius()) {
	                    movePlayer(newX, newY);
	                } else {
	                    // Keep goblin at edge of pond
	                    double edgeAngle = Math.atan2(newY - pondCenterY, newX - pondCenterX);
	                    double edgeX = pondCenterX + (pond.getRadius() + player.getRadius()) * Math.cos(edgeAngle);
	                    double edgeY = pondCenterY + (pond.getRadius() + player.getRadius()) * Math.sin(edgeAngle);
	                    movePlayer(edgeX, edgeY);
	                }
	            }
	        }
	    }
	    
	    public boolean caughtHuman() {
	        if (target == null) return false;
	        
	        // Human must be OUTSIDE the pond for goblin to catch them
	        double humanX = target.getX();
	        double humanY = target.getY();
	        double pondCenterX = pond.getCenterX();
	        double pondCenterY = pond.getCenterY();
	        
	        double humanDistFromCenter = Math.sqrt(
	            Math.pow(humanX - pondCenterX, 2) + Math.pow(humanY - pondCenterY, 2)
	        );
	        
	        // Check if human has left the pond
	        boolean humanOutsidePond = humanDistFromCenter > pond.getRadius();
	        
	        if (!humanOutsidePond) {
	            return false; // Human is still in pond, safe!
	        }
	        
	        // Human is outside pond, check if goblin is touching them
	        double dx = target.getX() - x;
	        double dy = target.getY() - y;
	        double distance = Math.sqrt(dx * dx + dy * dy);
	        
	        return distance < (player.getRadius() + target.getPlayer().getRadius());
	    }

}
