package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;


public abstract class Statement extends Element {

	protected Statement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

		//Break discovered in code
		public boolean hasBreak() {
			return false;
		}

		//Used time when running
		public boolean consumesTime(){
			return false;
		}
		
		public void execute() throws Exception{
			throw new Exception("Execute in abstract class has to be overridden");
		}
}