import hlt.*;

import java.util.ArrayList;

public class HartBot {

    public void run(GameMap gameMap, ArrayList<Move> moveList) {
      

        // We now have 1 full minute to analyse the initial map.
       

            for (final Ship ship : gameMap.getMyPlayer().getShips().values()) {
                if (ship.getDockingStatus() != Ship.DockingStatus.Undocked) {
                    continue;
                }
                for (final Planet planet : gameMap.getAllPlanets().values()) {
                    
                    if (planet.isOwned()) {
                        continue;
                    }
                    if (ship.canDock(planet) && !planet.isOwned()) {
                        moveList.add(new DockMove(ship, planet));
                        break;
                    }

                    final ThrustMove newThrustMove = Navigation.navigateShipToDock(gameMap, ship, planet, Constants.MAX_SPEED);
                    if (newThrustMove != null) {
                        moveList.add(newThrustMove);
                    }

                    break;
                }
            }
            return;
        
    }
}
