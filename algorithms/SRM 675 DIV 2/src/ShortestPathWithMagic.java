import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class ShortestPathWithMagic {
	
	public double getTime(String[] dist, int k) {
		int[][] adj = new int[dist.length][dist.length];
		for(int i =0;i<dist.length;i++) {
			for(int j=0;j<dist.length;j++) {
				adj[i][j] = Integer.parseInt(dist[i].charAt(j)+"");
			}
		}
		boolean[] visited = new boolean[dist.length];
		visited[0] = true;
		double minDist = Double.MAX_VALUE;
		TreeSet<Integer> bestK = new TreeSet<>();
		minDist = findBestPath(adj,0,1,0,visited,bestK,k,minDist);
		return minDist;
	}

	public double findBestPath(int[][] adj,int curr,int goal,int pathLength,boolean[] visited,TreeSet<Integer> bestK,int k,double minDist) {
		double localMin = -1;
		for(int i=0;i<visited.length;i++) {
			TreeSet<Integer> bestKLocal = (TreeSet<Integer>)bestK.clone();
			if (i==curr)
				continue;
			if(visited[i])
				continue;
			int distToI = pathLength+adj[curr][i];
			visited[i] = true;
			if (k!=0) {
				if (bestKLocal.size() < k)
					bestKLocal.add(adj[curr][i]);
				else {
					int smallest = bestKLocal.pollFirst();
					if (smallest < adj[curr][i]) {
						bestKLocal.add(adj[curr][i]);
					} else {
						bestKLocal.add(smallest);
					}
				}
			}
			if(i==goal) {
				double reducedPathLength = reduce(distToI,bestK);
				if (reducedPathLength < minDist)
					minDist = reducedPathLength;
				if (localMin == -1)
					localMin = reducedPathLength;
				else if (reducedPathLength < localMin)
					localMin = reducedPathLength;
			} else if (distToI < minDist){
				//dist to goal through i.
				double localTemp = findBestPath(adj,i,goal,distToI,visited,bestK,k,minDist);

				if (localTemp != -1 && localMin == -1)
					localMin = localTemp;
				else if (localTemp != -1 && localTemp < localMin) {
					localMin = localTemp;
					if (localTemp < minDist)
						minDist = localTemp;
				}
			}
		}
		return localMin;
	}

	public double reduce(int pathLength,Set<Integer> bestK) {
		int sum = 0;
		for(int k : bestK) {
			sum += k;
		}
		return pathLength - (double)sum/2;
	}



}
