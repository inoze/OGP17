package asteroids.model.program.statement;

import asteroids.model.program.Element;
import asteroids.model.program.Statement;
import asteroids.part3.programs.SourceLocation;

public class SkipStatement extends Element implements Statement {

	public SkipStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean consumesTime() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBreak() {
		// TODO Auto-generated method stub
		return false;
	}

}
