package asteroids.model.program;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;


public interface Statement {

	public void execute() throws Exception;
	
	void setProgram(Program program);
	
	boolean consumesTime();
	
	boolean hasBreak();
	
	Program getProgram();

	SourceLocation getSourceLocation();
}

