public class NBody {
    

    /**
     * Read radius from given file and return the radius
     * 
     * @param fileName 
     */
    public static double readRadius(String fileName){
        In in = new In(fileName);

        //skip the first line
        in.readInt();

        double radius = in.readDouble();

        return radius;
    }

    /**
     * Read Body from given file, and return an array of bodys
     * 
     * @param fileName
     */
    public static Body[] readBodies(String fileName){
        // Construct a in class with filename (string)
        In in = new In(fileName);

        // Read number of Bodies
        int n = in.readInt();

        // Initialize an Body object array with n element
        Body[] Bodies = new Body[n];

        // Skip radius row
        in.readDouble();

        // Read each body's info in loop
        for (int i=0; i<n; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            // Create a new Body object and insert it into Bodies array
            Bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
        }

        return Bodies;
    }
}
