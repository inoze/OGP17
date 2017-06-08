package asteroids.model.program.statement;

import java.util.Optional;
import java.util.Set;

import asteroids.model.Helper;
import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends Statement{

	public AssignmentStatement(SourceLocation sourceLocation, String variableName, Expression<?> value) {
		super(sourceLocation);
		setVariableName(variableName);
		setValue(value);
	}

	private String variableName;
	private Expression<?> value;
	private boolean hasBreak;
	
	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public Expression<?> getValue() {
		return value;
	}
	
	public void setHasBreak(boolean b){
		this.hasBreak = b;
	}
	
	public void setValue(Expression<?> value){
		this.value = value;
	}

	@Override
	public void execute() throws Exception{
		setHasBreak(false);
		try{
			getProgram().getFunction(this.variableName);
			throw new IllegalArgumentException("Already declared as function");
		} catch (Exception ex){}
		
		try{
			Set<Variable> programVariables = getProgram().getVariables();
			Optional<Variable> assign = programVariables.stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
			if(assign.isPresent()) getProgram().getVariable(getVariableName()).setValue(getValue().calculate());
			else getProgram().addVariable(new Variable(getVariableName(), getValue().calculate()));
			if (value instanceof Function && ((Function)value).hasBreak()) setHasBreak(true);
		}catch(Exception ex){
			throw new Exception("Error on assignmentStatement, var: " + getVariableName() + "; " + ex.getMessage());
		}
	}

	@Override
	public boolean hasBreak() {
		return this.hasBreak;
	}

}
