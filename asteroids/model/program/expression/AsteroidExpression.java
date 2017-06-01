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

public class AsteroidExpression extends Element implements Expression<Entity> {

	public Ship closestShip;
	private Set<? extends Entity> asteroids = new HashSet<Asteroid>();


	public AsteroidExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Asteroid calculate() {
		Ship ship = getProgram().getShip();
		if (ship.getWorld() == null) return null;
		asteroids = ship.getWorld().getEntitiesOfTheClass("Asteroid");
		Optional<? extends Entity> closestAsteroid = asteroids.stream().reduce((a1, a2) -> (ship.getDistanceBetween(a1) < ship.getDistanceBetween(a2) ? a1 : a2));
		if (closestAsteroid.isPresent()) return (Asteroid)closestAsteroid.get();
		return null;
	}

	@Override
	public Entity calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.calculate();
	}
	


}
