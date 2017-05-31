package asteroids.model.program.statement;

import asteroids.model.program.Element;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class FireStatement extends Element implements Statement{
	private boolean consumesTime;

	public FireStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	public boolean consumesTime() {
		return this.consumesTime;
	}

	public void setConsumesTime(boolean b) {
		this.consumesTime = b;
	}

	@Override
	public void execute() {
		setConsumesTime(true);
		getProgram().setSourceLocation(getSourceLocation());
		if (getProgram().getTime() <= 0.2) {
			setConsumesTime(false);
			return;
		}
		getProgram().getShip().fireBullet();
		getProgram().advanceTime();
	}


	@Override
	public boolean hasBreak() {
		// TODO Auto-generated method stub
		return false;
	}	
}
