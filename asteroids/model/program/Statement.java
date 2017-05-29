package asteroids.model.program;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class Statement {

	private SourceLocation location;
	private Program program;

	public Statement(SourceLocation location) {
		this.location = location;
	}

	public abstract void execute();

	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return program;
	}

	public boolean hasActiveBreakStatement() {
		return false;
	}

	public SourceLocation getSourceLocation() {
		return location;
	}

	public boolean failedToAdvanceTime() {
		return false;
	}
}
