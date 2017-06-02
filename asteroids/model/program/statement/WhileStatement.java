package asteroids.model.program.statement;

import java.util.Optional;
import java.util.Set;

import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Element implements Statement{

	public WhileStatement(SourceLocation sourceLocation, Expression<Boolean> condition, Statement body) {
		super(sourceLocation);
		this.setCondition(condition);
		this.setBody(body);
	}

	private Expression<Boolean> condition;
	private Statement body;
	private boolean executingBody;
	private boolean hasBreak;

	@Override
	public boolean consumesTime() {
		return false;
	}

	@Override
	public boolean hasBreak() {
		return this.hasBreak;
	}

	public Statement getBody() {
		return this.body;
	}

	public Expression<Boolean> getCondition() {
		return this.condition;
	}

	public boolean isExecutingBody() {
		return this.executingBody;
	}

	public void setExecutingBody(boolean executingBody) {
		this.executingBody = executingBody;
	}

	public void setCondition(Expression<Boolean> condition) {
		this.condition = condition;
	}

	public void setBody(Statement body) {
		this.body = body;
	}
	
	@Override
	public void execute() {
		
	}
}
