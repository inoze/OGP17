package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression extends Element implements Expression<Double>{

	private Expression<Double> expression;
	
	public ChangeSignExpression(SourceLocation sourceLocation, Expression<Double> expression) {
		super(sourceLocation);
		this.setExpression(expression);
	}

	public Expression<Double> getExpression() {
		return this.expression;
	}

	private void setExpression(Expression<Double> expression) {
		this.expression = expression;
	}

	@Override
	public Double calculate() throws Exception {
		return -this.getExpression().calculate();
	}

	@Override
	public Double calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		return -this.getExpression().calculate(actualArgs, localVars);
	}
}
