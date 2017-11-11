
import java.awt.List;

import java.util.ArrayList;


import hlt.*;

public class BlitzBotProtocol {

	public void blitz(final GameMap game, ArrayList<Move> MoveList) {
		Player enemyplayer = null;
		for(Player player : game.getAllPlayers()){
			if(!player.equals(game.getMyPlayer())){
			enemyplayer = player;
				break;
			}
		}
		for(Ship enemyship : enemyplayer.getShips().values()) {
				if(enemyship.getDockingStatus()==Ship.DockingStatus.Docked) {
					for(Ship ship : game.getMyPlayer().getShips().values()) {
						final ThrustMove newThrustMove = Navigation.navigateShipTowardsTarget(game, ship, ship.getClosestPoint(enemyship), Constants.MAX_SPEED, true, Constants.MAX_NAVIGATION_CORRECTIONS, Math.PI/180.0);
					MoveList.add(newThrustMove);
					}
					return;
				}
			}
			for(Ship enemyship : enemyplayer.getShips().values()) {
				
				if(enemyship!=null) {
					for(Ship ship : game.getMyPlayer().getShips().values()) {
						final ThrustMove newThrustMove = Navigation.navigateShipTowardsTarget(game, ship, ship.getClosestPoint(enemyship), Constants.MAX_SPEED, true, Constants.MAX_NAVIGATION_CORRECTIONS, Math.PI/180.0);
					MoveList.add(newThrustMove);
					}
					return;
				}
					
					
				
			}
	
}
	
}
