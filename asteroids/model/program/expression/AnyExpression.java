package asteroids.model.program.expression;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import asteroids.model.*;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class AnyExpression extends Element implements Expression<Entity>{

	public AnyExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Entity calculate() {
		Ship ship = getProgram().getShip();
		if (ship.getSuperWorld() == null) return null;
		Set<Entity> entities = new HashSet<Entity>();
		entities.addAll(ship.getSuperWorld().getEntities());
		Optional<Entity> entity = entities.stream().findAny();
		if (entity.isPresent()) return entity.get();
		return null;
		
	}

	@Override
	public Entity calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.calculate();
	}


}
