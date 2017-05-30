package asteroids.model.program;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public interface Expression<T> {
 
	public abstract T calculate() throws IllegalArgumentException;
	
	public abstract T calculate(List<Expression> actualArgs) throws IllegalArgumentException;
	
	public void setProgram(Program program);
	
	public Program getProgram();
	
}
