package graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixGraphImpl<T> implements Graph<T>{

	private Map<Integer,Map<Integer,Boolean>> adjMatr = null;
	private Map<Integer,T> elements = null;
	
	public MatrixGraphImpl() {
		adjMatr = new HashMap<>();
		elements = new HashMap<>();
	}
	
	@Override
	public void setEdges(int id, Integer[] adjacentVertexes) {
		
	}
	
	@Override
	public void deleteEdges(int id, Integer[] adjacentVertexes) {
		
	}

	@Override
	public void deleteVertex(int id) {
		
	}

	@Override
	public void setVertex(int id, T element, Integer[] adjacentVertexes) {
		elements.put(id, element);

	}
	
	@Override
	public boolean isConnected() {
		return false;
	}

	private boolean isConnected(List<Integer> checkedNodes, int nodeId) {
		return false;
	}

	@Override
	public int getDistance(int src, int dst) {
		return 0;
	}

}
