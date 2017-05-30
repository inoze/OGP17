package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class LessThanExpression extends ComparisonExpression{
	
	public LessThanExpression(SourceLocation sourceLocation, Expression<Double> e1, Expression<Double> e2) {
		super(sourceLocation, e1, e2);
	}

	@Override
	public Boolean calculate() throws IllegalArgumentException {
		return this.getLeftExpression().calculate() < this.getRightExpression().calculate();
	}

	@Override
	public Boolean calculate(List actualArgs) throws IllegalArgumentException {
		return this.getLeftExpression().calculate(actualArgs) < this.getRightExpression().calculate(actualArgs);
	}

}

