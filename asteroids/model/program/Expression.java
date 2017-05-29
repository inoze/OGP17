package asteroids.model.program;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public interface Expression<T> {
 
	public abstract T evaluate() throws IllegalArgumentException;
	
	public abstract T evaluate(List<Expression> actualArgs) throws IllegalArgumentException;
	
}
