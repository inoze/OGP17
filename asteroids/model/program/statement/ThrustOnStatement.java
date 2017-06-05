package asteroids.model.program.statement;

import asteroids.model.program.Element;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class ThrustOnStatement extends Statement {
	private boolean consumesTime;
	
	public ThrustOnStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	public boolean consumesTime() {
		return consumesTime;
	}

	public void setConsumesTime(boolean consumesTime) {
		this.consumesTime = consumesTime;
	}

	@Override
	public void execute() {
		this.setConsumesTime(true);
		this.getProgram().setSourceLocation(getSourceLocation());
		if (this.getProgram().getTime() < 0.2) {
			this.setConsumesTime(false);
			return;
			
		}
		this.getProgram().getShip().thrustOn();
		this.getProgram().advanceTime();		
	}
}
