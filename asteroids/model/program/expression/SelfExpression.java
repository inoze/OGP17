package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class SelfExpression extends Element implements Expression {

	protected SelfExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object calculate() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object calculate(List actualArgs) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
