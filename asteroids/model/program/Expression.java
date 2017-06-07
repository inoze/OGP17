package asteroids.model.program;

import java.util.Set;

import asteroids.model.Program;

public interface Expression<T> {
 
	public abstract T calculate() throws Exception;
	
	public abstract T calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception;
	
	public void setProgram(Program program);
	
	public Program getProgram();
	
}
