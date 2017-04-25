package asteroids.model;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import asteroids.part2.CollisionListener;

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
	 * HashSet containing all the planetoids in the world.
	 */
	private Set<Planetoid> planetoids = new HashSet<Planetoid>();
	/**
	 * HashSet containing all the asteroids in the world.
	 */
	private Set<Asteroid> asteroids = new HashSet<Asteroid>();
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
			throw new IllegalArgumentException("Invalid dimensions given @ World");
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
		return size;
	}

	/**
	 * Returns all ships located in this world
	 */
	@Basic
	public Set<? extends Ship> getShips(){
		return ships;
	}

	/**
	 * Returns all bullets located in this world
	 */
	@Basic
	public Set<? extends Bullet> getBullets(){
		return bullets;
	}
	/**
	 * Returns all planetoids located in this world
	 */
	@Basic
	public Set<? extends Planetoid> getPlanetoids(){
		return planetoids;
	}
	/**
	 * Returns all asteroids located in this world
	 */
	@Basic
	public Set<? extends Asteroid> getAsteroids(){
		return asteroids;
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
	 *          and the ship's superWorld is set to the world the method is invoked, if there isn't already an entity on that position.
	 *          | ships.add(ship)
	 *		    | ship.setSuperWorld(this)
	 */
	public void addShipToWorld(Ship ship) throws IllegalArgumentException {
		if(canPlaceEntity(ship)){
			ships.add(ship);
			ship.setSuperWorld(this);
		}else{
			Helper.log("Can't place entity");
		}
		/*if(isValidShip(ship)){
			if(ship.getWorld() == null){
				if(this.entityAt(ship.getPosition()[0], ship.getPosition()[1]) == null && !(entityOverlap(ship))){

					if(ship.getPosition()[0] > 0 && ship.getPosition()[1] > 0 && ship.getPosition()[0] + ship.getRadius() < this.getWorldSize()[0] && ship.getPosition()[1] + ship.getRadius() < this.getWorldSize()[1]){
						ships.add(ship);
						ship.setSuperWorld(this);
					}
					else
						throw new IllegalArgumentException("Ship is out of bounds");
				}
				else{
					Helper.log("Already an entity on the position");
				}
			}
			else{
				throw new IllegalArgumentException("Ship is already in a world");}
			}
		else{
			throw new IllegalArgumentException("ship isn't a valid Ship.");
			}*/
	}

	/**
	 * Remove the ship from the world.
	 * 
	 * @param   ship
	 *          The ship to be removed from the world.
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the ship isn't in the world.
	 *          | !(ships.contains(ship))
	 * @post    The ship is removed from the hashset ships
	 *          | for each ship in ships
	 *          |		ships.remove(ship)
	 */
	public void removeShipFromWorld(Ship ship) throws IllegalArgumentException {
		if (ships.contains(ship)){
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
		if(canPlaceEntity(bullet)){
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
	 * @post    The bullet is removed from the hashset bullets
	 *          | for each bullet in bullets
	 *          |		bullets.remove(bullet)
	 */
	public void removeBulletFromWorld(Bullet bullet) throws IllegalArgumentException {
		if (bullets.contains(bullet)){
			//bullet.setSuperWorld(null);
			//bullet.terminate();
			bullets.remove(bullet);
		}
		else	throw new IllegalArgumentException("bullet isn't in the world.");
		
	}
	/**
	 * Add the planetoid to the world.
	 * 
	 * @param   planetoid
	 *          The planetoid to be added to the world.
	 * @throws  IllegalArgumentExceptione
	 *          Throws an IllegalArgumentException if the planetoid isn't a valid Planetoid.
	 *          | !(isValidBullet(bullet))
	 * @post    planetoid is added to the hash set planetoids of the world to which the method is invoked
	 *          and the planetoid's superWorld is set to the world the method is invoked.
	 *          | planetoids.add(planetoid)
	 *		    | planetoid.setSuperWorld(this)
	 */
	public void addPlanetoidToWorld(Planetoid planetoid){
		if(canPlaceEntity(planetoid)){
			planetoids.add(planetoid);
			planetoid.setSuperWorld(this);
		}
		else	throw new IllegalArgumentException("planetoid isn't a valid Planetoid.");
	}

	/**
	 * Remove the planetoid from the world.
	 * 
	 * @param   planetoid
	 *          The planetoid to be removed from the world.
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the planetoid isn't in the world.
	 *          | !(planetoids.contains(planetoid))
	 * @post    The planetoid is removed from the hashset planetoids
	 *          | for each planetoid in planetoids
	 *          |		planetoids.remove(planetoid)
	 */
	public void removePlanetoidFromWorld(Planetoid planetoid) throws IllegalArgumentException {
		if (planetoids.contains(planetoid)){
			planetoids.remove(planetoid);
		}
		else	throw new IllegalArgumentException("planetoid isn't in the world.");
		
	}	/**
	 * Add the asteroid to the world.
	 * 
	 * @param   asteroid
	 *          The asteroid to be added to the world.
	 * @throws  IllegalArgumentExceptione
	 *          Throws an IllegalArgumentException if the asteroid isn't a valid asteroid.
	 *          | !(isValidasteroid(asteroid))
	 * @post    asteroid is added to the hash set asteroids of the world to which the method is invoked
	 *          and the asteroid's superWorld is set to the world the method is invoked.
	 *          | asteroids.add(asteroid)
	 *		    | bullet.setSuperWorld(this)
	 */
	public void addAsteroidToWorld(Asteroid asteroid){
		if(canPlaceEntity(asteroid)){
			asteroids.add(asteroid);
			asteroid.setSuperWorld(this);
		}
		else	throw new IllegalArgumentException("asteroid isn't a valid Asteroid.");
	}

	/**
	 * Remove the asteroid from the world.
	 * 
	 * @param   asteroid
	 *          The asteroid to be removed from the world.
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the asteroid isn't in the world.
	 *          | !(asteroids.contains(asteroid))
	 * @post    The asteroid is removed from the hashset asteroids
	 *          | for each asteroid in asteroids
	 *          |		asteroids.remove(asteroid)
	 */
	public void removeAsteroidFromWorld(Asteroid asteroid) throws IllegalArgumentException {
		if (asteroids.contains(asteroid)){
			asteroids.remove(asteroid);
		}
		else	throw new IllegalArgumentException("asteroid isn't in the world.");
		
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
		    	Helper.log("EntityAt");
		    }
		}
		for (Entity a : bullets){
			if (a.getPosition() == position){ 
		    	counter++;
		    	entity = a;
		    	Helper.log("EntityAt");
			}
		}

		if (counter > 1){
			Helper.log("More than one object on position " + position);
			entity = null;
		}

		return entity;
	}
	/**
	 * 
	 * @param entity
	 * 		  The entity from which you want to know if it overlaps with something already in this world
	 * @return True if there's already an entity on that position
	 * 		   | result == a.overlap(entity)
	 */
	private boolean entityOverlap(Entity entity){
		for (Entity a : ships) {
			if(a.overlap(entity) && a != entity)
				Helper.log("Ships overlap at spawn");
				return true;
		}
		for (Entity a : bullets) {
			if(a.overlap(entity))
				Helper.log("Bullets and ship overlap at spawn");
				return true;
		}
		return false;
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
		entities.addAll(asteroids);
		entities.addAll(planetoids);
		return entities;
	}
	
	public void evolve(double dt, CollisionListener collisionListener){
		if(dt < 0 || !(Helper.isValidDouble(dt)))
			throw new IllegalArgumentException("Time given at evolve is invalid");
		Helper.log("evolving world");
		double tC = getTimeToNextCollision();
		Helper.log("getting tC: " + tC);
		double[] pos = getNextCollisionPos();
		Helper.log("Getting pos: " + pos[0] + "; " + pos[1]);
		Entity[] entities = getNextCollidingEntities();
		while (tC <= dt){
			for(Entity entity: getEntities()) entity.move(tC);
			if(entities[1] == null){
				if(collisionListener != null) collisionListener.boundaryCollision(entities[0], pos[0], pos[1]);
				entities[0].collideBoundary();
				Helper.log("Found boundary collision");
			}
			else {
				if(collisionListener != null) collisionListener.objectCollision(entities[0], entities[1], pos[0], pos[1]);
				entities[0].collide(entities[1]);
				Helper.log("Found entity collision");
				//Helper.log("Collision position: " + pos[0] + "; " + pos[1]);
				Helper.log("Time to collision: " + tC);
				
			}
			
			dt = dt - tC;
			tC = getTimeToNextCollision();
			pos = getNextCollisionPos();
			entities = getNextCollidingEntities();
		}
		for(Entity entity: getEntities()) entity.move(dt);
	}

	public double getTimeToNextCollision(){
		double time = Double.POSITIVE_INFINITY;
		for (Entity entity1 : getEntities()){
			time = Math.min(time, entity1.getTimeCollisionBoundary());
			for (Entity entity2 : getEntities()){
				if (entity1 != entity2) {
					if (entity1.overlap(entity2)) return 0;
					Helper.log("Calculating time to next collision");
					time = Math.min(time, entity1.getTimeToCollision(entity2));
				}
			}
		}
		return time;
	}
	
	public double[] getNextCollisionPos(){
		Entity[] nextCollidingEntities = getNextCollidingEntities();
		if(nextCollidingEntities[0] == null) 
			return new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
		if (nextCollidingEntities[1] == null) 
			return nextCollidingEntities[0].getPositionCollisionBoundary();
		else 
			return nextCollidingEntities[0].getCollisionPosition(nextCollidingEntities[1]);	
	}
	
	public Entity[] getNextCollidingEntities(){
		Entity[] entities = new Entity[]{null,null};
		double timeNextCollision = Double.POSITIVE_INFINITY;
		for (Entity entity1 : getEntities()){
			if (timeNextCollision > entity1.getTimeCollisionBoundary()){
				timeNextCollision = entity1.getTimeCollisionBoundary();
				entities = new Entity[]{entity1,null};
			}
			for (Entity entity2 : getEntities()){
				if (entity1 != entity2) {
					if (entity1.overlap(entity2)) return new Entity[]{entity1,entity2};
					
					if (timeNextCollision > entity1.getTimeToCollision(entity2)){
						timeNextCollision = entity1.getTimeToCollision(entity2);
						entities = new Entity[]{entity1,entity2};
						Helper.log("Colliding entities: ");
						if(entity1 instanceof Ship)
							Helper.log("1: ship");
						else
							Helper.log("1: bullet");
						if(entity2 instanceof Bullet)
							Helper.log("2: bullet");
						else
							Helper.log("2: Ship");
					}
				}
				
			}
		}
		return entities;
	}
	
	private boolean canPlaceEntity(Entity entity) throws IllegalArgumentException{
		if(isValidEntity(entity)){
			return true;
		}
		else{
			throw new IllegalArgumentException("Entity is not valid");
		}
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
	
	private boolean isValidEntity(Entity entity){
		if(entity.isTerminated())
			return false;
		return true;
	}
}
