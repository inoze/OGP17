package asteroids.model;


/**
 * A class which deals with minor planets.
 * 	MinorPlanet is a subclass from Entity.
 * 
 * @version	2.0
 * @author 	Brent De Bleser & Jesse Geens
 *
 */
public class MinorPlanet extends Entity{
	
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
		
	/**
     * Checks whether or not a radius is valid for a Minor Planet.
     * 
     * @param 	radius
     * 			The radius to check.
     * @return	True if and only if radis is bigger than the minimal radius for a Minor Planer and if radius is a valid double.
     * 			| result == (radius > MINIMAL_MINORPLANET_RAD && Helper.isValidDouble(radius))
     */
    protected boolean isValidRadius(double radius){
    	return (radius >= MINIMAL_MINORPLANET_RAD && Helper.isValidDouble(radius));
    }
}
