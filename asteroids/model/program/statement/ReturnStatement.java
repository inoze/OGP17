package asteroids.model.program.statement;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
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
	public void execute() throws IllegalArgumentException{
		throw new IllegalArgumentException("Invalid run @ ReturnStatement");
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
