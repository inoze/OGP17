package asteroids.model.program.expression;

import java.util.List;

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
	public Double calculate() throws IllegalArgumentException {
		return -this.getExpression().calculate();
	}

	@Override
	public Double calculate(List actualArgs) throws IllegalArgumentException {
		return -this.getExpression().calculate(actualArgs);
	}
}
