package asteroids.model;

import asteroids.util.ModelException;

public class Bullet {
	
	private double[] Position = new double[2];
	
	private double[] Velocity = new double[2];
	
	private double radius;
	
	private double mass;
	
	private double maxVelocity = 300000;
	
	private boolean isTerminated;

	/**
	 * Create a new non-null bullet with the given position, velocity and
	 * radius,
	 * 
	 * The bullet is not located in a world nor loaded on a ship.
	 */
	public Bullet(){} 
	
	public void createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		if(isValidPosition(x) && isValidPosition(y) && isValidVelocity(xVelocity) && isValidVelocity(yVelocity) ){
			this.Position[0] = x;
			this.Position[1] = y;
			this.Velocity[0] = xVelocity;
			this.Velocity[1] = yVelocity;
			this.radius = radius;
		}
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
		return this.mass;
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
		return null;
	}

	/**
	 * Return the ship in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned on a ship.
	 */
	public Ship getBulletShip() throws ModelException {
		return null;
	}

	/**
	 * Return the ship that fired <code>bullet</code>.
	 */
	public Ship getBulletSource() throws ModelException {
		return null;
	}
	
	/**
	 * Terminate <code>bullet</code>.
	 */
	public void terminateBullet(Bullet bullet) throws ModelException {
		this.isTerminated = true;
	}
	
	private boolean isValidVelocity(double velocity){
		if(Helper.isValidDouble(velocity) && velocity < this.getMaxVelocity())
			return true;
		return false;
	}

	private boolean isValidPosition(double position){
		if(Helper.isValidDouble(position)){
			return true;
		}
		return false;
	}
}
