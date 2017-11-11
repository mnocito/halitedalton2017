package halite2;
import java.util.ArrayList;

import halite2.hlt.*;
public class BasicCombatStrats extends MyBot{


	public void groupskirmish(ArrayList<Ship> shiplist, ArrayList<Ship> enemyships, GameMap gameMap, ArrayList<Move> MoveList) {
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
		Ship nearestenemy = null;
		for(Ship ship : enemyships) {
			if(ship.getDistanceTo(midship)<nearestenemy.getDistanceTo(midship)||nearestenemy == null) {
				nearestenemy=ship;
			}
		}
		final ThrustMove newthrust = Navigation.navigateShipTowardsTarget(gameMap, midship, nearestenemy, Constants.MAX_SPEED, true, (int) Double.POSITIVE_INFINITY, Math.PI/180);
		if(newthrust!=null) {
			MoveList.add(newthrust);
		}
		for(Ship ship : shiplist) {
			if(!ship.equals(midship)) {
				final ThrustMove thrust = Navigation.navigateShipTowardsTarget(gameMap, ship, midship, Constants.MAX_SPEED, true, (int) Double.POSITIVE_INFINITY, Math.PI/180);
				if(thrust !=null) {
					MoveList.add(thrust);
				}
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



		//placeholders for health of enemyships and health of nearest enemy
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
		//if it looks bad for the ship then kamikaze
		if(healthofshipsinrange>ship.getHealth()) {
			final ThrustMove newthrustmove = Navigation.navigateShipTowardsTarget(gameMap, ship, nearestenemy.getClosestPoint(nearestenemy), Constants.MAX_SPEED, false, 0, Math.PI/180.0);
			if(newthrustmove!=null) {
				moveList.add(newthrustmove);
			}
		}


	}




}
