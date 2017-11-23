import java.util.ArrayList;


import hlt.*;

public class BlitzBotProtocol {
	GameMap game; ArrayList<Move> moveList;
	Player enemyplayer = null;
	public BlitzBotProtocol(final GameMap game, final ArrayList<Move> moveList) {
		this.game = game; this.moveList=moveList;

		
		

	}

	public void blitz() {

		for(Player player : game.getAllPlayers()) {
			if(!game.getMyPlayer().equals(player)) {
				enemyplayer = player;
				break;
			}
		}
		
			for(Ship enemyship : enemyplayer.getShips().values()) {
				if(enemyship.getDockingStatus()==Ship.DockingStatus.Docked) {
					for(Ship ship : game.getMyPlayer().getShips().values()) {
						final ThrustMove newThrustMove = Navigation.navigateShipTowardsTarget(game, ship, ship.getClosestPoint(enemyship), Constants.MAX_SPEED, true, Constants.MAX_NAVIGATION_CORRECTIONS, Math.PI/180.0);
					if(newThrustMove!=null) {
						moveList.add(newThrustMove);
					}
					}
					return;
				}
			}
			for(Ship enemyship : enemyplayer.getShips().values()) {
				
				if(enemyship!=null) {
					for(Ship ship : game.getMyPlayer().getShips().values()) {
						final ThrustMove newThrustMove = Navigation.navigateShipTowardsTarget(game, ship, ship.getClosestPoint(enemyship), Constants.MAX_SPEED, true, Constants.MAX_NAVIGATION_CORRECTIONS, Math.PI/180.0);
					if(newThrustMove!=null) {
						moveList.add(newThrustMove);
					}
					}
					return;
				}
					
					
				
			}


		
		
		


	





}
	
	
}
