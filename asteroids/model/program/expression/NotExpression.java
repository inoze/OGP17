package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class NotExpression extends Element implements Expression<Boolean>{

	private Expression<Boolean> expression;
	
	public NotExpression(SourceLocation sourceLocation, Expression<Boolean> expression) {
		super(sourceLocation);
		this.setExpression(expression);
	}

	public Expression<Boolean> getExpression() {
		return expression;
	}

	public void setExpression(Expression<Boolean> expression) {
		this.expression = expression;
	}

	@Override
	public Boolean calculate() throws Exception {
		return !expression.calculate();
	}

	@Override
	public Boolean calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		return !expression.calculate(actualArgs, localVars);
	}

}
