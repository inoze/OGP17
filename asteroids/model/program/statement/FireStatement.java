package asteroids.model.program.statement;

import asteroids.model.program.Element;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class FireStatement extends Statement{
	private boolean consumesTime;

	public FireStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	public boolean consumesTime() {
		return this.consumesTime;
	}

	public void setConsumesTime(boolean b) {
		this.consumesTime = b;
	}

	@Override
	public void execute() throws Exception{
		if(getProgram() == null) throw new NullPointerException("(fs): program is null");
		try{
			setConsumesTime(true);
			getProgram().setSourceLocation(getSourceLocation());
			if (getProgram().getTime() <= 0.2) {
				setConsumesTime(false);
				return;
			}
			getProgram().getShip().fireBullet();
			getProgram().advanceTime();
		}catch(Exception ex){
			throw new Exception("Firestatement cant execute");
		}
	}
}
