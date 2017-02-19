package dijk;

import java.util.LinkedList;
import java.util.List;

public class Test {
	public List<Node> findWay(Graph graph, String fromName, String toName){
		graph.setAllToUnvisited();
		
		LinkedList<Node> queue = new LinkedList<>();
		Node from = graph.get(fromName);

		
		queue.add(from);
		while(!queue.isEmpty()){
			Node r = queue.poll();
			
			for (Edge graphWeightedEdge : r) {
				
				queue.add(graphWeightedEdge.getNode());
			}
		}
			
		return null;
		
	}
	
	
	public static Graph getTestGraph1(){
		Graph g = new Graph();
		
		// a
		g.addConnection("a -> b,4");
		g.addConnection("a -> c,10");

		//b
		g.addConnection("b -> a,2");
		g.addConnection("b -> d,8");

		//c 
		g.addConnection("c -> f,11");
		
		//d
		g.addConnection("d -> e,7");

		//e
		g.addConnection("e -> g,2");
		g.addConnection("e -> h,6");

		//f
		g.addConnection("f -> g,4");

		//g
		g.addConnection("g -> e,2");
		g.addConnection("g -> i,5");
		g.addConnection("g -> f,3");

		//h
		g.addConnection("h -> i,4");

		//i
		g.addConnection("i -> h,3");
		return g;
	}
	
public static void main(String[] args) {
	Graph g =  getTestGraph1();
		
	System.out.println(g.toString());

}
}
