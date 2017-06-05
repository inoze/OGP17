package asteroids.model.program.statement;

import java.util.Optional;
import java.util.Set;

import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends  Element implements Statement{

	public AssignmentStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
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

	@Override
	public void execute() {
		setHasBreak(false);
		//Situation 1: Look up for functions, if name of the variable already exists as a function, nothing will happen.
		try{
			getProgram().getFunction(this.variableName);
			throw new IllegalArgumentException("Already declared as function");
		} catch (Exception ex){
			//If not like that, go further
		}
		
		//Situation 2: Find the first variable with that name and assign
		try{
			Set<Variable> programVariables = getProgram().getVariables();
			Optional<Variable> assign = programVariables.stream().filter(variable -> variable.getName().equals(variableName)).findFirst();
			if(assign.isPresent()){
				assign.get().setValue(value.calculate());
			}
			//Situation 3: If variable doesn't exist yet, make a new one with the provided name and value
			else getProgram().addVariable(new Variable(variableName, value.calculate()));
			//Set break if needed
			if (value instanceof Function && ((Function)value).hasBreak()) setHasBreak(true);
		}catch(Exception ex){
			throw new IllegalArgumentException("Error on assignmentStatemen: "+ex.getMessage());
		}
	}

	@Override
	public boolean consumesTime() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBreak() {
		return this.hasBreak;
	}

}
