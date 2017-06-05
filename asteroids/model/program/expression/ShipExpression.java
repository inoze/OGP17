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

public class ShipExpression extends Element implements Expression<Entity> {

	private Set<? extends Entity> ships = new HashSet<Ship>();

	public ShipExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Ship calculate() {
		Ship ship = getProgram().getShip();
		if (ship.getSuperWorld() == null) return null;
		ships = ship.getSuperWorld().getEntitiesOfTheClass("Ship");
		Optional<? extends Entity> closestShip = ships.stream().reduce((s1, s2) -> (ship.getDistanceBetweenCenter(s1) < ship.getDistanceBetweenCenter(s2) ? s1 : s2));
		if (closestShip.isPresent()) return (Ship)closestShip.get();
		return null;
	}

	@Override
	public Entity calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.calculate();
	}

}
