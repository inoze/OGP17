package asteroids.model.program.statement;

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
		if(this.getFunction() != null) throw new Exception("(p) print statement in function body");
		if(getExpression() == null) throw new Exception("(p) expression is null");
		if(this.getProgram() == null) throw new Exception("(p) statement has no program");
		if(this.getFunction() != null) throw new Exception("(p) Print statement cannot be executed within a function");
		getExpression().setProgram(this.getProgram());
		if(getProgram().getTime() < 0.2) return;
		try{
			//Helper.log(">>>starting print with expression: " + getExpression());
			getExpression().setProgram(this.getProgram());
			Object eval = getExpression().calculate();
			//Helper.log("adding result: " + eval);
			//Helper.log("calculated expression: " + getExpression());
			getProgram().addResult(eval);
			getProgram().advanceTime();
		}catch(Exception ex){
			throw new Exception("(p) cant calculate expression: " + getExpression().getClass().getName() + ", error: " + ex.getMessage());
		}
	}
}
