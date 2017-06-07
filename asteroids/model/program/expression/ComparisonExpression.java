package asteroids.model.program.expression;

import asteroids.model.Program;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public abstract class ComparisonExpression extends Element implements Expression<Boolean> {
	private Expression<?> leftExpression;
	private Expression<?> rightExpression;
	
	protected ComparisonExpression(SourceLocation sourceLocation, Expression<?> e1, Expression<?> e2) {
		super(sourceLocation);
		this.setLeftExpression(e1);
		this.setRightExpression(e2);
	}

	protected Expression<?> getLeftExpression(){
		return this.leftExpression;
	}

	protected Expression<?> getRightExpression(){
		return this.rightExpression;
	}

	private void setLeftExpression(Expression<?> expression){
		this.leftExpression = expression;
	}
	
	private void setRightExpression(Expression<?> expression){
		this.rightExpression = expression;
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		this.getLeftExpression().setProgram(program);
		this.getRightExpression().setProgram(program);
	}
}
