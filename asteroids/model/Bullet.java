package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
//import asteroids.util.ModelException;

public class Bullet extends Entity{
	
	/**
	 * Variable containing the source ship of the bullet.
	 * 
	 * @note  If the bullet isn't fired source contains null.
	 */
	private Ship source = null;

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
	 * @effect  Calls the constructor of the supraclass with arguments x, y, xVelocity, yVelocity, radius.
	 *          | super(x, y, xVelocity, yVelocity, radius)
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}

	
	
	/**
	 * Return the world in which bullet is positioned.
	 * 
	 * @return  At return the method will give the value of superWorld.
	 *          | result == this.superWorld
	 * @note    SuperWorld contains the world in which the entity is in.
	 *          if the superWorld isn't in any world it is per definition null. 
	 */
	@Basic
	public World getBulletWorld() {
			return this.superWorld;
	}
	
	/**
	 * Return the ship in which bullet is positioned.
	 * 
	 * This method must return null if a bullet is not positioned on a ship.
	 */
	@Basic
	public Ship getBulletShip() {
		if(this.superWorld == null)
			return this.getBulletSource();
		else
			return null;
		
	}
	
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
	
	/**
	 * Terminate <code>bullet</code>.
	 */
	@Basic
	public void terminateBullet() {
		this.isTerminated = true;
	}
	
	public void setSource(Ship source){
		if(this.getBulletSource() == null)
			this.source = source;
	}
	
}
