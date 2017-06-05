package asteroids.model.program.statement;

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
	private boolean consumesTime;

	@Override
	public boolean consumesTime() {
		return this.consumesTime;
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

	public void setConsumesTime(boolean b){
		this.consumesTime = b;
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
	public void execute() throws Exception {
		try{
			this.setConsumesTime(false);
			//Look if condition is true to start running the body, if not, do nothing
			if(!this.isExecutingBody()){
				if(this.getCondition().calculate()) this.setExecutingBody(true);
				else return;
			}
			//Load body
			body.execute();
			if (body.consumesTime()){
				this.setConsumesTime(true);
				return;
			}
			//Check if we can run again
			while(this.getCondition().calculate() && !body.hasBreak()){
				getProgram().setSourceLocation(this.getSourceLocation());
				body.execute();
				if (body.consumesTime()){
					this.setConsumesTime(true);
					return;
				}
			}
			this.setExecutingBody(false);
		}catch(Exception ex){
			throw new Exception("whileStatement doesnt work");
		}
	}
}
