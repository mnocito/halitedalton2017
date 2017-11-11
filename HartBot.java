import hlt.*;

import java.util.*;

public class HartBot {

    public void run(GameMap gameMap, ArrayList<Move> moveList) {
      

        // We now have 1 full minute to analyse the initial map.

            for (final Ship ship : gameMap.getMyPlayer().getShips().values()) {
                if (ship.getDockingStatus() != Ship.DockingStatus.Undocked) {
                    continue;
                }
                for (final Planet planet : gameMap.getAllPlanets().values()) {
                	Double mindist = 100000.0;
                	int j = 0;
        Map<Entity, Double> ebd = new TreeMap<>();
        ebd = nearbyEntitiesByDistance(ship);
       	Planet nearest_planet = null;
       	int plannum = 0;
       	for (int i = 0;i<ebd.size();i++) {
       		if(mindist > ebd.get(i))
       		{
       			mindist = ebd.get(i);
       			plannum = i;
       		}
       		nearest_planet = ebd.keySet(plannum);
       	}       		


                    
                    if (nearest_planet.isOwned()) {
                        continue;
                    }
                    if (ship.canDock(nearest_planet) && !nearest_planet.isOwned()) {
                        moveList.add(new DockMove(ship, nearest_planet));
                        break;
                    }

                    final ThrustMove newThrustMove = Navigation.navigateShipToDock(gameMap, ship, nearest_planet, Constants.MAX_SPEED);
                    if (newThrustMove != null) {
                        moveList.add(newThrustMove);
                    }

                    break;
                }
            }
            return;
        
    }


public Map<Double, Entity> nearbyEntitiesByDistance(final Entity entity) {
        final Map<Entity, Double> entityByDistance = new TreeMap<>();

        for (final Planet planet : planets.values()) {
            if (planet.equals(entity)) {
                continue;
            }
            entityByDistance.put(planet,entity.getDistanceTo(planet));
        }
        return entityByDistance;
    }
}
