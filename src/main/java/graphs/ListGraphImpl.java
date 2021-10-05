package graphs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ListGraphImpl<T> implements Graph<T>{

	private Map<Integer,Set<Integer>> adjList = null;
	private Map<Integer,T> elements = null;
	
	public ListGraphImpl() {
		adjList = new HashMap<>();
		elements = new HashMap<>();
	}
	
	@Override
	public void setVertex(int id, T element, Integer[] adjacentVertexes ) {
		elements.put(id, element);
		adjList.put(id, new HashSet<>());
		setEdges(id, adjacentVertexes);
	}
	
	@Override
	public void deleteVertex(int id) {
		elements.remove(id);
		deleteVertexFromTable(adjList, id);
	}
	
	private void deleteVertexFromTable(Map<Integer,Set<Integer>> table,int id) {
		table.remove(id);
		table.values().stream().forEach((n)->n.remove(id));
	}
	
	@Override
	public void setEdges(int id,Integer[] adjacentVertexes) {
		if(adjList.containsKey(id)) {
			adjList.get(id).addAll(Arrays.stream(adjacentVertexes).filter((n)->adjList.containsKey(n))
					.collect(Collectors.toList()));
			adjList.keySet().stream().filter((n)->!n.equals(id)).forEach((n)->{
				adjList.get(n).add(id);
			});
		}
		else {
			System.out.println("No such vertex id in list");
		}
	}
	
	@Override
	public void deleteEdges(int id, Integer[] adjacentVertexes) {
		if(adjList.containsKey(id)) {
			Set<Integer> l = adjList.get(id);
			Arrays.stream(adjacentVertexes).forEach((n)->{
				l.remove(n);
			});
		}
		else {
			System.out.println("No such vertex id in list");
		}
	}
	
	@Override
	public boolean isConnected() {
		return adjList.size()!=0?isConnected(new HashSet<>(),adjList.keySet().toArray(Integer[]::new)[0]):true;
	}
	
	private boolean isConnected(Set<Integer> checkedNodes, int nodeId) {
		checkedNodes.add(nodeId);
		for(Integer k:adjList.get(nodeId).stream().filter((n)->!checkedNodes.contains(n)).collect(Collectors.toList())) {
			if(isConnected(checkedNodes,k)) {
				break;
			}
		}
		return checkedNodes.size()==adjList.size();
	}

	@Override
	public int getDistance(int src, int dst) {
		Map<Integer,Integer> weights = new HashMap<>();
		weights.put(src, 0);
		adjList.keySet().stream().filter((n)->!n.equals(src)).forEach((n)->{
			weights.put(n, Integer.MAX_VALUE);
		});
		return getDistance(new HashMap<>(adjList), weights ,new HashSet<>(Arrays.stream(new Integer[] {src}).collect(Collectors.toList())),dst);
	}
	
	private int getDistance(Map<Integer,Set<Integer>> temp, Map<Integer,Integer> weights, Set<Integer> src, int dst) {
		src.stream().forEach((n)->{
			temp.get(n).stream().forEach((k)->{
				if(weights.get(n)+1<weights.get(k)) {
					weights.put(k, weights.get(n)+1);
				}
			});
			deleteVertexFromTable(temp, n);
		});	
		src.stream().forEach((n)->{
			src.addAll(temp.get(n));
			src.remove(n);
		});
		getDistance(temp,weights,src,dst);
		return weights.get(dst);
	}

}
