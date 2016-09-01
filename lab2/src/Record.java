package lab2;

import java.util.*;

/**
 * Record type for string hash table
 *
 * A record associates a certain string (the key value) with
 * a list of sequence positions at which that string occurs
 *
 * Change anything you want about this except the methods we have implemented already.
 */
public class Record {
    private final String key;
    private final ArrayList<Integer> positions;
    private  int newint;

    /**
     * Constructs a Record with the given string
     *
     * @param s key of the Record
     */
    public Record(String s) {
        key = s;
        positions = new ArrayList<Integer>(1);
        newint = StringTable.toHashKey(s);
    }

    /**
     * Returns the key associated with this Record.
     */
    public Record(){
        key="";
        positions = new ArrayList<Integer>(1);
        newint = 0;
    }
    public String getKey() {
        return key;
    }


    public int getnum(){
        return newint;
    }
    /**
     * Adds a new position to the positions list.
     *
     * @param position of the key
     */
    public void addPosition(Integer position) {
        positions.add(position);
    }

    /**
     * @return The number of positions we have for this key.
     */
    public int getPositionsCount() {
        return positions.size();
    }

    /**
     * @return the Integer position at index
     */
    public Integer getPosition(int index) {
        return positions.get(index);
    }
}