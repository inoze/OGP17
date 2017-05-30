package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class EqualityExpression extends Element implements Expression<Boolean>{

	private Expression<Double> leftExpression;
	private Expression<Double> rightExpression;
	
	protected EqualityExpression(SourceLocation sourceLocation, Expression<Double> e1, Expression<Double> e2) {
		super(sourceLocation);
		this.setLeftExpression(e1);
		this.setRightExpression(e2);
	}

	private Expression<Double> getLeftExpression(){
		return this.leftExpression;
	}

	private Expression<Double> getRightExpression(){
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
	
	@Override
	public Boolean calculate() throws IllegalArgumentException {
		return this.getLeftExpression().calculate().equals(this.getRightExpression().calculate());
	}

	@Override
	public Boolean calculate(List actualArgs) throws IllegalArgumentException {
		return this.getLeftExpression().calculate(actualArgs).equals(this.getRightExpression().calculate(actualArgs));
	}

}
