package asteroids.model;
import java.util.*;

import asteroids.model.program.*;
import asteroids.model.program.expression.*;
import asteroids.model.program.statement.*;
import asteroids.part3.programs.*;

public class Program {
	private List<Function> functions;
	private Statement body;
	private Set<Variable> variables = new HashSet<Variable>();
	private List<Object> results = new ArrayList<Object>();
	private double timeRemaining;
	private SourceLocation location = new SourceLocation(0, 0);
	private Ship ship;
	
	public Program(List<Function> functions, Statement main) {
		this.functions = functions;
		main.setProgram(this);
		for(Function function: functions) function.setProgram(this);
		timeRemaining = 0;
	}
	
	public List<Object> execute(double dt) throws IllegalArgumentException {
		timeRemaining += dt;
		body.execute();
		if (body.consumesTime()) { //!Important - not sure if negation is correct
			if(body.hasBreak()) throw new IllegalArgumentException("Break statements cannot occur outside function bodies");
			location = new SourceLocation(0, 0);
			List<Object> resultsToThrow = results; 
			results = null;
			return resultsToThrow;
		}
		throw new IllegalArgumentException("body doesnt consume time");
		
		//return null;
	}

	public void setShip(Ship ship) {
		if(ship.getProgram() != this) throw new IllegalArgumentException("Program is not set to ship");
		this.ship = ship;
	}

	public Ship getShip() {
		return this.ship;
	}

	public List<Object> getResults() {
		return results;
	}

	public Variable getVariable(String variableName) throws NoSuchElementException {
		return getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst().get();
	}
	
	public void addVariable(Variable variable){
		variables.add(variable);
	}

	public void advanceTime() {
		timeRemaining -= 0.2;
	}

	public double getTime() {
		return this.timeRemaining;
	}

	public void addResult(Object result) {
		results.add(result);
	}

	public SourceLocation getSourceLocation() {
		return this.location;
	}

	public void setSourceLocation(SourceLocation location) {
		this.location = location;
	}

	public Set<Variable> getVariables() {
		return new HashSet<Variable>(variables);
	}

	public Function getFunction(String functionName) throws NoSuchElementException {
		return this.functions.stream().filter(function -> function.getName().equals(functionName)).reduce((first, second) -> second).get();
	}
}
