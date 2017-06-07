package asteroids.model.program.expression;

import java.util.Set;

import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression extends Element implements Expression<Double>{

	private double value;
	
	public DoubleLiteralExpression(SourceLocation sourceLocation, double value) {
		super(sourceLocation);
		this.setValue(value);
	}
	
	public double getValue(){
		return this.value;
	}
	
	private void setValue(double value){
		this.value = value;
	}

	@Override
	public Double calculate() throws IllegalArgumentException {
		//try{
			return this.getValue();
		/*}catch(Exception ex){
			throw new IllegalArgumentException("(dle) " + ex.getMessage());
		}*/
	}

	@Override
	public Double calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.getValue();
	}
}
