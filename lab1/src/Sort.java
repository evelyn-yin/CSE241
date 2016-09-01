                                                                              package lab1;

public class Sort {
    //================================= sort =================================
    //
    // Input: array A of XYPoints refs 
    //        lessThan is the function used to compare points
    //
    // Output: Upon completion, array A is sorted in nondecreasing order
    //         by lessThan.
    //
    // DO NOT USE ARRAYS.SORT.  WE WILL CHECK FOR THIS.  YOU WILL GET A 0.
    // I HAVE GREP AND I AM NOT AFRAID TO USE IT.
    //=========================================================================
    public static void msort(XYPoint[] A, Comparator lessThan) {
        int i=0;
        int j=0;
        int k=0;
        int x=0;
        XYPoint tmp[]=new XYPoint[A.length];
        //System.out.println(temp[]);
        XYPoint L[]=new XYPoint[A.length/2];
        XYPoint R[]=new XYPoint[A.length - A.length/2];
        if (A.length>1) {
            i=0;
            while(i<A.length/2){
                L[i]=A[i];
                i++;
            } 
            j=A.length/2;
            i=0;
            while(i<A.length-A.length/2){
                R[i]=A[j];
                i++;
                j++;
            }


            

            Sort.msort(L,lessThan);
            Sort.msort(R,lessThan);

            i=0;
            j=0;
            k=0;
            while(i<L.length && j<R.length){
                if (lessThan.comp(L[i],R[j])) {
                    tmp[k]=L[i];
                    i++;
                    k++;    
                }
                else {
                    tmp[k]=R[j];
                    j++;
                    k++;
                }
            }
            while(i<L.length){ 
                tmp[k]=L[i];
                i++;
                k++;
            }
            while(j<R.length){
                tmp[k]=R[j];
                j++;
                k++;
            }
            x=0;
            while(x<A.length){
                A[x]=tmp[x];
                x++;
            }

        }
    }
}
