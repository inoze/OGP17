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
	 * Add <code>ship</code> to <code>world</code>.
	 */
	public void addShipToWorld(Ship ship) throws IllegalArgumentException {
		if(isValidShip(ship))
			ships.add(ship);
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeShipFromWorld(Ship ship) throws ModelException {
		ship.terminateShip();
		ships.remove(ship);
	}

	/**
	 * Add <code>bullet</code> to <code>world</code>.
	 */
	public void addBulletToWorld(Bullet bullet) throws ModelException {
		if(isValidBullet(bullet)){
			bullets.add(bullet);
		}
	}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeBulletFromWorld(Bullet bullet) throws ModelException {
		bullet.terminateBullet();
		bullets.remove(bullet);
	}
	
	public Entity whatEntityAtPos(double[] position){
		for(Ship ship : ships){
			if(ship.getShipPosition() == position){
				return ship;
			}
		}
		for(Bullet bullet : bullets){
			if(bullet.getBulletPosition() == position){
				return bullet;
			}
		}
		return null;
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

	private boolean isValidShip(Ship ship){
		if(ship.isTerminatedShip()){
			return false;
		}
		return true;
	}
	
	private boolean isValidBullet(Bullet bullet){
		if(bullet.isTerminatedBullet()){
			return false;
		}
		return true;
	}
}
