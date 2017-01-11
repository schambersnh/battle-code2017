package first;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

public class Archon extends Globals {

	public static void loop()  {

		while (true) {
			int startTurn = rc.getRoundNum();
			try {
				Globals.update();
			    turn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int endTurn = rc.getRoundNum();
			if (startTurn != endTurn) {
				System.out.println("OVER BYTECODE LIMIT");
			}
			Clock.yield();
		}

	}

	private static void turn() throws GameActionException {

		tryBuildGardener();
		
        // Move randomly
        Nav.tryMove(Nav.randomDirection());

        // Broadcast archon's location for other robots on the team to know
        MapLocation myLocation = rc.getLocation();
        rc.broadcast(0,(int)myLocation.x);
        rc.broadcast(1,(int)myLocation.y);

	}
	
	private static void tryBuildGardener() throws GameActionException
	{
        // Generate a random direction
        Direction dir = Nav.randomDirection();

        // Randomly attempt to build a gardener in this direction
        if (rc.canHireGardener(dir) && Math.random() < .01) {
            rc.hireGardener(dir);
        }
	}

}
