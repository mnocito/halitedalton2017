import hlt.*;

import java.util.ArrayList;

public class MyBot {

    public static void main(final String[] args) {
        final Networking networking = new Networking();
        final GameMap gameMap = networking.initialize("Tamagocchi");

        // We now have 1 full minute to analyse the initial map.
        final String initialMapIntelligence =
                "width: " + gameMap.getWidth() +
                "; height: " + gameMap.getHeight() +
                "; players: " + gameMap.getAllPlayers().size() +
                "; planets: " + gameMap.getAllPlanets().size();
        Log.log(initialMapIntelligence);

        final ArrayList<Move> moveList = new ArrayList<>();
        for (;;) {
            moveList.clear();
            networking.updateMap(gameMap);

            for (final Ship ship : gameMap.getMyPlayer().getShips().values()) {
               

                for(final Ship enemyship: gameMap.getAllPlayers(1).getShips().values()){

                
                   

                    
                    if (!enemyship.isdocked()) {
                        continue;
                    }
                   

                    

                    final ThrustMove newThrustMove = Navigation.navigateShipTowardsTarget(gameMap, ship, enemyship.Position, Constants.MAX_SPEED/2, true, Double.PositiveInfinity,Math.PI/180.0);
                    if (newThrustMove != null) {
                        moveList.add(newThrustMove);
                    }

                    break;
            }
        }
        for(move in moveList){
            if(move==null){
                move = Navigation.navigateShipToShip(gameMap,gameMap.getAllPlayers(1).getShips.values(1));
            }
        }
            Networking.sendMoves(moveList);
        
        }
    }
}
