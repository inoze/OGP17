package asteroids.model.program.expression;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import asteroids.model.Helper;
import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression extends Element implements Expression{
	
	private String name;
	private List<Expression> arguments;
	private boolean breakDiscovered;

	public FunctionCallExpression(SourceLocation sourceLocation, String name, List<Expression> arguments) {
		super(sourceLocation);
		this.setName(name);
		this.setArguments(arguments);
	}

	private List<Expression> getArguments() {
		return arguments;
	}

	private String getName() {
		return name;
	}

	private boolean isBreakDiscovered() {
		return breakDiscovered;
	}

	private void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}
	
	private void setBreakDiscovered(boolean breakDiscovered) {
		this.breakDiscovered = breakDiscovered;
	}

	private void setName(String name) {
		this.name = name;
	}

	@Override
	public Object calculate() throws Exception {
		Helper.log("FunctionCallExpression: function: " + name);
		Function function = this.getProgram().getFunction(name);
		Helper.log("Function ID: " + function);
		if(this.getProgram().getFunction(name) == null) throw new Exception("Function not defined");
		try{
			Helper.log("Executing function: " + this.getProgram().getFunction(name));
			this.getProgram().getFunction(name).calculate();
			return this.getProgram().getFunction(getName()).calculate();
		}catch(Exception ex){
			throw new Exception("cant execute function: " + ex.getMessage());
		}
	}

	@Override
	public Object calculate(Object[] actualArgs, Set localVars) throws Exception {
		Helper.log("FunctionCallExpression wa: function: " + name);
		Function function = this.getProgram().getFunction(name);
		Helper.log("Function ID: " + function);
		if(this.getProgram().getFunction(name) == null) throw new Exception("Function not defined");
		try{
			Helper.log("Executing function: " + this.getProgram().getFunction(name));
			this.getProgram().getFunction(name).calculate();
		}catch(Exception ex){
			throw new Exception("cant execute function: " + ex.getMessage());
		}
		return null;
	}


}
