package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.*;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class SelfExpression extends Element implements Expression<Entity> {

	public SelfExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Ship calculate() throws IllegalArgumentException {
		return getProgram().getShip();
	}

	@Override
	public Entity calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.calculate();
	}

}
