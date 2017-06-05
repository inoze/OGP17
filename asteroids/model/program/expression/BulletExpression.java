package asteroids.model.program.expression;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.program.Element;
import asteroids.model.program.Expression;
import asteroids.model.program.Variable;
import asteroids.part3.programs.SourceLocation;

public class BulletExpression extends Element implements Expression<Entity> {
	
	private Set<? extends Entity> bullets = new HashSet<Bullet>();
	
	public BulletExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public Bullet calculate() {
		Ship ship = getProgram().getShip();
		if (ship.getSuperWorld() == null) return null;
		bullets = ship.getSuperWorld().getEntitiesOfTheClass("Bullet");
		Optional<? extends Entity> closestBullet = bullets.stream().reduce((p1, p2) -> (ship.getDistanceBetweenCenter(p1) < ship.getDistanceBetweenCenter(p2) ? p1 : p2));
		if (closestBullet.isPresent()) return (Bullet)closestBullet.get();
		return null;
	}

	@Override
	public Entity calculate(Object[] actualArgs, Set<Variable> localVars) throws IllegalArgumentException {
		return this.calculate();
	}

}
