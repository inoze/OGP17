package asteroids.model.program.statement;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends Element implements Statement {
	
	private Expression angle;
	private boolean consumesTime;

	public TurnStatement(SourceLocation sourceLocation, Expression angle) {
		super(sourceLocation);
		this.setAngle(angle);
	}

	public Expression getAngle() {
		return angle;
	}

	@Override
	public boolean consumesTime() {
		return this.consumesTime;
	}

	public void setConsumesTime(boolean consumesTime) {
		this.consumesTime = consumesTime;
	}

	public void setAngle(Expression angle) {
		this.angle = angle;
	}

	@Override
	public void execute() {
		setConsumesTime(true);
		getProgram().setSourceLocation(getSourceLocation());
		if (getProgram().getTime() < 0.2) {
			setConsumesTime(true);
			return;
		}
		getProgram().getShip().turn((Double)angle.calculate());
		getProgram().advanceTime();		
	}

	@Override
	public boolean hasBreak() {
		return false;
	}

}
