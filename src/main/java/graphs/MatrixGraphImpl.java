package graphs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MatrixGraphImpl<T> implements Graph<T>{

	private Map<Integer,Map<Integer,Boolean>> adjMatr = null;
	private Map<Integer,T> elements = null;
	
	public MatrixGraphImpl() {
		adjMatr = new HashMap<>();
		elements = new HashMap<>();
	}
	
	@Override
	public void setEdges(int id, Integer[] adjacentVertexes) {
		if(elements.containsKey(id)) {
			Arrays.stream(adjacentVertexes).filter((n)->elements.containsKey(n)).forEach((n)->{
				adjMatr.get(id).put(n, true);
				adjMatr.get(n).put(id, true);
			});
		}else {
			System.out.println("No such node id");
		}	
	}
	
	@Override
	public void deleteEdges(int id, Integer[] adjacentVertexes) {
		if(elements.containsKey(id)) {
			Arrays.stream(adjacentVertexes).filter((n)->elements.containsKey(n)).forEach((n)->{
				adjMatr.get(id).put(n, false);
				adjMatr.get(n).put(id, false);
			});
		}else {
			System.out.println("No such node id");
		}	
	}

	@Override
	public void deleteVertex(int id) {
		if(elements.containsKey(id)) {
			adjMatr.remove(id);
			adjMatr.values().stream().forEach((n)->{
				n.remove(id);
			});
		}else {
			System.out.println("No such node id");
		}	
	}

	@Override
	public void setVertex(int id, T element, Integer[] adjacentVertexes) {
		Map<Integer,Boolean> nodesConnections = new HashMap<>();
		elements.keySet().stream().forEach((n)->{
			nodesConnections.put(n, false);
		});
		elements.put(id, element);
		adjMatr.put(id, nodesConnections);
		adjMatr.values().stream().forEach((n)->{
			n.put(id, false);
		});
	}
	
	@Override
	public boolean isConnected() {
		return adjMatr.size()!=0?isConnected(new HashSet<>(),adjMatr.keySet().toArray(Integer[]::new)[0]):true;
	}

	private boolean isConnected(Set<Integer> checkedNodes, int nodeId) {
		checkedNodes.add(nodeId);
		for(Integer n:adjMatr.get(nodeId).keySet().stream().filter((n)-> (!checkedNodes.contains(n))&&(adjMatr.get(nodeId).get(n)) ).collect(Collectors.toList())) {
			if(isConnected(checkedNodes,n)) {
				break;
			}
		}
		return checkedNodes.size()==elements.size();
	}

	@Override
	public int getDistance(int src, int dst) {
		return asListGraph().getDistance(src, dst);
	}
	
	public ListGraphImpl<T> asListGraph(){
		ListGraphImpl<T> res= new ListGraphImpl<>();
		adjMatr.keySet().stream().forEach((n)->{
			res.setVertex(n, elements.get(n), (Integer[])adjMatr.get(n).keySet().stream().filter((k)->adjMatr.get(n).get(k)).toArray());
		});
		return res;
	}

}
