package asteroids.model;

import java.util.*;

import javax.sound.sampled.AudioFileFormat.Type;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TypeHost;

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
 *          
 * @author  Brent De Bleser & Jesse Geens
 * @version 3.0
 */
public class World {

	/**
	 * A constant which conatins the maximum possible value for a dimension.
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
	@Raw
	public World(double width, double height){
		
		if(isValidDimension(width))
			this.width = width;
		
		else{
			if (width < 0.0)
				this.width = 0.0;
			
			else this.width = UPPER_BOUND_WORLD;
		}
		
		if (isValidDimension(height))
			this.height = height;
		
		else{
			if (height < 0.0)
				this.height = 0.0;
			
			else this.height = UPPER_BOUND_WORLD;
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
		return !((dimension < 0) || (dimension > UPPER_BOUND_WORLD));
	}
	
	/**
	 * Variable containing the width of the world.
	 */
	private final double width;
	/**
	 * Variable containing the height of the world.
	 */
	private final double height;
	
	/**
	 * Returns all entities located in this world
	 * 
	 * @result The method returns a set with all entities
	 *         | result == entities
	 */
	@Basic
	public Set<? extends Entity> getEntities(){
		return entities;
	}
	
	/**
	 * Return the number of entities in this world.
	 * 
	 * @return  returns the size of entities.
	 * 			| result == entities.size()
	 */
	@Basic @Raw
	public int getNbEntities() {
		return entities.size();
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
	public Set<? extends Entity> getEntitiesOfTheCLass(String type){
		
		Set<Entity> resultSet = new HashSet<Entity>();
		for	(Entity element : entities) {
			if (element.getTypeName() == type)
				resultSet.add(element);
		}
		return resultSet;
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
	public void addEtityToWorld(Entity entity){
		
		if(canPlaceEntity(entity)){
			entities.add(entity);
			entity.setSuperWorld(this);
		}
		else 	throw new IllegalArgumentException("Can't place entity in entities @ World");
	}
	
	/**
	 * Remove the Entity from the World.
	 * 
	 * @param   entity
	 *          The bullet to be removed from the world.
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the bullet isn't in the world.
	 *          | !(entities.contains(entity))
	 * @post    The bullet is removed from the hashset bullets
	 *          | for some element in entities
	 *          |	if  element == entity
	 *          |		entities.remove(elemnt)
	 */
	public void removeEntityFromWorld(Entity entity) throws IllegalArgumentException {
		//Lambda #1
		if (entities.removeIf(i -> i == entity) == false) throw new IllegalArgumentException("Entity isn't placed in the world");
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
	 * Checks wheter or not a Entity can be placed in the World.
	 * 
	 * @param	Entity
	 * 		 	The entity to be checked.
	 * @return	The method returns false if the Entity is already in another world. 
	 * 			In other words if the position of the Entity doesn't equal null.
	 * 			| if	entityAt(entity.getPosition()[0], entity.getPosition()[1]) != null
	 * 			| 		then	result == false
	 * @return  If the Entity isn't placed in any world, the result equals wheter or not the entity
	 * 			overlaps with an other entity in the world.
	 * 			| if	entityAt(entity.getPosition()[0], entity.getPosition()[1]) == null
	 * 			|		then result == !(entityOverlap(entity))
	 * @throws 	IllegalArgumentException
	 * 			Throws an IllegalArgumentException if the entity is terminated.
	 * 			| entity.isTerminated()
	 */
	@Model 
	private boolean canPlaceEntity(Entity entity) throws IllegalArgumentException{
		if(!(entity.isTerminated())){
			if(entityAt(entity.getPosition()[0], entity.getPosition()[1]) != null)
				return false;
			return !(entityOverlap(entity));
		}
		else{
			throw new IllegalArgumentException("Entity is not valid");
		}
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
		for (Entity a : entities) {
			if(a.overlap(entity))
				return true;
			}
		return false;
	}
	
	/**
	 * HashSet conatining all entities in the world.
	 */
	private Set<Entity> entities = new HashSet<Entity>();
	
		
	
	

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
	            this.removeEntityFromWorld(bullet);
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
	
	/**
	 * Variable containing whether the world is terminated.
	 */
	private boolean isTerminated;
	
	/**
	 * A method which retunrs all the entities in the world.
	 * 
	 * @return  Returns a set of entities which contains all the entities in the world
	 *          | Set<Entity> entities = new HashSet<Entity>()
	 *	        | entities.addAll(bullets);
	 *       	| entities.addAll(ships);
	 *	        | result ==  entities 
	 */
	/**@Basic
	public Set<Entity> getEntities(){
		Set<Entity> entities = new HashSet<Entity>();
		entities.addAll(bullets);
		entities.addAll(ships);
		entities.addAll(asteroids);
		entities.addAll(planetoids);
		return entities;
	}*/
	
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
		for(Entity entity: getEntities()){ 
			if(dt > 0) entity.move(dt);
			else Helper.log("dt is " + dt);
		}
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
	
	
}
