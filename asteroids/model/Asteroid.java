package asteroids.model;

public class Asteroid extends MinorPlanet{
	
	/**
	 * Constant containing the density of asteroids.
	 */
	private final double ASTEROID_DENSITY = 2.65E12;
	
	/**
	 * Create a new non-null asteroid with the given position, velocity and
	 * radius.
	 * 
	 * The asteroid is not located in a world.
	 */
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius){
		super(x, y, xVelocity, yVelocity, radius, "Asteroid");
		
		if (!isValidRadius(getRadius())) throw new IllegalArgumentException("Invalid radius @ bullet");
		setMass(4.0*Math.PI*Math.pow(getRadius(), 3)*ASTEROID_DENSITY / 3.0);
	}
}
