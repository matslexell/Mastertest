package dijk;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dijk.Node.State;

public class Test {
	public List<Node> findWay(Graph graph, String fromName, String toName) {
		graph.setAllToUnvisited();

		LinkedList<Path> queue = new LinkedList<>();
		Map<Node, Path> visited = new HashMap<>();
		Node from = graph.get(fromName);
		Node to = graph.get(toName);

		queue.add(new Path(from));

		while (!queue.isEmpty()) {
			Path r = queue.poll();

			r.node.setState(State.Visited);
			visited.put(r.node, r);
			for (Edge edge : r.node) {
				if (edge.node.getState() == State.Visited) {
					Path path = visited.get(edge.node);
					int w1 = r.getWeightToStart() + edge.weight;
					int w2 = path.getWeightToStart();
					if (w1 < w2) {
						path.path = r;
					}
					continue;
				}

				Path path = new Path(r, edge.node, edge.weight);
				queue.add(path);
				if (edge.node == to) {
					System.out.println("Found! " + path + ", "
							+ path.getWeightToStart());
				}
			}
		}

		return null;

	}

	public class Path {
		Node node;
		Path path;
		int weight;

		public Path(Node node) {
			this.path = null;
			this.node = node;
		}

		public Path(Path path, Node node, int weight) {
			this.path = path;
			this.node = node;
			this.weight = weight;
		}

		public int getWeightToStart() {
			if (path == null) {
				return 0;
			}

			return path.getWeightToStart() + weight;
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
		new Test().findWay(g, "b", "f");

		System.out.println(g.toString());

	}
}
