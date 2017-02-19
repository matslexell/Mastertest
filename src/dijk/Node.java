package dijk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node implements Iterable<Edge>{
	private List<Edge> neigbours = new ArrayList<>();
	private State state;
	private String name;
	
	public Node(String name){
		this.name = name;
	}
	
	public enum State {
		Unvisited, Visited, Visiting; 
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State state){
		this.state = state;
	}
	
	public void add(Node node, int weight){
		// if(neigbours.contains(node)){throw new RuntimeException("Neighbours already connected: " + toString() + "->" + node.toString());}
		neigbours.add(new Edge(node, weight));
	}
	
	public int size(){
		return neigbours.size();
	}
	
	public Edge get(int index){
		return neigbours.get(index);
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (Edge graphNode : neigbours) {
			str.append(graphNode.getNode().name + " (" + graphNode.getWeight() + "), ");
		}
		if(!neigbours.isEmpty()){
			str.delete(str.length()-2, str.length());
		}
		str.append("]");
		
		return name + str.toString();
	}
	
	@Override
	public Iterator<Edge> iterator() {
		return neigbours.iterator();
	}
	
}
