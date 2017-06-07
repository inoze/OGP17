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
	public Ship calculate() throws Exception {
		try{
			Helper.log("Program: " + getProgram());
			Ship ship = getProgram().getShip();
			Helper.log("Ship: " + ship);
			if (ship.getSuperWorld() == null) return null;
			ships = ship.getSuperWorld().getEntitiesOfTheClass("Ship");
			ships.remove(ship);
			Optional<? extends Entity> closestShip = ships.stream().reduce((s1, s2) -> (ship.getDistanceBetweenCenter(s1) < ship.getDistanceBetweenCenter(s2) ? s1 : s2));
			if (closestShip.isPresent()) return (Ship)closestShip.get();
			return null;
		}catch(Exception ex){
			throw new Exception("(se): " + ex.getMessage());
		}
	}

	@Override
	public Entity calculate(Object[] actualArgs, Set<Variable> localVars) throws Exception {
		return this.calculate();
	}

}
