package asteroids.model.program.statement;

import asteroids.model.Helper;
import asteroids.model.program.Expression;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends Statement {
	
	private Expression<Double> angle;
	private boolean consumesTime;

	public TurnStatement(SourceLocation sourceLocation, Expression<Double> angle) {
		super(sourceLocation);
		this.setAngle(angle);
	}

	public Expression<Double> getAngle() {
		return angle;
	}

	@Override
	public boolean consumesTime() {
		return this.consumesTime;
	}

	public void setConsumesTime(boolean consumesTime) {
		this.consumesTime = consumesTime;
	}

	public void setAngle(Expression<Double> angle) {
		this.angle = angle;
	}

	@Override
	public void execute() throws Exception{
		if(this.getFunction() != null) throw new Exception("ActionStatements cannot be executed within function bodies");
		setConsumesTime(true);
		getProgram().setSourceLocation(getSourceLocation());
		if (getProgram().getTime() < 0.2) {
			setConsumesTime(true);
			return;
		}
		if(Helper.isValidDouble(angle.calculate()) && angle.calculate() >= 0 && angle.calculate() <= 2*Math.PI && this.getFunction() == null){
			getProgram().getShip().turn((Double)angle.calculate());
		}else{throw new IllegalArgumentException("Given angle is not valid or executed within function body");}
		getProgram().advanceTime();		
	}

}
