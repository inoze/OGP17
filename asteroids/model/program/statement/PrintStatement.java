package asteroids.model.program.statement;

//import asteroids.model.Ship;
import asteroids.model.program.Expression;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement{
	
	private Expression<?> expression;

	public PrintStatement(SourceLocation sourceLocation, Expression<?> expression) {
		super(sourceLocation);
		this.setExpression(expression);
	}

	public Expression<?> getExpression() {
		return expression;
	}

	public void setExpression(Expression<?> expression) {
		this.expression = expression;
	}

	@Override
	public void execute() throws Exception{
		//Ship ship =this.getProgram().getShip();
		if(getExpression() == null) throw new Exception("(p) expression is null");
		if(this.getProgram() == null) throw new Exception("(p) statement has no program");
		//if(getExpression().getProgram() == null) throw new Exception("(p) expression has no program");
		try{
			Object eval = getExpression().calculate();
			getProgram().addResult(eval);
		}catch(Exception ex){
			throw new Exception("(p) cant calculate expression: " + getExpression().getClass().getName() + ", error: " + ex.getMessage());
		}
	}
}
