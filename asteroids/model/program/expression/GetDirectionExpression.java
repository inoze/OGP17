package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.Entity;
import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression extends EntityExpression{

	protected GetDirectionExpression(SourceLocation sourceLocation, Expression<Entity> entity) {
		super(sourceLocation, entity);
	}
	

	@Override
	public Double calculate() throws IllegalArgumentException {
		return this.getEntity().calculate().getDirection();
	}

	@Override
	public Double calculate(List actualArgs) throws IllegalArgumentException {
		return this.getEntity().calculate(actualArgs).getRadius();
	}

}
