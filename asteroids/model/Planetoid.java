package asteroids.model;

import be.kuleuven.cs.som.annotate.*;




/**
 * A class which deals with the behavoiur of a Planetoid.
 * 	Planetoids are a subclass of MinorPlanet.
 * 
 * @invar	The total distance traveled may not be to beg so that the effective radius of the
 * 			planetoid be smaller then the minimal radius of a planetoid.
 * 			| TotalDistancetraveled <= (MINIMAL_MINORPLANET_RAD - super.getRadius())/-0.000001 
 * 			
 * @version 2.0
 * @author 	Brent De Bleser & Jesse Geens
 */
public class Planetoid extends MinorPlanet{
	
	/**
	 * Constant containing the density of planetoids.
	 */
	private final double PLANETOID_DENSITY = 0.917E12;
	
	
	/**
	 * Method to create a planetoid.
	 * 
	 * @param 	x
	 * 			The x position of the planetoid.
	 * @param 	y
	 * 			The y position of the planetoid.
	 * @param 	xVelocity
	 * 			The x component of the total velocity.
	 * @param 	yVelocity
	 * 			The y component of the total velocity.
	 * @param 	radius
	 * 			The start radius of the planetoid.
	 * @param 	totalDistanceTraveled
	 * 			The distance traveled by the planetoid before it is created.
	 * 
	 * @effect	The super constructor of planetoid is called with the necessary arguments.
	 * 			| super(x, y, xVelocity, yVelocity, radius, "Planetoid")
	 * @effect  The total distance traveled is set to totalDistanceTraveled.
	 * 			| setTotalDistanceTraveled(totalDistanceTraveled)
	 * @effect	The mass is set to fout thirds of the radius cubed times the the planetoid density.
	 * 			| setMass(4.0*Math.PI*Math.pow(getRadius(), 3)*PLANETOID_DENSITY / 3.0)
	 * @throws	IllegalArgumentException
	 * 			Throws an IllegalArgumentExceptionif the radius isn't a valid radius for a minor planet.
	 * 			| !isValidRadius(this.getRadius())
	 */
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalDistanceTraveled){
		super(x, y, xVelocity, yVelocity, radius, "Planetoid");
		
		this.setTotalDistanceTraveled(totalDistanceTraveled);
		if (!isValidRadius(this.getRadius())) this.terminate();
		setMass(4.0*Math.PI*Math.pow(getRadius(), 3)*PLANETOID_DENSITY / 3.0);
		
	}
	
	

	/**
	 * This method returns the total distance traveled by a planetoid.
	 */
	@Basic @Raw
	public double getTotalDistanceTraveled() {
		return this.totalDistanceTraveled;
	}
	
	/**
	 * Method to set the total distance traveled to a given value.
	 * 
	 * @param 	distance
	 * 			The distance to set as the new total distance traveled.
	 * @post 	The new total distance traveled is set to the given distance.
	 * 			| new..totalDistanceTraveled = distance
	 * @throws	IllegalArgumentException
	 * 			Throws an IllegalArgumentException if the given distance isn't a valid double.
	 * 			| !Helper.isValidDouble(distance)
	 */
	@Raw
	private void setTotalDistanceTraveled(double distance){
		if(!Helper.isValidDouble(distance)) throw new IllegalArgumentException("Total distance traveled isn't a valid double @ setTotalDistanceTraveled)");
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
     * Moves the planetoid to a new position and remembers the distance traveled during a timespan of dt.
     *
     * @param   dt
     *          The time over which the ship is moving.
     * @throws  IllegalArgumentException
     *          Throws an IllegalArgumentException if dt is infinity or is smaller then zero.
     *         	|  ((dt < 0.0) && ( Double.isInfinite(dt)))
     * @effect 	The total distance traveled is set to the previous total distance traveled add
     * 			with the totalvelocity at this moment times dt.
     * 			| setTotalDistanceTraveled(getTotalDistanceTraveled() + getTotalVelocity()*dt);
     * @effect 	The super move method is called.
     * 			| super.move(dt)
     * @effecr	If the effective radius isn't a valid radius for a minor planet after the move and the
     * 			new total distance travel is set, the planetoid will be terminated.
     * 			| After	super.move(dt)
     *       	|		setTotalDistanceTraveled(getTotalDistanceTraveled() + getTotalVelocity()*dt)
     *       	| if !this.isValidRadius(getRadius())
     *       	|		then	this.terminate()
     */
    @Override @Raw
    public void move(double dt) throws IllegalArgumentException{
        if (dt < 0.0) {
            throw new IllegalArgumentException("Invalid time");
        }
        else{
            try {
            	super.move(dt);
            	setTotalDistanceTraveled(getTotalDistanceTraveled() + getTotalVelocity()*dt);
            	if (!this.isValidRadius(getRadius())) this.terminate();
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
    @Override @Raw
    public double getRadius(){
    	return (super.getRadius() - 0.000001*getTotalDistanceTraveled());
    }
    
    
    /**
     * A method which calculates the arguments for the constructors of the two asteroids which could spawn from a dead planetoid. And spawns them in the proper world.
     * 
     * @param 	world
     * 			The world in which the newly created asteroids must spawn.
     * @post	Two new asteroids are created with half the radius of the original planetoid.
     * 			The direction of the velocity of the first asteroid is determined at random. The other asteroid moves
	 *			in the opposite direction. The speed of their velocities is 1.5 times the speed
	 *			of the planetoid. Finally, both asteroids are placed at a distance r/2 (where
	 *			r is the radius of the planetoid) from the center of the planetoid. The
	 *			centres of the planetoid and of both asteroids should lie on a single line.
	 *			| double asteroidDirection = 2 * Math.PI * Math.random() 
	 *			|
	 *			| double speed = 1.5 *  Math.sqrt(Helper.square(this.getVelocity()[0]) + Helper.square(this.getVelocity()[1]))
	 *			|
	 *			| double XVelocityAs1 = speed * Math.cos(asteroidDirection)
	 *			| double YVelocityAs1 = speed * Math.sin(asteroidDirection)
	 *			| double XVelocityAs2 = -speed * Math.cos(asteroidDirection)
	 *			| double YVelocityAs2 = -speed * Math.sin(asteroidDirection)
	 *			|
	 *			| double asteroidRadius = getRadius()/2
	 *			|
	 *			| double newXChild1 = this.getPosition()[0] + Math.cos(asteroidDirection) * asteroidRadius
	 *			| double newYChild1 = this.getPosition()[1] + Math.sin(asteroidDirection) * asteroidRadius
	 *	        | double newXChild2 = this.getPosition()[0] - Math.cos(asteroidDirection) * asteroidRadius
	 *			| double newYChild2 = this.getPosition()[1] - Math.sin(asteroidDirection) * asteroidRadius
	 *			|
	 *			|
	 *			| Asteroid asteroid1 = new Asteroid(newXChild1, newYChild1, XVelocityAs1, YVelocityAs1, asteroidRadius)
	 *			| Asteroid asteroid2 = new Asteroid(newXChild2, newYChild2, XVelocityAs2, YVelocityAs2, asteroidRadius)
	 * @effect	The asteroids are spawned in world.
	 * 			| world.addEntityToWorld(asteroid1)
	 * 			| world.addEntityToWorld(asteroid2)
     */	
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
