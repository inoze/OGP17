package asteroids.model.program.statement;

import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class SkipStatement extends Statement {
	
	private boolean consumesTime;

	public SkipStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}
	
	public void setConsumesTime(boolean b){
		this.consumesTime = b;
	}

	@Override
	public void execute() {
		this.setConsumesTime(false);
		this.getProgram().setSourceLocation(getSourceLocation());
		if (this.getProgram().getTime() <= 0.2) {
			setConsumesTime(true);
			this.getProgram().setSkip(true);
			return;
		}
		this.getProgram().advanceTime();
		
	}

	@Override
	public boolean consumesTime() {
		return this.consumesTime;
	}

}
