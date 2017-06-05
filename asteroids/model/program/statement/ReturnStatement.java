package asteroids.model.program.statement;

import asteroids.model.Ship;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Function;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends  Element implements Statement{

	private Expression expression;
	
	public ReturnStatement(SourceLocation sourceLocation, Expression expression) {
		super(sourceLocation);
		setExpression(expression);
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public void execute() throws Exception{
		try{
			if (!this.getProgram().hasTimeLeft()) {
	            return;
	        }
		}catch(Exception ex){
			throw new Exception("return statement doesnt work");
		}
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
