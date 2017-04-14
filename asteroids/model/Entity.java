package asteroids.model;

import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Entity {

	protected final double SPEED_OF_LIGHT =  300000;
	
	private final double BULLET_DENSITY = 7.8 * Math.pow(10, 12);
	
	private final double SHIP_DENSITY = 1.42 * Math.pow(10, 12);
	/**
     * Variable containing the coordinates of the ship in the form of an array with length 2.
     */
    private double[] position = new double[2];
   
    /**
     * variable containing the velocity on the x and y axis as an array of length 2.
     */
    private double[] velocity = new double[2];
   
    /**
     * Variable containing the maximum velocity of the entity in km/s.
     */
    private double maxVelocity = SPEED_OF_LIGHT;
    /**
     * Variable containing the radius of the entity.
     */
    private double radius;
    /**
     * Variable containing the world in which the entity is positioned.
     */
    private World superWorld;
    /**
     * Variable containing whether the entity is terminated.
     */
    private boolean isTerminated;
    /**
     * Variable containing the individual mass of the entity.
     */
	private double mass;
	
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
    @Immutable
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
    @Basic
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
    	if(isValidMass(mass))
    		if(this instanceof Ship)
    			this.mass = mass;
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
	
	protected void terminate(){
		this.isTerminated = true;
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
    * @return  True if and only if the given radius is a double and is bigger or equal to 10.
    *          | result == ( radius >= 10 && Helper.isValidDouble(radius))
    */
   protected boolean isValidRadius(double radius){
       return Helper.isValidDouble(radius);
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
}
