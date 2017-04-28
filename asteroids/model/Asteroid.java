package asteroids.model;

public class Asteroid extends Entity{
	/**
	 * Create a new non-null asteroid with the given position, velocity and
	 * radius.
	 * 
	 * The asteroid is not located in a world.
	 */
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius){
		super(x, y, xVelocity, yVelocity, radius);
	}
}
