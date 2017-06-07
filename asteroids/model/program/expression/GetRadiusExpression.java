package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class GetRadiusExpression extends EntityExpression{

	public GetRadiusExpression(SourceLocation sourceLocation, Expression<Entity> entity) {
		super(sourceLocation, entity);
	}

	@Override
	public Double calculate() throws IllegalArgumentException {
		if(getEntity() == null) throw new IllegalArgumentException("(gre) entity is null");
		//throw new IllegalArgumentException("(gre): entity" + getEntity().getClass().getName());
		return this.getEntity().calculate().getRadius();
	}

	@Override
	public Double calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.getEntity().calculate(actualArgs, localVars).getRadius();
	}

}
