package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;


/**
 * A class of velocities involving a x and y velocity.
 * 
 * @invar	xVelocity and yVelocity must be valid velocities.
 * 			| this.isValidVelocity(this.getVelocity()[0], this.getVelocity()[1])
 * 
 * @author 	Brent De Bleser && Jesse Geens
 * @version	1.5
 *
 */
@Value
class Velocity {

	/**
	 * Initialize a this new velocity with a given x and y velocity.
	 * 
	 * @param 	x
	 * 			The x velocity to be set.
	 * @param 	y
	 * 			The velocity to be set.
	 * 
	 * @effect 	set the x and y velocity to the given x and y.
	 * 			| this.setVelocity(x, y)
	 */
	@Raw
	public Velocity(double x, double y) throws IllegalArgumentException {
		setVelocity(x, y);
	}
	
	/**
	 * A method which returns the x and y velocity of this velocity.
	 */
	@Basic @Raw
	public double[] getVelocity(){
		double[] result = {this.xVelocity, this.yVelocity};
	  	   return result;
	}
	 /**
     * Method to return the total velocity of this.
     * 
     * @return 	Returns the total velocity of this velocity..
     * 			|	result == Math.hypot(this.getVelocity()[0] + this.getVelocity()[1])
     */
	@Raw
    public double getTotalVelocity(){
    	double velocity = Math.hypot(this.getVelocity()[0], this.getVelocity()[1]);
		return velocity;
    }
	
	/**
	 * A method which sets the y velocity of this velocity to the given velocity.
	 * 
	 * @param 	x
	 * 			The x velocity to be set as the new x velocity.
	 * @post	The x velocity is set to the given x velocity.
	 * 			| this.xVelocity = x
	 */
	 @Raw
	public void setXYVelocity(double x){
		 if (!isValidVelocity(x)) this.xVelocity = 0.0;
		 else this.xVelocity = x;
	}
	
	/**
	 * A method to set the velocity to a given x and y. 
	 * 
	 * @param 	x
	 * 			The new x velocity of this velocity.
	 * @param 	y
	 * 			The new y velocity of this velocity.
	 *
	 * @effect	The x velocity is set to x.
	 * 			| this.setXYVelocity(x)
	 * @effect 	The y velocity is set to y.
	 * 			| this.setYVelocity(y)
	 */
	@Raw
	public void setVelocity(double x, double y){
		this.setXYVelocity(x);
		this.setYVelocity(y);
	}
	
	/**
	 * Variable containing the x velocity of this velocity
	 */
	private double xVelocity;
	
	/**
	 * A method which sets the y velocity of this velocity to the given velocity.
	 * 
	 * @param 	y
	 * 			The y velocity to be set as the new y velocity.
	 * @post	The y velocity is set to the given y velocity.
	 * 			| this.yVelocity = y
	 */
	 @Raw
	public void setYVelocity(double y){
		 if (!isValidVelocity(y)) this.yVelocity = 0.0;
		 else	this.yVelocity = y;
	}
	/**
	 * Variable containing the y velocity of this velocity.
	 */
	private double yVelocity;
	
	/**
	 * A method to check whether the given x and y velocity are valid.
	 * 
	 * @param 	x
	 * 			The x velocity to check.
	 * @param 	y
	 * 			The y velocity to check.
	 * @return	The method returns true if and only if the x and y are both valid doubles.
	 * 			| result == (Helper.isValidDouble(x) && Helper.isValidDouble(y))
	 */
	public static boolean isValidVelocity(double x, double y){
		return (Helper.isValidDouble(x) && Helper.isValidDouble(y));
	}
	
	/**
	 * A method to check whether the given velocity would be a valid x or y velocity.
	 * 
	 * @param 	velocity
	 * 			The velocity to be checked.
	 * @return	The method returns true if and only if the velocity is a valid double.
	 * 			| result == (Helper.isValidDouble(velocity)
	 */
	public static boolean isValidVelocity(double velocity){
		return (Helper.isValidDouble(velocity));
	}
	/**
	 * Checks whether this velocity is equal to a given object.
	 * 
	 * @return 	This method returns true if an only if the object isn't null, if it is of the class velocity
	 * 			and if both the x and y velocities of both classes are the same.
	 * 			| @ implementation
	 */
	@Override
	public boolean equals(Object other){
		if (other == null) return false;
		if (this.getClass() != other.getClass()) return false;
		Velocity velocity = (Velocity) other;
		return (this.xVelocity == (velocity.getVelocity()[0]) && this.yVelocity == (velocity.getVelocity()[1]));
	}
	
	
}
