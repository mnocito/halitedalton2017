import hlt.*;

import java.util.*;

public class HartBot {
	GameMap gameMap; ArrayList<Move> moveList;
	public HartBot(final GameMap gameMap, final ArrayList<Move> moveList) {
		this.gameMap=gameMap; this.moveList=moveList;
	}
	public void run() {


		// We now have 1 full minute to analyse the initial map.

		for (final Ship ship : gameMap.getMyPlayer().getShips().values()) {
			if (ship.getDockingStatus() != Ship.DockingStatus.Undocked) {
				continue;
			}
			

			Map<Integer, Planet> ebd = new TreeMap<>();
			ebd = nearbyEntitiesByDistance(gameMap,ship);
			for(Planet planet : ebd.values()) {
				if(planet.equals(null)) {
					continue;
				}
				if (planet.isOwned()) {
					continue;
				}
				else if (ship.canDock(planet) && !planet.isOwned()) {
					moveList.add(new DockMove(ship, planet));
					break;
				}
				else{
					final ThrustMove newThrustMove = Navigation.navigateShipToDock(gameMap, ship, planet, Constants.MAX_SPEED);
					if (newThrustMove != null) {
						moveList.add(newThrustMove);
						
					}
					break;
				}
			}

		}
		return;

	}


	public Map<Integer, Planet> nearbyEntitiesByDistance(final GameMap gameMap, final Entity entity) {
		final Map<Integer, Planet> map = gameMap.getAllPlanets();
		final Map<Integer, Planet> entityByDistance = new TreeMap<>();

		for (int i = 0; i < map.size(); i++) {
			int index = 0;
			
			for(int x = 0; x<map.size();x++){
				if (map.get(i).equals(map.get(x))) {
					continue;
				}
				else if(map.get(i).getDistanceTo(entity)>map.get(x).getDistanceTo(entity)){
					index++;
				}

			}


			entityByDistance.put(index,map.get(i));

		}
		return entityByDistance;
	}
	
	//get list of nearest ships
	public List<Ship> shipsbydistance (final GameMap gameMap, final Ship ship){
		ArrayList<Ship> shiplist = new ArrayList<Ship>();
		//add all ships to shiplist
		shiplist.addAll(gameMap.getAllShips());
		//final list by distance
		ArrayList<Ship> finalshiplist = new ArrayList<Ship>();
		for(int i =0; i <shiplist.size(); i++) {
			finalshiplist.add(null);
		}

		//go through each ship
		for (int i = 0; i < shiplist.size(); i++) {
			int index = 0;
			//order ship based on relation to other ships
			for(int x = 0; x<shiplist.size();x++){
				if (shiplist.get(i).equals(shiplist.get(x))||shiplist.get(i).equals(ship)) {
					continue;
				}
				else if(shiplist.get(i).getDistanceTo(ship)>shiplist.get(x).getDistanceTo(ship)){
					index++;
				}

			}

			//add ship
			finalshiplist.set(index,shiplist.get(i));

		}
		//return final list
		return finalshiplist;

	}
	
}
