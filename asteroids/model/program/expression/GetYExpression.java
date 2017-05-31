package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.Entity;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class GetYExpression extends EntityExpression{

	public GetYExpression(SourceLocation sourceLocation, Expression<Entity> entity) {
		super(sourceLocation, entity);
	}

	@Override
	public Double calculate() throws IllegalArgumentException {
		return this.getEntity().calculate().getPosition()[1];
	}

	@Override
	public Double calculate(List actualArgs) throws IllegalArgumentException {
		return this.getEntity().calculate(actualArgs).getPosition()[1];
	}

}
