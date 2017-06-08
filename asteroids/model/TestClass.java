package asteroids.model;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

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
    public void checkTerminateShip() throws ModelException {
        Ship ship = facade.createShip(60, 60, 30, 0, 50, Math.PI, 1.1E18);
        assertFalse(ship.isTerminated());
        ship.terminate();
        assertTrue(ship.isTerminated());
    }
   
   
    @Test
    public void checkShipAcceleration() throws ModelException {
        Ship ship1 = facade.createShip(250, 120, 10, 0, 11, Math.PI, 1E20 * 2);
        ship1.thrustOn();
        Ship ship = facade.createShip(250, 120, 10, 0, 11, Math.PI, (1E20));
        ship.thrustOn();
        double a1 = ship1.getAcceleration();
        double a2 = ship.getAcceleration();
        assertEquals(a1, a2 / 2, EPSILON);
    }
   
    
    @Test
    public void CheckEntityOutOfBoundSpawn() throws ModelException {
        World world = facade.createWorld(1000, 800);
        Ship ship1 = facade.createShip(1000, 800, 10, 0, 50, Math.PI, 1.1E18);
        Ship ship2 = facade.createShip(951, 120, 10, 0, 50, Math.PI, 1.1E18);
        Ship ship3 = facade.createShip(1300, 1220, 10, 0, 50, Math.PI, 1.1E18);
        Ship ship4 = facade.createShip(50, 50, 10, 0, 50, Math.PI, 1.1E18);
       try{
	        facade.addShipToWorld(world, ship1);
	        facade.addShipToWorld(world, ship2);
	        facade.addShipToWorld(world, ship3);
	        fail();
       	}catch(ModelException ex){}
    }
   
    @Test
    public void checkEntityMultipleWorlds() throws ModelException {
        Ship ship1 = facade.createShip(100, 420, 10, 0, 50, 0, 1.1E18);
        World world = facade.createWorld(1000, 800);
        World world2 = facade.createWorld(1000, 800);
        world.addEntityToWorld(ship1);
        try {
        	world2.addEntityToWorld(ship1);
        	fail();
        }
        catch (Exception e) {}
        assertFalse(world2.getEntities().contains(ship1));
    }
    
    @Test 
    public void testCreateShipInvalidRadius() throws ModelException {
      try {
    	  facade.createShip(100, 120, 10, 5, Double.NaN, 0, 1.0E20);
    	  fail();
      } 
      catch (ModelException exc) {
      }
    }
}