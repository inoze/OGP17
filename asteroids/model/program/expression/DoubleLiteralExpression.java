package asteroids.model.program.expression;

import java.util.List;

import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression extends Element implements Expression<Double>{

	private double value;
	
	public DoubleLiteralExpression(SourceLocation sourceLocation, double value) {
		super(sourceLocation);
		this.setValue(value);
	}
	
	private double getValue(){
		return this.value;
	}
	
	private void setValue(double value){
		this.value = value;
	}

	@Override
	public Double calculate() throws IllegalArgumentException {
		return this.getValue();
	}

	@Override
	public Double calculate(List actualArgs) throws IllegalArgumentException {
		return this.getValue();
	}
}
