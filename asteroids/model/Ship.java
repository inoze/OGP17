package asteroids.model;
 
import java.util.*;

import be.kuleuven.cs.som.annotate.*;

 
/**
 * A class for dealing with ships.
 * 	Ship is a subclass of Entity.
 *
 * @invar The radius of each ship must be a valid radius for a ship.
 *        | isValidRadius(getRadius())
 *
 * @invar The direction of each ship must be a valid direction for a ship
 *        | isValidDirection(getDirection())
 *        
 * @invar The initial mass of the ship must be a valid mass for the ship
 * 		  | isValidMass(getMass())
 * 
 * @invar The total mass of a ship is the mass of the ship added with the mass of all the bullets in the ship.
 *        | isValidTotalMass(getTotalMass())
 *
 * @invar The bullets in the ship are proper bullets for that ship
 * 		  | this.hasProperBullets()
 * 
 * @version 2.0
 * @author Brent De Bleser & Jesse Geens
 */
public class Ship extends Entity{
   
    /**
     * Constant that determines the force the thruster exerts
     */
    private final double THRUSTER_FORCE = 1.1E18;
    
	/**
	 * constant containing the minimal radius of a ship.
	 */
	 private final double MINIMAL_SHIP_RAD = 10;
	
	/**
	 * Constant containing the density of bullets.
	 */
	 private final double SHIP_DENSITY = 1.42E12;
	 
	 /**
	    * Initialize this new ship with a given x- and y-position, x- and y-velocity, radaius,
	    * a given direction and a mass.
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
	    * @effect The total mass is set to the new mass.
	    * 		  | setTotalMass(getMass())
	    * @effect The direction of the ship is set to the given direction.
	    *         | this.setDirection(direction)
	    * @effect The formal arguments of the constructor of the superclass are initialized with the corresponding formal arguments of ship.
	    * 		  |super(xPosition, yPosition, xVelocity, yVelocity, radius, "Ship")
	    * @throws IllegalArgumentException
	    *         Throws an IllegalArgumentException if the given radius is invalid.
	    *         | !isValidRadius(radius)
	    */
	 	@Raw
	    public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double direction, double mass) throws IllegalArgumentException {
	    	super(xPosition, yPosition, xVelocity, yVelocity, radius, "Ship");
	    	
	    	if (!isValidRadius(getRadius())) throw new IllegalArgumentException("Invalid radius @ ship");

	    	try {setDirection(direction);} catch(IllegalArgumentException ex){throw new IllegalArgumentException(ex.getMessage());}
	    	
	    	setMass(mass);
	    	setTotalMass(getMass());
	    }
	    
	
	/**
     * Return the direction of ship (in radians).
	 */
	 @Basic @Raw
	 public double getDirection(){
		 return this.direction;
	 }
	
	 
    /**
     * A method to set to direction of a ship to a given value.
     * 
     * @param direction
     *        The new direction of the ship
     * @pre   The given direction must be a valid direction.
     *        | isValidDirection(direction)
     * @post  The new direction of the ship is the given direction
     *        | new.direction == direction
     */
	 @Raw
    public void setDirection(double direction) throws IllegalArgumentException {
        if ( !isValidDirection(direction)) throw new IllegalArgumentException("assert setDirection Failed"); //assert fails with tests.
        this.direction = direction;
	}

    /**
     * Check whether the given direction is a valid direction for
     * a ship.
     *
     * @param   direction
     *          The direction to check.
     * @return  True if and only if the given direction is bigger or equal to zero and smaller or equal to 2*pi.
     *         | result == ((direction >= 0) && (direction <= (2*Math.PI) && Helepr.isValidDouble(direction)
     */
	@Model 
    private boolean isValidDirection(double direction){
        return ((direction >= 0) && (direction < 2*Math.PI) && Helper.isValidDouble(direction));
	}
	
    /**
     *variable containing the direction of the ship.
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
     * 			the minimal ship mass.
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
     * @return	Returns true if and only if the mass is equal to the sum of the mass of all the bullets
     * 			loaded within in and it's own mass.
     * 			| double i = 0
     * 			| for each bullet in bullets
     * 			| 	i =+ bullet.getMass()
     * 			|
     * 			| i =+ this.getMass()
     * 			| result ==  (mass == i) 
     */
    @Model 
    private boolean isValidTotalMass(double mass){
    	if (!Helper.isValidDouble(mass)) return false;
    	return (mass == (bullets.isEmpty() ? this.getMass() : 	this.getMass() + bullets.stream()
    															.map(bullet -> bullet.getMass()).reduce((u, t) -> u + t).get()));
    }
    
    /**
     * Variable containing the total mass of the ship.
     */
    private double totalMass;
    
    /**
	 * Return whether ship's thruster is active.
	 */
    @Basic
	public boolean isThrusterActive() {
		return this.isThrusterActive;
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
     *        |     vxl = Math.cos(alpha) * this.getMaxVelocity()
     *        |     vyl = Math.sin(alpha) * this.getMaxVelocity()
     *        | this.setShipVelocity(vxl, vyl);
     */
    public void thrust(double amount){
        if(!(amount >= 0) && (Helper.isValidDouble(amount)))
            amount = 0;
        double alpha = direction; 
        double vx = this.getVelocity()[0] + amount * Math.cos(alpha);
        double vy = this.getVelocity()[1] + amount * Math.sin(alpha);
        int velocity = (int) Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
        if(!(velocity > this.getMaxVelocity()))
            this.setVelocity(vx, vy);
        else{
            double vxl, vyl; 
            vxl = Math.cos(alpha) * this.getMaxVelocity();
            vyl = Math.sin(alpha) * this.getMaxVelocity();
            this.setVelocity(vxl, vyl);
            }
    }
    
    /**
     * Boolean that determines whether the thruster is active.
     */
    private boolean isThrusterActive;
    
    /**
	 * Check whether this ship has a given bullet in it.
	 * @param 	bullet
	 * 			The bullet to be check.
	 */
	@Basic 
	public boolean hasAsBullet(Bullet bullet){
		return bullets.contains(bullet);
	}
	
	
	/**
	 * Checks whether or not this ship has proper bullets in it.
	 * 
	 * @return	Returns true if and only if this ship can have each of it's bullets as
	 * 			bullet in it, if each of these bullets superworld is set to null and if each bullet isn't terminated.
	 * 			| result ==
	 * 			|	for each entity in 	entities :
	 * 			|			(canHaveAsBullet(bullet)
	 * 			|			&&	bullet.getSuperWorld() == null))
	 * 			|			&&	!bullet.isTerminated()
	 */
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
	 * Return the number of bullets loaded on the ship.
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
	 * 			Also the total mass of the ship will be increased by the mass of the bullet.
	 * 			| for each bullet in bulletsCol:
	 * 			|	if canLoadBulle(bullet)
	 * 			|		then	bullet.setSuperWorld(null)
	 * 			|			&&	bullet.setSource(this)
	 * 			|			&&	bullet.bouncesreset()
	 * 			|			&& 	new.totalMass += bullet.getMass()
	 * @throws	IllegalArgumentException
	 * 			throw IllegalArgumentException if one of the bullets can't be loaded.
	 * 			| if for some bullet in bulletsCol : !canLoadBullet(bullet)
	 */
	public void loadBulletsOnShip(Collection<Bullet> bulletsCol) throws IllegalArgumentException {
		for(Bullet bullet : bulletsCol){
			if (bullet == null) throw new IllegalArgumentException("Can't load bullet because bullet == null");
			if ( bullet.getBulletSource() == this || canLoadBullet(bullet)){
				bullets.add(bullet);
				if (bullet.getSuperWorld() != null){ bullet.getSuperWorld().removeEntityFromWorld(bullet);}
				bullet.setSource(this);
				bullet.bouncesReset();
				bullet.setPosition(this.getPosition()[0], this.getPosition()[1]);
				bullet.setVelocity(0, 0);
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
	@Model 
    private boolean canHaveAsBullet(Bullet bullet){
    	return !(bullet.isTerminated() || bullet.getSuperWorld() != null || bullet == null);
    }
	
	/**
	 * A Method to check whether or not a bullet can be loaded on the ship.
	 * 
	 * @param 	bullet
	 * 			The bullet to be checked.
	 * @return	Returns true if and only if the ship can have the bullet as one of its bullets,
	 * 			and if the distance between the edges of both ship and bullet is smaller or equal to null.
	 * 			| result == (canHaveAsBullet(bullet) && this.getDistanceBetweenEdge(bullet) <= 0)
	 */
	private boolean canLoadBullet(Bullet bullet){
		return (canHaveAsBullet(bullet) && this.getDistanceBetweenEdge(bullet) <= 0);
	}
	
    /**
     * Variable set containing all the (references to the) bullets in the ship. 
     */
    private Set<Bullet> bullets = new HashSet<Bullet>();
    
    
    /**
     * A method to get the program currently loaded on the ship.
     */
    @Basic
    public Program getProgram(){
    	return this.program;
    }
  
    /**
     * A method which loads certain program on the ship.
     * 
     * @param 	program
     * 			The program to be loaded.
     * @post	The program of the ship is set to the given program.
     * 			| this.program == program
     */
    public void setProgram(Program program){
    	this.program = program;
    }
    
    /**
     * Variable containing a program for the ship
     */
    private Program program;   

	/**
	 * Return the acceleration of ship.
	 * 
	 * @return	If the thrusters are of the current acceleration is 0.
	 * 			| if !isThrusterActive()
	 * 			|	then result == 0
	 * @return	If the thrusters are active the result will be equal to the thruster force divided 
	 * 			by it's total mass.
	 * 			| result ==  this.THRUSTER_FORCE/this.getTotalMass()
	 */
	public double getAcceleration() {
    	if (!isThrusterActive()) return 0;
		return this.THRUSTER_FORCE/this.getTotalMass();
	}
	
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
     * @post   	If the mass of the ship is invalid it will be set to the minimal mass for a ship.
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
    @Override
	public void terminate() {
    	bullets = null;
		super.terminate();
	}

    /**
     * Moves the ship to a new position and set a new velocity  during a time span of dt.
     *
     * @param   dt
     *          The time over which the ship is moving.
     * @throws  IllegalArgumentException
     *          Throws an IllegalArgumentException if dt is infinity or is smaller then zero.
     *         	|  ((dt < 0.0) && ( Double.isInfinite(dt)))
     * @effect  The x and y velocity is set to proper velocity for the acceleration over the time.
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
                for( Bullet bullet : bullets) bullet.setPosition(this.getPosition()[0], this.getPosition()[1]);
                }
            catch (IllegalArgumentException ex){
                throw new IllegalArgumentException(ex.getMessage());
                }
            }    
    }
    
    /**
     * A method that calculates the position of the bullet if it is fired at this moment.
     * 
     * @param 	bulletRadius
     * 			The radius of the bullet to be fired.
     * @return	Returns a array with the x and y position for the bullet to be spawned. The positions are
     * 			calculated with the direction.
     * 			| double distance = (getRadius() + bulletRadius) 
     *			| double newx = this.getPosition()[0] + (Math.cos(direction) * distance)
     *			| double newy = this.getPosition()[1] + (Math.sin(direction) * distance)
     *			| double[] coo = {newx,newy}
     *			| result == coo
     */
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
     * @return	True if and only if radius is bigger than the minimal radius for a ship and if radius is a valid double.
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
    
}

