package asteroids.model.program;

import asteroids.model.*;
import asteroids.part3.programs.*;

public class Element{
	protected Element(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	private SourceLocation sourceLocation;
	private Program program;
	
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
