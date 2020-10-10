package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * @author Nitish
 *
 * 09-Oct-2020
 */

public class TraversalAndSearch {
	
	static class Pair{
		String vtxName;
		String pathSoFar = "";
		public Pair(String vtxString, String addedVtx) {
			this.vtxName = vtxString;
			pathSoFar += addedVtx;
		}
	}
	
	static Graph g1 = new Graph();
	
	static void bfs(String src) {
		Graph.Vertex srcVtx = g1.vtces.get(src);
		
		Queue<Graph.Vertex> queue = new LinkedList<Graph.Vertex>();
		HashMap<Graph.Vertex, Boolean> isVisited = new HashMap<>();
		isVisited.put(srcVtx, true);
		queue.add(srcVtx);
		
		while(!queue.isEmpty()) {
			Graph.Vertex tempVertex = queue.poll();
			
			for(String nbr : tempVertex.nbrs.keySet()) {
				Graph.Vertex nbrVtx = g1.vtces.get(nbr);
				if(!isVisited.containsKey(nbrVtx)) {
					isVisited.put(nbrVtx, true);
					System.out.print(nbr + " ");
					queue.add(nbrVtx);					
				}
			}
		}
	}
	
	
	static boolean bfsPath(String src, String dest) {
		Queue<Pair> queue = new LinkedList<Pair>();
		HashMap<String, Boolean> isVisited = new HashMap<>();
		
		Pair srcPair = new Pair(src, src);
		queue.add(srcPair);
		
		while(!queue.isEmpty()) {
			Pair currPair = queue.poll();
			
			isVisited.put(currPair.vtxName, true);
			
			//check for direct edge
			if(g1.containsEdge(currPair.vtxName, dest)) {
				System.out.println(currPair.pathSoFar + dest);
				return true;
			}
			
			Graph.Vertex tempVtx = g1.vtces.get(currPair.vtxName);
			
			for(String nbr : tempVtx.nbrs.keySet()) {
				if(!isVisited.containsKey(nbr)) {
					Pair newPair = new Pair(nbr, currPair.pathSoFar + nbr);
					queue.add(newPair);
				}
			}
		}
		return false;
	}
	
	
	static boolean dfsPath(String src, String dest) {
		Graph.Vertex srcVtx = g1.vtces.get(src);
		
		Stack<Pair> stack = new Stack<Pair>();
		HashMap<Graph.Vertex, Boolean> isVisited = new HashMap<>();
		isVisited.put(srcVtx, true);
		
		Pair srcPair = new Pair(src, "");
		stack.add(srcPair);
		
		while(!stack.isEmpty()) {
			Pair currPair = stack.pop();
			
			if(g1.containsEdge(currPair.vtxName, dest)) {
				System.out.println(currPair.pathSoFar + currPair.vtxName + dest);
				return true;
			}
			
			Graph.Vertex tempVtx = g1.vtces.get(currPair.vtxName);
			
			for(String nbr : tempVtx.nbrs.keySet()) {
				Graph.Vertex nbrVtx = g1.vtces.get(nbr);
				if(!isVisited.containsKey(nbrVtx)) {
//					System.out.println(nbr);
					isVisited.put(nbrVtx, true);
					stack.add(new Pair(nbr, currPair.pathSoFar + currPair.vtxName));					
				}
			}
		}
		return false;
	}
	
	
	static void bfsPathTraversal() {
		
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
					continue;
				}
				
				isVisited.put(currPair.vtxName, true);
				
				System.out.println(currPair.vtxName + " via " + currPair.pathSoFar);
				
				Graph.Vertex tempVtx = g1.vtces.get(currPair.vtxName);
				
				for(String nbr : tempVtx.nbrs.keySet()) {
					if(!isVisited.containsKey(nbr)) {
						Pair newPair = new Pair(nbr, currPair.pathSoFar + nbr);
						queue.add(newPair);
					}
				}
			}			
		}
	}
	
	static void dfsPathTraversal() {
		
		Stack<Pair> stack = new Stack<Pair>();
		HashMap<String, Boolean> isVisited = new HashMap<>();
		
		
		for(String allVertices : g1.vtces.keySet()) {	//this outer loop for checking for disconnected graphs
			
			if(isVisited.containsKey(allVertices)) {	//for ensuring that the below loops doesn't run for the vertices already dealt with
				continue;
			}
			
			Pair srcPair = new Pair(allVertices, allVertices);
			stack.add(srcPair);
			
			while(!stack.isEmpty()) {
				Pair currPair = stack.pop();
				
				if(isVisited.containsKey(currPair.vtxName)) {
					continue;
				}
				
				isVisited.put(currPair.vtxName, true);
				
				System.out.println(currPair.vtxName + " via " + currPair.pathSoFar);
				
				Graph.Vertex tempVtx = g1.vtces.get(currPair.vtxName);
				
				for(String nbr : tempVtx.nbrs.keySet()) {
					if(!isVisited.containsKey(nbr)) {
						Pair newPair = new Pair(nbr, currPair.pathSoFar + nbr);
						stack.add(newPair);
					}
				}
			}			
		}
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
//		g1.removeEdge("E", "D");
		g1.display();
//		bfs("D");
		System.out.println(bfsPath("A", "E"));
//		System.out.println(dfsPath("A", "F"));
		bfsPathTraversal(); System.out.println();
		dfsPathTraversal();
	}

}
