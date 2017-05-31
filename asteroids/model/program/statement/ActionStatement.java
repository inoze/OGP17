package asteroids.model.program.statement;

import java.util.Optional;
import java.util.Set;

import asteroids.model.program.Element;
import asteroids.model.program.Statement;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class ActionStatement extends Element implements Statement{

	protected ActionStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasBreak() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean consumesTime() {
		// TODO Auto-generated method stub
		return false;
	}

}
