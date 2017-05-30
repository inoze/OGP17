package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class MultiplicationExpression extends MathematicalExpression{
	
	public MultiplicationExpression(SourceLocation sourceLocation, Expression<Double> e1, Expression<Double> e2) {
		super(sourceLocation, e1, e2);
	}
	
	@Override
	public Double calculate() throws IllegalArgumentException {
		return this.getLeftExpression().calculate() * this.getRightExpression().calculate();
	}

	@Override
	public Double calculate(List actualArgs) throws IllegalArgumentException {
		return this.getLeftExpression().calculate() * this.getRightExpression().calculate(actualArgs);
	}

}
