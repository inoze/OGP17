package asteroids.model;

public class Planetoid extends Entity{
	
	private double totalDistanceTraveled;
	
	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalDistanceTraveled){
		super(x, y, xVelocity, yVelocity, radius);
		this.setTotalDistanceTraveled(totalDistanceTraveled);
	}
	
	public double getTotalDistanceTraveled(){
		return this.totalDistanceTraveled;
	}
	
	private void setTotalDistanceTraveled(double distance){
		if(Helper.isValidDouble(totalDistanceTraveled))
			this.totalDistanceTraveled = distance;
	}
	
	@Override
	public double getRadius(){
		return radius-(0.000001*getTotalDistanceTraveled());
	}
}
