package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.Entity;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class GetVYExpression extends EntityExpression{

	protected GetVYExpression(SourceLocation sourceLocation, Expression<Entity> entity) {
		super(sourceLocation, entity);
	}

	@Override
	public Double calculate() throws IllegalArgumentException {
		return this.getEntity().calculate().getVelocity()[1];
	}

	@Override
	public Double calculate(List actualArgs) throws IllegalArgumentException {
		return this.getEntity().calculate(actualArgs).getVelocity()[1];
	}

}
