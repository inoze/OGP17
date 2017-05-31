package asteroids.model.program.statement;

import java.util.Optional;
import java.util.Set;

import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends  Element implements Statement{

	public AssignmentStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}


	private String variableName;
	private Expression<?> value;
	
	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public Expression<?> getValue() {
		return value;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean consumesTime() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBreak() {
		// TODO Auto-generated method stub
		return false;
	}

}
