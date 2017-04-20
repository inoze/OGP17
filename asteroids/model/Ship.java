package asteroids.model;
 
import java.util.*;

import asteroids.util.ModelException;
//import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;
 
/**
 * A class for dealing with ships in a costum game loosely based
 * on asteroids.
 *
 * @invar The velocity of each ship must be a valid velocity for a ship
 *        | isValidVelocity(getVelocity()[0]) && isValidVelocity(getVelocity()[1])
 *
 * @invar The position of each ship must be a valid position for a ship
 *        | isValidPosition(getPosition()[0]) && isValidPosition(getPosition()[1])
 *
 * @invar The radius of each ship must be a valid radius for a ship
 *        | isValidRadius(getRadius())
 *
 * @invar The direction of each ship must be a valid direction for a ship
 *        | isValidOrientation(getOrientation())
 *
 * @invar The maximum speed of each ship must be between 0 and 300 000
 *        | getMaxVelocity > 0 && getMaxVelocity < 300000
 *
 * @version 1.0
 * @author Brent De Bleser & Jesse Geens
 */
public class Ship extends Entity{
   
    //Variables
   
    /**
     * Variable registering the direction of the ship in radients with right being 0 and left being pi.
     */
	@Deprecated
    private double orientation;
    /**
     *variable containg the direction of the ship.
     */
    private double direction;
    /**
     * Variable containing the total mass of the ship.
     */
    private double totalMass;
    /**
     * Variable containing the acceleration value of the ship.
     */
    private double acceleration;
    /**
     * Boolean that determines whether the thruster is active.
     */
    private boolean isThrusterActive;
    /**
     * Variable that determines the force the thruster exerts
     */
    private double thrusterForce = 1.1E21;
    /**
     * Variable set containing all the (references to the) bullets in the ship. 
     */
    private Set<Bullet> bullets = new HashSet<Bullet>();
    
    //Initializers
   
    //Defensive
    
   /**
    * Initialize this new ship with a given x- and y-position, x- and y-velocity, radaius and
    * a given direction
    *
    * @param  xPosition
    *         The x-position of the ship.
    * @param  yPosition
    *         The y-position of the ship.
    * @param  xVelocity
    *         The ship's velocity on the x-axis
    * @param  yVelocity
    *         The ship's velocity on the y-axis
    * @param  radius
    *         The radius (size) of the ship
    * @param  direction
    *         The direction of the ship in radians.
    * @effect The given x-position is set to the new x-position and
    *         the given y-position is set to the new y-position.
    *         | this.setShipPosition(xPosition, yPosition)
    * @effect The given x-velocity is set to the new x-velocity and
    *         the given y-velocity is equal to the new y-velocity.
    *         | this.setShipVelocity(xVelocity, yVelocity)
    * @post   The given radius is equal to the new radius.
    *         | new.getShipRadius() == radius
    * @effect The given direction is set to the new direction.
    *         | this.setShipOrientation(direction)
    * @throws IllegalArgumentException
    *         Throws an IllegalArgumentException if any of the given parameters is invalid
    *         | if(!(isValidPosition(xPosition) && isValidPosition(yPosition) && isValidVelocity(xVelocity) && isValidVelocity(yVelocity) && isValidOrientation(direction) && isValidRadius(radius)))
    *         |     then throw new IllegalArgumentException("Illegal argument given at CreateShip")
    */

    public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double direction, double mass) throws IllegalArgumentException, ModelException {
    	super(xPosition, yPosition, xVelocity, yVelocity, radius);
    	if(isValidDirection(direction) && isValidShipRadius(radius)){
            this.direction = direction;
            if(this.getMass() > mass)
            	throw new IllegalArgumentException("Ship is lighter than minimum density");
            	this.setMass(mass);
        }
        else{
        	this.terminateShip();
            Helper.log("posx: " + isValidPosition(xPosition) + "; " + xPosition);
            Helper.log("posy: " + isValidPosition(yPosition) + "; " + yPosition);
            Helper.log("velx: " + isValidVelocity(xPosition) + "; " + xPosition);
            Helper.log("vely: " + isValidVelocity(yPosition) + "; " + yPosition);
            Helper.log("rad: " + isValidRadius(radius) + "; " + radius);
            throw new IllegalArgumentException("Illegal argument given at CreateShip");
        }
    }
 
   
    //Getters   
    /**
     * Return the direction of ship (in radians).
     */
    @Basic
    public double getShipDirection(){
        return this.direction;
    }
   
       
	/**
	 * Return the total mass of ship (i.e., including bullets
	 * loaded onto the ship).
	 */
    @Basic
	public double getShipTotalMass() {
		return this.totalMass;
	}
	
	/**
	 * Return the acceleration of ship.
	 */
    @Basic
	public double getShipAcceleration() {
    	if (!isShipThrusterActive()) return 0;
		return this.thrusterForce/this.getMass();
	}
	
	/**
	 * Return whether ship's thruster is active.
	 */
    @Basic
	public boolean isShipThrusterActive() {
		return this.isThrusterActive;
	}
    
   
    ///Setters
   
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
    public void setShipPosition(double xPosition, double yPosition) throws IllegalArgumentException {
        if ( (!isValidPosition(xPosition)) || (!isValidPosition(yPosition))) throw new IllegalArgumentException("Invalid position");
        else {
            this.setPosition(xPosition, yPosition);
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
    public void setShipVelocity(double xVelocity, double yVelocity){
        if ( (!isValidVelocity(xVelocity)) || (!isValidVelocity(yVelocity))){
            this.setVelocity(0, 0);
        }
        else {
            this.setVelocity(xVelocity, yVelocity);
        }
    }
   
    //Nominal
    /**
     * A method to set to direction of a ship
     * to a given value.
     * @param direction
     *        The new direction of the ship
     * @pre   The given direction must be a double.
     *        | assert Helper.isValidDouble(direction)
     * @post  The new direction of the ship is the given direction
     *        in radians notation with direction equal or bigger than 0
     *        and smaller or equal to 2*Pi.
     *        | direction = surplusRadians(direction)
     *        | new.getShipOrientation == direction
     */
    public void setShipDirection(double direction) {
        assert Helper.isValidDouble(direction);
        direction = surplusRadians(direction);
        this.direction = direction;
    }
    
	/**
	 * Terminate ship.
	 */
    @Basic
	public void terminateShip() {
		this.terminate();
	}


	public void thrustOn(){
		this.isThrusterActive = true;
	}
	
	public void thrustOff(){
		this.isThrusterActive = false;
	}

    //Methods
	
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
        if ((dt < 0.0) && ( Double.isInfinite(dt)))
            throw new IllegalArgumentException("Invalid time");
        else{
            try {
                   this.setShipPosition(this.getPosition()[0] + (this.getVelocity()[0] * dt), this.getPosition()[1] + (this.getVelocity()[1] * dt));
            }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex);
                }
            }    
    }
   
 
    //Total
    /**
     * A method to increase the velocity of a ship in the direction
     *  of its direction.
     *  
     * @param amount
     *        The amount of velocity added in the current direction.
     * @post  If amount is smaller then 0 or isn't a double it is set to 0.
     *        | if(!(amount >= 0) && (Helper.isValidDouble(amount)))
     *        |     then amount = 0
     * @effect te velocity of the ship is set to the sum of the new amount which is
     *         calculated upon the X- and Y-axis and the previous velocities on the X- and Y-axis.
     *        | double alpha = direction
     *        | double vx = this.getShipVelocity()[0] + amount * Math.cos(alpha)
     *        | double vy = this.getShipVelocity()[1] + amount * Math.sin(alpha)
     *        | int velocity = (int) Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2))
     *        | if(!(velocity > this.getMaxVelocity()))
     *        |     then this.setShipVelocity(vx, vy)
     *        | else
     *        |     double vxl, vyl; //vx limited, vy limited
     *        |     vxl = Math.cos(alpha) * this.getMaxVelocity();
     *        |     vyl = Math.sin(alpha) * this.getMaxVelocity();
     *        | this.setShipVelocity(vxl, vyl);
     */
    public void thrust(double amount){
        if(!(amount >= 0) && (Helper.isValidDouble(amount)))
            amount = 0;
        double alpha = direction; //Math.atan(this.getShipVelocity()[1] / this.getShipVelocity()[0]);
        double vx = this.getVelocity()[0] + amount * Math.cos(alpha);
        double vy = this.getVelocity()[1] + amount * Math.sin(alpha);
        int velocity = (int) Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
        if(!(velocity > this.getMaxVelocity()))
            this.setShipVelocity(vx, vy);
        else{
            double vxl, vyl; //vx limited, vy limited
            Helper.log("velocities: " + vx + "; " + vy);
            Helper.log("alpha: " + alpha);
            vxl = Math.cos(alpha) * this.getMaxVelocity();
            vyl = Math.sin(alpha) * this.getMaxVelocity();
            this.setShipVelocity(vxl, vyl);
            }
    }
   
    //Nominal
    /**
     * A method to turn a ship with a given angle.
     *
     * @param  angle
     *         The angle to be turned by the ship.
     * @effect The direction of the ship is set to the current direction added with angle.
     *         | new.getShipOrientation == this.direction + angle
     */
    public void turn(double angle){
        assert isValidAngle(angle);
        this.setShipDirection(this.direction + angle);
    }
   
    //Total
    /**
     * A helper method to convort radian notation bigger or equal to 2*Pi
     * to a notation smaller or equal to 2*Pi.
     *
     * @param  radians
     *         The radians notation to be converted.
     * @return Return an equivalent notation for radians between or equal to 0 and 2*Pi.
     *         | while(radians > 2*Math.PI)
     *         |        radians -= 2*Math.PI
     *        | while(radians < 0)
     *         |    radians += 2*Math.PI
     *         | return == radians
     *        
     *
     */
    public double surplusRadians(double radians){
       while(radians > 2*Math.PI){
            radians -= 2*Math.PI;
        }
        while(radians < 0){
            radians += 2*Math.PI;
            }
        return radians;
    }


	/**
	 * Return the set of all bullets loaded on <code>ship</code>.
	 * 
	 * For students working alone, this method may return null.
	 */
	public Set<? extends Bullet> getBulletsOnShip() {
		return bullets;
	}

	/**
	 * Return the number of bullets loaded on <code>ship</code>.
	 */
	@Basic
	public int getNbBulletsOnShip() throws ModelException {
		return bullets.size();
	}

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 */
	public void loadBulletOnShip(Bullet bullet) {
		if(isValidBullet(bullet)){
			bullets.add(bullet);
			bullet.setSource(this);
		}
	}

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 * 
	 * For students working alone, this method must not do anything.
	 */
	public void loadBulletsOnShip(Collection<Bullet> bulletsCol) {
		for(Bullet bullet : bulletsCol){
			loadBulletOnShip(bullet);
		}
	}

	/**
	 * Remove a bullet from the ship.
	 * 
	 * @param   bullet
	 *          The bullet to be removed.
	 * @effect  The bullet is removed from bullets.
	 *          | bullets.remove(bullet)
	 */
	public void removeBulletFromShip(Bullet bullet) {
		bullets.remove(bullet);
	}

	/**
	 * Fire a bullet from the ship
	 * 
	 * @effect
	 */
	public void fireBullet() {
		if(bullets.size() > 0){
			Bullet bullet = bullets.iterator().next();
			if(isValidBullet(bullet)){
				this.removeBulletFromShip(bullet);
				World world = this.superWorld;
				world.addBulletToWorld(bullet);
				bullet.setSuperWorld(world);
				bullet.setPosition(this.getPosition()[0] + ((this.getRadius() + bullet.getRadius()) * Math.cos(this.direction)), this.getPosition()[1] + ((this.getRadius() + bullet.getRadius()) * Math.sin(this.direction)));
				bullet.setVelocity(250 * Math.cos(this.getShipDirection()), 250 * Math.sin(this.getShipDirection()));
			}
			else{
				Helper.log("Trying to fire invalid bullet");
			}
		}
		else{
			Helper.log("Trying to fire a bullet but you've run out!");
		}
	}
	
	   
    //Validity checkers
   
    //Nominal
    /**
     * Check whether the given direction is a valid direction for
     * a ship.
     *
     * @param   direction
     *          The orienation to check.
     * @return  True if and only if the given direction is bigger or equal to zero and smaller or equal to 2*pi.
     *         | result == ((direction >= 0) && (direction <= (2*Math.PI))
     */
    public boolean isValidDirection(double direction){
        //return ((direction >= 0) && (direction <= 2*Math.PI));
    	return Helper.isValidDouble(direction);
    }
   
    //Total
    /**
     * Check whether the given angle is a valid angle.
     *  
     * @param   angel
     *          The angle to check.
     * @return  True if and only if the given angle is a double.
     *          | result == Helper.isValidDouble(double number)
     */
    private boolean isValidAngle(double angle){
        return Helper.isValidDouble(angle);
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
    private boolean isValidShipRadius(double radius){
        return ( radius >= 10 && Helper.isValidDouble(radius));
    }
    /**
     * Checks whether a given bullet is valid.
     * @param   bullet
     *          The bullet to check.
     * @return  True if and only if the bullet isn't terminated.
     *          | result == (!(bullet.isTerminated()))
     */
    private boolean isValidBullet(Bullet bullet){
    	if(bullet.isTerminated())
    		return false;
    	return true;
    }
}

