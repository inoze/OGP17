package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression extends Element implements Expression<Double>{

	public GetDirectionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	

	@Override
	public Double calculate() throws IllegalArgumentException {
		return this.getProgram().getShip().getDirection();
	}

	@Override
	public Double calculate(Object[] actualArgs, Set localVars) throws IllegalArgumentException {
		return this.getProgram().getShip().getDirection();
	}

}
