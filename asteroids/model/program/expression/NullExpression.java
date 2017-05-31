package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.Entity;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class NullExpression extends Element implements Expression<Entity> {

	public NullExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Entity calculate() throws IllegalArgumentException {
		return null;
	}

	@Override
	public Entity calculate(List actualArgs) throws IllegalArgumentException {
		return null;
	}

}
