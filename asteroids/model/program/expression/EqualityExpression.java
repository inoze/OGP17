package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.Program;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class EqualityExpression extends ComparisonExpression{
	public EqualityExpression(SourceLocation sourceLocation, Expression<Double> e1, Expression<Double> e2) {
		super(sourceLocation, e1, e2);
	}

	@Override
	public Boolean calculate() throws IllegalArgumentException {
		return this.getLeftExpression().calculate().equals(this.getRightExpression().calculate());
	}

	@Override
	public Boolean calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.getLeftExpression().calculate(actualArgs, localVars).equals(this.getRightExpression().calculate(actualArgs, localVars));
	}

}
