package asteroids.model;

import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @invar The velocity of each entity must be a valid velocity for a entity.
 *        | isValidVelocity(getVelocity()[0]) && isValidVelocity(getVelocity()[1])
 *
 * @invar The position of each entity must be a valid position for a entity.
 *        | isValidPosition(getPosition()[0]) && isValidPosition(getPosition()[1])
 *
 * @invar The radius of each entity must be a valid radius for a entity
 *        | isValidRadius(getRadius())
 *
 * @invar The direction of each entity must be a valid direction for a entity
 *        | isValidOrientation(getOrientation())
 *
 * @invar The maximum speed of each entity must be between 0 and 300 000
 *        | getMaxVelocity > 0 && getMaxVelocity < 300000
 *
 *
 * @author Brent De Bleser & Jesse Geens
 * @version 1.0
 */
public class Entity {
	
	/**
	 * Cnstant containing the speed of light.
	 */
	protected final double SPEED_OF_LIGHT =  300000;
	/**
	 * Cnstant containing the density of bullets.
	 */
	protected final double BULLET_DENSITY = 7.8 * Math.pow(10, 12);
	/**
	 * Constant containing the density of bullets.
	 */
	protected final double SHIP_DENSITY = 1.42 * Math.pow(10, 12);
	/**
	 * Constant containing the minimal radien of a bullet.
	 */
	protected final double MINIMAL_BULLET_RAD = 1;
	/**
	 * constant containing the minimal radien of a ship.
	 */
	protected final double MINIMAL_SHIP_RAD = 10;
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
    protected double radius;
    /**
     * Variable containing the world in which the entity is positioned.
     * 
     * @note If the entity isn't in a world superWorld conatins null.
     */
    protected World superWorld = null;
    /**
     * Variable containing whether the entity is terminated.
     */
    protected boolean isTerminated;
    /**
     * Variable containing the individual mass of the entity.
     */
	protected double mass;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param xVelocity
	 * @param yVelocity
	 * @param radius
	 * @throws IllegalArgumentException
	 */
	protected Entity (double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		if(isValidPosition(x) && isValidPosition(y) && isValidVelocity(xVelocity) && isValidVelocity(yVelocity) && isValidRadius(radius)){
            try{this.setPosition(x, y);} catch(IllegalArgumentException ex){throw new IllegalArgumentException(ex.getMessage());}
            this.setVelocity(xVelocity, yVelocity);
            this.radius = radius;
            if(this instanceof Bullet){this.mass = (4/3)*Math.PI*Math.pow(radius, 3)*BULLET_DENSITY;}
            if(this instanceof Ship){this.mass = (4/3)*Math.PI*Math.pow(radius, 3)*SHIP_DENSITY;}
        } 
		else{
			throw new IllegalArgumentException("Illegal argument at createEntity");
		}
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
	 * Return the world of the entity.
	 */
    @Basic
	public World getWorld() {
		//return ship.getShipWorld();
		return null;
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
     * set the velocity on the Y_axis to yVelocity..
     *
     * @param xVelocity
     *        The new velocity on the X-axis.
     * @param yVelocity
     *        The new velocity on the Y-axis.
     * @post  If the given velocities for the X- and Y-axis are of type double and aren't infinite
     *        the new velocities on the X- and Y-axis are equal the given ones.
     *        | if (!(isValidVelocity(yVelocity)) || (isValidVelocity(xVelocity))
     *        |      then new.getShipVelocity()[0] == xVelocity
     *        |           new.getShipVelocity()[1] == yVelocity
     * @post  If the given velocity on the X- or Y-axis isn't a double or is infinity
     *        the new velocities on the X- and Y-axis will be equal to 0.
     *        | if ((isValidVelocity(yVelocity)) || (isValidVelocity(xVelocity))
     *        |      then new.getShipvelocity()[0] == 0
     *        |           new.getShipVelocity()[1] == 0
     *
     */
    protected void setVelocity(double xVelocity, double yVelocity){
        if ( (!isValidVelocity(xVelocity)) || (!isValidVelocity(yVelocity))){
            this.velocity[0] = 0;
            this.velocity[1] = 0;
        }
        else {
            this.velocity[0] = xVelocity;
            this.velocity[1] = yVelocity;
        }
    }
    
    protected void setMass(double mass){
    	if(isValidMass(mass)){
    		if(this instanceof Ship) this.mass = mass;
    	}
    		
    }
    
    protected void setSuperWorld(World world){
    	if(isValidWorld(world))
    		this.superWorld = world;
    }
    
	@Basic
	protected void terminate(){
		this.isTerminated = true;
	}
	
    /**
	 * Return the shortest time in which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double getTimeCollisionBoundary() {
		return 0;
	}

	/**
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
	 * Return the shortest time in which the first entity will collide with the
	 * second entity.
	 */
	public double getTimeCollisionEntity(Entity entity) throws ModelException {
		return 0;
	}

	/**
	 * Return the first position at which the first entity will collide with the
	 * second entity.
	 */
	public double[] getPositionCollisionEntity(Entity entity) throws ModelException {
		double[] pos = new double[2];
		pos[0] = this.getPosition()[0] + this.getVelocity()[0] * this.getTimeCollisionEntity(entity);
		pos[1] = this.getPosition()[1] + this.getVelocity()[1] * this.getTimeCollisionEntity(entity);
		return pos;
	}
	
	//Defensive
    /**
     * A method to get the distance between two ships.
     *
     * @param ship
     *         The ship between which and the ship to which the method is invoked
     *         the distance is measured.
     * @return Returns the distance between ship and the ship on which the method is invoced.
     *         | double diffx = Math.abs(this.position[0] - ship.getShipPosition()[0])
     *         | double diffy = Math.abs(this.position[1] - ship.getShipPosition()[1])
     *         | double diff = Math.sqrt(Math.abs(square(diffx) - square(diffy)))
     *         | return == diff
     * @throws IllegalArgumentException
     *         Throws an IllegalArgumentException if the given ship is the same ship
     *         to which the method is invoced.
     *         | this == ship
     */
    public double getDistanceBetween(Entity entity) throws IllegalArgumentException{
        if(this == entity) throw new IllegalArgumentException("this == ship");
        else{
            double diffx = Math.abs(this.getPosition()[0] - entity.getPosition()[0]);
            double diffy = Math.abs(this.getPosition()[1] - entity.getPosition()[1]);
            double diff = Math.sqrt(Math.abs(Helper.square(diffx) - Helper.square(diffy)));
            return diff;
        }
    }
   
    //Defensive
    /**
     * A method to check whether two ships overlap.
     *
     * @param  ship
     *         The ship to check whether it and the ship on which the method is invoced
     *         are overlapping.
     * @return Returns true if the ship on which the method is invoced is the
     *         the same ship as ship.
     *         | if(this == ship)
     *         |    then return == true
     * @return Returns false if the ships don't overlap.
     *         |if(this.getDistanceBetween(ship) >= this.getShipRadius() + ship.getShipRadius())
     *         |    then return == false
     * @return Returns true if the ships overlap.
     *         |if(this.getDistanceBetween(ship) < this.getShipRadius() + ship.getShipRadius())
     *         |    then return == true
     */
    public boolean overlap(Entity entity){
        if(this == entity){
            return true;
        }
        else{
            double radius = this.getRadius() + entity.getRadius();
            if(this.getDistanceBetween(entity) >= radius)
                return false;
            else
                return true;
        }
    }
   
    //Defensive
    /**
     * A method to get the time it wil take to get two ships colliding
     * if they wil collide.
     * @param  ship
     *         The ship to get the time of collision from with itself and
     *         the ship on which the method is invoced.
     * @post   If the ships keep moving in the same way without an one of them getting changed
     *         in any way (velocity, position and radius must remain the same)
     *         The ships will collide with each other after the returned amount of time.
     *         The ships won't collide before the given amount of time given if not changed in anyway
     *         (velocity, position and radius must remain the same)
     *         | this.overlap(ship) == true
     *         |    This is true after the following code:
     *         |        this.move(this.getTimeToCollision(ship));
     *         |        ship.move(this.getTimeToCollision(ship));
     * @post   The ships will collide with each other after the returned amount of time.
     *         The ships won't collide before the given amount of time given if not changed in anyway
     *         (velocity, position and radius must remain the same)
     *         | this.overlap(ship) == False
     *         |    This is true after the following code:
     *         |        If (duration < this.getTimeToCollision(ship)){
     *         |            this.move(duration);
     *         |            ship.move(duration);
     *         |        }
     * @throws IllegalArgumentException
     *         Throws an IllegalArgumentException if the given ship is already overlapping
     *         with the ship to which the method is invoced.
     *         | this.overlap(ship)
     */
   
    public double getTimeToCollision(Entity entity) throws IllegalArgumentException{
           
        double a1 = entity.getVelocity()[0] - this.getVelocity()[0];
        double a2 = entity.getVelocity()[1] - this.getVelocity()[1];
        double a = Helper.square(a1) + Helper.square(a2);
        //exception 1:
        //The two ships follow an identical path so they don't collide.
        if (a == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
       
        double b1 = entity.getPosition()[0] - this.getPosition()[0];
        double b2 = entity.getPosition()[1] - this.getPosition()[1];
        double b = 2*((b1 * a1) + (b2 *a2));
       
        double s = this.getRadius() + entity.getRadius();
        double c = Helper.square(b1) + Helper.square(b2) - Helper.square(s);
     
        //exception 2:
        //If the quadratic equation doesn't give an answer the ships' paths cross but
        //don't the ships don't touch each other.
        if (Helper.quadraticSolver(a, b, c)[2] == 1.0){
            return Double.POSITIVE_INFINITY;
        }
        else{
            double dt1 = Helper.quadraticSolver(a, b, c)[0];
            double dt2 = Helper.quadraticSolver(a, b, c)[1];
           
          //dt exceptions:
           
            //  exception 3:
            //  if both answers are negative the ships are moving away from each other.
            if (dt1 < 0 && dt2 <0){
                return Double.POSITIVE_INFINITY;
            }
            if ((dt1 <= 0 && dt2 >= 0 ) || (dt1 > 0 && dt2 < 0)){
                throw new IllegalArgumentException("Already overlaps while calling timeToCollision");
            }
            if (dt1 < dt2) return dt1;
            else return dt2 ;
        }
       
}
 
    //Defensive
    /**
     *A method to get the position where two ships collide.
     *
     * @param ship
     *        The ship between which and the ship to which the method invoced
     *        the collision position is searched.
     * @return Returns null if the ships don't collide.
     *         | if(this.getTimeToCollision(ship) == Double.POSITIVE_INFINITY)
     *         |    then return == null
     * @return Returns the collision position
     *         | double[] pos = new double[2]
     *         | double[] cp1 = this.getDistanceTraveled(this.getTimeToCollision(ship))
     *         | double[] cp2 = ship.getDistanceTraveled(this.getTimeToCollision(ship))
     *         | double s = this.getShipRadius() + ship.getShipRadius()
     *         | double diffx = Math.abs(cp2[0] - cp1[0])
     *         | double diffy = Math.abs(cp2[1] - cp1[1])
     *         | double cosinus, sinus
     *         |
     *         |if(diffx != 0)
     *         |     then cosinus = (square(diffx) + square(s) - square(diffy))/(2*diffx*s)
     *         |          sinus = Math.sin(Math.acos(cosinus))
     *         |          pos[0] = cp1[0] + this.getShipRadius() * cosinus
     *         |          pos[1] = cp1[1] + this.getShipRadius() * sinus
     *         | else
     *         |     then pos[0] = this.getDistanceTraveled(this.getTimeToCollision(ship))[0]
     *         |          pos[1] = this.getDistanceTraveled(this.getTimeToCollision(ship))[1] + this.getShipRadius()
     *         | return == pos
     * @throws IllegalArgumentException
     *         Throws IllegalArgumentException if the ships already overlap.
     *         | this.overlap(ship)
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
     * A helper method to get the centre of a ship when it traveled over a time.
     *
     * @param  time
     *         The time over which the ship moves.
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
    * Check whether the given velocity is a valid velocity for
    * a ship.
    *
    * @param   velocity
    *          The velocity to check.
    * @return  True if and only if the given velocity is a double isn't negative and isn't faster then maxVelocity.
    *         | result == ((velocity >= -1*this.maxVelocity) && (velocity <= this.maxVelocity) && Helper.isValidDouble(velocity))
    */
   protected boolean isValidVelocity(double velocity){
       return ((velocity >= -1*this.maxVelocity) && (velocity <= this.maxVelocity) && Helper.isValidDouble(velocity));
   }  
   
    //defensive
   /**
    * Check whether the given position is a valid position for
    * a ship.
    *  
    * @param   position
    *          The position to check.
    * @return  True if and only if the given position is a double.
    *          | result == Helper.isValidDouble(position)
    */
   protected boolean isValidPosition(double position){
       return Helper.isValidDouble(position);
   }
   
   //defensive
   /**
    * Check whether the given radius is a valid radius for
    * a ship.
    *  
    * @param   radius
    *          The radius to check.
    * @return  True if and only if the given radius is a double and is bigger or equal to MINIMAL_SHIP_RAD
    *          if the entity is an instance of ship.
    *          | if this instanceof Ship
    *          | 	then result == ( radius >= MINIMAL_SHIP_RAD && Helper.isValidDouble(radius))
    * @return  True if and only if the given radius is a double and is bigger or equal to MINIMAL_BULLET_RAD
    *          if the entity is an instance of bullet.
    *          |  if this instanceof Bullet
    *          | 	then result == ( radius >= MINIMAL_BULLET_RAD && Helper.isValidDouble(radius))
    * @throws  IllegalArgumentException
    *          Throws an IllegalArgumentException if the method is invoced to a non ship, non bullet entity
    *          | !((this instanceof Bullet) && (this instanceof Ship))
    */
   protected boolean isValidRadius(double radius) throws IllegalArgumentException{
	   if  (Helper.isValidDouble(radius)){
    	   if(this instanceof Bullet)	return radius >= MINIMAL_BULLET_RAD;
    	   if(this instanceof Ship) 	return radius >= MINIMAL_SHIP_RAD;
       }
       else return false;
       throw new IllegalArgumentException("isValidRadius is invoced to a non ship, non bullet entity.");
   }
   
   protected boolean isValidMass(double mass){
	   if(this instanceof Bullet){if(mass != (4/3)*Math.PI*Math.pow(radius, 3)*BULLET_DENSITY){
		   Helper.log("Warning! You shouldn't change a bullet's mass");
		   return false;
	   }
	   else{
		   return true;
	   }}
	   if(this instanceof Ship){
		   if(mass < (4/3)*Math.PI*Math.pow(radius, 3)*BULLET_DENSITY){
			   return false;
		   }
		   else if(Helper.isValidDouble(mass)){
			   return true;
		   }
		   else{
			   return false;
		   }}
	   else{
		   return false;
	   }
   }
   
   protected boolean isValidWorld(World world){
	   return true;
   }
}
