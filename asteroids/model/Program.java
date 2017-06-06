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
	private boolean skip;
	private int recursion =0;
	
	public Program(List<Function> functions, Statement main) {
		//if(main == null) throw new NullPointerException("(pro) main statement is null");
		try{
			this.functions = functions;
			if(main != null) main.setProgram(this);
			setBody(main);
			for(Function function: functions){ 
				if(function != null) function.setProgram(this);
				//else throw new NullPointerException("(pro) Null function in function list");
			}
			timeRemaining = 0;
		}catch(Exception ex){
			throw new IllegalArgumentException("(pro) " + ex.getMessage());
		}
	}
	
	public boolean hasSkip() {
		return skip;
	}

	public int getRecursion() {
		return recursion;
	}
	
	public List<Function> getFunctions(){
		return this.functions;
	}
	public Ship getShip() {
		return this.ship;
	}

	public List<Object> getResults() {
		return results;
	}

	public SourceLocation getSourceLocation() {
		return this.location;
	}

	public double getTime() {
		return this.timeRemaining;
	}
	
	public Set<Variable> getVariables() {
		return new HashSet<Variable>(variables);
	}
	
	public Variable getVariable(String variableName) throws NoSuchElementException {
		return getVariables().stream().filter(variable -> variable.getName().equals(variableName)).findFirst().get();
	}
	
	public Function getFunction(String functionName) throws NoSuchElementException {
		return this.functions.stream().filter(function -> function.getFunctionName().equals(functionName)).reduce((first, second) -> second).get();
	}
	
	public boolean hasTimeLeft(){
		return this.timeRemaining >= 0.2;
	}
	
	public void setSourceLocation(SourceLocation location) {
		this.location = location;
	}
	
	public void setFunctions(List<Function> functions){
		this.functions = functions;
	}

	public void setRecursion(int recursion) {
		this.recursion = recursion;
	}

	public void setShip(Ship ship) {
		if(ship.getProgram() != this) throw new IllegalArgumentException("Program is not set to ship");
		this.ship = ship;
	}
	
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	public void setBody(Statement body){
		this.body = body;
	}
	
	public void addVariable(Variable variable){
		variables.add(variable);
	}
	
	public void addResult(Object result) {
		results.add(result);
	}
	
	public void advanceTime() {
		timeRemaining -= 0.2;
	}

	
	public List<Object> execute(double dt) throws IllegalArgumentException {
		timeRemaining += dt;
		//Helper.log("body: " + body.getClass().getName());
		try{body.execute();}catch(Exception ex){throw new IllegalArgumentException("(b): " + ex.getMessage());}
		//if(hasSkip()) return null;
		if (body.consumesTime()) {
			if(body.hasBreak()) throw new IllegalArgumentException("Break statements cannot occur outside function bodies");
			location = new SourceLocation(0, 0);
			if(results == null){throw new IllegalArgumentException("results is null");}
			List<Object> resultsToThrow = results; 
			results = null;
			return resultsToThrow;
		}
		//throw new IllegalArgumentException("body doesnt consume time");
		
		return null;
	}
}
