package asteroids.model.program.expression;

import asteroids.model.Program;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public abstract class MathematicalExpression extends Element implements Expression<Double>{

	private Expression<Double> leftExpression;
	private Expression<Double> rightExpression;
	
	public MathematicalExpression(SourceLocation sourceLocation, Expression<Double> e1, Expression<Double> e2) {
		super(sourceLocation);
		setLeftExpression(e1);
		setRightExpression(e2);
	}
	
	protected Expression<Double> getLeftExpression(){
		return this.leftExpression;
	}

	protected Expression<Double> getRightExpression(){
		return this.rightExpression;
	}

	private void setLeftExpression(Expression<Double> expression){
		this.leftExpression = expression;
	}
	
	private void setRightExpression(Expression<Double> expression){
		this.rightExpression = expression;
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		this.getLeftExpression().setProgram(program);
		this.getRightExpression().setProgram(program);
	}
	
}
