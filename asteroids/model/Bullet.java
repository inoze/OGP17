package asteroids.model;

import asteroids.util.ModelException;

public class Bullet {
	
	private double[] Position = new double[2];
	
	private double[] Velocity = new double[2];
	
	private double radius;
	
	private double mass;
	
	private boolean isTerminated;

	/**
	 * Create a new non-null bullet with the given position, velocity and
	 * radius,
	 * 
	 * The bullet is not located in a world nor loaded on a ship.
	 */
	public Bullet(){} 
	
	public void createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		if(isValidPosition(x) && isValidPosition(y)){
			this.Position[0] = x;
			this.Position[1] = y;
			this.Velocity[0] = xVelocity;
			this.Velocity[1] = yVelocity;
			this.radius = radius;
		}
	}

	/**
	 * Terminate <code>bullet</code>.
	 */
	public void terminateBullet(Bullet bullet) throws ModelException {
		
	}

	/**
	 * Check whether <code>bullet</code> is terminated.
	 */
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return this.isTerminated;
	}

	/**
	 * Return the position of <code>ship</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return this.Position;
	}

	/**
	 * Return the velocity of <code>ship</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return this.Velocity;
	}

	/**
	 * Return the radius of <code>bullet</code>.
	 */
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return this.radius;
	}

	/**
	 * Return the mass of <code>bullet</code>.
	 */
	public double getBulletMass(Bullet bullet) throws ModelException {
		return this.mass;
	}

	/**
	 * Return the world in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned in a world, or
	 * if it is positioned on a ship.
	 */
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return null;
	}

	/**
	 * Return the ship in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned on a ship.
	 */
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return null;
	}

	/**
	 * Return the ship that fired <code>bullet</code>.
	 */
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return null;
	}
	private boolean isValidVelocity(double velocity){
		
	}

	private boolean isValidPosition(double position){
		if(!(Double.isNaN(position)) && Double.isFinite(position)){
			return true;
		}
		return false;
	}
}
