package asteroids.model.program.statement;

import asteroids.model.Helper;
import asteroids.model.program.Expression;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement{

	private Expression<?> expression;
	
	public ReturnStatement(SourceLocation sourceLocation, Expression<?> expression) {
		super(sourceLocation);
		setExpression(expression);
	}

	public Expression<?> getExpression() {
		return expression;
	}

	public void setExpression(Expression<?> expression) {
		this.expression = expression;
	}

	@Override
	public void execute() throws Exception{
		Helper.log("expression: " + getExpression());
		getExpression().setProgram(this.getProgram());
		try{
			if (!this.getProgram().hasTimeLeft()) {
	            return;
	        }
			if(this.getFunction() != null) this.getFunction().setReturnValue(this.getExpression().calculate());
		}catch(Exception ex){
			throw new Exception("return statement doesnt work");
		}
	}
}
