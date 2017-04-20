package asteroids.model;

import java.util.*;

import asteroids.util.ModelException;

public class World {
	/**************
	 * WORLD: Basic methods
	 *************/

	private double width;
	
	private double height;
	
	private boolean isTerminated;
	
	private Set<Ship> ships = new HashSet<Ship>();
	
	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * Create a new world with the given <code>width</code> and
	 * <code>height</code>.
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
	 * Terminate <code>world</code>.
	 */
	public void terminateWorld(){
		this.isTerminated = true;
	}

	/**
	 * Check whether <code>world</code> is terminated.
	 */
	public boolean isTerminatedWorld(){
		return this.isTerminated;
	}

	/**
	 * Return the size of <code>world</code> as an array containing the width,
	 * followed by the height.
	 */
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
	public Set<? extends Ship> getWorldShips() throws ModelException {
		return ships;
	}

	/**
	 * Return all bullets located in <code>world</code>.
	 */
	public Set<? extends Bullet> getWorldBullets() throws ModelException {
		return bullets;
	}

	/**
	 * Add a ship to the world
	 * 
	 * @param  Ship
	 *         The ship to be added to the world.
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the ship isn't a valid ship.
	 *          (isValidShip(ship))
	 *
	 *
	 */
	public void addShipToWorld(Ship ship) throws IllegalArgumentException {
		if(isValidShip(ship)){
			ships.add(ship);
			ship.setSuperWorld(this);
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeShipFromWorld(Ship ship) throws ModelException {
		ship.terminateShip();
		ship.setSuperWorld(null);
		ships.remove(ship);
	}

	/**
	 * Add <code>bullet</code> to <code>world</code>.
	 */
	public void addBulletToWorld(Bullet bullet) throws ModelException {
		if(isValidBullet(bullet)){
			bullets.add(bullet);
			bullet.setSuperWorld(this);
		}
	}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeBulletFromWorld(Bullet bullet) throws ModelException {
		bullet.setSuperWorld(this);
		bullet.terminateBullet();
		bullets.remove(bullet);
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
	 * 			| 
	 * @post	If there isn't an etity at the given x and y return null.
	 * 			|
	 * @post 	If there are multiple entities at a x and y position return null.
	 * 			| 
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
	
	public Set<Entity> getEntities(){
		Set<Entity> entities = new HashSet<Entity>();
		entities.addAll(bullets);
		entities.addAll(ships);
		return entities;
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
		if((dimension <= 0) || (dimension >= Double.MAX_VALUE) || !(Double.isFinite(dimension)))
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
