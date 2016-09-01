package lab2;

/**
 * A hash table mapping Strings to their positions in the pattern sequence.
 *
 * Fill in the methods for this class.
 */
public class StringTable {
	private static int length;
	private static Record[] hashtable;
	private static int hashi;
    private final static Record flag=new Record("flag");
    //private static boolean trydb=false;
    /**
     * Create an empty table of size n
     *
     * @param n size of the table
     */
    public StringTable(int n) {
        // TODO: implement this method
    	length=n;
    	hashtable=new Record[length];
    	hashi=0;
    }

    /**
     * Create an empty table.  You should use this construction when you are
     * implementing the doubling procedure.
     */
    public StringTable() {
        // TODO: implement this method
    	length=8;
    	hashtable=new Record[length];
    	hashi=0;
    }

    /**
     * Insert a Record r into the table.
     *
     * If you get two insertions with the same key value, return false.
     *
     * @param r Record to insert into the table.
     * @return boolean true if the insertion succeeded, false otherwise
     */
    void doubling(){
        Record[] pretable=hashtable;
        int presize=length;
            length=2*length;
            hashtable=new Record[length];
            hashi=0;
           /*while(i<presize){
            if ( (pretable[i]!=null) && (!(pretable[i].getKey().equals("flag"))) ){
                boolean t=doubleInsert(pretable[i]);
                i++;
           }*/
           for (int i=0;i<presize;i++){
                if ((pretable[i]!=null) && (!(pretable[i].getKey().equals("flag"))) ){
                    boolean t = insert(pretable[i]);
                }
            }
    }

    /*public boolean doubleInsert(Record r) {
        int keynum=r.getnum();
        int hash1=baseHash(keynum);
        int hash2=stepHash(keynum);
        int slot1=hash1;
        while ( (hashtable[slot1] != null) && (!(hashtable[slot1].getKey().equals("flag"))) ){
            slot1=(slot1+hash2)%length;
            if (slot1 == hash1) {
                return false;
            }
        }
        hashtable[slot1]=r;
        hashi++;
        return true;
    }*/
    
    public boolean insert(Record r) {
        // TODO: implement this method
        if ((hashi*1.0)>=(length/4.0)){
            doubling();
            
        }
    	int keynum=r.getnum();
    	int hash1=baseHash(keynum);
    	int hash2=stepHash(keynum);
    	int slot1=hash1;
        for(int k=0;k<length;k++){
        if (hashtable[slot1] == null) {
            hashtable[slot1]=r;
            hashi++;
            return true;
        }
        else if (hashtable[slot1].getKey().equals("flag")) {
            hashtable[slot1]=r;
            hashi++;
            return true;
        }
        else{
            if ((hashtable[slot1].getnum()==keynum) && (r.getKey().equals(hashtable[slot1].getKey())) ) {
                return false;
            }else{
                slot1=(slot1+hash2)%length;
                if (slot1 == hash1) {
                    return false;
                }
            }
        }}
        return false;
        /*while ( (hashtable[slot1] != null) && (!(hashtable[slot1].getKey().equals("flag"))) ){
            if ((hashtable[slot1].getnum()==keynum) && (r.getKey().equals(hashtable[slot1].getKey())) ) {
                return false;
            }else{
                slot1=(slot1+hash2)%length;
                if (slot1 == hash1) {
                    return false;
                }
            }
        }
        hashtable[slot1]=r;
        hashi++;
        return true;*/
    }

    /**
     * Delete a record from the table.
     *
     * Note: You'll have to find the record first unless you keep some
     * extra information in the Record structure.
     *
     * @param r Record to remove from the table.
     */
    public void remove(Record r) {
        // TODO: implement this method
    	int keynum=r.getnum();
        //String rs=r.getKey();
    	int hash1=baseHash(keynum);
    	int hash2=stepHash(keynum);
    	int slot1=hash1;
        for (int k=0;k<length;k++) {  
        /*while (hashtable[slot1] != null)  */
        if ( (hashtable[slot1].getnum()==keynum)&&(hashtable[slot1].getKey().equals(r.getKey())) ) {
                hashtable[slot1]=flag;
                hashi--;
                break;
            }else{
                slot1=(slot1+hash2)%length;
                if (slot1 == hash1) {
                    break;
                }
            }
        }
    }
    

    /**
     * Find a record with a key matching the input.
     *
     * @param key to find
     * @return the matched Record or null if no match exists.
     */
    public Record find(String key) {
        // TODO: implement this method
    	int keynum=toHashKey(key);
    	int hash1=baseHash(keynum);
    	int hash2=stepHash(keynum);
    	int slot1=hash1;
        if (hashi == 0){
        return null;
        }
        for (int k=0;k<length;k++) {
        if (hashtable[slot1] == null) {
            return null;
        }
        else{
            if ((hashtable[slot1].getnum()==keynum)&&(hashtable[slot1].getKey().equals(key)) ) {
                return hashtable[slot1];
            }else{
                slot1=(slot1+hash2)%length;
                if (slot1 == hash1) {
                    return null;
                }
            }
        }
        /*while (hashtable[slot1] != null) {            
            if ( (hashtable[slot1].getnum()==keynum)&&(hashtable[slot1].getKey().equals(key)) ) {
                return hashtable[slot1];
            }else{
                slot1=(slot1+hash2)%length;
                if (slot1 == hash1) {
                    return null;
                }
            }
        }*/
        }
    	return null;
    }

    

    /**
     * Return the size of the hash table (i.e. the number of elements
     * this table can hold)
     *
     * @return the size of the table
     */
    public int size() {
        // TODO: implement this method
       return length;
    }

    /**
     * Return the hash position of this key.  If it is in the table, return
     * the postion.  If it isn't in the table, return the position it would
     * be put in.  This value should always be smaller than the size of the
     * hash table.
     *
     * @param key to hash
     * @return the int hash
     */
    public int hash(String key) {
        // TODO: implement this method
    	boolean getit=false;
    	int keynum=toHashKey(key);
    	int hash1=baseHash(keynum);
    	int hash2=stepHash(keynum);
    	int slot1=hash1;
        if (hashi==0){
            getit=false;
        }else{
            while (hashtable[slot1] != null) {
                if ( (hashtable[slot1].getnum()==keynum)&&(hashtable[slot1].getKey().equals(key)) ){
                    return slot1;
                }else{
                    slot1=(slot1+hash2)%length;
                    if (slot1 == hash1) {
                        break;
                    }
                }
            }
        }
    	if (!getit){
        	slot1=hash1;
            while ( (hashtable[slot1] != null) && (!(hashtable[slot1].getKey().equals("flag"))) ){
                slot1=(slot1+hash2)%length;
                if (slot1 == hash1) {
                    return 0;
                }
            }
            return(slot1);
        }
    	return 0;
    }

    /**
     * Convert a String key into an integer that serves as input to hash functions.
     * This mapping is based on the idea of a linear-congruential pseuodorandom
     * number generator, in which successive values r_i are generated by computing
     *    r_i = (A * r_(i-1) + B) mod M
     * A is a large prime number, while B is a small increment thrown in so that
     * we don't just compute successive powers of A mod M.
     *
     * We modify the above generator by perturbing each r_i, adding in the ith
     * character of the string and its offset, to alter the pseudorandom
     * sequence.
     *
     * @param s String to hash
     * @return int hash
     */
    public static int toHashKey(String s) {
        int A = 1952786893;
        int B = 367253;
        int v = B;

        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            v = A * (v + (int) c + j) + B;
        }

        if (v < 0) {
            v = -v;
        }
        return v;
    }

    /**
     * Computes the base hash of a hash key
     *
     * @param hashKey
     * @return int base hash
     */
    int baseHash(int hashKey) {
        // TODO: implement this method
        int basenum=(int)(length* ((hashKey*(Math.sqrt(5)-1)/2 )%1));
       return basenum;
    }


    /**
     * Computes the step hash of a hash key
     *
     * @param hashKey
     * @return int step hash
     */
    int stepHash(int hashKey) {
        // TODO: implement this method
    	int basenum= (int)(length* ((hashKey*(Math.sqrt(7)-1)/2)%1));
    	if ((basenum==0)||((length%basenum)==0)){
    		basenum=1;
    	}
        return basenum;
    }
}
