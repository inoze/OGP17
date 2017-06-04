package asteroids.model;

public class Planetoid extends MinorPlanet{
	
	/**
	 * Constant containing the density of planetoids.
	 */
	private final double PLANETOID_DENSITY = 0.917E12;
	
	
	

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
	private void setTotalDistanceTraveled(double distance){
		if(Helper.isValidDouble(distance))
			this.totalDistanceTraveled = distance;
	}
	
	/**
	 * Variable containg the distance traveled of a planetoid.
	 */
	private double totalDistanceTraveled = 0.0;
	
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalDistanceTraveled){
		super(x, y, xVelocity, yVelocity, radius, "Planetoid");
		this.setTotalDistanceTraveled(totalDistanceTraveled);
	}
	
	@Override
	public double getRadius(){
		//return radius-(0.000001*getTotalDistanceTraveled());
		return 0.0;
	}
	
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
 			//spawnAsteroids(getSuperWorld());
		}
		super.terminate();
 	}
	
}
