package asteroids.model.program;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import asteroids.model.Helper;
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
      if(body != null) body.setFunction(this);
    }
    
    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

	public Statement getbody() {
		return this.body;
	}
	
	public String getFunctionName(){
		return functionName;
	}
	
	public Program getProgram() {
		return this.program;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public boolean hasBreak() {
		return hasBreak;
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
	
	private void setFunctionName(String functionName) {
		this.functionName = functionName;
		
	}

	public void setProgram(Program program) {
		this.program =program;
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
	
	public void setHasBreak(boolean hasBreak) {
		this.hasBreak = hasBreak;
	}
	
	public Object calculate(){
		Helper.log("Calculating function: " + this.getFunctionName() + "; ID:" + this);
		setHasBreak(false);
		Set<Variable> locals = new HashSet<Variable>();
		body.setProgram(this.getProgram());
		try{
			body.execute();
			if(body.hasBreak()){
				setHasBreak(true);
				return null;
			}
			else setHasBreak(false);
			Helper.log("calculating function, return: " + getReturnValue());
			return getReturnValue();
		} 
		
		catch(Exception ex){
			throw new IllegalArgumentException();
		}
	}
}