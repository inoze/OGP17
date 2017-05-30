package asteroids.model.program;

import asteroids.model.*;
import asteroids.part3.programs.*;

public abstract class Element{
	private SourceLocation sourceLocation;
	private Program program;
	
	protected Element(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return program;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
}
