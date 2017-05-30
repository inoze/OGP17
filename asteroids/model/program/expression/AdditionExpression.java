package asteroids.model.program.expression;


import java.util.List;

import asteroids.model.Program;
import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class AdditionExpression extends Element implements Expression<Double> {
	private Expression<Double> leftExpression;
	private Expression<Double> rightExpression;

	public AdditionExpression(SourceLocation sourceLocation, Expression<Double> e1, Expression<Double> e2) {
		super(sourceLocation);
		setLeftExpression(e1);
		setRightExpression(e2);
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
	public Double calculate() throws IllegalArgumentException {
		return this.getLeftExpression().calculate() + this.getRightExpression().calculate();
	}

	@Override
	public Double calculate(List actualArgs) throws IllegalArgumentException {
		return this.getLeftExpression().calculate() + this.getRightExpression().calculate(actualArgs);
	}

}
