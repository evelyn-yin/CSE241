package lab3;


public class Handle<T> {
    public int index;
    public PQNode<T> node;
        
   
    public Handle(int key, T value) 
    { 
        this.node = new PQNode<T>(key, value);     
    }
        
        
    public void theIndex(int i)
    {
        this.index = i;        	
    }   
}