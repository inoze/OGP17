package asteroids.model.program;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;


public abstract class Statement extends Element {
	
	private Function function;
	private Program program;

	protected Statement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	public Program getProgram(){
		return this.program;
	}
	
	public  Function getFunction() {
		return this.function;
	}
	
	public boolean hasBreak() {
		return false;
	}

	public boolean consumesTime(){
		return false;
	}
		
	public void setFunction(Function function) {
		this.function = function;
	}
	
	public void setProgram(Program program){
		this.program = program;
	}
		
	public void execute() throws Exception{
		throw new Exception("Execute in abstract class has to be overridden");
	}
}