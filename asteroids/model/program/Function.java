package asteroids.model.program;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import asteroids.model.Program;
import asteroids.model.program.expression.DoubleLiteralExpression;
import asteroids.part3.programs.SourceLocation;

public class Function {

	private Program program;
    private SourceLocation sourceLocation;
    private Statement body;
	private String functionName;
	private HashMap<String, Expression<?>> parameters=new HashMap<>();
	private Object returnValue;
	private boolean returnReached;
	private boolean hasBreak;
    
    public  Function (String functionName, Statement body, SourceLocation sourceLocation) {
      setFunctionName(functionName);
      SetBody(body);
      setSourceLocation(sourceLocation);
    }
    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public boolean isReturnReached() {
        return returnReached;
    }

    public void setReturnReached(boolean returnReached) {
        this.returnReached = returnReached;
    }

	private void SetBody(Statement body) {
		this.body = body;
	
	}
	public Statement getbody() {
		return body;
	}

	private void setFunctionName(String functionName) {
		this.functionName = functionName;
		
	}
	public String getFunctionName(){
		return functionName;
	}

	public void setProgram(Program program) {
		this.program =program;
    	}
	public Program getProgram() {
			return this.program;
		}
	


	public SourceLocation getSourceLocation() {
			return this.sourceLocation;
		}

	public void setSourceLocation(SourceLocation sourceLocation) {
			this.sourceLocation = sourceLocation;
		}

	public void setParameters(HashMap <String, Expression<?>> parameters) {
		this.parameters = parameters;
	}


	public HashMap <String, Expression<?>> getParameters() {
		return parameters;
	}
	
	public boolean hasBreak() {
		return hasBreak;
	}
	public void setHasBreak(boolean hasBreak) {
		this.hasBreak = hasBreak;
	}
	
	public Object calculate(Object[] arguments){//
		setHasBreak(false);
		Set<Variable> locals = new HashSet<Variable>();
		try{
			Optional result = body.execute(arguments, locals);
			if(body.hasBreak()){
				setHasBreak(true);
				return null;
			}
			else setHasBreak(false);
			return result.get();
		} 
		
		catch(Exception ex){
			throw new IllegalArgumentException();
		}
	}
}
	
	/*
	public Object execute(List<Expression<?>> actualArgs) throws Exception {
        for(int i = 1; i <= actualArgs.size(); i++){
        	Expression<?> arg = (Expression<?>)actualArgs.toArray()[i-1];
            if (arg instanceof DoubleLiteralExpression) {
                this.getParameters().put("$" + Integer.toString(i), arg);
            }
            else {
                this.getProgram().setRecursion(this.getProgram().getRecursion() - 1);
                Expression<?> eval = new DoubleLiteralExpression(this.getSourceLocation(), (Double) arg.calculate());
                this.getProgram().setRecursion(this.getProgram().getRecursion() + 1);
                this.getParameters().put("$" + Integer.toString(i), eval);
            }
        }
        while(!this.isReturnReached()){
        	this.getbody().setFunction(this);
            this.getbody().execute();
            if(!this.isReturnReached()){
            	throw new Exception("Infinte loop");
            }
        }
        this.setReturnReached(false);
        return this.getReturnValue();
	}
}
*/
/*package asteroids.model.program;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import asteroids.model.Program;
import asteroids.model.program.expression.DoubleLiteralExpression;
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
	
	public Object calculate(List<Expression> arguments) throws Exception{
		this.setHasBreak(false);
		Set<Variable> vars = new HashSet<Variable>();
		try{
			for(int i = 1; i <= arguments.size(); i++){
	        	Expression arg = (Expression)arguments.toArray()[i-1];
	            if (arg instanceof DoubleLiteralExpression) {
	                this.getParameters().put("$" + Integer.toString(i), arg);
	            }
	            else {
	                this.getProgram().setRecursion(this.getProgram().getRecursion() - 1);
	                MyExpression<? extends Type> eval = new DoubleLiteralExpression(((doubleType)arg.evaluate(this.getProgram().getShip(), this)).getDouble(), this.getSourceLocation());
	                this.getProgram().setRecursion(this.getProgram().getRecursion() + 1);
	                this.getParameters().put("$" + Integer.toString(i), eval);
	            }
	        }
	        while(!this.isReturnReached()){
	        	this.getbody().setFunction(this);
	            this.getbody().execute();
	            if(!this.isReturnReached()){
	            	throw new ClassNotFoundException("Infinte loop");
	            }
	        }
	        this.setReturnReached(false);
	        return this.getReturnValue();
		} 
		
		catch(Exception ex){
			throw new IllegalArgumentException();
		}
		
	}

	public Object getVariable(String variableName) throws NoSuchElementException {
		return getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst().get().getValue();
	}

}*/
