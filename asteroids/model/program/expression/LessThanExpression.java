package asteroids.model.program.expression;

import java.util.Set;

import asteroids.model.Program;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class LessThanExpression extends Element implements Expression<Boolean>{
	private Expression<Double> leftExpression;
	private Expression<Double> rightExpression;
	
	public LessThanExpression(SourceLocation sourceLocation, Expression<Double> e1, Expression<Double> e2) {
		super(sourceLocation);
		this.setLeftExpression(e1);
		this.setRightExpression(e2);
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
	
	@Override
	public Boolean calculate() throws Exception {
		return this.getLeftExpression().calculate() < this.getRightExpression().calculate();
	}

	@Override
	public Boolean calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		return this.getLeftExpression().calculate(actualArgs, localVars) < this.getRightExpression().calculate(actualArgs, localVars);
	}

}

