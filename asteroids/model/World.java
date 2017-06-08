package asteroids.model;

import java.util.*;


import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of worlds involving width, a height, a set of
 * ships which are in the world and a set of bullets which are in the world.
 * 
 * @invar   Width must be a valid dimension.
 *          | isValidDimension(width)
 * @invar   Height must be a valid dimension.
 *          | isValidDimension(height)
 * @invar	The entities in each world must be proper entities
 * 			for that world.
 * 			| hasProperEntities()
 *          
 * @author  Brent De Bleser & Jesse Geens
 * @version 3.0
 */
public class World {

	/**
	 * A constant which contains the maximum possible value for a dimension.
	 */
	private final static Double UPPER_BOUND_WORLD = Double.MAX_VALUE;

	
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
	 * @post    If one of the given dimensions isn't a valid dimension set that dimension to the closest possible dimension.
	 *          | if	!(isValidDimension(width))
	 *          |	then	if		width < 0.0
	 *          |				then	new.width = 0.0
	 *       	|			else	new.width = UPPER_BOUND_WORLD
	 *          | if	!(isValidDimension(height))
	 *          |	then 	if 		height <0.0
	 *          |				then	new.height = 0.0
	 *          |			else	new.height = UPPPER_BOUND_WORLD
	 */
	public World(double width, double height){
		
		if(isValidDimension(width))
			this.worldWidth = width;
		
		else{
			if (width < 0.0)
				this.worldWidth = 0.0;
			
			else this.worldWidth = UPPER_BOUND_WORLD;
		}
		
		if (isValidDimension(height))
			this.worldHeight = height;
		
		else{
			if (height < 0.0)
				this.worldHeight = 0.0;
			
			else this.worldHeight = UPPER_BOUND_WORLD;
		}
		
	}
	
	/**
	 * Initializes a world with the default size.
	 * 
	 * @effect 	A new world with the highest possible dimensions.
	 * 			| World(UPPER_BOUND_WORLD, UPPER_BOUND_WORLD)
	 */
	@Raw
	public World() {
		this(UPPER_BOUND_WORLD, UPPER_BOUND_WORLD);
	}
			
	
	/**
	 * Returns the width of the world.
	 */
	@Basic @Raw @Immutable
	public double getWorldWidth(){
		return worldWidth;
	}
	
	/**
	 * Variable containing the width of the world.
	 */
	private final double worldWidth;
	
	/**
	 * returns the height of the world.
	 */
	@Basic @Raw @Immutable
	public double getWorldHeight(){
		return worldHeight;
	}
	
	//Total
	/**
	 * Check whether the given angle is a valid angle.
	 * 
	 * @param  dimension
	 *         The dimension to check.
     * @return True if and only if dimension is smaller or equal to 0 and
	 *         dimension is smaller or equal.
	 *         | result == !((dimension < 0) || (dimension > Double.MAX_VALUE))
	 */
	@Model @Raw
	private boolean isValidDimension(double dimension){
		return !((dimension < 0) || (dimension > UPPER_BOUND_WORLD) || !Helper.isValidDouble(dimension));
	}
		
	/**
	 * Variable containing the height of the world.
	 */
	private final double worldHeight;
	
	
	
	/**
	 * Check whether this world has the given entity as one of the entities in it.
	 * @param 	entity
	 * 			The entity to be check.
	 */
	@Basic @Raw
	public boolean hasAsEntity(Entity entity){
		return entities.contains(entity);
	}
	
	
	/**
	 * Checks whether or not this world has proper entities in it.
	 * 
	 * @return	Returns true if and only if this world can have each of it's entities as
	 * 			entity in it and if each of these entities references this world as their superWorld.
	 * 			| result ==
	 * 			|	for each entity in 	entities :
	 * 			|		(	if	(this.hasAsEntity(entity))
	 * 			|			then	(canHaveAsEntity(entity)
	 * 			|				&&	entity.getWorld() == this))
	 */
	@Raw
	public boolean hasProperEntities(){
		
		for (Entity entity : entities){
			if ( this.canHaveAsEntity(entity) && entity.getSuperWorld() == this) return false;
		}
		return true;
	}
	
	/**
	 * Return the number of entities in this world.
	 */
	@Basic
	public int getNbEntities() {
		return entities.size();
	}
	
	/**
	 * Returns all entities located in this world
	 * 
	 * @result The method returns a set with all entities
	 *         | result == entities
	 */
	@Basic
	public Set<? extends Entity> getEntities(){
		Set<Entity> resultSet = new HashSet<Entity>(entities);
		return resultSet;
	}

	//Total
	/**
	 * Returns all entities located in this world
	 * 
	 * @result The method returns a set with all entities in the set entities of a specified type.
	 *         | Set<Entity> resultSet = new HashSet<Entity>();
     *		   | for each 	element in	 entities
     *		   | 	 if 	element.typeNamrr() == type
     *		   | 		then	resultSet.add(element)
     *		   | result == result	
	 */
	public Set<? extends Entity> getEntitiesOfTheClass(String type){
		
		Set<Entity> resultSet = new HashSet<Entity>();
		for	(Entity element : entities) {
			if (element.getTypeName() == type)
				resultSet.add(element);
		}
		return resultSet;
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
			
			return getEntities().stream()
					.filter(i -> i.getPosition()[0] == x && i.getPosition()[1] == y)
					.findFirst()
					.orElse(null);
		}

	
	/**
	 * Add an entity to the world.
	 * 
	 * @param   entity
	 *          The Entity to be added to the World.
	 * @throws  IllegalArgumentExceptione
	 *          Throws an IllegalArgumentException if the entity can't be placed on in the world.
	 *          | !(canPlaceEntity(entity))
	 * @post    entity is added to the hash set bullets of the world to which the method is invoked
	 *          and the entity's superWorld is set to the world the method is invoked.
	 *          | entities.add(entity)
	 *		    | entity.setSuperWorld(this)
	 */
	public void addEntityToWorld(Entity entity){
		
		if(canHaveAsEntity(entity)){
			entities.add(entity);
			entity.setSuperWorld(this);
		}
		else 	throw new IllegalArgumentException("Can't place entity in entities @ World");
	}
	
	/**
	 * Remove the Entity from the World.
	 * 
	 * @param   entity
	 *          The entity to be removed from the world.
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the bullet isn't in the world.
	 *          | !(entities.contains(entity))
	 * @post    The entity is removed from the hashset entities
	 *          | for some element in entities
	 *          |	if  element == entity
	 *          |		entities.remove(elemnt)
	 * @post	The entitie's superWorld is set to null.
	 * 			| entity.setSuperWorld(null)
	 */
	public void removeEntityFromWorld(Entity entity) throws IllegalArgumentException {
		if(entities.contains(entity)){
			entity.setSuperWorld(null);
			entities.remove(entity);
		}
		else throw new IllegalArgumentException("entity isn't part of the world on which it want's to be removed.");
	}	
		
	/**
	 * Checks whether or not an Entity can be placed in the World.
	 * 
	 * @param	entity
	 * 		 	The entity to be checked.
	 * @return	The method returns false if the Entity is already in another world. 
	 * 			| if	entity.getWorld() != null
	 * 			| 		then	result == false
	 * @return  If the Entity isn't placed in any world, the result equals wheter or not the entity
	 * 			overlaps with an other entity in the world or the boundries of the world.
	 * 			| if	entity.getWorld() == null
	 * 			|		then result == !(entityOverlap(entity) || entityBoundryOverlap(entity))
	 * @throws 	IllegalArgumentException
	 * 			Throws an IllegalArgumentException if the entity is terminated.
	 * 			| entity.isTerminated()
	 */
	@Model @Raw
	private boolean canHaveAsEntity(Entity entity) throws IllegalArgumentException{
		if(!(entity.isTerminated())){
			if(entity.getSuperWorld() != null){
				return false;
		}
			return !(entityOverlap(entity) || entityBoundryOverlap(entity));
		}
		else{
			throw new IllegalArgumentException("Entity is already terminated.");
		}
	}
	/**
	 * A method which controls whether or not an entity is overlapping with a boundry.
	 * 
	 * @param 	entity
	 * 			The entity to be controlled.
	 * @return 	Returns true if and only if the x or y position minus 99% of the radius of the entity is smaller then the 0
	 * 			or if the x and y position of the entity added with 99% of the radius exceed the worldWidth or worldHeight respectively.
	 * 			| result == 
	 * 			| 	entity.getPosition()[0]-(entity.getRadius()/100)*99) <= 0.0 
	 * 			|		|| (entity.getPosition()[1]-(entity.getRadius()/100)*99) <= 0.0
	 * 			|			|| entity.getPosition()[0]+(entity.getRadius()/100)*99) >= worldWidth
	 * 			|				|| entity.getPosition()[1]+(entity.getRadius()/100)*99) >= worldHeight
	 */
	public boolean entityBoundryOverlap(Entity entity){
		
		if ( (entity.getPosition()[0]-(entity.getRadius()/100)*99) <= 0.0 || (entity.getPosition()[0]+(entity.getRadius()/100)*99) >= worldWidth) return true;
		if ( (entity.getPosition()[1]-(entity.getRadius()/100)*99) <= 0.0 || (entity.getPosition()[1]+(entity.getRadius()/100)*99) >= worldHeight) return true;
		return false;
	}
	/**
	 * 
	 * @param entity
	 * 		  The entity from which you want to know if it overlaps with something already in this world
	 * @return True if there's already an entity on that position
	 * 		   | result == a.overlap(entity)
	 */
	@Model 
	private boolean entityOverlap(Entity entity){
		return getEntities().stream()
				.filter(other -> entity.overlap(other) && (entity != other))
				.findFirst()
				.isPresent();
	}
	
	/**
	 * HashSet containing all entities in the world.
	 */
	private Set<Entity> entities = new HashSet<Entity>();
	

	/**
	 * Terminate this world.
	 * 
	 * @effect	Every superWorld of each entity in this world is set to null
	 * 			| for each entity in entitiesm
	 * 			|	entity.setSuperWorld(null)
	 * @effect	entities is cleared.
	 * 			| entities.clear()
	 * @post	isTerminated is set to true.
	 * 			| new.isTerminated = true
	 */
	public void terminate(){
		 entities.forEach(entity -> entity.setSuperWorld(null));
		 entities.clear();
	     this.isTerminated = true;
	}
	
	/**
	 * Checks whether the world is terminated.
	 */
	@Basic @Raw
	public boolean isTerminatedWorld(){
		return this.isTerminated;
	}
	
	/**
	 * Variable containing whether the world is terminated.
	 */
	private boolean isTerminated = false;
	
	
	/**
	 * The method which evolves the state of the game
	 * 
	 * @param	dt 
	 * 			The time over which the world is evolved.
	 * @param	collisionListener
	 * 
	 * 			A object that listens if there are any collisions while evolving.
	 * @post	Every entity in the world will be moved for the given time dt according to it's specifics.	
	 * 			| @ implementation
	 * @post	If there are any collisions detected while evolving the entities will collide.
	 * 			| @ implementation
	 * @post 	If there are any collisions collisionListener will hear them.
	 * 			| @ implementation 	I
	 */
	public void evolve(double dt, CollisionListener collisionListener) throws IllegalArgumentException{

		
		if(dt < 0 || !(Helper.isValidDouble(dt)))
			throw new IllegalArgumentException("Time given at evolve is invalid");
		
		double tC = getTimeToNextCollision();
		
		double[] pos = getNextCollisionPos();
		
		Entity[] entities = getNextCollidingEntities();
		
		while (tC <= dt){
			for(Entity entity: getEntities()) entity.move(tC);
			if(entities[1] == null){
				if(collisionListener != null) collisionListener.boundaryCollision(entities[0], pos[0], pos[1]);
				entities[0].collideBoundary();
			}
			else {
				if(collisionListener != null) collisionListener.objectCollision(entities[0], entities[1], pos[0], pos[1]);
				entities[0].collide(entities[1]);
				
			}
			
			dt = dt - tC;
			tC = getTimeToNextCollision();
			pos = getNextCollisionPos();
			entities = getNextCollidingEntities();
		}
		for(Entity entity: getEntities()){ 
			if(dt > 0){
				if (entity instanceof Planetoid) {Planetoid planetoid = (Planetoid) entity; planetoid.move(dt);}
				else if (entity instanceof Ship) {Ship ship = (Ship) entity; ship.move(dt);}	
				else entity.move(dt);
			}
		}
	}

	/**
	 * A method which caclutates the time to the next collision.
	 * 
	 * @return	This method's result is the time to the next collision whether this is a boundary collision or a entity collision.
	 * 			If two entities seem too collide but they move away from each other the method recognizes this
	 * 			and will not consider this time as a future collision.
	 * 			| @ implementation
	 * @return	If there aren't any collisions in the futere the time to the next collision is infinity.
	 * 			| @ implementation
	 */
	public double getTimeToNextCollision(){
		
		double time = Double.POSITIVE_INFINITY;
		for (Entity entity1 : getEntities()){
		
			time = Math.min(time, entity1.getTimeCollisionBoundary());
			
			for (Entity entity2 : getEntities()){
				if (entity1 != entity2) {
					if (entity1.overlap(entity2)) return 0;
					double currentdistance = (Math.hypot(entity2.getPosition()[0] - entity1.getPosition()[0], 
                            entity2.getPosition()[1] - entity1.getPosition()[1]) - (entity1.getRadius() + entity2.getRadius()));
                    double nextdistance = (Math.hypot((entity2.getPosition()[0] + (entity2.getVelocity()[0] * 0.00001)) - 
                            (entity1.getPosition()[0] + (entity1.getVelocity()[0] * 0.00001)), 
                            (entity2.getPosition()[1] + (entity2.getVelocity()[1] * 0.00001)) - (entity1.getPosition()[1] + (entity1.getVelocity()[1] * 0.00001)))
                            - (entity1.getRadius() + entity2.getRadius()));
                    if (currentdistance >= nextdistance) {
					time = Math.min(time, entity1.getTimeToCollision(entity2));}
				}
			}
		}
		return time;
	}
	
	/**
	 * A method which finds the position of the next collision.
	 * 
	 * @return	If there will be no collisions ever the position returned is an array
	 * 			containing two infinities.
	 * 			| Entity[] nextCollidingEntities = getNextCollidingEntities()
	 *			|	if(nextCollidingEntities[0] == null)
	 *			|		then result == new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}
	 * @effect 	If the next collision is a boundary collision the result will be the value of the method getPositionCollisionBoundary()
	 * 			invoked on the colliding entity.
	 * 			| Entity[] nextCollidingEntities = getNextCollidingEntities()
	 * 			| if  nextCollidingEntities[1] == null
	 * 			|	then	result == nextCollidingEntities[0].getPositionCollisionBoundary()
	 * @effect  If the next collision is a collision between two entities the result will be the value of the method
	 * 			getCollisionPosition() which is invoked on of the colliding entities and with argument the other colliding entity
	 * 			| Entity[] nextCollidingEntities = getNextCollidingEntities()
	 * 			| if (nextCollidingEntities[1] == null)
	 * 			|	then result == nextCollidingEntities[0].getCollisionPosition(nextCollidingEntities[1])
	 */
	public double[] getNextCollisionPos(){
		Entity[] nextCollidingEntities = getNextCollidingEntities();
		if(nextCollidingEntities[0] == null) {
			return new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
			}
		if (nextCollidingEntities[1] == null) {
			return nextCollidingEntities[0].getPositionCollisionBoundary();
			}
		else
			return nextCollidingEntities[0].getCollisionPosition(nextCollidingEntities[1]);	
	}
	
	
	/**
	 * A method that finds the entities which will collide the earliest.
	 * 
	 * @return	The method returns an array of size two which contains the two entities which 
	 * 			will collide earliest. If this is a boundary collision the entity at index 0 will 
	 * 			be the colliding entity and the entity at index 1 will be null. 
	 * 			If there aren't any collisions in the future the array will contain two nulls.
	 * 			| @ implementation
	 */
	public Entity[] getNextCollidingEntities(){
		
		Entity[] entities = new Entity[]{null,null};
		double timeNextCollision = Double.POSITIVE_INFINITY;
		
		for (Entity entity1 : this.getEntities()){
			if (timeNextCollision > entity1.getTimeCollisionBoundary()){
				timeNextCollision = entity1.getTimeCollisionBoundary();
				entities = new Entity[]{entity1,null};
			}
			for (Entity entity2 : this.getEntities()){
				if (entity1 != entity2) {
					if (entity1.overlap(entity2)) return new Entity[]{entity1,entity2};
					if (timeNextCollision > entity1.getTimeToCollision(entity2)){
						timeNextCollision = entity1.getTimeToCollision(entity2);
						entities = new Entity[]{entity1,entity2};
						
					}
				}
			}
		}
		return entities;
	}
}
