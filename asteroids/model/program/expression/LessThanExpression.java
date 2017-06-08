package asteroids.model.program.expression;

import java.util.Set;

import asteroids.model.Helper;
import asteroids.model.Program;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class LessThanExpression extends Element implements Expression<Boolean>{
	private Expression<?> leftExpression;
	private Expression<?> rightExpression;
	
	public LessThanExpression(SourceLocation sourceLocation, Expression<?> e1, Expression<?> e2) {
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
		if(this.getLeftExpression() != null)this.getLeftExpression().setProgram(program);
		if(this.getRightExpression() != null)this.getRightExpression().setProgram(program);
	}
	
	@Override
	public Boolean calculate() throws Exception {
		if(this.getLeftExpression() == null || this.getRightExpression() == null) throw new Exception("expression is null");
		if(!(this.getLeftExpression().calculate() instanceof Double && this.getRightExpression().calculate() instanceof Double)) throw new Exception("invalid expression given");
		return (Double)this.getLeftExpression().calculate() < (Double)this.getRightExpression().calculate();
	}

	@Override
	public Boolean calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		if(this.getLeftExpression() == null || this.getRightExpression() == null) throw new Exception("expression is null");
		if(!(this.getLeftExpression().calculate() instanceof Double && this.getRightExpression().calculate() instanceof Double)) throw new Exception("invalid expression given");
		return (Double)this.getLeftExpression().calculate(actualArgs, localVars) < (Double)this.getRightExpression().calculate(actualArgs, localVars);
	}

}

