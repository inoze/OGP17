package asteroids.model.program.statement;

import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class ThrustOffStatement extends Statement {
	private boolean consumesTime;
	
	public ThrustOffStatement(SourceLocation sourceLocation) {
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
		if (this.getProgram().getTime() < 0.2) {
			this.setConsumesTime(false);
			return;
			
		}
		if(this.getFunction() == null){
			this.getProgram().getShip().thrustOff();
		}else throw new Exception("Cant execute ActionStatement within function body");
		this.getProgram().advanceTime();		
	}

}
