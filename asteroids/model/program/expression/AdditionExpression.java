package asteroids.model.program.expression;

import java.util.Set;
import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class AdditionExpression extends MathematicalExpression {

	public AdditionExpression(SourceLocation sourceLocation, Expression<Double> e1, Expression<Double> e2) {
		super(sourceLocation, e1, e2);
	}
	
	@Override
	public Double calculate() throws IllegalArgumentException {
		return this.getLeftExpression().calculate() + this.getRightExpression().calculate();
	}

	@Override
	public Double calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.getLeftExpression().calculate(actualArgs, localVars) + this.getRightExpression().calculate(actualArgs, localVars);
	}

}
