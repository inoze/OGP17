package asteroids.model.program.expression;

import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class GetVYExpression extends EntityExpression{

	public GetVYExpression(SourceLocation sourceLocation, Expression<Entity> entity) {
		super(sourceLocation, entity);
	}

	@Override
	public Double calculate() throws Exception {
		if(getEntity() == null) throw new IllegalArgumentException("(gvye) entity is null");
		return this.getEntity().calculate().getVelocity()[1];
	}

	@Override
	public Double calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		if(getEntity() == null) throw new IllegalArgumentException("(gvye) entity is null");
		return this.getEntity().calculate(actualArgs, localVars).getVelocity()[1];
	}
}
