package asteroids.model.program;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Function {

	private String name;
	private Statement body;
	private SourceLocation sourceLocation;
	private Program program;
	private Set<Variable> variables = new HashSet<Variable>();
	private boolean hasBreak;

	public Function(String functionName, Statement body, SourceLocation sourceLocation) {
		this.name = functionName;
		this.body = body;
		this.setSourceLocation(sourceLocation);
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public String getName() {
		return this.name;
	}

	public boolean hasBreak() {
		return this.hasBreak;
	}
	

	public Set<Variable> getVariables() {
		return new HashSet<Variable>(variables);
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public Program getProgram(){
		return this.program;
	}
	
	private void setHasBreak(boolean b) {
		this.hasBreak = b;
	}

	public void setProgram(Program program) {
		this.program = program;
		body.setProgram(program);
	}
	
	public void addVariable(Variable variable) {
		variables.add(variable);
	}
	
	public Object calculate(Object[] calculatedarguments){
		return null;
	}

	public Object getVariable(String variableName) throws NoSuchElementException {
		return getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst().get().getValue();
	}

}
