package asteroids.model.program.statement;

import java.util.Optional;
import java.util.Set;

import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Element implements Statement{

	public WhileStatement(SourceLocation sourceLocation, Expression<Boolean> condition, Statement body) {
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
	}

	private Expression<Boolean> condition;
	private Statement body;
	private boolean executingBody;
	private boolean failedToAdvanceTime;
	
	
	
	@Override
	public void execute() {
		
	}
}
