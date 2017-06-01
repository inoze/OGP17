package asteroids.model.program.expression;

import java.util.Set;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends Element implements Expression<Object>{

	private String parameter;

	public ReadParameterExpression(SourceLocation sourceLocation, String paramName) {
		super(sourceLocation);
		setParameter(paramName);
	}

	public String getParameter() {
		return this.parameter;
	}

	private void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public Object calculate() {
		throw new IllegalArgumentException();
	}

	@Override
	public Object calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		int index = Integer.parseInt(getParameter().substring(1, getParameter().length()));
		return actualArgs[index-1];
	}

}
