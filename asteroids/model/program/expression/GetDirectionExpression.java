package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.Entity;
import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression extends Element implements Expression{

	public GetDirectionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	

	@Override
	public Double calculate() throws IllegalArgumentException {
		return this.getProgram().getShip().getDirection();
	}

	@Override
	public Double calculate(List actualArgs) throws IllegalArgumentException {
		return this.getProgram().getShip().getDirection();
	}

}
