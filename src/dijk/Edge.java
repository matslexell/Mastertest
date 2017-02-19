package dijk;

public class Edge {
	Node node;
	int weight;
	
	public Edge(Node node, int weight){
		this.weight = weight;
		this.node = node;
	}
	
	public Node getNode(){
		return node;
	}
	public int getWeight(){
		return weight;
	}
}
