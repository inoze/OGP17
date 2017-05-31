package asteroids.model.program.statement;

import java.util.Optional;
import java.util.Set;

import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Statement;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class IfStatement extends  Element implements Statement{
	
	private Expression<? extends Boolean> condition;
	private Statement ifBody;
	private Statement elseBody;
	private boolean hasIf;
	private boolean hasElse;
	private boolean consumesTime;
	private boolean hasBreak;

	public IfStatement(SourceLocation sourceLocation, Expression condition, Statement ifBody, Statement elseBody) {
		super(sourceLocation);
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}


	@Override
	public boolean consumesTime() {
		return this.consumesTime;
	}

	@Override
	public boolean hasBreak() {
		return this.hasBreak;
	}
	
	public Expression<? extends Boolean> getCondition() {
		return this.condition;
	}

	public Statement getIfBody() {
		return this.ifBody;
	}

	public Statement getElseBody() {
		return this.elseBody;
	}

	public boolean isHasIf() {
		return this.hasIf;
	}

	public boolean isHasElse() {
		return this.hasElse;
	}

	public void setHasBreak(boolean hasBreak) {
		this.hasBreak = hasBreak;
	}

	public void setConsumesTime(boolean consumesTime) {
		this.consumesTime = consumesTime;
	}
	
	public void setHasElse(boolean hasElse) {
		this.hasElse = hasElse;
	}

	public void setHasIf(boolean hasIf) {
		this.hasIf = hasIf;
	}

	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}

	public void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
	}

	public void setCondition(Expression<? extends Boolean> condition) {
		this.condition = condition;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
