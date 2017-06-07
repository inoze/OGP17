package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class GetVXExpression extends EntityExpression{
	
	public GetVXExpression(SourceLocation sourceLocation, Expression<Entity> entity) {
		super(sourceLocation, entity);
	}

	@Override
	public Double calculate() throws Exception {
		getEntity().setProgram(this.getProgram());
		if(getEntity() == null) throw new IllegalArgumentException("(gvxe) entity is null");
		return this.getEntity().calculate().getVelocity()[0];
	}

	@Override
	public Double calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		getEntity().setProgram(this.getProgram());
		if(getEntity() == null) throw new IllegalArgumentException("(gvxe) entity is null");
		return this.getEntity().calculate(actualArgs, localVars).getVelocity()[0];
	}
}
