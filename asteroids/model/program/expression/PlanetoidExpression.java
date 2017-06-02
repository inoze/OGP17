package asteroids.model.program.expression;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import asteroids.model.*;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class PlanetoidExpression extends Element implements Expression<Entity> {

	private Set<? extends Entity> planetoids = new HashSet<Planetoid>();


	public PlanetoidExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Planetoid calculate() {
		Ship ship = getProgram().getShip();
		if (ship.getWorld() == null) return null;
		planetoids = ship.getWorld().getEntitiesOfTheClass("Planetoid");
		Optional<? extends Entity> closestPlanetoid = planetoids.stream().reduce((p1, p2) -> (ship.getDistanceBetweenCenter(p1) < ship.getDistanceBetweenCenter(p2) ? p1 : p2));
		if (closestPlanetoid.isPresent()) return (Planetoid)closestPlanetoid.get();
		return null;
	}

	@Override
	public Entity calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.calculate();
	}

}
