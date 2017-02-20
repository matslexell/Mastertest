package dijk;

import java.util.LinkedList;
import java.util.List;

import dijk.Node.State;

public class Test {
	public List<Node> findWay(Graph graph, String fromName, String toName){
		graph.setAllToUnvisited();
		
		LinkedList<Path> queue = new LinkedList<>();
		Node from = graph.get(fromName);
		Node to = graph.get(toName);
		
		
		queue.add(new Path(from));
		while(!queue.isEmpty()){
			Path r = queue.poll();
			
			r.node.setState(State.Visited);
			for (Edge edge : r.node) {
				if(edge.node.getState() == State.Visited){
					continue;
				}
				Path path = new Path(r, edge.node);
				queue.add(path);
				if(edge.node == to){
					System.out.println("Found! " + path.path);
				}
				
			}
		}
			
		return null;
		
	}
	
	public class Path {
		Node node;
		String path;
		public Path(Node node){
			this.path = node.getName();
			this.node = node;
		}
		
		public Path(Path path, Node node){
			this.path = path.path + "->" + node.getName();
			this.node = node;
		}
		
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
	new Test().findWay(g, "b", "f");
		
	System.out.println(g.toString());

}
}
