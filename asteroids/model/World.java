package asteroids.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

/**
 * A class of worlds involving width, a height, a set of
 * ships which are in the world and a set of bullets which are in the world.
 * 
 * @invar   Width must be a valid dimension.
 *          | isValidDimension(width)
 * @invar   Height must be a valid dimension.
 *          | isValidDimension(height)
 * @author  Brent De Bleser & Jesse Geens
 * @version 1.0
 */
public class World {
	/**
	/**
	 * Variable containing the width of the world.
	 */
	private double width = 0.0;
	/**
	 * Variable containing the height of the world.
	 */
	private double height = 0.0;
	/**
	 * Variable containing whether the world is terminated.
	 */
	private boolean isTerminated;
	/**
	 * HashSet containing all the ships in the world.
	 */
	private Set<Ship> ships = new HashSet<Ship>();
	/**
	 * HashSet containing all the bullets in the world.
	 */
	private Set<Bullet> bullets = new HashSet<Bullet>();
	/**
	 * A constant which conatins the maximum possible value for a dimension.
	 */
	private final static Double UPPER_BOUND_WORLD =  Double.MAX_VALUE;
	
	
	/**
	 * Create a new world with the given width and
	 * height.
	 * 
	 * @param   width
	 *          The width of the new world.
	 * @param   heigt
	 *          The height of the new world.
	 * @post    The given width and height become the width and height of the new world.
	 *          | new.width = width
	 *          | new.height = height
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the height or width isn't a valid dimension.
	 *          | !(isValidDimension(width) && isValidDimension(height))
	 */
	public World(double width, double height) throws IllegalArgumentException {
		if(isValidDimension(width) && isValidDimension(height)){
			this.width = width;
			this.height = height;
		}
		else{
			throw new IllegalArgumentException("Invalid dimensions given @ createWorld");
		}
	}
	
	/**
	 * Initializes a world with the default size.
	 * 
	 * @effect 	A new world with the highest possible dimensions.
	 * 			| World(Double.MAX_VALUE, Double.MAX_VALUE)
	 */
	public World() {
		this(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	/**
	 * Terminate the world.
	 * 
	 * @post The ships and bullets are removed from the the hashsets bullets and ships, the boolean isTerminated is set to true.
	 *       | implementation
	 */
	@Basic
	public void terminateWorld(){
		 for (Ship ship : ships) {
	            this.removeShipFromWorld(ship);
	        }
		    ships.clear();
	        for (Bullet bullet : bullets) {
	            this.removeBulletFromWorld(bullet);
	        }
	        bullets.clear();
	        this.isTerminated = true;
	}
	
	/**
	 * Check whether the world is terminated.
	 */
	@Basic @Raw
	public boolean isTerminatedWorld(){
		return this.isTerminated;
	}
	
	//Total
	/**
	 * Return the size of the world as an array containing the width,
	 * followed by the height.
	 * 
	 * @return Returns the width and height of the world in array form with length 2,
	 * 		   with the width on index 0 and haight index 1
	 *         | double[] size = new double[2]
     *    	   | size[0] = this.width
	 *	       | size[1] = this.height
	 *         | result == size
	 */
	@Basic
	public double[] getWorldSize() {
		double[] size = new double[2];
		size[0] = this.width;
		size[1] = this.height;
		Helper.log("w: " + size[0] + "; h: " + size[1]);
		return size;
	}

	/**
	 * Return all ships located within <code>world</code>.
	 */
	@Basic
	public Set<? extends Ship> getWorldShips() throws ModelException {
		return ships;
	}

	/**
	 * Return all bullets located in <code>world</code>.
	 */
	@Basic
	public Set<? extends Bullet> getWorldBullets() throws ModelException {
		return bullets;
	}
	
	//Defensief
	/**
	 * Add a ship to the world
	 * 
	 * @param   Ship
	 *          The ship to be added to the world.
	 * @throws  IllegalArgumentExceptione
	 *          Throws an IllegalArgumentException if the ship isn't a valid Ship.
	 *          | !(isValidShip(ship))
	 * @post    Ship is added to the hash set ships of the world to which the method is invoked
	 *          and the ship's superWorld is set to the world the method is invoked.
	 *          | ships.add(ship)
	 *		    | ship.setSuperWorld(this)
	 */
	public void addShipToWorld(Ship ship) throws IllegalArgumentException {
		if(isValidShip(ship)){
			ships.add(ship);
			ship.setSuperWorld(this);
		}
		else	throw new IllegalArgumentException("ship isn't a valid Ship.");
	}

	/**
	 * Remove the ship from the world.
	 * 
	 * @param   ship
	 *          The ship to be removed from the world.
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the ship isn't in the world.
	 *          | !(ships.contains(ship))
	 * @post    The ship's superWorld is set to null.
	 *          | implementation
	 * @post    The ship is removed from the hashset ships
	 *          | implementation
	 */
	public void removeShipFromWorld(Ship ship) throws IllegalArgumentException {
		if (ships.contains(ship)){
			ship.terminateShip();
			ship.setSuperWorld(null);
			ships.remove(ship);
		}
		else	throw new IllegalArgumentException("Ship isn't in the world.");
		
	}

	/**
	 * Add the bullet to the world.
	 * 
	 * @param   bullet
	 *          The bullet to be added to the world.
	 * @throws  IllegalArgumentExceptione
	 *          Throws an IllegalArgumentException if the bullet isn't a valid Bullet.
	 *          | !(isValidBullet(bullet))
	 * @post    bullet is added to the hash set bullets of the world to which the method is invoked
	 *          and the bullet's superWorld is set to the world the method is invoked.
	 *          | bullets.add(bullet)
	 *		    | bullet.setSuperWorld(this)
	 */
	public void addBulletToWorld(Bullet bullet){
		if(isValidBullet(bullet)){
			bullets.add(bullet);
			bullet.setSuperWorld(this);
		}
		else	throw new IllegalArgumentException("bullet isn't a valid Bullet.");
	}

	/**
	 * Remove the bullet from the world.
	 * 
	 * @param   bullet
	 *          The bullet to be removed from the world.
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the bullet isn't in the world.
	 *          | !(bullets.contains(bullet))
	 * @post    The ship's superWorld is set to null.
	 *          | implementation
	 * @post    The bullet is terminated.
	 *          | implementation
	 * @post    The bullet is removed from the hashset bullets
	 *          | implementation
	 */
	public void removeBulletFromWorld(Bullet bullet) throws IllegalArgumentException {
		if (bullets.contains(bullet)){
			bullet.setSuperWorld(this);
			bullet.terminate();
			bullets.remove(bullet);
		}
		else	throw new IllegalArgumentException("bullet isn't in the world.");
		
	}
	//Total
	/**
	 * A method that gives an entity back  if any for a given x and y coordinate.
	 * 
	 * @param   x
	 *          The x coordinate to check.
	 * @param   y 
	 * 			The y coordinate to check
	 * @post	If there is an entity at the given x and y return that entity.
	 * 			| implementation
	 * @post	If there isn't an etity at the given x and y return null.
	 * 			| implementation
	 * @post 	If there are multiple entities at a x and y position return null.
	 * 			| implementation
	 */
	public Entity entityAt(double x, double y){
		double[] position = {x,y};
		int counter = 0;
		Entity entity = null;
		for (Entity a : ships) {
		    if (a.getPosition() == position){ 
		    	counter++;
		    	entity = a;
		    }
		}
		for (Entity a : bullets){
			if (a.getPosition() == position){ 
		    	counter++;
		    	entity = a;
			}
		}

		if (counter > 1){
			Helper.log("More than one object on position " + position);
			entity = null;
		}

		return entity;
	}
	
	/**
	 * A method which retunrs all the entities in the world.
	 * 
	 * @return  Returns a set of entities which contains all the entities in the world
	 *          | Set<Entity> entities = new HashSet<Entity>()
	 *	        | entities.addAll(bullets);
	 *       	| entities.addAll(ships);
	 *	        | result ==  entities 
	 */
	@Basic
	public Set<Entity> getEntities(){
		Set<Entity> entities = new HashSet<Entity>();
		entities.addAll(bullets);
		entities.addAll(ships);
		return entities;
	}
	
	public void evolve(double dt, CollisionListener collisionListener){
		
	}

	//Total
	/**
	 * Check whether the given angle is a valid angle.
	 * 
	 * @param  dimension
	 *         The dimension to check.
	 * @return True if and only if dimension is smaller or equal to 0,
	 *         dimension is smaller or equal to Double.MAX_VALUE and dimesnion is finite.
	 *         | result == ((dimension <= 0) || (dimension >= Double.MAX_VALUE) || !(Double.isFinite(dimension)))
	 */
	private boolean isValidDimension(double dimension){
		if((dimension <= 0) || (dimension >= UPPER_BOUND_WORLD) || !(Double.isFinite(dimension)))
			return false;
		return true;
	}
	
	//Total
	/**
	 * Check whether the given angle is a valid angle.
	 * 
	 * @param  ship
	 *         The ship to check.
	 * @return True if and only if the ship isn't terminated.
	 *         | result == (!(ship.isTerminatedShip()))
	 */
	private boolean isValidShip(Ship ship){
		if(ship.isTerminated()){
			return false;
		}
		return true;
	}
	
	//Total
	/**
	 * Check whether the given angle is a valid angle.
	 * 
	 * @param  bullet
	 *         The bullet to check.
	 * @return True if and only if the bullet isn't terminated.
	 *         | result == (!(bullet.isTerminatedBullet()))
	 */
	private boolean isValidBullet(Bullet bullet){
		if(bullet.isTerminated()){
			return false;
		}
		return true;
	}
}
