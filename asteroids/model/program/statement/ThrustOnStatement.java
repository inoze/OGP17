package asteroids.model.program.statement;

import asteroids.model.Helper;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class ThrustOnStatement extends Statement {
	private boolean consumesTime;
	
	public ThrustOnStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	public boolean consumesTime() {
		return consumesTime;
	}

	public void setConsumesTime(boolean consumesTime) {
		this.consumesTime = consumesTime;
	}

	@Override
	public void execute() throws Exception{
		this.setConsumesTime(true);
		this.getProgram().setSourceLocation(getSourceLocation());
		Helper.log("executing thrustOnStatement");
		if (this.getProgram().getTime() < 0.2) {
			Helper.log("Not enough time left");
			this.setConsumesTime(false);
			return;
		}else{
		if(this.getFunction() == null){
			this.getProgram().advanceTime();	
			Helper.log("advanced time");
			this.getProgram().getShip().thrustOn();
		}else throw new Exception("Cant execute ActionStatement within function body");	
	}}
}
