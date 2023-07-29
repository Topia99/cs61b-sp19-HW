public class NBody {
    

    /**
     * Retrieve radius from given file
     * @param fileName 
     */
    public static double readRadius(String fileName){
        In in = new In(fileName);

        int numOfBody =  in.readInt();
        double radius = in.readDouble();

        return radius;
    }
}
