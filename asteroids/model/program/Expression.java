package asteroids.model.program;

import java.util.Set;

import asteroids.model.Program;

public interface Expression<T> {
 
	public abstract T calculate() throws IllegalArgumentException;
	
	public abstract T calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException;
	
	public void setProgram(Program program);
	
	public Program getProgram();
	
}
