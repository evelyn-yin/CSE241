package lab1;

public class ClosestPair {

    public final static double INF = java.lang.Double.POSITIVE_INFINITY;
    static double sdist = INF;
    static XYPoint sp[] = new XYPoint[2];

    /** 
     * Given a collection of points, find the closest pair of point and the
     * distance between them in the form "(x1, y1) (x2, y2) distance"
     *
     * @param pointsByX points sorted in nondecreasing order by X coordinate
     * @param pointsByY points sorted in nondecreasing order by Y coordinate
     * @return Result object containing the points and distance
     */
    static Result findClosestPair(XYPoint pointsByX[], XYPoint pointsByY[]) {
       // YOUR CODE GOES HERE
        int mid=pointsByX.length/2;
        int n=pointsByX.length;
        sdist=INF;
        /*if (n==1) {
        sdist=INF;
        sp[0]=pointsByX[0];
        sp[1]=pointsByX[0];    
        }*/
        closestp(pointsByX);
        Result rMin = new Result(sp[0], sp[1], sdist);
        return rMin;                    
    }

    static void closestp(XYPoint A[]){

        int n=A.length;
        XYPoint L[]=new XYPoint[A.length/2];
        XYPoint R[]=new XYPoint[A.length-A.length/2]; 
        int mid=A.length/2;

        if (n==2) {
            if (A[0].dist(A[1])<=sdist) {
            sdist=A[0].dist(A[1]);
            sp[0]=A[0];
            sp[1]=A[1];    
            }
        }
        if (n>2) {
            int i=0;
            while(i<A.length/2){
                L[i]=A[i];
                i++;
            } 
            int j=A.length/2;
            i=0;
            while(i<A.length-A.length/2){
                R[i]=A[j];
                i++;
                j++;
            }
            closestp(L);
            closestp(R);
            merge(L,R,mid);
        }
    }
    

    static void merge(XYPoint A[],XYPoint B[],int mid){
        int Lline=mid;
        int Rline=mid;
        while(Lline>1){
            if (abs(A[Lline-1].x-A[mid-1].x)<=sdist){
                Lline--;
            }
            else{
                Lline++;
                break;
            }
        }
        while(Rline < A.length+B.length){
            if (abs(B[Rline-mid].x-B[0].x)<=sdist) {
                Rline++;    
            }
            else{
                Rline--;
                break;
            }
        }

        if (Lline!=Rline) {
            for(int X=Lline-1;X <mid;X++){
                for(int Y=0;Y <Rline-mid;Y++ ){
                    if(A[X].dist(B[Y])<=sdist){
                        sdist=A[X].dist(B[Y]);
                        sp[0]=A[X];
                        sp[1]=B[Y];
                    }
                }
            }
            
        }

    }



    static int abs(int x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }


}
