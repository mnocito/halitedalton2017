public class timetoleave{
	int turncount;
	boolean timetoleave;

	public static boolean gettimetoleave(){
		return timetoleave;
	}
	public static void settimetoleave(){
		if(timetoleave==null){
			timetoleave = false;
		}
		else{
			if(turncount>6){
				timetoleave = true;
			}
		}
		turncount++;
	}
}