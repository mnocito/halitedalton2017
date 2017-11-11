
import java.awt.List;

import java.util.ArrayList;


import hlt.*;

public class BlitzBotProtocol {

	public void blitz(final GameMap game, ArrayList<Move> MoveList) {
		
		for(Ship ship : game.getMyPlayer().getShips().values()) {
			for(Ship enemyship : enemyplayer.getShips().values()) {
				if(enemyship.getDockingStatus()==Ship.DockingStatus.Undocked) {
					for (final Planet planet : game.getAllPlanets().values()) {



					if (planet.isOwned()) {
						continue;
					}


					if (ship.canDock(planet)) {
						MoveList.add(new DockMove(ship, planet));
						break;
					}

					final ThrustMove thrust = Navigation.navigateShipToDock(game, ship, planet, Constants.MAX_SPEED);
					if (thrust != null) {
						MoveList.add(thrust);

						break;
					}




				}
				break;
				}
				
				final ThrustMove newThrustMove = Navigation.navigateShipTowardsTarget(game, ship, ship.getClosestPoint(enemyship), Constants.MAX_SPEED, true, Constants.MAX_NAVIGATION_CORRECTIONS, Math.PI/180.0);
			
				if(newThrustMove!=null) {
					MoveList.add(newThrustMove);
					break;
				}
				
					for (final Planet planet : game.getAllPlanets().values()) {
		                  

		                 
		                 if (planet.isOwned()) {
		                     continue;
		                 }
		                

		                 if (ship.canDock(planet)) {
		                     MoveList.add(new DockMove(ship, planet));
		                     break;
		                 }

		                 final ThrustMove thrust = Navigation.navigateShipToDock(game, ship, planet, Constants.MAX_SPEED);
		                 if (thrust != null) {
		                     MoveList.add(thrust);
		                     
		                     break;
		                 }
		                 
		                 
		             
					
				}
				
			}


		}
		
		

return;
	



//return list of move orders


}
	
}
