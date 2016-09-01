package lab3;
import java.util.ArrayList;
/**
 * A priority queue class supporting operations needed for
 * Dijkstra's algorithm.
 */
class PriorityQueue<T> {

    /**
     * Constructor
     */
    ArrayList<Handle<T>> heapqueue = new ArrayList<Handle<T>>();

    public PriorityQueue() {
    }

    /**
     * @return true iff the queue is empty.
     */
    public boolean isEmpty() {
        if (heapqueue.size()==0) {
            return true;
        }
        else {
            return false;}
    }

    /**
     * Insert a (key, value) pair into the queue.
     *
     * @return a Handle to this pair so that we can find it later.
     */
    Handle insert(int key, T value) {
        double INF = java.lang.Double.POSITIVE_INFINITY;
        Handle<T> newele = new Handle<T>((int) key, value);
        heapqueue.add(newele);
        int n = heapqueue.size()-1;
        heapqueue.get(n).theIndex(n);
        while((n > 0) && (heapqueue.get(n).node.key < heapqueue.get((n-1)/2).node.key)){
            letswap(n,(n-1)/2);
            n = (n-1)/2;
        }
        //System.out.println("key\n"+heapqueue.get(n).node.key);
       return heapqueue.get(n);
    }

    public void letswap(int i, int j){
        Handle<T> tempi = heapqueue.get(i);
        Handle<T> tempj = heapqueue.get(j);
        heapqueue.set(i, tempj); 
        heapqueue.set(j, tempi);
        heapqueue.get(i).theIndex(i); //reset them in the arraylist
        heapqueue.get(j).theIndex(j);
    }

    /**
     * @return the min key in the queue.
     */
    public int min() {
        return heapqueue.get(0).node.key;
    }

    /**
     * Find and remove the smallest (key, value) pair in the queue.
     *
     * @return the value associated with the smallest key
     */
    public T extractMin() {
        if (heapqueue.size() < 1){
        System.exit(1);
        }

        T minValue = heapqueue.get(0).node.value;
        letswap(0,heapqueue.size()-1);
        heapqueue.get(0).theIndex(0);
        heapqueue.get(heapqueue.size()-1).theIndex(-1);
        heapqueue.remove(heapqueue.size()-1);
        Heapify(0);
        return minValue;
    }

    public void Heapify(int i){
        int heapsize = heapqueue.size();
        int heaproot = i;
        int leftx = i*2+1;
        int rightx = i*2+2;

        if ((leftx < heapsize) && (heapqueue.get(leftx).node.key < heapqueue.get(heaproot).node.key)) {
            heaproot = leftx;
        }

        if ((rightx < heapsize) && (heapqueue.get(rightx).node.key < heapqueue.get(heaproot).node.key)) {
            heaproot = rightx;
        }

        if (heaproot != i) {
            letswap(i,heaproot);
            Heapify(heaproot);
        }

    }
    /**
     * Decrease the key to the newKey associated with the Handle.
     *
     * If the pair is no longer in the queue, or if its key  <=newKey,
     * do nothing and return false.  Otherwise, replace the key with newkey,
     * fix the ordering of the queue, and return true.
     *
     * @return true if we decreased the key, false otherwise.
     */
    public boolean decreaseKey(Handle h, int newKey) {
        if (newKey >= heapqueue.get(h.index).node.key) {
            return false;      
        }
        if (h.index > heapqueue.size()-1) {
            return false; 
        }

        else{
            heapqueue.get(h.index).node.key = newKey;
            Heapify(0);
            return true;
        }
    }

    /**
     * @return the key associated with the Handle.
     */
    public int handleGetKey(Handle h) {
        if (h.index != -1) {
            return heapqueue.get(h.index).node.key;
        }
       else{
        return (Integer) null;
        }
    }

    /**
     * @return the value associated with the Handle.
     */
    public T handleGetValue(Handle h) {
         if (h.index != -1) {
            return heapqueue.get(h.index).node.value;
        }
       else{
        return null;
        }
    }

    /**
     * Print every element of the queue in the order in which it appears
     * (i.e. the array representing the heap)
     */
    public String toString() {
        String s = "( ";
                for (int i = 0; i < heapqueue.size();i++){
                        s = s +"("+ heapqueue.get(i).node.key+ ","+ heapqueue.get(i).node.value.toString() +")";
                }
                return s+" )";
    }
}