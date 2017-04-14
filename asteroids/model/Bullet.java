package asteroids.model;

import asteroids.util.ModelException;

public class Bullet extends Entity{
	
	private double[] Position = new double[2];
	
	private double[] Velocity = new double[2];
	
	private double radius;
		
	private double maxVelocity = 300000;
	
	private boolean isTerminated;
	
	private World superWorld;
	
	private Ship source = null;

	/**
	 * Create a new non-null bullet with the given position, velocity and
	 * radius,
	 * 
	 * The bullet is not located in a world nor loaded on a ship.
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius, double mass) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius, mass);
	}

	/**
	 * Check whether <code>bullet</code> is terminated.
	 */
	public boolean isTerminatedBullet() {
		return this.isTerminated;
	}

	/**
	 * Return the position of <code>ship</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getBulletPosition() {
		return this.Position;
	}

	/**
	 * Return the velocity of <code>ship</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getBulletVelocity() {
		return this.Velocity;
	}

	/**
	 * Return the radius of <code>bullet</code>.
	 */
	public double getBulletRadius() {
		return this.radius;
	}

	/**
	 * Return the mass of <code>bullet</code>.
	 */
	public double getBulletMass() {
		return this.getMass();
	}

	public double getMaxVelocity(){
		return this.maxVelocity;
	}
	
	/**
	 * Return the world in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned in a world, or
	 * if it is positioned on a ship.
	 */
	
	public World getBulletWorld() throws ModelException {
			return superWorld;
	}

	/**
	 * Return the ship in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned on a ship.
	 */
	public Ship getBulletShip() {
		if(superWorld == null)
			return source;
		else
			return null;
	}

	/**
	 * Return the ship that fired <code>bullet</code>.
	 */
	public Ship getBulletSource() {
		return source;
	}
	
	/**
	 * Terminate <code>bullet</code>.
	 */
	public void terminateBullet() {
		this.isTerminated = true;
	}
	
	public void setSource(Ship source){
		if(this.getBulletSource() == null)
			this.source = source;
	}
	
}
