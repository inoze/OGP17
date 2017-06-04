package asteroids.model;

abstract class MinorPlanet extends Entity{
	
	/**
	 * constant containing the minimal radius of a ship.
	 */
	private final double MINIMAL_MINORPLANET_RAD = 5;
	
	/**
	 * Create a new non-null asteroid with the given position, velocity and
	 * radius.
	 * 
	 * The asteroid is not located in a world.
	 */
	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius, String type){
		super(x, y, xVelocity, yVelocity, radius, type);
	}
}
