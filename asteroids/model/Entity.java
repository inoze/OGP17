package asteroids.model;

import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.Basic;

public class Entity {

	protected final double SPEED_OF_LIGHT =  300000;
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
     * Variable containing whether the entity is terminated.
     */
    private boolean isTerminated;
	
	
	protected Entity (double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException {
		if(isValidPosition(x) && isValidPosition(y) && isValidVelocity(xVelocity) && isValidVelocity(yVelocity) && isValidRadius(radius)){
            try{this.setPosition(x, y);} catch(IllegalArgumentException ex){throw new IllegalArgumentException(ex.getMessage());}
            this.setVelocity(xVelocity, yVelocity);
            this.radius = radius;
        } 
		else{
			throw new IllegalArgumentException("Illegal argument at createEntity");
		}
	}
	
	//Getters
	   
    /**
     * Returns the position of the ship.
     */
    @Basic
    public double[] getPosition(){
        return this.position;
    }
   
    /**
     *Returns the velocity of the ship.
     */
    @Basic
    public double[] getVelocity(){
        return this.velocity;
    }
   
    /**
     * Returns the maximum velocity of the ship.
     */
    @Basic
    public double getMaxVelocity(){
        return this.maxVelocity;
    }
   
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
    public void setPosition(double xPosition, double yPosition) throws IllegalArgumentException {
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
    public void setVelocity(double xVelocity, double yVelocity){
        if ( (!isValidVelocity(xVelocity)) || (!isValidVelocity(yVelocity))){
            this.velocity[0] = 0;
            this.velocity[1] = 0;
        }
        else {
            this.velocity[0] = xVelocity;
            this.velocity[1] = yVelocity;
        }
    }
    
    /**
	 * Return the shortest time in which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double getTimeCollisionBoundary() throws ModelException {
		return 0;
	}

	/**
	 * Return the first position at which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double[] getPositionCollisionBoundary() throws ModelException {
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
}
