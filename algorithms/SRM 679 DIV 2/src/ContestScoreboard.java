import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class ContestScoreboard {
	
	public int[] findWinner(String[] scores) {
		int[] result = new int[scores.length];
		List<ScoreTime> scoreTimes = new ArrayList<>();
		for(int i=0;i<scores.length;i++) {
			String[] parts = scores[i].split(" ");
			for(int j=1;j<parts.length;j++) {
				String[] subParts = parts[j].split("/");
				int score = Integer.parseInt(subParts[0]);
				int time = Integer.parseInt(subParts[1]);
				ScoreTime st = new ScoreTime(i,score,time,scores[0]);
				scoreTimes.add(st);
			}
		}

		int time = 1;
		for(ScoreTime st : scoreTimes) {

		}
		return result;
	}

	class ScoreTime implements Comparable<ScoreTime>{
		String teamName;
		int teamIndex;
		int score;
		int time;
		public ScoreTime(int teamIndex,int score,int time,String teamName) {
			this.teamName = teamName;
			this.teamIndex = teamIndex;
			this.score = score;
			this.time = time;
		}

		@Override
		public int compareTo(ScoreTime other) {
			if(this.time == other.time)
				return this.teamName.compareTo(other.teamName);
			return other.time-this.time;
		}
	}
}
