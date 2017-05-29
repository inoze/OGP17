package asteroids.model.program;

import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;


public interface Statement {

	public void execute();
	
	void setProgram(Program program);
	
	Program getProgram();

	SourceLocation getSourceLocation();
}

