package asteroids.model.program.expression;

import java.util.List;
import java.util.Set;

import asteroids.model.Program;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class EqualityExpression extends Element implements Expression<Boolean>{
	private Expression<?> leftExpression;
	private Expression<?> rightExpression;
	
	public EqualityExpression(SourceLocation sourceLocation, Expression<?> e1, Expression<?> e2) {
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
	
	@Override
	public Boolean calculate() throws Exception {
		if(!(this.getLeftExpression().getClass().equals(this.getRightExpression().getClass()))) return false;
		return this.getLeftExpression().calculate().equals(this.getRightExpression().calculate());
	}
	
	@Override
	public Boolean calculate(Object[] actualArgs, Set localVars) throws Exception {
		if(!(this.getLeftExpression().getClass().equals(this.getRightExpression().getClass()))) return false;
		return this.getLeftExpression().calculate(actualArgs, localVars).equals(this.getRightExpression().calculate(actualArgs, localVars));
	}

}
