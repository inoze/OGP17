package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @invar The velocity of each entity must be a valid velocity for a entity.
 *        | isValidVelocity(getVelocity()[0]) && isValidVelocity(getVelocity()[1])
 *
 * @invar The position of each entity must be a valid position for a entity.
 *        | isValidPosition(getPosition()[0]) && isValidPosition(getPosition()[1])
 *
 * @invar The maximum speed of each entity must be between 0 and 300 000
 *        | getMaxVelocity > 0 && getMaxVelocity < 300000
 *
 *
 * @author Brent De Bleser & Jesse Geens
 * @version 2.92
 */
public class Entity {
	
	/**
	 * Cnstant containing the speed of light.
	 */
	protected final double SPEED_OF_LIGHT =  300000;
	
	/**
	 * Constant containing the density of asteroids.
	 */
	protected final double ASTEROID_DENSITY = 2.65E12;
	/**
	 * Constant containing the density of planetoids.
	 */
	protected final double PLANETOID_DENSITY = 0.917E12;
	
	/**
	 * constant containing the minimal radius of a ship.
	 */
	protected final double MINIMAL_ASTEROID_RAD = 5;
	/**
	 * constant containing the minimal radius of an asteroid.
	 */
	protected final double MINIMAL_PLANETOID_RAD = 5;
	/**
     * Variable containing the coordinates of the ship in the form of an array with length 2.
     */
    protected double[] position = new double[2];
    /**
     * variable containing the velocity on the x and y axis as an array of length 2.
     */
    protected double[] velocity = new double[2];
    /**
     * Variable containing the maximum velocity of the entity in km/s.
     */
    protected double maxVelocity = SPEED_OF_LIGHT;
    /**
     * Variable containing the radius of the entity.
     */
    protected final double radius;
    
	/**
	 * Return the world of the entity.
	 */
    @Basic
	public World getWorld() {
		return this.superWorld;
	}
    
    /**
     * Variable containing the world in which the entity is positioned.
     * 
     * @note If the entity isn't in a world superWorld conatins null.
     */
    protected World superWorld = null;
    /**
     * Variable containing whether the entity is terminated.
     */
    protected boolean isTerminated = false;
    
    /**
     * This method sets the mass of a entity.
     * 
     * @param 	mass
     * 			The mass to be set.
     * @post	The mass is set to the given mass.
     * 			| new.mass == mass
     */
    protected void setMass(double mass){
    	this.mass = mass;
    }
    
    /**
     * Variable containing the individual mass of the entity.
     */
	protected double mass;
	
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
	 * @throws  IllegalArgumentException
	 *          Throws an IllegalArgumentException if the radius is invalid.
	 *          | !(isValidRadius(radius))
	 *          
	 * @effect  x and y are used to set the position of the entity.
	 *          | this.setPosition(x, y)       
	 * @effect  xVelocity and yVelocity are used to set the velocity of the entity.
	 *          | this.setVelocity(xVelocity, yVelocity)
	 *          
	 * @post    radius is set as the radius of the entity.
	 *          | new.radius = radius
	 * @post    If the Entity is an instance of Bullet the mass is calculated with BULLET_DENSITY.
	 *          | if 	this instanceof Bullet
	 *          |	then	new.mass = (4/3)*Math.PI*Math.pow(radius, 3)*BULLET_DENSITY
	 * @post	If the Entity is an instance of Ship the mass is calculated with SHIP_DENSITY.
	 *          | if 	this instanceof Ship
	 *          |	then	new.mass = (4/3)*Math.PI*Math.pow(radius, 3)*SHIP_DENSITY
	 * @post    typeName is set to the given typeName.
	 *          | new.typeName = typeName
	 */
	protected Entity (double x, double y, double xVelocity, double yVelocity, double radius, String typeName) throws IllegalArgumentException {
		//Control if the radius is valid.
		
			// Set position of entity (setter itself is defensive.
            try{this.setPosition(x, y);} catch(IllegalArgumentException ex){throw new IllegalArgumentException(ex.getMessage());}
            //Set velocity of the entity (setter itself is total).
            this.setVelocity(xVelocity, yVelocity);
            //Set radius of entity.
            this.radius = radius;
            //Set the typeName of the entity.
            this.typeName = typeName;
            //Set the mass of the entity.
            if(this instanceof Asteroid){this.mass = (4/3)*Math.PI*Math.pow(radius, 3)*ASTEROID_DENSITY;}
            if(this instanceof Planetoid){this.mass = (4/3)*Math.PI*Math.pow(radius, 3)*PLANETOID_DENSITY;}            
        
	}
	
	//Getters  
    /**
     * Returns the position of the entity.
     */
    @Basic
    public double[] getPosition(){
        return this.position;
    }
    /**
     *Returns the velocity of the entity.
     */
    @Basic
    public double[] getVelocity(){
        return this.velocity;
    }
    /**
     * Returns the maximum velocity of the entity.
     */
    @Basic
    public double getMaxVelocity(){
        return this.maxVelocity;
    }
    /**
     * Returns the radius of entity.
     */
    @Immutable
    @Basic
    public double getRadius(){
        return this.radius;
    }
    /**
     * returns the mass of the entity.
     */
    @Basic
    public double getMass(){
    	return this.mass;
    }

    /**
     * Returns whether the entity is terminated.
     */
    @Basic @Raw
    public boolean isTerminated(){
    	return this.isTerminated;
    }
   

    //Defensive
    /**
     * Set the position on the X-axis to xPosition.
     * Set the position on the Y-axis to yPosition.
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
   
    //Total
    /**
     * Set the velocity on the X-axis to xVelocity and
     * set the velocity on the Y_axis to yVelocity.
     *
     * @param xVelocity
     *        The new velocity on the X-axis.
     * @param yVelocity
     *        The new velocity on the Y-axis.
     * @post  If the given velocities for the X- and Y-axis are of type double and aren't infinite
     *        the new velocities on the X- and Y-axis are equal the given ones.
     *        | if !isValidVelocity(xVelocity, yVelocity)
     *        |      then new.getShipVelocity()[0] == xVelocity
     *        |           new.getShipVelocity()[1] == yVelocity
     * @post  If the given velocity on the X- or Y-axis isn't a double or is infinity
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
     * Set the mass of a entity to a given mass.
     * 
     * @param mass
     *        The new mass of the entity.
     * @post  If the mass is a valid mass and the entity is an instance of ship
     *        the new mass of the ship will be mass.
     *        | If 	isValidMass(mass) && this instanceof Ship
     *        | 	then new.mass = mass
     * @post  If the mass isn't valid and the entity is an instance of ship the new 
     *        mass of the ship will be the mass according to it's radius.
     *        | If !(isValidMass(mass) && this instanceof Ship) 
     *        | 	then 	this.mass = (4/3)*Math.PI*Math.pow(radius, 3)*SHIP_DENSITY
     * @post  If the entity isn't an instance of ship the method does nothing.
     */
   /** protected void setMass(double mass){
    	if(isValidMass(mass) && (this instanceof Ship))		 this.mass = mass;
    	else if  (this instanceof Ship) 	 this.mass = (4/3)*Math.PI*Math.pow(radius, 3)*SHIP_DENSITY;
    }*/
    
    //Defensive
    /**
     * Set the superWorld of an entity to the given world.
     * 
     * @param  world
     *         The world to be set as the new superWorld.
     * @post   If the world is a valid world the world will be set as the new superWorld.
     *         | if 		isValidWorld(world)
     *         | 	then 	new.superWorld = world
     * @throws IllegalArgumentException
     *         An IllegalArgumentException is thown if the world isn't a valid one.
     *         | !(isValidWorld(world))
     */
    protected void setSuperWorld(World world) throws IllegalArgumentException{
    	if(world == null || isValidWorld(world))		this.superWorld = world;
    	else throw new IllegalArgumentException("Isn't a valid world");
    }
    
    /**
     * Terminate entity.
     * 
     * @post   The superWorld of the entity is set to null and is Terminayed is set to true.
     *         | new.superWorld = null
	 *	       | new.isTerminated = true
	 * @effect If the entity is an instence of ship the ship is removed from it's superWorld.
	 *         | this.superWorld.removeShipFromWorld((Ship) this)
	 * @effect If the entity is an instance of bullet the bullet is removed from it's superWorld.
	 *         | this.superWorld.removeBulletFromWorld((Bullet) this)
     */
	@Basic
	public void terminate(){
		if (this instanceof Ship){
			if (superWorld != null)
			this.superWorld.removeEntityFromWorld((Ship) this);
		}
		
		this.superWorld = null;
		this.isTerminated = true;
	}
	
	//defensive
    /**
     * Moves the ship to a new position during a timespan of dt.
     *
     * @param   dt
     *          The time over which the ship is moving.
     * @throws  IllegalArgumentException
     *          Throws an IllegalArgumentException if dt is infinity or is smaller then zero.
     *         |  ((dt < 0.0) && ( Double.isInfinite(dt)))
     * @effect  Sets the ship position with the current position and velocity times dt.
     *         | this.setShipPosition(this.position[0] + (this.velocity[0] * dt), this.position[1] + (this.velocity[1] * dt))
     */
    public void move(double dt) throws IllegalArgumentException{
        if (dt < 0.0) {
        	Helper.log(Double.toString(dt));
            throw new IllegalArgumentException("Invalid time");
        }
        else{
            try {
                   this.setPosition(this.getPosition()[0] + (this.getVelocity()[0] * dt), this.getPosition()[1] + (this.getVelocity()[1] * dt));
                   if(this instanceof Ship){
                	   Ship ship = (Ship) this;
                	   ship.setVelocity(ship.getVelocity()[0] + ship.getAcceleration() * Math.cos(ship.getDirection()) * dt, ship.getVelocity()[1] + ship.getAcceleration() * Math.sin(ship.getDirection())* dt);
                   }
            }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex.getMessage());
                }
            }    
    }
    
    
    //TODO comments
    public void collide(Entity entity){
    	Helper.log("Colliding entities");
    	//if(this.getDistanceBetween(entity) > 0.99 * (this.getRadius() + entity.getRadius()) && this.getDistanceBetween(entity) < 1.01 * (this.getRadius() + entity.getRadius())){
	    	
    	if(this instanceof Ship){
	    		if(entity instanceof Ship){
	    			this.setVelocity(this.getVelocity()[0] * -1, this.getVelocity()[1] * -1);
	    			entity.setVelocity(entity.getVelocity()[0] * -1, entity.getVelocity()[1] * -1);
	    		}
	    		if(entity instanceof Bullet){
	    			Bullet bullet = (Bullet) entity;
		    		if(bullet.getBulletSource() != entity){
		    			this.terminate();
		    			entity.terminate();
		    		}
		    		else{
		    			Ship ship = (Ship) this;
		    			ship.loadBulletsOnShip(bullet);
		    		}
	    		}
	    	}
	    	if(this instanceof Bullet){
	    		Bullet bullet = (Bullet) this;
	    		if(bullet.getBulletSource() != entity){
	    			this.terminate();
	    			entity.terminate();
	    		}
	    		else{
	    			Ship ship = (Ship) entity;
	    			ship.loadBulletsOnShip(bullet);
	    		}
	    	}
    	/*}
    	else{
    		if(this.overlap(entity)){
    			Helper.log("overlap detected");
    			this.terminate();
    			entity.terminate();
    		}
    		else
    			Helper.log("[ERROR] invalid collision called");
    			if(this instanceof Ship)
    				this.terminate();
    	}*/
    }
    
    //Total
   /**
    * Method that deals with collisions between entities and boundaries.
    * 
    * @post   If the entity collides with the vertical boundries the x velocity is multiplied by minus one.
    * 		  | if	 (position[0] + radius == this.superWorld.getWorldSize()[0]) || (position[0] - radius == 0)
    * 		  |		then 	new.velocity[0] = old.velocity[0] * -1
    * @post   If the entity collides with the horizontal boundries the y velocity is multiplied by minus one.
    * 		  | if	 (position[1] + radius == this.superWorld.getWorldSize()[1]) || (position[1] - radius == 0)
    * 		  |		then 	this.velocity[1] = old.velocity[1] * -1
    * @effect If the entity on which this method is invoced is a bullet and the entity collides with a boundary
    *         the method bouncesCounter is invoced on the entity.
    *         | if 	((position[0] + radius == this.superWorld.getWorldSize()[0]) || (position[0] - radius == 0) || 
    *         |     (position[1] + radius == this.superWorld.getWorldSize()[1]) || (position[1] - radius == 0)) &&
    *         |		(this instanceof Bullet)
    *         |		then    	Bullet bullet = (Bullet) this
    *		  |					bullet.bouncesCounter()	 
    */
    public void collideBoundary(){
    	if ((position[0] + radius == this.superWorld.getWorldWidth()) || (0.0 == position[0] - radius)){
    		if (this instanceof Bullet){
        		Bullet bullet = (Bullet) this;
        			bullet.bouncesCounter();
        	}
    		this.velocity[0] = this.velocity[0] * -1;
    	}
    	if ((position[1] + radius == this.superWorld.getWorldHeight()) || (0.0 == position[1] - radius)){
    		if (this instanceof Bullet){
        		Bullet bullet = (Bullet) this;
        			bullet.bouncesCounter();
        	}
    		this.velocity[1] = this.velocity[1] * -1;
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
	 *	       | else 	resutl == tY
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
			edgeX = position[0] + radius;
			mX = this.superWorld.getWorldWidth();
		}
		else edgeX = position[0] - radius;
		if (velocity[1] > 0){
			edgeY = position[1] + radius;
			mY = this.superWorld.getWorldHeight();
		}
		else edgeY = position[1] - radius;
		
		double tX = (mX-edgeX)/velocity[0];
		double tY = (mY-edgeY)/velocity[1];
		
		//Return the smallest value
		return Math.min(tX, tY); 
		
	}

	/**
	 * TODO comments
	 * Return the first position at which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double[] getPositionCollisionBoundary(){
		double[] pos = new double[2];
		pos[0] = this.getPosition()[0] + this.getVelocity()[0] * this.getTimeCollisionBoundary();
		pos[1] = this.getPosition()[1] + this.getVelocity()[1] * this.getTimeCollisionBoundary();
		return pos;
	}

	/**
	 * TODO comments
	 * Return the first position at which the first entity will collide with the
	 * second entity.
	 */
	/**public double[] getPositionCollisionEntity(Entity entity) throws ModelException {
		double[] pos = new double[2];
		pos[0] = this.getPosition()[0] + this.getVelocity()[0] * this.getTimeToCollision(entity);
		pos[1] = this.getPosition()[1] + this.getVelocity()[1] * this.getTimeToCollision(entity);
		return pos;
	}
	
	**/
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
    	if(this == entity) throw new IllegalArgumentException("this == entity");
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
     * @return  Returns whether the entitiies overlap.
     *          | return (this.getDistanceBetween(entity)/(this.getRadius()+entity.getRadius()) <= -0.01)
     * @throws  IllegalArgumentException
     *          Throws an IllegalArgumentException if the argument entity is null.
     *          | entity == null
     *          
     */
    public boolean overlap(Entity entity){
    	
    	if (entity == null) throw new IllegalArgumentException("The second entity does not exist.");
		if (this == entity) return true;
    	return this.getDistanceBetweenCenter(entity) <= (0.99 * (this.getRadius() + entity.getRadius()));
    	
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
            	return Double.POSITIVE_INFINITY;
            }
            //Helper.log("Ships will collide");
            return Math.min(dt1,dt2); 
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
         
         if(this.overlap(entity)){
        
            Helper.log("Entities overlap");
            throw new IllegalArgumentException("Entities overlap");
        }
        else{
            if(this.getTimeToCollision(entity) == Double.POSITIVE_INFINITY){
                Helper.log("Entities will never collide");
                return null;
            }
            else{
                Helper.log("Calculating collision position");
               
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
	
	//Total
    /**
    * Check whether the given velocities are a valid velocities for
    * a entity.
    *
    * @param   	velocityX
    *          	The velocity on the X-axis to check.
    * @param	velocitY
    * 			The velocity on the Y-axis to check.
    * @return  	True if and only if the given velocities is a double isn't negative and the total velocity isn't faster then maxVelocity.
    *         	| result == ( ( Math.sqrt(Helper.square(velocityX) + Helper.square(velocityY)) <= maxVelocity && Helper.isValidDouble(velocityX) && Helper.isValidDouble(velocityY))
    */
   protected boolean isValidVelocity(double velocityX, double velocityY){
       return ( Math.sqrt(Helper.square(velocityX) + Helper.square(velocityY)) <= maxVelocity && Helper.isValidDouble(velocityX) && Helper.isValidDouble(velocityY));
   }  
   
    //defensive
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
   protected boolean isValidPosition(double position){
	   if (this.superWorld == null) return (!Double.isNaN(position));
	   else return Helper.isValidDouble(position);
   }
   
      
   /**
    * Check whether the given world is a valid world for
    * a entity.
    * 
    * @param   world
    *          The world to be checked.
    * @return  The method returns whether or not the world is terminated.
    *          |  result ==  world.isTerminatedWorld()
    */ 
   protected boolean isValidWorld(World world){

	   return !(world.isTerminatedWorld());
   }
  
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
}
