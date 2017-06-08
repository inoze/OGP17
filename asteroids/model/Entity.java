package asteroids.model;
import be.kuleuven.cs.som.annotate.*;



/**
 * A class which deals with the behavior of Entities in the this version of asteroids. 
 * 
 * @invar  	The velocity of each entity must be a valid velocity for an entity.
 *         	| this.isValidVelocity(this.getVelocity()[0]) && this.isValidVelocity(this.getVelocity()[1])
 *
 * @invar  	The position of each entity must be a valid position for an entity.
 *         	| this.isValidPosition(this.getPosition()[0]) && this.isValidPosition(this.getPosition()[1])
 *
 * @invar  	The radius of an entity must be a valid double.
 *         	| Helper.isValidDouble(this.getRadius())
 *        
 * @invar  	The max velocity of an entity must be a valid max velocity for an entity.
 * 		 	| this.isValidMaxVelocity(this.getMaxVelocity()) 
 *
 * @invar 	The super world of entity must be a valid super world for an entity.
 * 			| this.isvalidSuperWorld(this.getSuperWorld))
 * 
 * @invar	The mass of an entity must be bigger then 0 and musn't be infinity.
 * 			| this.getMass() > 0 && this.getMass() != Double.POSITIVE_INFINITY
 *
 * @author Brent De Bleser & Jesse Geens
 * @version 2.92
 */
public abstract class Entity {
	
	/**
	 * Constant containing the speed of light.
	 */
	private final double SPEED_OF_LIGHT = 300000;
	
	/**
	 * 
	 * @param   x
	 *          The x coordinate of the new entity.
	 * @param   y
	 * 		    The y coordinate of the new entity.
	 * @param   xVelocity
	 *          The x velocity of the new entity.
	 * @param   yVelocity
	 *          The y velocity of the new entity.
	 * @param   radius
	 *          The radius of the new entity.
	 * @param   typeName
	 *          String  representation of the type of the entity.
	 *          
	 * @effect  x and y are used to set the position of the entity.
	 *          | this.setPosition(x, y)       
	 * @effect  xVelocity and yVelocity are used to set the velocity of the entity.
	 *          | this.setVelocity(xVelocity, yVelocity)
	 *          
	 * @post    radius is set as the radius of the entity.
	 *          | new.radius = radius
	 * @post    typeName is set to the given typeName.
	 *          | new.typeName = typeName
	 */
	protected Entity (double x, double y, double xVelocity, double yVelocity, double radius, String typeName) throws IllegalArgumentException {
			// Set position of entity (setter itself is defensive.
            try{this.setPosition(x, y);} catch(IllegalArgumentException ex){throw new IllegalArgumentException(ex.getMessage());}
            //Set velocity of the entity (setter itself is total).
            this.setVelocity(xVelocity, yVelocity);
            //Set radius of entity.
            this.startRadius = radius;
            //Set the typeName of the entity.
            this.typeName = typeName;
	}
	
	
	
	/**
    * Returns the position of the entity.
    * 
    * @note The x coordinate is at index 0 and the y coordinate at 1.
    */
   @Basic @Raw
   public double[] getPosition(){
	   double[] result = {this.position[0], this.position[1]};
	   return result;
   }
   
   /**
    * Moves the entity to a new position during a time span of dt.
    *
    * @param   dt
    *          The time over which the ship is moving.
    * @throws  IllegalArgumentException
    *          Throws an IllegalArgumentException if dt id smaller then zero.
    *         |  ((dt < 0.0) 
    * @effect  Sets the entity position with the current position and velocity times dt.
    *         | this.setPosition(this.position[0] + (this.velocity[0] * dt), this.position[1] + (this.velocity[1] * dt))
    */
   public void move(double dt) throws IllegalArgumentException{
	   
       if (dt < 0.0) throw new IllegalArgumentException("Invalid time");
       else{
           this.setPosition(this.getPosition()[0] + (this.getVelocity()[0] * dt), this.getPosition()[1] + (this.getVelocity()[1] * dt));          
           }    
   }
   
   /**
    * Set the position of an entity to the given values.
    *
    * @param  xPosition
    *         The x coordinate of the new position.
    * @param  yPosition
    *         The y coordinate of the new position;
    * @post   The arguments xPosition and yPosition become the X- and Y-coordinates respectively.
    *         | new.position[0] = xPosition;
    *         | new.position[1] = yPosition;
    * @throws IllegalArgumentException
    *         xPosition isn't valid.
    *         | (!isValidPosition(xPosition))
    * @throws IllegalArgumentException
    *         yPosition isn't valid.
    *         | (!isValidPosition(yPosition)
    */
   protected void setPosition(double xPosition, double yPosition) throws IllegalArgumentException {
       if ( (!isValidPosition(xPosition)) || (!isValidPosition(yPosition))) throw new IllegalArgumentException("Invalid position");
       else {
           this.position[0] = xPosition;
           this.position[1] = yPosition;
       }
   }
	
   /**
    * Check whether the given position is a valid position for
    * a entity.
    *  
    * @param   position
    *          The position to check.
    * @return  True if and only if the entity is in a world  and the position is a valid double or if and only if the entity isn't 
    * 		   in a world and the position isn't NaN.
    *          | result ==
    *          |	if this.superWorld == null
    *          |		then 	!Double.isNaN(position)
    *          |	else		Helper.isValidDouble(position)
    */
   	@Model
   private boolean isValidPosition(double position){
	   if (this.superWorld == null) return (!Double.isNaN(position));
	   else return Helper.isValidDouble(position);
   }
   
	/**
     * Variable containing the coordinates of the ship in the form of an array with length 2.
     */
    private double[] position = new double[2];
    
    /**
     *Returns the velocity of the entity.
     *
     *@note The x component of the velocity is at index 0 and the y component is at index 1.
     */
    @Basic @Raw
    public double[] getVelocity(){
    	 double[] result = {this.velocity[0], this.velocity[1]};
  	   return result;
    }
    
    /**
     * Method to return the total velocity of the entity.
     * 
     * @return 	Returns the total velocity of an entity if it is a valid max velocity..
     * 			Else the result is the speed of light
     * 			| velocity = Math.hypot(this.getVelocity()[0] + this.getVelocity()[1])
     * 			| if (velocity <= getMaxVelocity())
     * 			|	result == velocity
     * 			| else
     * 			|	result == SPEED_OF_LIGHT
     */
    public double getTotalVelocity(){
    	double velocity = Math.hypot(this.getVelocity()[0], this.getVelocity()[1]);
		if (velocity > getMaxVelocity()) velocity = SPEED_OF_LIGHT;
		return velocity;
    }
    
    /**
     * Set the velocity of the entity to the given values.
     *
     * @param xVelocity
     *        The new velocity on the X-axis.
     * @param yVelocity
     *        The new velocity on the Y-axis.
     * @post  If the given velocities for the X- and Y-axis are valid velocities
     *        the new velocities on the X- and Y-axis are equal the given ones.
     *        | if !isValidVelocity(xVelocity, yVelocity)
     *        |      then new.getShipVelocity()[0] == xVelocity
     *        |           new.getShipVelocity()[1] == yVelocity
     * @post  If the given velocity on the X- or Y-axis aren't valid velocities
     *        the new velocities on the X- and Y-axis will be equal to 0.
     *        | if isValidVelocity(xVelocity, yVelocity)
     *        |      then new.getShipvelocity()[0] == 0
     *        |           new.getShipVelocity()[1] == 0
     *
     */
    protected void setVelocity(double xVelocity, double yVelocity){
        if ( !isValidVelocity(xVelocity, yVelocity)){
            this.velocity[0] = 0;
            this.velocity[1] = 0;
        }
        else {
            this.velocity[0] = xVelocity;
            this.velocity[1] = yVelocity;
        }
    }
    
    /**
     * Check whether the given velocities are a valid velocities for
     * a entity.
     *
     * @param   velocityX
     *          The velocity on the X-axis to check.
     * @param	velocitY
     * 			The velocity on the Y-axis to check.
     * @return  True if and only if the given velocities is a double isn't negative and the to be total velocity isn't faster then maxVelocity.
     *         	| result == ( (Math.hypot(velocityX, velocityY) <= maxVelocity && Helper.isValidDouble(velocityX) && Helper.isValidDouble(velocityY))
     */
    @Model
    private boolean isValidVelocity(double velocityX, double velocityY){
        return (Math.hypot(velocityX, velocityY) <= maxVelocity && Helper.isValidDouble(velocityX) && Helper.isValidDouble(velocityY));
    }  
    
    /**
     * variable containing the velocity on the x and y axis as an array of length 2.
     */
    private double[] velocity = new double[2];
    
    /**
     * Returns the maximum velocity of the entity.
     */
    @Basic @Raw @Immutable
    public double getMaxVelocity(){
        return this.maxVelocity = SPEED_OF_LIGHT;
    }
    
    /**
     * A method which sets the value of the maximum (total) velocity of an entity.
     * 
     * @param 	max
     * 			The value to be set as the max velocity.
     * @post	If the given velocity isn't a valid max velocity it, the max velocity will be set
     * 			to the speed of light.
     * 			| if !isValidMaxVelocity(max) 
     * 			|	then	this.maxVelocity = SPEED_OF_LIGHT
     */
    private void setMaxVelocity(double max){
    	if (!isValidMaxVelocity(max)) this.maxVelocity = SPEED_OF_LIGHT;
    	else this.maxVelocity = max;
    }
    /**
     * A method which checks whether or a not a given velocity is a valid maxVelocity.
     * 
     * @param 	max
     * 			The velocity to check.
     * @return 	The result is true if and only if the speed isn't faster then the speed of light, isn't smaller then 0 and if it is a valid double.
     * 			| result == !(max > SPEED_OF_LIGHT || !Helper.isValidDouble(max) || max < 0)
     */
    @Model
    private boolean isValidMaxVelocity(double max){
    	return !(max > SPEED_OF_LIGHT || !Helper.isValidDouble(max) || max < 0);
    }
    
    /**
     * Variable containing the maximum velocity of the entity in km/s.
     */
    private double maxVelocity = SPEED_OF_LIGHT;
    
    /**
     * Returns the radius of entity.
     */
    @Basic @Raw @Immutable
    public double getRadius(){
        return this.startRadius;
    }
    
    /**
     * Variable containing the radius of the entity.
     */
    private final double startRadius;
    
	/**
	 * Return the world of the entity.
	 */
    @Basic @Raw
	public World getSuperWorld() {
		return this.superWorld;
	}
    
    /**
     * Set the superWorld of an entity to the given world.
     * 
     * @param  world
     *         The world to be set as the new superWorld.
     * @post   If the world is a valid world or the world is null the world will be set as the new superWorld.
     *         | if 		isValidWorld(world) || world == null
     *         | 	then 	new.superWorld = world
     * @throws IllegalArgumentException
     *         An IllegalArgumentException is thrown if the world isn't a valid one.
     *         | !(isValidWorld(world))
     */
    protected void setSuperWorld(World world) throws IllegalArgumentException{
    	if(world == null || isValidSuperWorld(world))		this.superWorld = world;
    	else throw new IllegalArgumentException("Isn't a valid world @setSuperWorld()");
    }
    
    /**
     * Check whether the given world is a valid world for
     * a entity.
     * 
     * @param   world
     *          The world to be checked.
     * @return  Returns true if and only if the world isn't terminated or if it is null.
     *          |  result ==  world == null || !world.isTerminatedWorld()
     */ 
    @Model
    private boolean isValidSuperWorld(World world){
 	   return (world == null || !world.isTerminatedWorld());
    }
    
    /**
     * Variable containing the world in which the entity is positioned.
     * 
     * @note If the entity isn't in a world superWorld contains null.
     */
    private World superWorld = null;
    
    /**
     * Returns whether the entity is terminated.
     */
    @Basic @Raw
    public boolean isTerminated(){
    	return this.isTerminated;
    }
    
    /**
     * Method to terminate this entity.
     * 
     * @post   The superWorld of the entity is set to null and isTerminated is set to true.
     *         | new.superWorld = null
	 *	       | new.isTerminated = true
	 * @effect If the entity's superWorld isn't null it will be removed from it's superWorld.
	 * 		   | if this.getSuperWorld() == null
	 * 		   | 	then	this.superWorld.removeEntityFromWorld(this)
     */
	public void terminate(){
		if (superWorld != null)
		this.superWorld.removeEntityFromWorld(this);
		
		this.superWorld = null;
		this.isTerminated = true;
	}
    
    /**
     * Variable containing whether the entity is terminated.
     */
    protected boolean isTerminated = false;
    
    /**
     * returns the mass of the entity.
     */
    @Basic @Raw
    public double getMass(){
    	return this.mass;
    }
    
    /**
     * This method sets the mass of a entity.
     * 
     * @param 	mass
     * 			The mass to be set.
     * @post	The mass is set to the given mass.
     * 			| new.mass == mass
     */
    @Raw
    protected void setMass(double mass){
    	this.mass = mass;
    }
    
    /**
     * Variable containing the individual mass of the entity.
     */
	private double mass;
	
	
	/**
	    * returns in String form which type of Entity this is.
	    * 
	    * @return returns the typeName of this entity.
	    * 		  | result ==  typeName
	    */
	   @Basic @Immutable
	   public String getTypeName() {
		   return typeName;
	   }
	  /**
	   * A string representation of the type of the entity.
	   */
	   private final String typeName;

	   
	   public void collide(Entity entity) throws IllegalArgumentException{
	 
    	if (this instanceof Ship && entity instanceof Ship) this.defaultCollide(entity);
    	else if (this instanceof MinorPlanet && entity instanceof MinorPlanet) this.defaultCollide(entity);
    	else if (entity instanceof Bullet) {
    		Bullet bullet = (Bullet) entity;
    		if (bullet.getBulletSource() == this) bullet.bulletCollideOwnShip((Ship) this);
    		else bullet.bulletCollideSomethingElse(this);
    		}
    	
    	else if (this instanceof Bullet) {
    		Bullet bullet = (Bullet) this;
    		if (bullet.getBulletSource() == entity) bullet.bulletCollideOwnShip((Ship) entity);
    		else bullet.bulletCollideSomethingElse(entity);
    		}
    	
    	else if ((this instanceof Ship && entity instanceof Asteroid) || (entity instanceof Ship && this instanceof Asteroid)){
    		Ship ship = null;
    		if (this instanceof Ship){
    			ship = (Ship) this;
    		} else {
    			ship = (Ship) entity;
    		}
    		ship.getSuperWorld().removeEntityFromWorld(ship);
    		ship.terminate();
    	}
    	
    	else if ((this instanceof Ship && entity instanceof Planetoid) || (entity instanceof Ship && this instanceof Planetoid)){
    		Ship ship = null;
    		if (this instanceof Ship){
    			ship = (Ship) this;
    		} else {
    			ship = (Ship) entity;
    		}
    		World world = ship.getSuperWorld();
    		double xnew = ship.getRadius() + (Math.random() * (world.getWorldHeight() - ship.getRadius() * 2));
    		double ynew = ship.getRadius() + (Math.random() * (world.getWorldWidth() - ship.getRadius() * 2));
    		ship.setPosition(xnew, ynew);
    	}
	    	
    	
    }
    
    //Total
    /**
	 * Return the shortest time in which the given entity will collide with the
	 * boundaries of its world.
	 * 
	 * @return If the entity isn't in a world the shortest time to a bondary collision is POSITIVE_INFINITY.
	 *         | if 	this.superWorld == null
	 *         | 		then	result == Double.POSITIVE_INFINITY
	 * @return If the entity has a x and y velocity of zero the time to a boundary collision is POSITIVE_INFINITY.
	 *         | if 	this.velocity[0] == 0 && this.velocity[1] == 0
	 *         |		then 	result == Double.POSITIVE_INFINITY
	 * @return The method returns the shortest time to a boundary collision.
	 *         | double edgeY
	 *		   | double edgeX
	 *	       | double mY = 0
	 *	       | double mX = 0
	 *	       |
	 *	       | if 	velocity[0] > 0
	 *		   |	then 	edgeX = position[0] + radius
	 *		   |        	mX = this.superWorld.getWorldSize()[0]
	 *	       |
	 *	       | else 	edgeX = position[0] - radius
	 *	       | 	
	 *         |if 		velocity[1] > 0
	 *	       |	then	edgeY = position[1] + radius
	 *		   |        	mY = this.superWorld.getWorldSize()[1]
	 *		   |
	 *	       | else	 edgeY = position[1] - radius
	 *	       |
	 *	       | double tX = (mX-edgeX)/velocity[0]
	 *	       | double tY = (mY-edgeY)/velocity[1]
	 *	       |
	 *	       | if 	(tX <= tY) 
	 *         | 	then 	result == tX
	 *	       | else 	result == tY
	 */
	public double getTimeCollisionBoundary() {
		//Exceptions
		if (this.superWorld == null)	return Double.POSITIVE_INFINITY;
		if (this.velocity[0] == 0 && this.velocity[1] == 0)		return Double.POSITIVE_INFINITY;
		
		double edgeY;
		double edgeX;
		double mY = 0;
		double mX = 0;
		
		if (velocity[0] > 0){
			edgeX = position[0] + startRadius;
			mX = this.superWorld.getWorldWidth();
		}
		else edgeX = position[0] - startRadius;
		if (velocity[1] > 0){
			edgeY = position[1] + startRadius;
			mY = this.superWorld.getWorldHeight();
		}
		else edgeY = position[1] - startRadius;
		
		double tX  = Double.POSITIVE_INFINITY;
		double tY  = Double.POSITIVE_INFINITY;
		
		if (velocity[0] != 0){
			tX = (mX-edgeX)/velocity[0];
		}
		if (velocity[1] != 0){
			tY = (mY-edgeY)/velocity[1];
		}
		
		//Return the smallest value
		return Math.min(tX, tY); 
		
	}

	/**
	 * Return the first position at which the given entity will collide with the
	 * boundaries of its world.
	 * 
	 * @return
	 */
	public double[] getPositionCollisionBoundary(){
		if (!Helper.isValidDouble(this.getTimeCollisionBoundary()) || this.superWorld == null) return null;
		double[] pos = new double[2];
		pos[0] = this.getPosition()[0] + (this.getVelocity()[0] * this.getTimeCollisionBoundary());
		pos[1] = this.getPosition()[1] + (this.getVelocity()[1] * this.getTimeCollisionBoundary());
		
		if (pos[0] + this.getRadius() >= this.superWorld.getWorldHeight()) pos[0]+= this.getRadius();
		else if (pos[0] - this.getRadius() >= this.superWorld.getWorldHeight()) pos[0]-= this.getRadius();
		else if (pos[1] + this.getRadius() >= this.superWorld.getWorldHeight()) pos[1]+= this.getRadius();
		else	pos[1] -= this.getRadius();
			
		
		return pos;
	}
	
	/**
	    * Method that deals with collisions between entities and boundaries.
	    * 
	    * @post   If the entity collides with the vertical boundries the x velocity is multiplied by minus one.
	    * 		  | if	 (position[0] + radius == this.superWorld.getWorldSize()[0]) || (position[0] - radius == 0)
	    * 		  |		then 	new.velocity[0] = this.velocity[0] * -1
	    * @post   If the entity collides with the horizontal boundries the y velocity is multiplied by minus one.
	    * 		  | if	 (position[1] + radius == this.superWorld.getWorldSize()[1]) || (position[1] - radius == 0)
	    * 		  |		then 	new.velocity[1] = this.velocity[1] * -1
	    * @effect If the entity on which this method is invoced is a bullet and the entity collides with a boundary
	    *         the method bouncesCounter is invoced on the entity.
	    *         | if 	((position[0] + radius == this.superWorld.getWorldSize()[0]) || (position[0] - radius == 0) || 
	    *         |     (position[1] + radius == this.superWorld.getWorldSize()[1]) || (position[1] - radius == 0)) &&
	    *         |		(this instanceof Bullet)
	    *         |		then    	Bullet bullet = (Bullet) this
	    *		  |					bullet.bouncesCounter()	 
	    */
	    public void collideBoundary(){
	    	int bulletBouncer = 0;
	    	if ((this.getPosition()[0]-(this.getRadius()) <= 0.0 || (this.getPosition()[0]+(this.getRadius()) >= this.superWorld.getWorldWidth()))){
	    		if (this instanceof Bullet){
	        			bulletBouncer++;
	        	}
	    		this.velocity[0] = this.velocity[0] * -1;
	    	}
	    	if ((this.getPosition()[1]-(this.getRadius()) <= 0.0 || (this.getPosition()[1]+(this.getRadius()) >= this.superWorld.getWorldHeight()))){
	    		if (this instanceof Bullet){
	        		bulletBouncer++;
	        	}
	    		this.velocity[1] = this.velocity[1] * -1;
	    	} 	
	    	if (this instanceof Bullet){
	    	Bullet bullet = (Bullet) this;
	    	for (int i = 0; i < bulletBouncer; i++){
	    		if (!bullet.isTerminated())
	    		bullet.bouncesCounter();
	    	}
	    	}
	    }
	
	//Defensive
    /**
     * A method to get the distance between two centers entities.
     *
     * @param entity
     *         The entity between which and the entity to which the method is invoked
     *         the distance is measured.
     * @return Returns the distance between entity and the entity on which the method is invoced.
     *         | double diffx = Math.abs(this.position[0] - entity.getPosition()[0])
     *         | double diffy = Math.abs(this.position[1] - entity.getPosition()[1])
     *         | double diff = Math.sqrt(Helper.square(diffx) + Helper.square(diffy))
     *         | result == diff
     * @throws IllegalArgumentException
     *         Throws an IllegalArgumentException if the given entity is the same entity
     *         to which the method is invoced.
     *         | this == entity
     */
    public double getDistanceBetweenCenter(Entity entity) throws IllegalArgumentException{
        if(this == entity) throw new IllegalArgumentException("this == entity");
        else{
            double diffx = Math.abs(entity.getPosition()[0] - this.getPosition()[0]);
            double diffy = Math.abs(entity.getPosition()[1] - this.getPosition()[1]);
            double diff = Math.sqrt(Helper.square(diffx) + Helper.square(diffy));
            return diff;
        }
    }
   
    /**
     *  A method to get the distance between two edges entities.
     *
     * @param 	entity
     *         	The entity between which and the entity to which the method is invoked
     *         	the distance is measured.
     * @return	Returns the distance between the two centres minus both entity's radii.
     * 			| result == getDistanceBetweenCenter(entity) - this.getRadius() - entity.getRadius();
     * @throws 	IllegalArgumentException
     * 			Throws an IllegalArgumentException if the given entity is the same entity
     *         	to which the method is invoced.
     *         	| this == entity
     */
    public double getDistanceBetweenEdge(Entity entity)  throws IllegalArgumentException{
    	if(this == entity) throw new IllegalArgumentException("this == entity @ getDistanceBetweenEdge");
    	return getDistanceBetweenCenter(entity) - this.getRadius() - entity.getRadius();
    	
    }
    //Defensive
    /**
     * A method to check whether two entity overlap.
     *
     * @param   entity
     *          The entity to check whether it and the ship on which the method is invoced
     *          are overlapping.
     * @return  Returns true if the entity on which the method is invoced is the
     *          the same entity as entity.
     *          | if(this == entity)
     *          |    then result == true
     * @return  Returns whether the entities overlap significantly.
     *          | return (this.getDistancebetweenEdge(entity) <= -0.01)
     * @throws  IllegalArgumentException
     *          Throws an IllegalArgumentException if the argument entity is null.
     *          | entity == null
     *          
     */
    public boolean overlap(Entity entity){
    	
    	if (entity == null) throw new IllegalArgumentException("The second entity does not exist. @overlap");
		if (this == entity) return true;
		return this.getDistanceBetweenEdge(entity) <= -0.01;
    	//return this.getDistanceBetweenCenter(entity) <= (0.99 * (this.getRadius() + entity.getRadius()));
    	
    }
   
    //Defensive
    /**
     * A method to get the time it wil take to get two entities colliding
     * if they wil collide.
     * @param  entity
     *         The entity to get the time of collision from with itself and
     *         the entity on which the method is invoced.
     * @post   If the entities keep moving in the same way without an one of them getting changed
     *         in any way (velocity, position and radius must remain the same)
     *         The ships will collide with each other after the returned amount of time.
     *         The entites won't collide before the given amount of time given if not changed in anyway
     *         (velocity, position and radius must remain the same)
     *         | this.overlap(entity) == true
     *         |    This is true after the following code:
     *         |        this.move(this.getTimeToCollision(entity));
     *         |        entity.move(this.getTimeToCollision(entity));
     * @post   The entity will collide with each other after the returned amount of time.
     *         The entity won't collide before the given amount of time given if not changed in anyway
     *         (velocity, position and radius must remain the same)
     *         | this.overlap(ship) == False
     *         |    This is true after the following code:
     *         |        If (duration < this.getTimeToCollision(entity)){
     *         |            this.move(duration);
     *         |            entity.move(duration);
     *         |        }
     * @throws IllegalArgumentException
     *         Throws an IllegalArgumentException if the given entity is already overlapping
     *         with the entity to which the method is invoced.
     *         | this.overlap(entity)
     */
    public double getTimeToCollision(Entity entity) throws IllegalArgumentException{
    	if (this.getSuperWorld() != entity.getSuperWorld()) return Double.POSITIVE_INFINITY;
    	
        double a1 = entity.getVelocity()[0] - this.getVelocity()[0];
        double a2 = entity.getVelocity()[1] - this.getVelocity()[1];
        double a = Helper.square(a1) + Helper.square(a2);
        //exception 1:
        //The two entities follow an identical path so they don't collide.
        if (a == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
       
        double b1 = entity.getPosition()[0] - this.getPosition()[0];
        double b2 = entity.getPosition()[1] - this.getPosition()[1];
        double b = 2*((b1 * a1) + (b2 *a2));
       
        double s = this.getRadius() + entity.getRadius();
        double c = Helper.square(b1) + Helper.square(b2) - Helper.square(s);
     
        //exception 2:
        //If the quadratic equation doesn't give an answer the entites' paths cross but
        //don't the entities don't touch each other.
        if (Helper.quadraticSolver(a, b, c)[2] == 1.0){
            return Double.POSITIVE_INFINITY;
        }
        else{
            double dt1 = Helper.quadraticSolver(a, b, c)[0];
            double dt2 = Helper.quadraticSolver(a, b, c)[1];
           
          //dt exceptions:
           
            //  exception 3:
            //  if both answers are negative the entities are moving away from each other.
            if (dt1 < 0 && dt2 <0){
            	//Helper.log("Ships are moving away from eachother");
                return Double.POSITIVE_INFINITY;
            }
            if ((dt1 <= 0 && dt2 >= 0 ) || (dt1 > 0 && dt2 < 0)){
                //throw new IllegalArgumentException("Already overlaps while calling timeToCollision");
            	//Helper.log("Ships already overlap when calling timeToCollision");
            	//return Double.POSITIVE_INFINITY;
            	return 0;
            }
            double llo = Math.min(dt1,dt2);
            if (llo >= 0) return llo;
            else return Math.max(dt1,dt2);
        }
       
}
 
    //Defensive
    /**
     *A method to get the position where two entities collide.
     *
     * @param entity
     *        The entity between which and the entity to which the method invoced
     *        the collision position is searched.
     * @return Returns null if the entities don't collide.
     *         | if(this.getTimeToCollision(entity) == Double.POSITIVE_INFINITY)
     *         |    then return == null
     * @return Returns the collision position
     *         | double[] pos = new double[2]
     *         | double[] cp1 = this.getDistanceTraveled(this.getTimeToCollision(entity))
     *         | double[] cp2 = entity.getDistanceTraveled(this.getTimeToCollision(entity))
     *         | double s = this.getRadius() + ship.getRadius()
     *         | double diffx = Math.abs(cp2[0] - cp1[0])
     *         | double diffy = Math.abs(cp2[1] - cp1[1])
     *         | double cosinus, sinus
     *         |
     *         |if(diffx != 0)
     *         |     then cosinus = (square(diffx) + square(s) - square(diffy))/(2*diffx*s)
     *         |          sinus = Math.sin(Math.acos(cosinus))
     *         |          pos[0] = cp1[0] + this.getRadius() * cosinus
     *         |          pos[1] = cp1[1] + this.getRadius() * sinus
     *         | else
     *         |     then pos[0] = this.getDistanceTraveled(this.getTimeToCollision(entity))[0]
     *         |          pos[1] = this.getDistanceTraveled(this.getTimeToCollision(entity))[1] + this.getRadius()
     *         | return == pos
     * @throws IllegalArgumentException
     *         Throws IllegalArgumentException if the entities already overlap.
     *         | this.overlap(entity)
     */
    public double[] getCollisionPosition(Entity entity) throws IllegalArgumentException{
         
        /** if(this.overlap(entity)){
            throw new IllegalArgumentException("Entities overlap");
        }
        else{
            if(this.getTimeToCollision(entity) == Double.POSITIVE_INFINITY){
                return null;
            }
            else{
               
                double[] pos = new double[2];
                double[] cp1 = this.getDistanceTraveled(this.getTimeToCollision(entity));
                double[] cp2 = entity.getDistanceTraveled(this.getTimeToCollision(entity));
                double s = this.getRadius() + entity.getRadius();
                double diffx = Math.abs(cp2[0] - cp1[0]);
                double diffy = Math.abs(cp2[1] - cp1[1]);
                double cosinus, sinus;
               
                if(diffx != 0){
                    cosinus = (Helper.square(diffx) + Helper.square(s) - Helper.square(diffy))/(2*diffx*s);
                    sinus = Math.sin(Math.acos(cosinus));
                    Helper.log("sinus: " + sinus + "; cosinus: " + cosinus);
                    pos[0] = cp1[0] + this.getRadius() * cosinus;
                    pos[1] = cp1[1] + this.getRadius() * sinus;
                }
                else{
                    Helper.log("diffx is 0");
                    pos[0] = this.getDistanceTraveled(this.getTimeToCollision(entity))[0];
                    pos[1] = this.getDistanceTraveled(this.getTimeToCollision(entity))[1] + this.getRadius();
                }
               
                return pos;
            }
        }
        }  */
    	if (entity == null) throw new IllegalArgumentException("getCollisionPosition called with a non-existing circular object!");
		if (this.overlap(entity)) throw new IllegalArgumentException("These two circular objects overlap!");
		double timeToCollision = getTimeToCollision(entity);
			
		if (timeToCollision == Double.POSITIVE_INFINITY) return null;
		
		double[] positionThisShip = this.getPosition();
		double[] velocityThisShip = this.getVelocity();
		double[] positionShip2 = entity.getPosition();
		double[] velocityShip2 = entity.getVelocity();
		
		double xPositionCollisionThisShip = positionThisShip[0] + velocityThisShip[0] * timeToCollision;
		double yPositionCollisionThisShip = positionThisShip[1] + velocityThisShip[1] * timeToCollision;
		
		double xPositionCollisionShip2 = positionShip2[0] + velocityShip2[0] * timeToCollision;
		double yPositionCollisionShip2 = positionShip2[1] + velocityShip2[1] * timeToCollision;
		
		double slope = Math.atan2(yPositionCollisionShip2 - yPositionCollisionThisShip, xPositionCollisionShip2 - xPositionCollisionThisShip);
		
		
		return new double[] {xPositionCollisionThisShip + Math.cos(slope) * this.getRadius(), yPositionCollisionThisShip + Math.sin(slope) * this.getRadius()};
	}

 
   
  //Nominal
    /**
     * A helper method to get the centre of a entity when it traveled over a time.
     *
     * @param  time
     *         The time over which the entity moves.
     * @pre    time must be bigger then zero and musn't be infinty.
     *         | (time > 0) && Helper.isValidDouble(time)
     * @return Returns an array of length two, which contains the coordinates of the center of the ship when it
     *         has moved over time.
     *         | double pos[] = new double[2]
     *         |pos[0] = this.position[0] + this.velocity[0] * time
     *         |pos[1] = this.position[1] + this.velocity[1] * time
     *         |return == pos
     */
    public double[] getDistanceTraveled(double time){
        assert ((time > 0) && Helper.isValidDouble(time));
        double pos[] = new double[2];
        pos[0] = this.getPosition()[0] + this.getVelocity()[0] * time;
        pos[1] = this.getPosition()[1] + this.getVelocity()[1] * time;
        return pos;
    }
    
    public void defaultCollide(Entity ship){
   	 if ((ship == null) || (ship.isTerminated() == true) || (this.getSuperWorld() == null )
                || (ship.getSuperWorld() == null) || ((this.getSuperWorld() != ship.getSuperWorld()))) {
            return;
        }
        double [] locationdifference = {ship.getPosition()[0] - this.getPosition()[0], ship.getPosition()[1] - this.getPosition()[1]};
        double [] velocitydifference = {ship.getVelocity()[0] - this.getVelocity()[0],ship.getVelocity()[1] - this.getVelocity()[1]};
        double velocitylocationdifference = (velocitydifference[0] * locationdifference[0]) + (velocitydifference[1] * locationdifference[1]);
        double changetotal = (2 * this.getMass() * ship.getMass() * velocitylocationdifference) / ((this.getRadius() + ship.getRadius()) * (this.getMass() + ship.getMass()));
        double changex = changetotal * locationdifference[0] / (this.getRadius() + ship.getRadius());
        double changey = changetotal * locationdifference[1] / (this.getRadius() + ship.getRadius());
        this.setVelocity(this.getVelocity()[0] + changex / this.getMass(), this.getVelocity()[1] + changey / this.getMass());
        ship.setVelocity(ship.getVelocity()[0] - changex / ship.getMass(), ship.getVelocity()[1] - changey / ship.getMass());
		
	}

}
