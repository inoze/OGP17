package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class GetXExpression extends EntityExpression{

	public GetXExpression(SourceLocation sourceLocation, Expression<Entity> entity) {
		super(sourceLocation, entity);
	}

	@Override
	public Double calculate() throws Exception {
		if(getEntity() == null) throw new IllegalArgumentException("(gxe) entity is null");
		return this.getEntity().calculate().getPosition()[0];
	}

	@Override
	public Double calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		if(getEntity() == null) throw new IllegalArgumentException("(gxe) entity is null");
		return this.getEntity().calculate(actualArgs, localVars).getPosition()[0];
	}

}
