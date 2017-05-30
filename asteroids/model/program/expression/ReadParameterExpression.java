package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Function;
import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends Element implements Expression<Object>{

	private String parameter;

	protected ReadParameterExpression(SourceLocation sourceLocation, String paramName) {
		super(sourceLocation);
		setParameter(paramName);
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public Object calculate() {
		throw new IllegalArgumentException();
	}


	@Override
	public Object calculate(List actualArgs) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
