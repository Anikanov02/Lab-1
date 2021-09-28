package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListGraphImpl<T> implements Graph<T>{

	private Map<Integer,List<Integer>> adjList = null;
	private Map<Integer,T> elements = null;
	
	public ListGraphImpl() {
		adjList = new HashMap<>();
		elements = new HashMap<>();
	}
	
	@Override
	public void setVertex(int id, T element, Integer[] adjacentVertexes ) {
		elements.put(id, element);
		adjList.put(id, new ArrayList<>());
		setEdges(id, adjacentVertexes);
	}
	
	@Override
	public void deleteVertex(int id) {
		elements.remove(id);
		deleteVertexFromTable(adjList, id);
	}
	
	private void deleteVertexFromTable(Map<Integer,List<Integer>> table,int id) {
		table.remove(id);
		table.values().stream().forEach((n)->{
			n.removeIf((k)->k.equals(id));
		});
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
			List<Integer> l = adjList.get(id);
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
		return adjList.size()!=0?isConnected(new ArrayList<>(),adjList.keySet().toArray(Integer[]::new)[0]):true;
	}
	
	private boolean isConnected(List<Integer> checkedNodes, int nodeId) {
		checkedNodes.add(nodeId);
		adjList.get(nodeId).stream().distinct().filter((n)->!checkedNodes.contains(n)).forEach((k)->{
			if(isConnected(checkedNodes,k)) {
				return;
			}
		});
		return checkedNodes.size()==adjList.size();
	}

	@Override
	public int getDistance(int src, int dst) {
		return getDistance(new HashMap<>(adjList), new HashMap<>(),new ArrayList<>(Arrays.stream(new Integer[] {src}).collect(Collectors.toList())),dst);
	}
	
	private int getDistance(Map<Integer,List<Integer>> temp, Map<Integer,Integer> weights, List<Integer> src, int dst) {
		src.stream().forEach((n)->{
			temp.get(n).stream().forEach((k)->{
				if(weights.get(src.get(n))+1>weights.get(k)) {
					weights.put(k, weights.get(src.get(n))+1);
				}
			});
			deleteVertexFromTable(temp, src.get(n));
			weights.remove(src.get(n));
		});	
		src.stream().forEach((n)->{
			src.addAll(temp.get(n));
			src.removeIf((k)->k==n);
		});
		getDistance(temp,weights,src,dst);
		return weights.get(dst);
	}

}
