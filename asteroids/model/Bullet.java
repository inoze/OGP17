package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
//import asteroids.util.ModelException;

/**
 * A method which involves a bullet.
 * 
 * @invar Bounces can never be more than two.
 *        | Bouncs <= 2 
 * 
 * @author Brent De Bleser & Jesse Geens
 * @version 1.0 
 */
public class Bullet extends Entity{
	
	/**
	 * Create a new non-null bullet with the given position, velocity and
	 * radius,
	 * 
	 * The bullet is not located in a world nor loaded on a ship.
	 * 
	 * @param   x
	 *          The x coordinate on which the bullet is created.
	 * @param   y
	 * 		    The y coordinate on which the bullet is created.
	 * @param   xVelocity
	 *          The velocity on the x axis when the bullet is created.
	 * @param   yVelocity
	 *          The velocity on the y axis when the bullet is created.
	 * @param   radius
	 *          The radius of the bullet.
	 * @effect  Calls the constructor of the superclass with arguments x, y, xVelocity, yVelocity, radius and "Bullet".
	 *          | super(x, y, xVelocity, yVelocity, radius, "Bullet")
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius,"Bullet");
		
		if (!isValidRadius(getRadius())) throw new IllegalArgumentException("Invalid radius @ bullet");
		setMass(4.0*Math.PI*Math.pow(getRadius(), 3)*BULLET_DENSITY / 3.0);
	}
	
	/**
	 * Constant containing the minimal radien of a bullet.
	 */
	private final double MINIMAL_BULLET_RAD = 1;
	
	/**
	 * Returns the density of a bullet.
	 */
	@Immutable
	public double getBulletDensity(){
		return BULLET_DENSITY;
	}
	
	/**
	 * Constant containing the density of bullets.
	 */
	private final double BULLET_DENSITY = 7.8E12;
	
	//Total
	/**
	 * Return the ship that fired the bullet.
	 * 
	 * @return  Returns source of the bullet.
	 *          | resutl == source
	 * @note    If the bullet isn't fired source contains null by default.
	 */
	@Basic
	public Ship getBulletSource() {
		return source;
	}
	
	//Total
	/**
	 * Return the ship in which bullet is positioned.
	 * 
	 * @return If the 
	 */
	@Basic
	public Ship getBulletShip() {
		if(this.getWorld() == null)
			return this.getBulletSource();
		else
			return null;
		
	}
	
	 /**
	  * A method to set the source of bullet.
	  * 
	  * @param source
	  * 	   The source to be set.
	  * @post  The source of the bullet is set to the given source.
	  *        | new.source == source
	  */
	public void setSource(Ship source){
			this.source = source;
	}
	
	/**
	 * Variable containing the source ship of the bullet.
	 * 
	 * @note  If the bullet isn't fired source contains null.
	 */
	private Ship source = null;
	
	/**
	 * A method which deals with bounces of a bullet.
	 * 
	 * @post   The amount of bounces in icremented.
	 *         | new.bounces = old.bounces + 1
	 * @effect If the bullet has three bounces or more it is terminated.
	 *         | if	 	bounces >2
	 *         |	then 	this.terminateBullet()
	 */
	@Raw
	public void bouncesCounter(){
		bounces++;
		if (bounces > 2) this.terminateBullet();
	}
	
	/**
	 * A method that resets the amount of bounces a bullet has done
	 * 
	 * @post The amount of bounces is reset to zero
	 * 		 | this.bounces = 0
	 */
	public void bouncesReset(){
		this.bounces = 0;
	}
	
	/**
	 * Variable containing the amount of bounces the bullet has done.
	 */
	private int bounces = 0;
	
	
	/**
	 * Terminate this bullet.
     *
     * @post   The source of this bullet is set to null.
     *         | new.source = null
     * @effect The bullet is terminated at entity level
     *         | this.terminate()
	 */
	@Basic
	public void terminateBullet() {
		this.terminate();
		this.source = null;
	}
	
	
	/**
     * Checks whether or not a radius is valid.
     * 
     * @param 	radius
     * 			The radius to check.
     * @return	True if and only if radis is bigger than the minimal radius for a bullet and if radius is a valid double.
     * 			| result == (radius > MINIMAL_BULLET_RAD && Helper.isValidDouble(radius))
     */
    @Model
    private boolean isValidRadius(double radius){
    	return (radius > MINIMAL_BULLET_RAD && Helper.isValidDouble(radius));
    }
}
