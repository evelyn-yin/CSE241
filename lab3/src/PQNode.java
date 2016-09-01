package lab3;

public class PQNode<T> {
	public T value;
	public int key;

	public PQNode(int key, T value){
		this.value = value;
		this.key = key;
	}

	public PQNode(){
		this.key = -1;
		this.value = null;
	}

}