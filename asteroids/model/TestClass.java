package asteroids.model;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
 
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part3.facade.IFacade;
import asteroids.util.ModelException;
 
public class TestClass {
 
    private static final double EPSILON = 0.0001;
 
    IFacade facade;
 
    @Before
    public void setUp() {
        facade = new Facade();
    }
 
    @Test
    public void testCreateWorld() throws ModelException {
        World world = facade.createWorld(1000, 800);
        assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
        assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
        assertTrue(facade.getWorldShips(world).isEmpty());
        assertTrue(facade.getWorldBullets(world).isEmpty());
    }
    
    @Test 
    public void testCreateShipRadiusNan() throws ModelException {
      try {
    	  facade.createShip(100, 120, 10, 5, Double.NaN, 0, 1.0E20);
    	  fail();
      } 
      catch (ModelException exc) {
      }
    }
 
   
    @Test
    public void controleTerminateShip() throws ModelException {
        Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
        assertFalse(ship.isTerminated());
        ship.terminate(ship);
        assertTrue(ship.isTerminated());
    }
   
   
    @Test
    public void confirmShipAcceleration() throws ModelException {
        Ship ship1 = facade.createShip(100, 120, 10, 0, 11, Math.PI, 1.1E18 * 2);
        ship1.thrustOn();
        Ship ship = facade.createShip(100, 120, 10, 0, 11, Math.PI, (1.1E18));
        ship.thrustOn();
        double acceleration1 = ship1.getAcceleration();
        double acceleration2 = ship.getAcceleration();
        assertEquals(acceleration1, acceleration2 / 2, EPSILON);
    }
   
    
    @Test
    public void outOfBoundsControle() throws ModelException {
        World world = facade.createWorld(1000, 800);
        Ship ship1 = facade.createShip(1000, 800, 10, 0, 50, Math.PI, 1.1E18);
        Ship ship2 = facade.createShip(951, 120, 10, 0, 50, Math.PI, 1.1E18);
        Ship ship3 = facade.createShip(1300, 1220, 10, 0, 50, Math.PI, 1.1E18);
        Ship ship4 = facade.createShip(50, 50, 10, 0, 50, Math.PI, 1.1E18);
        ship1.addEntityToWorld(world);
        ship2.addShipToWorld(world);
        ship3.addShipToWorld(world);
        assertEquals(0, world.allobjects.size());
        ship4.addShipToWorld(world);
        assertEquals(1, world.allobjects.size());
    }
   
   
    @Test
    public void loadBulletFromWorld() throws ModelException {
        Ship ship1 = facade.createShip(100, 420, 10, 0, 50, 0, 1.1E18);
        World world = facade.createWorld(10000, 8000);
        Bullet bullet1 = facade.createBullet(600, 620, 50, 5, 50);
        world.addObjectToWorld(bullet1);
        try {
        	ship1.loadBulletOnShip(bullet1);
        	fail();
        }
        catch (IllegalArgumentException e) {
        }
    }
   
   
    @Test
    public void multipleWorlds() throws ModelException {
        Ship ship1 = facade.createShip(100, 420, 10, 0, 50, 0, 1.1E18);
        World world = facade.createWorld(1000, 800);
        World world2 = facade.createWorld(1000, 800);
        world.addObjectToWorld(ship1);
        try {
        	world2.addObjectToWorld(ship1);
        	fail();
        }
        catch (IllegalArgumentException e) {
        }
        
        assertFalse(world2.allobjects.contains(ship1));
    }
}