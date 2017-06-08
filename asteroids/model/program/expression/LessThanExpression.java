package asteroids.model.program.expression;

import java.util.Set;

import asteroids.model.Helper;
import asteroids.model.Program;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class LessThanExpression extends Element implements Expression<Boolean>{
	private Expression<Double> leftExpression;
	private Expression<Double> rightExpression;
	
	public LessThanExpression(SourceLocation sourceLocation, Expression<?> e1, Expression<?> e2) {
		super(sourceLocation);
		Helper.log("e1: " + e1.getClass());
		if(e1 instanceof DoubleLiteralExpression && e2 instanceof DoubleLiteralExpression){
			this.setLeftExpression((Expression<Double>) e1);
			this.setRightExpression((Expression<Double>) e2);
		}
		else{
			this.setLeftExpression(null);
			this.setRightExpression(null);
		}
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
		if(this.getLeftExpression() != null)this.getLeftExpression().setProgram(program);
		if(this.getRightExpression() != null)this.getRightExpression().setProgram(program);
	}
	
	@Override
	public Boolean calculate() throws Exception {
		if(this.getLeftExpression() == null || this.getRightExpression() == null) throw new Exception("Invalid expression");
		return this.getLeftExpression().calculate() < this.getRightExpression().calculate();
	}

	@Override
	public Boolean calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		if(this.getLeftExpression() == null || this.getRightExpression() == null) throw new Exception("Invalid expression");
		return this.getLeftExpression().calculate(actualArgs, localVars) < this.getRightExpression().calculate(actualArgs, localVars);
	}

}

