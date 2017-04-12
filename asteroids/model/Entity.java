package asteroids.model;

public class Entity {

	private double[] position = new double[2];
	
	private double[] velocity = new double[2];
	
	private double radius;
	
	protected Entity(){}
	
	public void createEntity(double x, double y, double xVelocity, double yVelocity, double radius){
		
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
   public boolean isValidVelocity(double velocity){
      return Helper.isValidDouble(velocity);
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
   public boolean isValidPosition(double position){
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
   public boolean isValidRadius(double radius){
       return Helper.isValidDouble(radius);
   }
}
