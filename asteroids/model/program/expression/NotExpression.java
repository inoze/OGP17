package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
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
	public Boolean calculate() throws IllegalArgumentException {
		return !expression.calculate();
	}

	@Override
	public Boolean calculate(List<Expression> actualArgs) throws IllegalArgumentException {
		return !expression.calculate(actualArgs);
	}

}
