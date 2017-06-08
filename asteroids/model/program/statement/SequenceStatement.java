package asteroids.model.program.statement;

import java.util.List;

import asteroids.model.Helper;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement extends Statement{

	private List<Statement> statements;
	private boolean consumesTime;
	private boolean hasBreak;
	
	public SequenceStatement(SourceLocation sourceLocation, List<Statement> statements) {
		super(sourceLocation);
		this.setStatements(statements);
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public boolean consumesTime() {
		return this.consumesTime;
	}

	public boolean hasBreak() {
		return this.hasBreak;
	}

	public void setHasBreak(boolean hasBreak) {
		this.hasBreak = hasBreak;
	}

	public void setConsumesTime(boolean b) {
		this.consumesTime = b;
	}

	public void setStatements(List<Statement> statements) {
		if(statements.size() == 0) throw new IllegalArgumentException("empty list given");
		this.statements = statements;
	}

	@Override
	public void execute() throws Exception {
		try{
		setConsumesTime(false);
		setHasBreak(false);
		SourceLocation curLocation = getProgram().getSourceLocation();
		if(getStatements().size() == 0) throw new Exception("empty list given");
		for(int i = 0; i < getStatements().size(); i++) {
			Statement statement = statements.get(i);
			SourceLocation nextLocation = null;
			if (i == statements.size()-1){
				nextLocation = null;
			}
			else nextLocation = statements.get(i+1).getSourceLocation();
			if(i == statements.size()-1 || nextLocation.getLine() > curLocation.getLine()||
					(nextLocation.getLine() == curLocation.getLine() && nextLocation.getColumn() > curLocation.getColumn())){
				statement.setProgram(this.getProgram());
				statement.setFunction(this.getFunction());
				Helper.log("SQS: Function: " + this.getFunction() + "; size: " + statements.size());
				try{
					Helper.log("executing statement " + statement.getClass().getName());
					statement.execute();
					Helper.log("Time left: " + this.getProgram().getTime());
					if(statement.consumesTime()){
						setConsumesTime(true);
					}
					if(statement.hasBreak()) {
						 setHasBreak(true);
						return;
					}
				}catch(Exception ex){
					Helper.log("cant execute statement in sequence: " + ex.getMessage());
					throw new Exception("(sqs) " + ex.getMessage());}
			}
		}
		
	}catch(Exception ex){
		throw new Exception("(s): " + ex.getMessage());
	}
	}
}
