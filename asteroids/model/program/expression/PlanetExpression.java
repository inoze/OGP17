package asteroids.model.program.expression;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import asteroids.model.*;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class PlanetExpression extends Element implements Expression<Entity>{

	public PlanetExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Entity calculate() {
		Ship ship = getProgram().getShip();
		if (ship.getSuperWorld() == null) return null;
		Set<Entity> planets = new HashSet<Entity>();
		planets.addAll(ship.getSuperWorld().getEntitiesOfTheClass("Planetoid"));
		planets.addAll(ship.getSuperWorld().getEntitiesOfTheClass("Asteroid"));
		Optional<? extends Entity> closestPlanet = planets.stream().reduce((p1, p2) -> (ship.getDistanceBetweenCenter(p1) < ship.getDistanceBetweenCenter(p2) ? p1 : p2));
		if (closestPlanet.isPresent()) return (Entity)closestPlanet.get();
		return null;
	}

	@Override
	public Entity calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.calculate();
	}

}
