package asteroids.model.program.expression;

import java.util.Set;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class SqrtExpression extends Element implements Expression<Double>{

	private Expression<Double> expression;
	
	public SqrtExpression(SourceLocation sourceLocation, Expression<Double> e1) {
		super(sourceLocation);
		this.setExpression(e1);
	}
	
	private Expression<Double> getExpression(){
		return this.expression;
	}
	
	private void setExpression(Expression<Double> expression){
		this.expression = expression;
	}

	@Override
	public Double calculate() throws Exception {
		return Math.sqrt(this.getExpression().calculate());
	}

	@Override
	public Double calculate(Object[] actualArgs, Set localVars) throws Exception {
		return Math.sqrt(this.getExpression().calculate(actualArgs, localVars));
	}

}
