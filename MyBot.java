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
        BlitzBotProtocol rush = new BlitzBotProtocol();
        HartBot hart = new HartBot();
        for (;;) {
            moveList.clear();
            networking.updateMap(gameMap);
        if(gameMap.getAllPlayers().size()==2){
        
            rush.blitz(gameMap, moveList);
        }
            else{
           
                hart.run(gameMap,moveList);
            }
            
            Networking.sendMoves(moveList);
        
        }
    }
}
