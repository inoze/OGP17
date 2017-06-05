package asteroids.model.program.statement;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement{
	
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
	public void execute() throws Exception{
		try{
			Object eval = getExpression().calculate();
			getProgram().addResult(eval);
		}catch(Exception ex){
			throw new Exception("print cant execute");
		}
	}
}
