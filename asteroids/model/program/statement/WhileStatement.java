package asteroids.model.program.statement;

import asteroids.model.Helper;
import asteroids.model.program.*;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement{

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
		this.getProgram().setHasWhile(true);
		try{
			this.getCondition().setProgram(this.getProgram());
			this.setConsumesTime(false);
			if(!(this.getCondition().calculate())){
				Helper.log("condition not fulfilled, not running loop");
				return;
			}
			
			Helper.log("body: " + this.getBody());
			try{
				getBody().setProgram(this.getProgram());
				getBody().execute();
				if(getBody().consumesTime()) this.setConsumesTime(true);
			}catch(Exception ex){
				Helper.log("error in while loop on statement: " + body.getClass().getName() + "; " + ex.getMessage());
				return;
			}
		
			while(this.getCondition().calculate() && !body.hasBreak()){
				getProgram().setSourceLocation(this.getSourceLocation());
				getBody().setProgram(this.getProgram());
				try{body.execute();
					if (body.consumesTime()){
						this.setConsumesTime(true);
					}
				}catch(Exception ex){
					Helper.log("error in while loop on statement: " + body.getClass().getName() + "; " + ex.getMessage());
					return;
				}
			}
		
			this.setExecutingBody(false);
		
		}catch(Exception ex){Helper.log("w error: " + ex.getMessage());return;}
	}
}
