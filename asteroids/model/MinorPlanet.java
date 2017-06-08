package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class which deals with minor planets.
 * 	MinorPlanet is a subclass from Entity.
 * 
 * @invar	The radius of a minor planet must be a valid radius for a minor planet.
 * 			| this.isValidradius(getRadius())
 * 
 * @version	2.0
 * @author 	Brent De Bleser & Jesse Geens
 *
 */
public abstract class MinorPlanet extends Entity{
	
	/**
	 * constant containing the minimal radius of a ship.
	 */
	private final double MINIMAL_MINORPLANET_RAD = 5;
	
	/**
	 * Create a new minor planet with the given position, velocity and radius.
	 * 
	 * @param 	x
	 * 			The x position of the asteroid.
	 * @param 	y
	 * 			The y position of the asteroid.
	 * @param 	xVelocity
	 * 			The y component of the total velocity of the asteroid.
	 * @param 	yVelocity
	 * 			The y component of the total velocity of the asteroid.
	 * @param 	radius
	 * 			The radius of the asteroid.
	 * @param	type
	 * 			The type of the minor planet.
	 * 
	 * @effect	The super constructor is called with the corresponding arguments.
	 * 			| super(x, y, xVelocity, yVelocity, radius, type);
	 * @throws	IllegalArgumentException
	 * 			Throws an IllegalArgumentException if the radius isn't valid.
	 * 			| !isValidRadius(getRadius())
	 */
	@Raw
	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius, String type){
		super(x, y, xVelocity, yVelocity, radius, type);
		
		if (!isValidRadius(getRadius())) throw new IllegalArgumentException("Invalid radius @ MinorPlanet");
	}
		
	/**
     * Checks whether or not a radius is valid for a Minor Planet.
     * 
     * @param 	radius
     * 			The radius to check.
     * @return	True if and only if radius is bigger than the minimal radius for a Minor Planer and if radius is a valid double.
     * 			| result == (radius > MINIMAL_MINORPLANET_RAD && Helper.isValidDouble(radius))
     */
    protected boolean isValidRadius(double radius){
    	return (radius >= MINIMAL_MINORPLANET_RAD && Helper.isValidDouble(radius));
    }
}
