package asteroids.model;

import java.util.Set;

import asteroids.util.ModelException;

public class World {
	/**************
	 * WORLD: Basic methods
	 *************/

	private double width;
	
	private double height;
	
	private boolean isTerminated;
	/**
	 * Create a new world with the given <code>width</code> and
	 * <code>height</code>.
	 */
	public World createWorld(double width, double height) throws IllegalArgumentException {
		if(isValidDimension(width) && isValidDimension(height)){
			this.width = width;
			this.height = height;
			return this;
		}
		else{
			throw new IllegalArgumentException("Invalid dimensions given @ createWorld");
		}
	}

	/**
	 * Terminate <code>world</code>.
	 */
	public void terminateWorld(){
		if(!(this.isTerminated))
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
		return size;
	}

	/**
	 * Return all ships located within <code>world</code>.
	 */
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return null;
	}

	/**
	 * Return all bullets located in <code>world</code>.
	 */
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return null;
	}

	/**
	 * Add <code>ship</code> to <code>world</code>.
	 */
	public void addShipToWorld(Ship ship) throws ModelException {}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeShipFromWorld(Ship ship) throws ModelException {}

	/**
	 * Add <code>bullet</code> to <code>world</code>.
	 */
	public void addBulletToWorld(Bullet bullet) throws ModelException {}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeBulletFromWorld(Bullet bullet) throws ModelException {}
	
	/**
	 * 
	 * @param dimension
	 * @return
	 */
	private boolean isValidDimension(double dimension){
		if((dimension < 0) || (dimension > Double.MAX_VALUE) || !(Double.isFinite(dimension)))
			return false;
		return true;
	}

}
