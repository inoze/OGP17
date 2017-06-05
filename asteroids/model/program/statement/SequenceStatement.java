package asteroids.model.program.statement;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import asteroids.model.program.Element;
import asteroids.model.program.Statement;
import asteroids.model.program.Variable;
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
		this.statements = statements;
	}

	@Override
	public void execute() throws Exception {
		try{
		setConsumesTime(false);
		setHasBreak(false);
		SourceLocation curLocation = getProgram().getSourceLocation();
		for(int i = 0; i < statements.size(); i++) {
			Statement statement = statements.get(i);
			SourceLocation nextLocation = null;
			if (i == statements.size()-1){
				nextLocation = null;
			}
			else nextLocation = statements.get(i+1).getSourceLocation();
			if(i == statements.size()-1 || nextLocation.getLine() > curLocation.getLine()||
					(nextLocation.getLine() == curLocation.getLine() && nextLocation.getColumn() > curLocation.getColumn())){
				statement.execute();
				if(statement.consumesTime()){
					setConsumesTime(true);
					return;
				}
				if(statement.hasBreak()) {
					 setHasBreak(true);
					return;
				}
			}
		}
		
	}catch(Exception ex){
		throw new Exception("sequenceStatement doesnt work");
	}
	}
}
