
import java.awt.List;

import java.util.ArrayList;


import hlt.*;

public class BlitzBotProtocol {
	final Networking net; final GameMap game; ArrayList <Move> MoveList = new ArrayList();
	Player enemyplayer = null;
	public BlitzBotProtocol(GameMap game, Networking net, ArrayList <Move> moveList ) {
		this.game = game; this.net = net; this.MoveList = moveList;

		for(Player player : game.getAllPlayers()) {
			if(!game.getMyPlayer().equals(player)) {
				enemyplayer = player;
				break;
			}
		}


	}

	public ArrayList<Move> blitz() {
		//this code somehow magically breaks the result
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

					final ThrustMove thrust = Navigation.navigateShipToDock(game, ship, planet, Constants.MAX_SPEED/2);
					if (thrust != null) {
						MoveList.add(thrust);

						break;
					}




				}
				break;
				}
				
				final ThrustMove newThrustMove = Navigation.navigateShipTowardsTarget(game, ship, ship.getClosestPoint(enemyship), Constants.MAX_SPEED/2, true, Constants.MAX_NAVIGATION_CORRECTIONS, Math.PI/180.0);
			
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

		                 final ThrustMove thrust = Navigation.navigateShipToDock(game, ship, planet, Constants.MAX_SPEED/2);
		                 if (thrust != null) {
		                     MoveList.add(thrust);
		                     
		                     break;
		                 }
		                 
		                 
		             
					
				}
				
			}


		}
		
		

return MoveList;
	



//return list of move orders


}
	
}
