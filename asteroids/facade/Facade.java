package asteroids.facade;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.*;
import asteroids.part3.facade.IFacade;
import asteroids.part3.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements IFacade {

	public int getNbStudentsInTeam(){
		return 2;
	}
	/**
	 * Return the position of ship as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 */
	
	public double[] getShipPosition(Ship ship) throws ModelException{
		return ship.getPosition();
	}

	/**
	 * Return the velocity of ship as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 */
	public double[] getShipVelocity(Ship ship) throws ModelException{
		return ship.getVelocity();
	}

	/**
	 * Return the radius of ship.
	 */
	public double getShipRadius(Ship ship) throws ModelException{
		return ship.getRadius();
	}

	/**
	 * Return the orientation of ship (in radians).
	 */
	public double getShipOrientation(Ship ship) throws ModelException{
		return ship.getShipDirection();
	}

	/**
	 * Update the direction of ship by adding angle
	 * (in radians) to its current direction. angle may be
	 * negative.
	 */
	public void turn(Ship ship, double angle) throws ModelException {
		try{
			ship.turn(angle);
		}
		catch( IllegalArgumentException ex){
			throw new ModelException(ex.getMessage());
		}
	}
	/**
	 * Return the distance between >ship1 and ship2.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 */
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException{
		try{
			return ship1.getDistanceBetween(ship2);
		}
		catch( IllegalArgumentException ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Check whether ship1 and ship2 overlap. A ship
	 * always overlaps with itself.
	 */
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException{
		try{
			return ship1.overlap(ship2);
		}
		catch( IllegalArgumentException ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Return the number of seconds until the first collision between
	 * ship1 and ship2, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 */
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException{
		 return ship1.getTimeToCollision(ship2);
		 
	}

	/**
	 * Return the first position where ship1 and ship2
	 * collide, or null if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 */
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException{
		return ship1.getCollisionPosition(ship2);
	}
	
	/**
	 * Create a new non-null ship with the given position, velocity, radius,
	 * direction and mass.
	 * 
	 * The thruster of the new ship is initially inactive. The ship is not
	 * located in a world.
	 */
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass) throws ModelException {
		try{
			Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
			return ship;
		} catch(IllegalArgumentException ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Terminate <code>ship</code>.
	 */
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminateShip();
	}

	/**
	 * Check whether <code>ship</code> is terminated.
	 */
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	/**
	 * Return the total mass of <code>ship</code> (i.e., including bullets
	 * loaded onto the ship).
	 */
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}

	/**
	 * Return the world of <code>ship</code>.
	 */
	public World getShipWorld(Ship ship) throws ModelException {
		//return ship.getShipWorld();
		return ship.getWorld();
	}

	/**
	 * Return whether <code>ship</code>'s thruster is active.
	 */
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.isShipThrusterActive();
	}

	/**
	 * Enables or disables <code>ship</code>'s thruster depending on the value
	 * of the parameter <code>active</code>.
	 */
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		if(active)
			ship.thrustOn();
		else
			ship.thrustOff();
	}

	/**
	 * Return the acceleration of <code>ship</code>.
	 */
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getShipAcceleration();
	}

	/**
	 * This method is deprecated; you should not implement nor use it.
	 * 
	 * 
	 * @deprecated Since part 2 of the project. This behavior is now triggered
	 *             through the {@link #evolve(World, double, CollisionListener)}
	 *             method.
	 */
	@Override
	@Deprecated
	public void move(Ship ship, double dt) throws ModelException {
		// do nothing; this behaviour is now triggered by the evolve method.
	}

	/**************
	 * BULLET: Basic methods
	 *************/

	/**
	 * Create a new non-null bullet with the given position, velocity and
	 * radius,
	 * 
	 * The bullet is not located in a world nor loaded on a ship.
	 */
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException {
		try{
			Bullet bullet = new Bullet(x, y, xVelocity, yVelocity, radius);
			return bullet;
		} catch(Exception ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Terminate <code>bullet</code>.
	 */
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminate();
	}

	/**
	 * Check whether <code>bullet</code> is terminated.
	 */
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	/**
	 * Return the position of <code>ship</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPosition();
	}

	/**
	 * Return the velocity of <code>ship</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getPosition();
	}

	/**
	 * Return the radius of <code>bullet</code>.
	 */
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	/**
	 * Return the mass of <code>bullet</code>.
	 */
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}

	/**
	 * Return the world in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned in a world, or
	 * if it is positioned on a ship.
	 */
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getBulletWorld();
	}

	/**
	 * Return the ship in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned on a ship.
	 */
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getBulletShip();
	}

	/**
	 * Return the ship that fired <code>bullet</code>.
	 */
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getBulletSource();
	}

	/**************
	 * WORLD: Basic methods
	 *************/

	/**
	 * Create a new world with the given <code>width</code> and
	 * <code>height</code>.
	 */
	public World createWorld(double width, double height) throws ModelException {
		try{
			World world = new World(width , height);
			return world;
		} catch(Exception ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Terminate <code>world</code>.
	 */
	public void terminateWorld(World world) throws ModelException {
		world.terminateWorld();
	}

	/**
	 * Check whether <code>world</code> is terminated.
	 */
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminatedWorld();
	}

	/**
	 * Return the size of <code>world</code> as an array containing the width,
	 * followed by the height.
	 */
	public double[] getWorldSize(World world) throws ModelException {
		return world.getWorldSize();
	}

	/**
	 * Return all ships located within <code>world</code>.
	 */
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return (Set<? extends Ship>) world.getEntitiesOfTheSameCLassAs("Ship");
	}

	/**
	 * Return all bullets located in <code>world</code>.
	 */
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return (Set<? extends Bullet>) world.getEntitiesOfTheSameCLassAs("Bullet");
	}

	/**
	 * Add <code>ship</code> to <code>world</code>.
	 */
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try{
			world.addShipToWorld(ship);
		}
		catch(Exception ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try{world.removeShipFromWorld(ship);}
		catch(IllegalArgumentException ex){throw new ModelException(ex.getMessage());}
	}

	/**
	 * Add <code>bullet</code> to <code>world</code>.
	 */
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addBulletToWorld(bullet);
	}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeBulletFromWorld(bullet);
	}

	/**************
	 * SHIP: Methods related to loaded bullets
	 *************/

	/**
	 * Return the set of all bullets loaded on <code>ship</code>.
	 * 
	 * For students working alone, this method may return null.
	 */
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBulletsOnShip();
	}

	/**
	 * Return the number of bullets loaded on <code>ship</code>.
	 */
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBulletsOnShip();
	}

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 */
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		ship.loadBulletOnShip(bullet);
	}

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 * 
	 * For students working alone, this method must not do anything.
	 */
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		ship.loadBulletsOnShip(bullets);
	}

	/**
	 * Remove <code>ship</code> from <code>ship</code>.
	 */
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBulletFromShip(bullet);
	}

	/**
	 * <code>ship</code> fires a bullet.
	 */
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
	}

	/******************
	 * COLLISIONS
	 **************/

	/**
	 * Return the shortest time in which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		if(object instanceof Entity)
			return ((Entity)object).getTimeCollisionBoundary();
		else
			throw new ModelException("Object is not an entity");
	}

	/**
	 * Return the first position at which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		if(object instanceof Entity)
			return ((Entity)object).getPositionCollisionBoundary();
		else
			throw new ModelException("Object is not an entity");
	}

	/**
	 * Return the shortest time in which the first entity will collide with the
	 * second entity.
	 */
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return 0;
	}

	/**
	 * Return the first position at which the first entity will collide with the
	 * second entity.
	 */
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		return null;
	}

	/**
	 * Return the time that must pass before a boundary collision or an entity
	 * collision will take place in the given world. Positive Infinity is
	 * returned if no collision will occur.
	 */
	public double getTimeNextCollision(World world) throws ModelException {
		return 0;
	}

	/**
	 * Return the position of the first boundary collision or entity collision
	 * that will take place in the given world. Null is returned if no collision
	 * will occur.
	 */
	public double[] getPositionNextCollision(World world) throws ModelException {
		return null;
	}

	/**
	 * Advance <code>world</code> by <code>dt<code> seconds. 
	 * 
	 * To enable explosions within the UI, notify <code>collisionListener</code>
	 * whenever an entity collides with a boundary or another entity during this
	 * method. <code>collisionListener</code> may be null. If
	 * <code>collisionListener</code> is <code>null</code>, do not call its
	 * notify methods.
	 */
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try{
			world.evolve(dt, collisionListener);
		}catch(IllegalArgumentException ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Return the entity at the given <code>position</code> in the given
	 * <code>world</code>.
	 */
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.entityAt(x, y);
	}

	/**
	 * Return a set of all the entities in the given world.
	 */
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntities();
	}

	/**************
	 * WORLD: Asteroids and planetoids
	 *************/

	/**
	 * Return all asteroids located in <code>world</code>.
	 */
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException{
		return (Set<? extends Asteroid>) world.getEntitiesOfTheSameCLassAs("Asteroid");
	}

	/**
	 * Add <code>asteroid</code> to <code>world</code>.
	 */
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException{
		try{world.addAsteroidToWorld(asteroid);}
		catch(IllegalArgumentException ex){throw new ModelException(ex.getMessage());}
	}

	/**
	 * Remove <code>asteroid</code> from <code>world</code>.
	 */
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException{
		try{world.removeAsteroidFromWorld(asteroid);}
		catch(IllegalArgumentException ex){throw new ModelException(ex.getMessage());}	
	}

	/**
	 * Return all planetoids located in <code>world</code>.
	 */
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException{
		return (Set<? extends Planetoid>) world.getEntitiesOfTheSameCLassAs("Planetoid");
	}

	/**
	 * Add <code>planetoid</code> to <code>world</code>.
	 */
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException{
		try{world.addPlanetoidToWorld(planetoid);}
		catch(IllegalArgumentException ex){throw new ModelException(ex.getMessage());}	
		}

	/**
	 * Remove <code>planetoid</code> from <code>world</code>.
	 */
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException{
		try{world.removePlanetoidFromWorld(planetoid);}
		catch(IllegalArgumentException ex){throw new ModelException(ex.getMessage());}
	}

	/**************
	 * ASTEROID: Basic methods
	 *************/

	/**
	 * Create a new non-null asteroid with the given position, velocity and
	 * radius.
	 * 
	 * The asteroid is not located in a world.
	 */
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException{
		try{
			Asteroid asteroid =  new Asteroid(x, y, xVelocity, yVelocity, radius);
			return asteroid;
		}catch(IllegalArgumentException ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Terminate <code>asteroid</code>.
	 */
	public void terminateAsteroid(Asteroid asteroid) throws ModelException{
		asteroid.terminate();
	}

	/**
	 * Check whether <code>asteroid</code> is terminated.
	 */
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException{
		return asteroid.isTerminated();
	}

	/**
	 * Return the position of <code>asteroid</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException{
		return asteroid.getPosition();
	}

	/**
	 * Return the velocity of <code>asteroid</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException{
		return asteroid.getVelocity();
	}

	/**
	 * Return the radius of <code>asteroid</code>.
	 */
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException{
		return asteroid.getRadius();
	}

	/**
	 * Return the mass of <code>asteroid</code>.
	 */
	public double getAsteroidMass(Asteroid asteroid) throws ModelException{
		return asteroid.getMass();
	}

	/**
	 * Return the world in which <code>asteroid</code> is positioned.
	 */
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException{
		return asteroid.getWorld();
	}

	/**************
	 * PLANETOID: Basic methods
	 *************/

	/**
	 * Create a new non-null planetoid with the given position, velocity,
	 * radius, and total traveled distance.
	 * 
	 * The planetoid is not located in a world.
	 */
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance) throws ModelException{
		try{
			Planetoid planetoid = new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
			return planetoid;
		}
		catch(IllegalArgumentException ex){
			throw new ModelException(ex.getMessage());
		}
	}

	/**
	 * Terminate <code>planetoid</code>.
	 */
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException{
		planetoid.terminate();
	}

	/**
	 * Check whether <code>planetoid</code> is terminated.
	 */
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException{
		return planetoid.isTerminated();
	}

	/**
	 * Return the position of <code>planetoid</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException{
		return planetoid.getPosition();
	}

	/**
	 * Return the velocity of <code>planetoid</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException{
		return planetoid.getVelocity();
	}

	/**
	 * Return the radius of <code>planetoid</code>.
	 */
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException{
		return planetoid.getRadius();
	}

	/**
	 * Return the mass of <code>planetoid</code>.
	 */
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException{
		return planetoid.getMass();
	}

	/**
	 * Return the total traveled distance of <code>planetoid</code>.
	 */
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException{
		return planetoid.getTotalDistanceTraveled();
	}

	/**
	 * Return the world in which <code>planetoid</code> is positioned.
	 */
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException{
		return planetoid.getWorld();
	}

	/**********
	 * PROGRAMS
	 **********/

	/**
	 * Return the program loaded on the given ship.
	 */
	public Program getShipProgram(Ship ship) throws ModelException{
		return null;
	}

	/**
	 * Load the given program on the given ship.
	 */
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException{}

	/**
	 * Execute the program loaded on the given ship during the given period of
	 * time. The ship is positioned in some world. Returns null if the program
	 * is not completely executed. Otherwise, returns the objects that have been
	 * printed.
	 */
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException{
		return null;
	}

	/**
	 * Creates a new program factory.
	 */
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException{
		return null;
	}
}

