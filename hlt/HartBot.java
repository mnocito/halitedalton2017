import hlt.*;

import java.util.*;

public class HartBot {

    public void run(GameMap gameMap, ArrayList<Move> moveList) {
      

        // We now have 1 full minute to analyse the initial map.

            for (final Ship ship : gameMap.getMyPlayer().getShips().values()) {
                if (ship.getDockingStatus() != Ship.DockingStatus.Undocked) {
                    continue;
                }
                
                	double mindist = Double.POSITIVE_INFINITY;
                
        Map<Integer, Planet> ebd = new TreeMap<>();
        ebd = nearbyEntitiesByDistance(gameMap,ship);
       
       	Planet nearest_planet = null;
       	int plannum = 0;
       	for (int i = 0;i<ebd.size();i++) {
       		if(mindist > ebd.get(i).getdistance())
       		{
       			mindist = ebd.get(i).getdistance();
       			plannum = i;
       		}
       		nearest_planet = ebd.get(plannum);
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
            return;
        
    }


public Map<Integer, Planet> nearbyEntitiesByDistance(final GameMap gameMap, final Entity entity) {
        final Map<Integer, Planet> entityByDistance = new TreeMap<>();
        int index = 0;
        Planet planet = null;
        for (int i = 0; i < gameMap.planets.size(); i++) {
        	planet = gameMap.planets.get(i);
        planet.setdistance(entity.getDistanceTo(planet));
            if (planet.equals(entity)) {
                continue;
            }
            index++;
            entityByDistance.put(index,planet);
        }
        return entityByDistance;
    }
}
