package dijk;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dijk.Node.State;

public class Test {
	public Path dijkstras(Graph graph, String fromName, String toName) {
		graph.setAllToUnvisited();

		Map<Node, Path> visited = new HashMap<>();

		LinkedList<Path> queue = new LinkedList<>();

		int shortestPathToFin = Integer.MAX_VALUE;

		// Goals
		Node from = graph.get(fromName);
		Node fin = graph.get(toName);

		queue.add(new Path(from));

		while (!queue.isEmpty()) {
			Path pathCurrent = queue.poll();
			// Visit node
			pathCurrent.node.setState(State.Visited);
			visited.put(pathCurrent.node, pathCurrent);

			// Go through all neighbors
			for (Edge edge : pathCurrent.node) {
				Path pathNew = new Path(pathCurrent, edge.node, edge.weight);

				// If the new total path has exceeded the shortest path to
				// finish, no need to keep looking
				if (pathNew.getWeightToStart() > shortestPathToFin) {
					continue;
				}

				// If neighbor is already visited, check if current path was
				// quicker
				if (pathNew.node.getState() == State.Visited) {
					Path pathOfVisited = visited.get(pathNew.node);
					if (pathNew.getWeightToStart() < pathOfVisited
							.getWeightToStart()) {
						pathOfVisited.path = pathCurrent;
					}
					continue;
				}

				if (pathNew.node == fin) {
					shortestPathToFin = Math.min(pathNew.getWeightToStart(),
							shortestPathToFin);
				}

				queue.add(pathNew);
			}
		}

		return visited.get(fin);

	}

	public class Path {
		Node node;
		Path path;
		int edgeWeight;

		public Path(Node node) {
			this.path = null;
			this.node = node;
		}

		public Path(Path path, Node node, int weight) {
			this.path = path;
			this.node = node;
			this.edgeWeight = weight;
		}

		public int getWeightToStart() {
			if (path == null) {
				return 0;
			}

			return path.getWeightToStart() + edgeWeight;
		}

		public String toString() {
			if (path == null) {
				return node.getName();
			}

			return path.toString() + "->" + node.getName();
		}

	}

	public static Graph getTestGraph1() {
		Graph g = new Graph();

		// a
		g.addConnection("a -> b,4");
		g.addConnection("a -> c,10");

		// b
		g.addConnection("b -> a,2");
		g.addConnection("b -> d,8");

		// c
		g.addConnection("c -> f,11");

		// d
		g.addConnection("d -> e,7");

		// e
		g.addConnection("e -> g,2");
		g.addConnection("e -> h,6");

		// f
		g.addConnection("f -> g,4");

		// g
		g.addConnection("g -> e,2");
		g.addConnection("g -> i,5");
		g.addConnection("g -> f,3");

		// h
		g.addConnection("h -> i,4");

		// i
		g.addConnection("i -> h,3");
		return g;
	}

	public static void main(String[] args) {
		Graph g = getTestGraph1();
		Path p = new Test().dijkstras(g, "b", "f");

		System.out.println(p.toString());

	}
}
