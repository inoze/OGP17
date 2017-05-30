package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class ReadVariableExpression extends Element implements Expression<Object>{
	
	private String variableName;

	protected ReadVariableExpression(SourceLocation sourceLocation, String name) {
		super(sourceLocation);
		setVariableName(name);
	}

	public String getVariableName() {
		return this.variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public Object calculate() throws IllegalArgumentException {
		return getProgram().getVariable(variableName).getValue();
	}

	@Override
	public Object calculate(List actualArgs) throws IllegalArgumentException {
		return getProgram().getVariable(variableName).getValue();
	}

}
