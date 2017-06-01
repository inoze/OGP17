package asteroids.model.program.expression;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import asteroids.model.*;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.part3.programs.SourceLocation;

public class AsteroidExpression extends Element implements Expression {

	public Ship closestShip;
	private Set<? extends Entity> AsteroidsInWorld = new HashSet<>();


	public AsteroidExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Asteroid calculate() {
		Ship currentship = getProgram().getShip();
		if (currentship.getWorld() == null) return null;
		AsteroidsInWorld = currentship.getWorld().getEntitiesOfTheClass("Asteroid");
		Optional<? extends Entity> closestPlanet = AsteroidsInWorld.stream().
				reduce((planet1, planet2) -> (currentship.getDistanceBetween(planet1) < currentship.getDistanceBetween(planet2) ? planet1 : planet2));
		if (closestPlanet.isPresent()) return (Asteroid)closestPlanet.get();
		return null;
	}


	@Override
	public Object calculate(List actualArgs) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	


}
