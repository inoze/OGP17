package asteroids.model;
 
import java.util.*;

import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;
 
/**
 * A class for dealing with ships which are a special class of entity.
 *
 * @invar The radius of each ship must be a valid radius for a ship.
 *        | isValidRadius(getRadius())
 *
 * @invar The direction of each ship must be a valid direction for a ship
 *        | isValidDirection(getDirection())
 *
 * @invar The total mass of a ship is the mass of the ship added with the mass of all the bullets in the ship.
 *        | isValidTotalMass(getTotalMass)
 *
 * @invar The bullets in the ship are proper bullets for that ship
 * 		  | this.hasProperBullets()
 * 
 * @version 2.0
 * @author Brent De Bleser & Jesse Geens
 */
public class Ship extends Entity{
   
	/**
	 * constant containing the minimal radius of a ship.
	 */
	 private final static double MINIMAL_SHIP_RAD = 10;
	
	/**
	 * Constant containing the density of bullets.
	 */
	 private final double SHIP_DENSITY = 1.42E12;
	 
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
	    * @param  mass
	    * 		  The mass of the ship.
	    * @post   The given radius is equal to the new radius.
	    *         | new.getShipRadius() == radius
	    * @effect The mass of the ship is set to the given mass.
	    * 		  | this.setMass(mass)
	    * @effect The direction of the ship is set to the given direction.
	    *         | this.setDirection(direction)
	    * @effect The formal arguments of the constructor of the superclass are initiallised with the corresponding formal arguments of ship.
	    * 		  |super(xPosition, yPosition, xVelocity, yVelocity, radius, "Ship")
	    * @throws IllegalArgumentException
	    *         Throws an IllegalArgumentException if the given radius is invalid.
	    *         | radius <= MINIMAL_SHIP_RAD
	    */
	    public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double direction, double mass) throws IllegalArgumentException {
	    	super(xPosition, yPosition, xVelocity, yVelocity, radius, "Ship");
	    	
	    	if (!isValidRadius(getRadius())) throw new IllegalArgumentException("Invalid radius @ ship");

	    	try {setDirection(direction);} catch(IllegalArgumentException ex){throw new IllegalArgumentException(ex.getMessage());}
	    	
	    	setMass(mass);
	    	setTotalMass(mass);
	    }
	    
	
	/**
     * Return the direction of ship (in radians).
	 */
	 @Basic
	 public double getDirection(){
		 return this.direction;
	 }
	
	 
    /**
     * A method to set to direction of a ship
     * to a given value.
     * @param direction
     *        The new direction of the ship
     * @pre   The given direction must be a valid direction.
     *        | isValidDirection(direction)
     * @post  The new direction of the ship is the given direction
     *        | new.direction == direction
     */
    public void setDirection(double direction) throws IllegalArgumentException {
        if ( !isValidDirection(direction)) throw new IllegalArgumentException("assert setDirection Failed");
        this.direction = direction;
	}

    /**
     * Check whether the given direction is a valid direction for
     * a ship.
     *
     * @param   direction
     *          The orienation to check.
     * @return  True if and only if the given direction is bigger or equal to zero and smaller or equal to 2*pi.
     *         | result == ((direction >= 0) && (direction <= (2*Math.PI) && Helepr.isValidDouble(direction)
     */
	@Model @Raw
    private boolean isValidDirection(double direction){
        return ((direction >= 0) && (direction < 2*Math.PI) && Helper.isValidDouble(direction));
	}
	
    /**
     *variable containg the direction of the ship.
     */
    private double direction;
    
	/**
	 * Return the total mass of ship (i.e., including bullets
	 * loaded onto the ship).
	 */
    @Basic @Raw
	public double getTotalMass() {
		return this.totalMass;
	}
	
    
    /**
     * Method that set's the total mass of the ship to a given mass.
     * 
     * @param 	mass
     * 			The mass to be set.
     * @post	If the mass is a valid total mass the total mass is set to the given mass.
     * 			| if isValidTotalMass(mass)
     * 			|	then new.totalMass == mass
     * @post	If the mass isn't a valid total mass the total mass is set to 
     * 			the miimal ship mass.
     * 			| if !isValidTotalMass(mass)
     * 			|	then new.totalMass == (Math.pow(this.getRadius(), 3) * Math.PI * (4/3) * SHIP_DENSITY)
     */
    @Raw
    public void setTotalMass(double mass){
    	if (isValidTotalMass(mass)) this.totalMass = mass;
    	else totalMass = (Math.pow(this.getRadius(), 3) * Math.PI * 4.0 * SHIP_DENSITY / 3.0);
    }
    
    /**
     * Checks whether a mass is a valid total mass.
     * 
     * @param 	mass
     * 			| The mass to check.
     * @return	Returns true if and only if the mass is esual to the sum of the mass of all the bullets
     * 			loaded within in and it's own mass.
     * 			| double i = 0
     * 			| for each bullet in bullets
     * 			| 	i =+ bullet.getMass()
     * 			|
     * 			| i =+ this.getMass()
     * 			| result ==  (mass == i) 
     */
    @Model @Raw
    private boolean isValidTotalMass(double mass){
    	if (!Helper.isValidDouble(mass)) return false;
    	return (mass == (bullets.isEmpty() ? this.getMass() : this.getMass() + bullets.stream().map(bullet -> bullet.getMass()).reduce((u, t) -> u + t).get()));
    }
    
    /**
     * Variable containing the total mass of the ship.
     */
    private double totalMass;
    
    /**
     * Boolean that determines whether the thruster is active.
     */
    private boolean isThrusterActive;
    /**
     * Variable that determines the force the thruster exerts
     */
    private double thrusterForce = 1.1E18;
    
    
    
    /**
	 * Check whether this ship has a given bullet in it.
	 * @param 	bullet
	 * 			The bulet to be check.
	 */
	@Basic @Raw
	public boolean hasAsBullet(Bullet bullet){
		return bullets.contains(bullet);
	}
	
	
	/**
	 * Checks whether or not this ship has proper bullets in it.
	 * 
	 * @return	Returns true if and only if this ship can have each of it's bullets as
	 * 			bullet in it, if each of these bullets superworld is set to null and if eacg bullet isn't terminated.
	 * 			| result ==
	 * 			|	for each entity in 	entities :
	 * 			|			(canHaveAsBullet(bullet)
	 * 			|			&&	bullet.getWorld() == null))
	 * 			|			&&	!bullet.isTerminated()
	 */
	@Raw
	public boolean hasProperBullets(){
		
		for (Bullet bullet : bullets){
			if (this.canHaveAsBullet(bullet) && bullet.getSuperWorld() == null && !bullet.isTerminated) return false;
		}
		return true;
	}
    
    /**
	 * Return the set of all bullets loaded on ship.
	 *
	 */
    @Basic
	public Set<? extends Bullet> getBulletsOnShip() {
    	Set<Bullet> resultSet = new HashSet<Bullet>(bullets);
		return resultSet;
	}

	/**
	 * Return the number of bullets loaded on theship.
	 */
	@Basic
	public int getNbBulletsOnShip() {
		return bullets.size();
	}

	/**
	 * Loads bullets on the ship.
	 * 
	 * @param	bulletsCol
	 * 			The collection of bullets which need to be loaded on the ship.
	 * @post	For each bullet if the bullet can be loaded, the bullet will be added to bullets,
	 * 			it's superWorld will be set to null, it's source will be set to the ship and it's bounces will be reset.
	 * 			Also tge total mass of the ship will be increased by the mass of the bullet.
	 * 			| for each bullet in bulletsCol:
	 * 			|	if canLoadBulle(bullet)
	 * 			|		then	bullet.setSuperWorld(null)
	 * 			|			&&	bullet.setSource(this)
	 * 			|			&&	bullet.bouncesreset()
	 * 			|			&& 	new.totalMass += bullet.getMass()
	 * @throws	IllegalArgumentException
	 * 			throw IllegalArgumentException if one of the bullets can't be loaded.
	 * 			| if for some bullet in bulletsCol : !canLoadBullrt(bullet)
	 */
	@Raw
	public void loadBulletsOnShip(Collection<Bullet> bulletsCol) throws IllegalArgumentException {
		for(Bullet bullet : bulletsCol){
			if(canLoadBullet(bullet)){
				bullets.add(bullet);
				bullet.setSuperWorld(null);
				bullet.setSource(this);
				bullet.bouncesReset();
				totalMass += bullet.getMass();
			}
			else throw new IllegalArgumentException("Can't load bullet.");
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
	public void removeBulletFromShip(Bullet bullet) throws IllegalArgumentException {
		if (!hasAsBullet(bullet)) throw new IllegalArgumentException("bullet can't be removed from ship, because it's isn't in the ship.");
		bullets.remove(bullet);
		totalMass -= bullet.getMass();
	}
	
	
	public void fireBullet() {
		if (getNbBulletsOnShip() > 0 && this.getSuperWorld() != null){
			
			Bullet bullet = bullets.iterator().next();
			
			bullet.setPosition(this.bulletSpawnCalculator(bullet.getRadius())[0], this.bulletSpawnCalculator(bullet.getRadius())[1]);
			
			if (!this.getSuperWorld().entityBoundryOverlap(bullet)){

				for (Entity entity : getSuperWorld().getEntities()){
					if (bullet.overlap(entity))	{
						bullet.terminate();
						entity.getSuperWorld().removeEntityFromWorld(entity);
						entity.terminate();
						bullet.setSource(this);
						return;
					}
				}
				this.removeBulletFromShip(bullet);
				getSuperWorld().addEntityToWorld(bullet);
				bullet.setVelocity(250 * Math.cos(this.getDirection()), 250 * Math.sin(this.getDirection()));
			} 
			
			else bullet.terminate();
		}
	}
	
	/**
     * Checks whether this ship can have a given bullet as a bullet.
     * 
     * @param   bullet
     *          The bullet to check.
     * @return  True if and only if the bullet isn't terminated.
     *          | result == (!(bullet.isTerminated()))
     */
	@Model @Raw
    private boolean canHaveAsBullet(Bullet bullet){
    	return !(bullet.isTerminated() || bullet.getSuperWorld() != null);
    }
	
	
	private boolean canLoadBullet(Bullet bullet){
		return (canHaveAsBullet(bullet) && this.getDistanceBetweenCenter(bullet) <= Math.abs(bullet.getRadius() - this.getRadius()));
	}
	
    /**
     * Variable set containing all the (references to the) bullets in the ship. 
     */
    private Set<Bullet> bullets = new HashSet<Bullet>();
    
    
    private Program program;   

    

	/**
	 * Return the acceleration of ship.
	 */
    @Basic
	public double getAcceleration() {
    	if (!isThrusterActive()) return 0;
    	Helper.log("Thruster force = " + thrusterForce);
    	Helper.log("mass: " + this.getTotalMass() + "; a: " + this.thrusterForce/this.getTotalMass());
		return this.thrusterForce/this.getTotalMass();
	}
	
    @Basic
    public Program getProgram(){
    	return this.program;
    }
    
	/**
	 * Return whether ship's thruster is active.
	 */
    @Basic
	public boolean isThrusterActive() {
		return this.isThrusterActive;
	}
    
  
    
    public void setProgram(Program program){
    	this.program = program;
    }
    



    /**
     * A method that turns on a ship's thruster
     * 
     * @post The thruster is set to active
     * 		 | this.isThrusterActive = true
     */
    @Basic
	public void thrustOn(){
		this.isThrusterActive = true;
	}
	/**
	 * A method that turns off a ship's thruster
	 * 
	 * @post The thruster is set to inactive
	 *		 | this.isThrusterActive = false
	 */		 
    @Basic
	public void thrustOff(){
		this.isThrusterActive = false;
	}

    //Methods
 
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
            this.setVelocity(vx, vy);
        else{
            double vxl, vyl; //vx limited, vy limited
            Helper.log("velocities: " + vx + "; " + vy);
            Helper.log("alpha: " + alpha);
            vxl = Math.cos(alpha) * this.getMaxVelocity();
            vyl = Math.sin(alpha) * this.getMaxVelocity();
            this.setVelocity(vxl, vyl);
            }
    }
   
    //Nominal
    /**
     * A method to turn a ship with a given angle.
     *
     * @param  	angle
     *         	The angle to be turned by the ship.
     * @pre		The given angle must be a valid double.
     * 			| isValidAngle(angle)
     * @effect 	The direction of the ship is set to the current direction added with angle modulo 2*Pi.
     *         	| new.getShipOrientation == (this.direction + angle) % (2*Math.PI)
     */
    public void turn(double angle){
        assert Helper.isValidDouble(angle);
        this.setDirection( (direction+ angle) % (2*Math.PI));
    }
	
    
    /**
     * A method to set the mass of a ship to a given value.
     * 
     * @param 	mass
     * 			The mass to be set.
     * @post   	If the mass of the ship is invalid it wil be set to the minimal mass for a ship.
     *         	| this.setMass(Math.pow(this.getRadius(), 3) * Math.PI * (4/3) * SHIP_DENSITY
     * @post	If the given mass is valid the mass of the ship is set to the given mass.
     * 			| if this.isvalidMass(mass)
     * 			| 	then super.setMass(mass)
     */
    @Override
    public void setMass(double mass){
		
    	if (isValidMass(mass)) super.setMass(mass);
    	else  super.setMass(Math.pow(this.getRadius(), 3) * Math.PI * 4.0 * SHIP_DENSITY / 3.0);
    }
    
    
	/**
	 * Terminate ship.
	 * 
	 * @post    The set of all bullets is set to null.
	 *          | bullets = null
	 * @effect  The ship is terminated on entity level.
	 *          | super.terminate()
	 */
    @Basic @Override
	public void terminate() {
    	bullets = null;
		super.terminate();
	}

    /**
     * Moves the ship to a new position and set a neww velocity  during a timespan of dt.
     *
     * @param   dt
     *          The time over which the ship is moving.
     * @throws  IllegalArgumentException
     *          Throws an IllegalArgumentException if dt is infinity or is smaller then zero.
     *         	|  ((dt < 0.0) && ( Double.isInfinite(dt)))
     * @effect  The x and y velocity is set to propet velocity for the acceleration over the time.
     * 			| this.setVelocity(
     * 			|	this.getVelocity()[0] + this.getAcceleration() * Math.cos(this.getDirection()) * dt,
     * 			|		 this.getVelocity()[1] + this.getAcceleration() * Math.sin(this.getDirection())* dt)
     * @effect 	The super move method is called.
     * 			| super.move(dt)
     */
    @Override
    public void move(double dt) throws IllegalArgumentException{
        if (dt < 0.0) {
            throw new IllegalArgumentException("Invalid time");
        }
        else{
            try {
            	super.move(dt);
                this.setVelocity(this.getVelocity()[0] + this.getAcceleration() * Math.cos(this.getDirection()) * dt, this.getVelocity()[1] + this.getAcceleration() * Math.sin(this.getDirection())* dt);
                }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex.getMessage());
                }
            }    
    }
    
    private double[] bulletSpawnCalculator(Double bulletRadius){
    	double distance = (getRadius() + bulletRadius) ;
    	double newx = this.getPosition()[0] + (Math.cos(direction) * distance);
    	double newy = this.getPosition()[1] + (Math.sin(direction) * distance);
    	double[] coo = {newx,newy};
    	return coo;
    }
    
    /**
     * Checks whether or not a radius is valid.
     * 
     * @param 	radius
     * 			The radius to check.
     * @return	True if and only if radis is bigger than the minimal radius for a ship and if radius is a valid double.
     * 			| result == (radius > MINIMAL_SHIP_RAD && Helper.isValidDouble(radius))
     */
    @Model
    private boolean isValidRadius(double radius){
    	return (radius >= MINIMAL_SHIP_RAD && Helper.isValidDouble(radius));
    }
    
    /**
     * Checks whether a mass is a valid mass for a ship.
     * 
     * @param	mass
     * 			The mass to check.
     * @return	Returns true if and only if the given mass is bigger or equal to
     * 			(4/3) times Pi times radius cubed times the ship density and if it is a valid double.
     * 			| result ==  (mass >= (4/3)*Math.PI*Math.pow(radius, 3)*SHIP_DENSITY && Helper.isValidDouble(mass))
     */
    @Model
    private boolean isValidMass(double mass){
    	return (mass >= 4.0*Math.PI*Math.pow(getRadius(), 3)*SHIP_DENSITY / 3.0 && Helper.isValidDouble(mass));
    }

    
    public void collide(Entity entity){
    	if (entity instanceof Bullet){
    		Bullet bullet = (Bullet) entity;
    		
    		if (this == bullet.getBulletSource()){
    			Set<Bullet> bullets = new HashSet<Bullet>();
    			bullets.add(bullet);
    			this.loadBulletsOnShip(bullets);
    		}
    		else{
    			getSuperWorld().removeEntityFromWorld(bullet);
    			bullet.terminate();
    			getSuperWorld().removeEntityFromWorld(this);
    			}
    	}
    	else if(entity instanceof )
    	}
    }
}

