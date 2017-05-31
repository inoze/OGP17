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
 * @invar All bullets in the ship aren't in the world.
 *        | for each bullet in bullets
 *        |		 bullet.superWorld == null
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
	
    //Variables
    /**
     *variable containg the direction of the ship.
     */
    private double direction;
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
     * Variable set containing all the (references to the) bullets in the ship. 
     */
    private Set<Bullet> bullets = new HashSet<Bullet>();
    
    private Program program;
    
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

    public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double direction, double mass) throws IllegalArgumentException, ModelException {
    	super(xPosition, yPosition, xVelocity, yVelocity, radius, "Ship");
    	
    	if (!isValidRadius(getRadius())) throw new IllegalArgumentException("Invalid radius @ ship");

    	setDirection(direction);
    	setMass(mass);
    	setTotalMass(mass);
    }
 
   
    //Getters   
    /**
     * Return the direction of ship (in radians).
     */
    @Basic
    public double getDirection(){
        return this.direction;
    }
   
       
	/**
	 * Return the total mass of ship (i.e., including bullets
	 * loaded onto the ship).
	 */
    @Basic
	public double getTotalMass() {
		return this.totalMass;
	}
	
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
    
   
    ///Setters
   
    //Nominal
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
    public void setDirection(double direction) {
        assert isValidDirection(direction) : "klote";
        this.direction = direction;
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
    
    public void setProgram(Program program){
    	this.program = program;
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
    public void setTotalMass(double mass){
    	if (isValidTotalMass(mass)) this.totalMass = mass;
    	else totalMass = (Math.pow(this.getRadius(), 3) * Math.PI * 4.0 * SHIP_DENSITY / 3.0);
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
		this.terminate();
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
	 * Return the set of all bullets loaded on <code>ship</code>.
	 * 
	 * For students working alone, this method may return null.
	 */
	public Set<? extends Bullet> getBulletsOnShip() {
		return bullets;
	}

	/**
	 * Return the number of bullets loaded on theship.
	 * 
	 * 
	 */
	@Basic
	public int getNbBulletsOnShip() throws ModelException {
		return bullets.size();
	}

	/**
<<<<<<< HEAD
	 * Load <code>bullet</code> on <code>ship</code>.
	 * 
=======
	 * Loads bullets on ship
>>>>>>> 37ecc355a7ddf604c79b91fd202612f3db20ae47
	 */
	@Raw
	public void loadBulletOnShip(Bullet bullet) {
		if(isValidBullet(bullet)){
			bullets.add(bullet);
			bullet.setSource(this);
			bullet.bouncesReset();
			totalMass += bullet.mass;
		}
	}

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 * 
	 * For students working alone, this method must not do anything.
	 */
	@Raw
	public void loadBulletsOnShip(Collection<Bullet> bulletsCol) {
		for(Bullet bullet : bulletsCol){
			loadBulletOnShip(bullet);
			totalMass += bullet.mass;
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
		totalMass -= bullet.mass;
	}

	/**
	 * Fire a bullet from the ship
	 * 
	 * @effect
	 */
	@Raw
	public void fireBullet() {
		if(bullets.size() > 0){
			Bullet bullet = bullets.iterator().next();
			if(isValidBullet(bullet)){
				this.removeBulletFromShip(bullet);
				World world = this.superWorld;
				world.addEntityToWorld(bullet);
				bullet.setSuperWorld(world);
				bullet.setPosition(this.bulletSpawnCalculator(bullet.getRadius())[0], this.bulletSpawnCalculator(bullet.getRadius())[1]);
				bullet.setVelocity(250 * Math.cos(this.getDirection()), 250 * Math.sin(this.getDirection()));
				Helper.log("Firing a bullet");
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
     *         | result == ((direction >= 0) && (direction <= (2*Math.PI) && Helepr.isValidDouble(direction)
     */
	@Model
    private boolean isValidDirection(double direction){
        return ((direction >= 0) && (direction < 2*Math.PI) && Helper.isValidDouble(direction));
    }

	/**
     * Checks whether a given bullet is valid.
     * 
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
    
    private double[] bulletSpawnCalculator(Double bulletRadius){
    	double distance = radius + bulletRadius;
    	double newy = this.getPosition()[0] + (Math.sin(direction) * distance);
    	double newx = this.getPosition()[1] + (Math.cos(direction) * distance);
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
    	return (radius > MINIMAL_SHIP_RAD && Helper.isValidDouble(radius));
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
    protected boolean isValidMass(double mass){
    	return (mass >= 4.0*Math.PI*Math.pow(radius, 3)*SHIP_DENSITY / 3.0 && Helper.isValidDouble(mass));
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
    @Model
    private boolean isValidTotalMass(double mass){
    	if (!Helper.isValidDouble(mass)) return false;
    	return (mass == (bullets.isEmpty() ? this.mass : this.mass + bullets.stream().map(bullet -> bullet.getMass()).reduce((u, t) -> u + t).get()));
    }
}

