package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class which deals with asteroids.
 * 	Asteroid is a subclass of MinorPlanet.
 *
 * @invar 	The mass of an asteroid is always equal to four thirds of the radius cubed times the asteroid density.
 * 			| this.getMass() == 4.0*Math.PI*Math.pow(getRadius(), 3)*ASTEROID_DENSITY / 3.0
 *
 * @version	2.0
 * @author 	Brent De Bleser & Jesse Geens
 *
 */
public class Asteroid extends MinorPlanet{
	
	/**
	 * Constant containing the density of asteroids.
	 */
	private final double ASTEROID_DENSITY = 2.65E12;
	
	/**
	 * Method to create a asteroid.
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
	 * 
	 * @effect	The super constructor is called with the corresponding arguments.
	 * 			| super(x, y, xVelocity, yVelocity, radius, "Asteroid");
	 * @effect	The mass is set to four thirds of the radius cubed times the asteroid density.
	 * 			| setMass(4.0*Math.PI*Math.pow(getRadius(), 3)*ASTEROID_DENSITY / 3.0)
	 * 
	 */
	@Raw
	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius){
		super(x, y, xVelocity, yVelocity, radius, "Asteroid");
		
		setMass(4.0*Math.PI*Math.pow(getRadius(), 3)*ASTEROID_DENSITY / 3.0);
	}
	
}
