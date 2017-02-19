package dijk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dijk.Node.State;

public class Graph implements Iterable<Node> {
	private Map<String, Node> nodes = new HashMap<>();

	public Node get(String name) {
		name = name.replace(" ", "");
		if (nodes.get(name) == null) {
			throw new RuntimeException("No node with that name");
		}

		return nodes.get(name);
	}

	private Node getAndAddNode(String node){
		node = node.replace(" ", "");
		if (node.contains("->")) {
			throw new RuntimeException(
					"You added a connection (->), not possible for add");
		}
		
		Node graphNode = nodes.get(node);
		
		if(graphNode == null){
			graphNode = new Node(node);
			nodes.put(node, graphNode);
		}
		return graphNode;
		
	}
	
	public void addConnection(String...connection){
		for (String string : connection) {
			addConnectionSub(string);
		}
	}

	private void addConnectionSub(String connection) {
		connection = connection.replace(" ", "");
		if (!connection.contains(",")) {
			throw new RuntimeException("You forgot the weight (end parameter with \",weight\")");
		}
		if (!connection.contains("->")) {
			throw new RuntimeException("You forgot the connection (->)");
		}
		;
		
		String nodeWeight[] = connection.split(",");

		if (nodeWeight.length != 2) {
			throw new RuntimeException("Something wrong with the format, include comma for sepparating weight");
		}
		int weight = Integer.parseInt(nodeWeight[1]);
		
		String node[] = nodeWeight[0].split("->");

		if (node.length != 2) {
			throw new RuntimeException("Something wrong with the format");
		}
		

		Node left = getAndAddNode(node[0]);
		Node right = getAndAddNode(node[1]);

		left.add(right, weight);

	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Node graphNode : nodes.values()) {
			str.append(graphNode.toString() + "\n");
		}

		if (str.length() > 0) {
			str.delete(str.length() - 1, str.length());
		}
		return str.toString();
	}
	
	public void setAllToUnvisited(){
		for (Node node : nodes.values()) {
			node.setState(State.Unvisited);
		}
	}

	@Override
	public Iterator<Node> iterator() {
		return nodes.values().iterator();
	}
}
