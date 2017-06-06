package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Planetoid extends MinorPlanet{
	
	/**
	 * Constant containing the density of planetoids.
	 */
	private final double PLANETOID_DENSITY = 0.917E12;
	
	
	
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalDistanceTraveled){
		super(x, y, xVelocity, yVelocity, radius, "Planetoid");
		
		this.setTotalDistanceTraveled(totalDistanceTraveled);
		if (!isValidRadius(this.getRadius())) this.terminate();
		setMass(4.0*Math.PI*Math.pow(getRadius(), 3)*PLANETOID_DENSITY / 3.0);
		
	}
	
	

	/**
	 * This method returns the total distance traveled by a planetoid.
	 */
	public double getTotalDistanceTraveled() {
		return this.totalDistanceTraveled;
	}
	
	/**
	 * Method to set the total distance traveled to a given value.
	 * 
	 * @param 	distance
	 * 			The distance to set as the new total distance traveled.
	 * @post 	The new total distance traveled is set to the given distance if 
	 * 			and only if the given distance is a valid double.
	 * 			| if (Helper.isValidDouble(distance)
	 * 			|	then new..totalDistanceTraveled = distance
	 */
	@Raw
	private void setTotalDistanceTraveled(double distance){
		if(Helper.isValidDouble(distance))
			this.totalDistanceTraveled = distance;
	}
	
	/**
	 * Variable containg the distance traveled of a planetoid.
	 */
	private double totalDistanceTraveled = 0.0;
	

	/**
	 * Method to terminate a planetoid.
	 * 
	 * @effect	If the planetoid is in a world and it's radius is bigger or equal to 30
	 * 			the planetoids spawns 2 new asteroids.
	 * 		 	| if (this.getRadius()>= 30 && getSuperWorld() != null)
	 * 		 	|    then spawnAsteroidTerminate(this.world)
	 * @effect	The planetoid is terminated on the level of it's superclass.
	 * 		 	|     super.terminate()
	 */
 	@Override
 	public void terminate() {
 		if (getRadius() >= 30 && getSuperWorld() != null) {
 			spawnAsteroids(getSuperWorld());
		}
		super.terminate();
 	}
	
 	/**
     * Moves the ship to a new position and set a neww velocity  during a timespan of dt.
     *
     * @param   dt
     *          The time over which the ship is moving.
     * @throws  IllegalArgumentException
     *          Throws an IllegalArgumentException if dt is infinity or is smaller then zero.
     *         	|  ((dt < 0.0) && ( Double.isInfinite(dt)))
     * @effect  The x and y velocity is set to propet velocity for the acceleration over the time.
     * 			| this.setVelocity(
     * 			|	this.getVelocity()[0] + this.getAcceleration() * Math.cos(this.getDirection()) * dt,
     * 			|		 this.getVelocity()[1] + this.getAcceleration() * Math.sin(this.getDirection())* dt)
     * @effect 	The super move method is called.
     * 			| super.move(dt)
     */
    @Override
    public void move(double dt) throws IllegalArgumentException{
        if (dt < 0.0) {
            throw new IllegalArgumentException("Invalid time");
        }
        else{
            try {
            	double[] prevPos = this.getPosition();
            	super.move(dt);
            	double[] currentPos = this.getPosition();
            	double difference = Math.abs(Math.sqrt(Helper.square(prevPos[0]) + Helper.square(prevPos[1])) - Math.sqrt(Helper.square(currentPos[0]) + Helper.square(currentPos[1])));
                setTotalDistanceTraveled(getTotalDistanceTraveled() + difference);
                }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex.getMessage());
                }
            }    
    }
    
    /**
     * returns the current radius of the planetoid.
     * 
     * @return 	returs the start radius of the planetoid minus the radius difference.
     * 			| result == super.getRadius() - 0.000001*getTotalDistanceTraveled()
     */
    @Override
    public double getRadius(){
    	return (super.getRadius() - 0.000001*getTotalDistanceTraveled());
    }
    
    
    
    private void spawnAsteroids(World world){
    	
    	
		double asteroidDirection = 2 * Math.PI * Math.random(); 
		
		double speed = 1.5 *  Math.sqrt(Helper.square(this.getVelocity()[0]) + Helper.square(this.getVelocity()[1]));
		
		double XVelocityAs1 = speed * Math.cos(asteroidDirection);
		double YVelocityAs1 = speed * Math.sin(asteroidDirection);
		double XVelocityAs2 = -speed * Math.cos(asteroidDirection);
		double YVelocityAs2 = -speed * Math.sin(asteroidDirection);
		
		
		double asteroidRadius = getRadius()/2;
		
		double newXChild1 = this.getPosition()[0] + Math.cos(asteroidDirection) * asteroidRadius;
		double newYChild1 = this.getPosition()[1] + Math.sin(asteroidDirection) * asteroidRadius;
		double newXChild2 = this.getPosition()[0] - Math.cos(asteroidDirection) * asteroidRadius;
		double newYChild2 = this.getPosition()[1] - Math.sin(asteroidDirection) * asteroidRadius;
		
		
		
		Asteroid asteroid1 = new Asteroid(newXChild1, newYChild1, XVelocityAs1, YVelocityAs1, asteroidRadius);
		Asteroid asteroid2 = new Asteroid(newXChild2, newYChild2, XVelocityAs2, YVelocityAs2, asteroidRadius);
		world.addEntityToWorld(asteroid1);
		world.addEntityToWorld(asteroid2);
    	
    }
}
