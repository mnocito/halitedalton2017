package halite2;
import java.util.ArrayList;

import halite2.hlt.*;
public class BasicCombatStrats extends MyBot{


	public void skirmish(ArrayList<Ship> shiplist, ArrayList<Ship> enemyships, GameMap gameMap) {
		//get average position of ships
		double averagepositionx = 0;
		double averagepositiony = 0;
		Ship midship = null;
		for(Ship ship : shiplist) {
			averagepositionx+=ship.getClosestPoint(ship).getXPos();
			averagepositiony+=ship.getClosestPoint(ship).getYPos();
		}
		double midpointx = averagepositionx/=shiplist.size();
		double midpointy = averagepositiony/=shiplist.size();	
		//midpoint of ships
		Position midpoint = new Position(midpointx, midpointy);
		//find middleship
		for(Ship ship: shiplist) {
			if((ship!=null&&ship.getDistanceTo(ship.getClosestPoint(ship))<midship.getDistanceTo(midpoint))||midship==null) {
				midship = ship;
			}
		}

		//work in progress

	}
	//great song
	//more importantly this has our ships suicide ram the enemy if they have lower health
	//remaining implementation: 
	public void suicideispainless(Ship ship, GameMap gameMap, ArrayList<Move> moveList) {
		//get enemy player
		Player enemyplayer = null;
		for(Player player : gameMap.getAllPlayers()) {
			if(!player.equals(gameMap.getMyPlayer())) {
				enemyplayer = player;
				break;
			}
		}




		int healthofshipsinrange = 0;
		Ship nearestenemy = null;

		for(Ship enemyship : enemyplayer.getShips().values()) {
			if(Math.abs(enemyship.getXPos()-ship.getXPos())+Math.abs(enemyship.getYPos()-ship.getYPos())<5) {
				healthofshipsinrange+=enemyship.getHealth();
				if(enemyship.getDistanceTo(ship.getClosestPoint(ship))<nearestenemy.getDistanceTo(ship.getClosestPoint(ship))||nearestenemy==null) {
					nearestenemy = enemyship;
				}
			}
		}

		if(healthofshipsinrange>ship.getHealth()) {
			final ThrustMove newthrustmove = Navigation.navigateShipTowardsTarget(gameMap, ship, nearestenemy.getClosestPoint(nearestenemy), Constants.MAX_SPEED, false, 0, Math.PI/180.0);
			if(newthrustmove!=null) {
				moveList.add(newthrustmove);
			}
		}


	}




}
