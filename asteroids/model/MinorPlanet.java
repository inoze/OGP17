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
	
	
	public void minorPlanetCollide(MinorPlanet minorPlanet){
		double deltaPosX = this.getPosition()[0] - minorPlanet.getPosition()[0];
		double deltaPosY = this.getPosition()[1] - minorPlanet.getPosition()[1];

		double deltaVelX = this.getVelocity()[0] - minorPlanet.getVelocity()[0];
		double deltaVelY = this.getVelocity()[1] - minorPlanet.getVelocity()[1];
		
		double deltaVR = (deltaVelX*deltaPosX)  + (deltaVelY*deltaPosY);
		
		double radiusSum = minorPlanet.getRadius() + this.getRadius();
		double J = (2 * minorPlanet.getMass() * this.getMass() * deltaVR) / ((minorPlanet.getMass() + this.getMass()) * radiusSum);
		
		double Jx = (J*deltaPosX)/(radiusSum);	
		double Jy = (J*deltaPosY)/(radiusSum);
		
		double newVelocityX1 = minorPlanet.getVelocity()[1] + (Jx/minorPlanet.getMass());
		double newVelocityY1 = minorPlanet.getVelocity()[0] + (Jy/minorPlanet.getMass());
		
		double newVelocityX2 = this.getVelocity()[0] - (Jx/this.getMass());
		double newVelocityY2 = this.getVelocity()[1] - (Jy/this.getMass());
		
		minorPlanet.setVelocity(newVelocityX1, newVelocityY1);
		this.setVelocity(newVelocityX2, newVelocityY2);
		
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
