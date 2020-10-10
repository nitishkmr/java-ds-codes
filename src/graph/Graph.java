package graph;

import java.util.HashMap;

/**
 * @author Nitish
 *
 *         09-Oct-2020
 */

public class Graph {

	public class Vertex {
		HashMap<String, Integer> nbrs = new HashMap<>();
	}

	HashMap<String, Vertex> vtces;

	public Graph() {
		vtces = new HashMap<>();
	}

	public int numVertex() {
		return this.vtces.size();
	}

	public boolean containsVertex(String vname) {
		return this.vtces.containsKey(vname);
	}

	public void addVertex(String vname) {
		// a new vertex added (0 neighbors)
		Vertex vtx = new Vertex();
		this.vtces.put(vname, vtx);
	}

	public int numEdges() {
		// total number of edges in the whole graph
		int count = 0;
		for (String key : vtces.keySet()) {
			Vertex v = vtces.get(key);
			count += v.nbrs.size();
		}
		return count / 2;
	}

	public boolean containsEdge(String vname1, String vname2) {

		Vertex vtx1 = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);
		// whenever using .get -> always think about its existence

		if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
			return false;
		}
		return true;
//		return vtx1.nbrs.containsKey(vname2);
	}

	public void addEdge(String vname1, String vname2, int cost) {
		Vertex vtx1 = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);
		// whenever using .get -> always think about its existence

		if (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vname2)) { // check if anyone is null or already an
			return; // edge exists
		}

		vtx1.nbrs.put(vname2, cost);
		vtx2.nbrs.put(vname1, cost);
	}

	public void removeEdge(String vname1, String vname2) {
		Vertex vtx1 = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);

		if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
			return;
		}

		vtx1.nbrs.remove(vname2);
		vtx2.nbrs.remove(vname1);
	}

	public void removeVertex(String vname) {
		Vertex v = vtces.get(vname);
		for (String neighbor : v.nbrs.keySet()) {
			Vertex neighborVtx = vtces.get(neighbor);
			neighborVtx.nbrs.remove(vname);
		}
		vtces.remove(vname);
	}

	public void display() {
		for (String key : this.vtces.keySet()) {
			Vertex vtx = this.vtces.get(key);
			System.out.println(key + " : " + vtx.nbrs);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Graph g1 = new Graph();
		
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
		System.out.println(g1.numEdges());
		System.out.println(g1.numVertex());
		
		System.out.println(g1.containsEdge("A", "B"));
		g1.removeEdge("A", "B");
		g1.display();
		
		g1.removeVertex("F");
		g1.display();
		
		g1.addVertex("F");
		g1.addEdge("A", "F", 2);
		g1.display();
	}

}
