package graphs;

public interface Graph<T>{

	public void setEdges(int id,Integer[] adjacentVertexes);
	
	public void deleteEdges(int id, Integer[] adjacentVertexes);
	
	public void deleteVertex(int id);
	
	public void setVertex(int id, T element, Integer[] adjacentVertexes );
	
	public boolean isConnected();
	
	public int getDistance(int src, int dst);
	
}
