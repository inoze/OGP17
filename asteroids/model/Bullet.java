package asteroids.model;
import java.util.HashSet;
import java.util.Set;
import be.kuleuven.cs.som.annotate.*;


/**
 * A method which involves a bullet.
 * 	Bullet is a subclass of Entity.
 * 
 * @invar 	Bounces can never be more than two.
 *        	| Bouncs <= 2 
 *        
 * @invar 	The mass of a bullet is always equal to four thirds of the radius cubed times the the bullet density.
 * 			| this.getMass() == 4.0*Math.PI*Math.pow(getRadius(), 3)*BULLET_DENSITY / 3.0
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
	 *          
	 * @effect  Calls the constructor of the superclass with arguments x, y, xVelocity, yVelocity, radius and "Bullet".
	 *          | super(x, y, xVelocity, yVelocity, radius, "Bullet")
	 * @effect	The mass is set to four thirds of the radius cubed times the the bullet density.
	 * 			| setMass(4.0*Math.PI*Math.pow(getRadius(), 3)*BULLET_DENSITY / 3.0)
	 * 
	 * @throws	IllegalArgumentException
	 * 			Throws an IllegalArgumentException if the radius isn't valid.
	 * 			| !isValidRadius(getRadius())
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
	 * Constant containing the density of bullets.
	 */
	private final double BULLET_DENSITY = 7.8E12;
	
	/**
	 * Return the ship that fired the bullet.
	 * 
	 * @note    If the bullet isn't fired source contains null by default.
	 */
	@Basic @Raw
	public Ship getBulletSource() {
		return source;
	}
	
	
	/**
	 * Return the ship in which bullet is positioned.
	 * 
	 * @return	If the super world of the bullet is null the result equals the source of the bullet,
	 * 			else the result will be null.
	 * 			| if 	this.getSuperWorld() == null
	 * 			|	then	result == this.getBulletSource()
	 * 			| else		result == null
	 */
	public Ship getBulletShip() {
		if(this.getSuperWorld() == null)
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
     * Set the word of bullet to the given world.
     * 
     * @param 	world
     * 			The world to be set as the new world of the bullet.
     * @effect	If the given world is null the source of the bullet will also be set to null.
     * 			| if world == null
     * 			|	then	setSource(null)
     * 			|			super.setSuperWorld(world)
     * @effect 	The world of bullet is set to the given world.
     * 			| super.setSuperWorld(world)
     */
    @Override 
    public void setSuperWorld(World world){
    	if (world == null)	setSource(null);
    	super.setSuperWorld(world);
    }
    
	/**
	 * Variable containing the source ship of the bullet.
	 * 
	 * @note  If the bullet isn't fired source contains the ship on which it is loaded.
	 */
	private Ship source = null;
	
	/**
	 * Returns the amount of bounces the bullet has done.
	 */
	@Basic @Raw
	public int getBounces(){
		return bounces;
	}
	
	/**
	 * A method which deals with bounces of a bullet.
	 * 
	 * @post   The amount of bounces in incremented.
	 *         | new.bounces = old.bounces + 1
	 * @effect If the bullet has three bounces or more it is terminated.
	 *         | if	 	bounces >2
	 *         |	then 	this.terminateBullet()
	 */
	public void bouncesCounter(){
		bounces++;
		if (bounces > 2) this.terminate();
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
     * @post   isTerminated is set to true.
     * 		   | new.isTerminated == true
     * @effect If the world of the bullet isn't null the bullet is removed from that world
     * 		   and the world of the bullet is set to null.
     * 		   | if getWorld() != null
     * 		   |	then 	getWorld().removeEntityFromWorld((Bullet) this)
	 *         |			setSuperWorld(null);
	 * @effect If the world of the bullet is null the bullet will be removed from the ship 
	 * 		   on which it is loaded.
	 * 		   | if getWolrd() == null
	 * 		   |	then 	source.removeBulletFromShip(this)
	 * 
	 */
	@Override 
	public void terminate() {
		
		if(getSuperWorld() != null){
			getSuperWorld().removeEntityFromWorld(this);
			this.setSuperWorld(null);
		}
		else if (source != null){
			source.removeBulletFromShip(this);
		}
		
		this.source = null;
		this.isTerminated = true;
		
	}
	
 
    /**
     * A method for dealing with the situation if a ship collides with one
     * of its own bullets.
     * 
     * @param 	ship
     * 			The ship to collide with.
     * @effect	The bullet is loaded on to the ship.
     * 			| Set<Bullet> bullets = new HashSet<Bullet>()
	 *			| bullets.add(this)
	 *		    | ship.loadBulletsOnShip(bullets)
     */
    public void bulletCollideOwnShip(Ship ship){
			Set<Bullet> bullets = new HashSet<Bullet>();
			bullets.add(this);
			ship.loadBulletsOnShip(bullets);
		}
    
    
    /**
     * A method to deal with the situation if a bullet collides with an entity that 
     * isn't its source.
     * 
     * @param 	entity
     * 			The entity to collide with
     * @post	The bullet is removed form its  super world and is terminated.
     * 			| this.getSuperWorld().removeEntityFromWorld(this)
	 *			| this.terminate()
	 * @post	The entity is removed from its world and is terminated according to its instance.
	 * 			| entity.getSuperWorld().removeEntityFromWorld(entity)
	 * 			| if entity instanceof Planetoid 
	 * 			|		then	Planetoid planetoid = (Planetoid) entity	
	 * 			|				planetoid.terminate()
	 *			| if ship instanceof Ship) 
	 *			|		then	Ship ship = (Ship) entity	
	 *			|				 ship.terminate()
	 *			| if entity instanceof Bullet 
	 *			|		then	Bullet bullet = (Bullet) entity
	 *			|		 		bullet.terminate()
	 *			| else 	then	skr.terminate()
     */
    public void bulletCollideSomethingElse(Entity entity){

		this.getSuperWorld().removeEntityFromWorld(this);
		this.terminate();
		entity.getSuperWorld().removeEntityFromWorld(entity);

		if (entity instanceof Planetoid) {Planetoid planetoid = (Planetoid) entity; planetoid.terminate();}
		if (entity instanceof Ship) {Ship ship = (Ship) entity; ship.terminate();}
		if (entity instanceof Bullet) {Bullet bullet = (Bullet) entity; bullet.terminate();}
		else {entity.terminate();}
    }
	
	/**
     * Checks whether or not a radius is valid.
     * 
     * @param 	radius
     * 			The radius to check.
     * @return	True if and only if radius is bigger than the minimal radius for a bullet and if radius is a valid double.
     * 			| result == (radius > MINIMAL_BULLET_RAD && Helper.isValidDouble(radius))
     */
    @Model 
    private boolean isValidRadius(double radius){
    	return (radius > MINIMAL_BULLET_RAD && Helper.isValidDouble(radius));
    }
    
}
