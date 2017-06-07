package asteroids.model.program.expression;

import asteroids.model.Entity;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public abstract class EntityExpression extends Element implements Expression<Double>{

	private Expression<Entity> entity;

	protected EntityExpression(SourceLocation sourceLocation, Expression<Entity> entity) {
		super(sourceLocation);
		this.setEntity(entity);
	}

	public Expression<Entity> getEntity() {
		return this.entity;
	}

	public void setEntity(Expression<Entity> entity) {
		if(!(entity instanceof Entity)) this.entity = null;//throw new IllegalArgumentException("entity not a subclass of Entity");
		this.entity = entity;
	}


}
