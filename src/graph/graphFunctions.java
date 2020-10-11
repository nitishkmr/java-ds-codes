package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import graph.TraversalAndSearch.Pair;

/**
 * @author Nitish
 *
 * 10-Oct-2020
 */

public class graphFunctions {
	
	static Graph g1 = new Graph();

	static boolean isCyclic() {
		
		Queue<Pair> queue = new LinkedList<Pair>();
		HashMap<String, Boolean> isVisited = new HashMap<>();
		
		
		for(String allVertices : g1.vtces.keySet()) {	//this outer loop for checking for disconnected graphs
			
			if(isVisited.containsKey(allVertices)) {	//for ensuring that the below loops doesn't run for the vertices already dealt with
				continue;
			}
			
			Pair srcPair = new Pair(allVertices, allVertices);
			queue.add(srcPair);
			
			while(!queue.isEmpty()) {
				Pair currPair = queue.poll();
				
				if(isVisited.containsKey(currPair.vtxName)) {
					return true;
				}
				
				isVisited.put(currPair.vtxName, true);
				
//				System.out.println(currPair.vtxName + " via " + currPair.pathSoFar);
				
				Graph.Vertex tempVtx = g1.vtces.get(currPair.vtxName);
				
				for(String nbr : tempVtx.nbrs.keySet()) {
					if(!isVisited.containsKey(nbr)) {
						Pair newPair = new Pair(nbr, currPair.pathSoFar + nbr);
						queue.add(newPair);
					}
				}
			}			
		}
		return false;
	}	
	
	static boolean isConnected() {
		
		Queue<Pair> queue = new LinkedList<Pair>();
		HashMap<String, Boolean> isVisited = new HashMap<>();
		int flag = 0;
		
		for(String allVertices : g1.vtces.keySet()) {	//this outer loop for checking for disconnected graphs
			
			if(isVisited.containsKey(allVertices)) {	//for ensuring that the below loops doesn't run for the vertices already dealt with
				continue;
			}
			
			flag++;	//denotes number of disconnected components
			
			Pair srcPair = new Pair(allVertices, allVertices);
			queue.add(srcPair);
			
			while(!queue.isEmpty()) {
				Pair currPair = queue.poll();
				
				if(isVisited.containsKey(currPair.vtxName)) {
					continue;
				}
				
				isVisited.put(currPair.vtxName, true);
				
//				System.out.println(currPair.vtxName + " via " + currPair.pathSoFar);
				
				Graph.Vertex tempVtx = g1.vtces.get(currPair.vtxName);
				
				for(String nbr : tempVtx.nbrs.keySet()) {
					if(!isVisited.containsKey(nbr)) {
						Pair newPair = new Pair(nbr, currPair.pathSoFar + nbr);
						queue.add(newPair);
					}
				}
			}			
		}
		return flag == 1 ? true:false;
	}
	
	
	public static void main(String[] args) {
		g1.addVertex("A");
		g1.addVertex("B");
		g1.addVertex("C");
		g1.addVertex("D");
		g1.addVertex("E");
		g1.addVertex("F");
		g1.addVertex("G");
		
		g1.addEdge("A", "B", 2);
		g1.addEdge("A", "D", 2);
		g1.addEdge("B", "C", 2);
		g1.addEdge("C", "D", 2);
		g1.addEdge("D", "E", 2);
		g1.addEdge("E", "F", 2);
		g1.addEdge("E", "G", 2);
		g1.addEdge("F", "G", 2);
		
		g1.display();
		System.out.println(isCyclic());
		System.out.println(isConnected());
		
	}

}
