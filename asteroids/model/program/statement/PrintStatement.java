package asteroids.model.program.statement;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends  Element implements Statement{
	
	private Expression expression;

	public PrintStatement(SourceLocation sourceLocation, Expression expression) {
		super(sourceLocation);
		this.setExpression(expression);
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public void execute() {
		Object eval = getExpression().calculate();
		getProgram().addResult(eval);
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
