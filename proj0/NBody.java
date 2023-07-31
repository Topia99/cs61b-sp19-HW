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
            String img = "images/" + in.readString();

            // Create a new Body object and insert it into Bodies array
            Bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
        }

        return Bodies;
    }

    public static void main(String[] args){
        if (args.length != 3) {
            System.out.println("Enter Command-line arguments as such: java NBody T dt filename");
            return;
        }

        try{
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String filename = "./data/" + args[2];

            // Read radius and read all bodies in filename
            double radius = readRadius(filename);
            Body[] Bodies = readBodies(filename);
            
            // for fluent animation
            StdDraw.enableDoubleBuffering();
            
            // Set Canvas
            StdDraw.setCanvasSize(1280,720);
            StdDraw.setScale(-1 * radius, radius);
            StdDraw.clear();
            
            // Set time to 0
            double time = 0;

            // Continue bodies motion until time reach T
            int n = Bodies.length;
            while (time < T){
                 // Create xForces array and yForces array
                double[] xForces = new double[n];
                double[] yForces = new double[n];
                
                /* For each body in bodies, calculate the net force exerted
                on this body by all bodies in the array
                */
                for (int i=0; i<n; i++) {
                    for (int j=0; j<n; j++){
                        if (j == i) {
                            continue;
                        }
                        xForces[i] += Bodies[i].calcForceExertedByX(Bodies[j]);
                        yForces[i] += Bodies[i].calcForceExertedByY(Bodies[j]);

                    }
                }

                // Update position for each body in bodies
                for (int i=0; i<n; i++){
                    Bodies[i].update(dt, xForces[i], yForces[i]);
                }

                // Draw the background image
                StdDraw.picture(0, 0, "images/starfield.jpg",radius*2, radius*2);

                // Draw all bodies
                for (int i=0; i<Bodies.length; i++){
                    Bodies[i].draw();
                }

                StdDraw.show();
                StdDraw.pause(10);

                // increment time by dt
                time += dt;
            }
            
            // Print out the final state if the universe
            StdOut.printf("%d\n", n);
            StdOut.printf("%.2e\n", radius);
            for (int i=0; i<n; i++){
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %12s\n",
                            Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
                            Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);
            }


           
            
        } catch (NumberFormatException e){

            System.out.println("Command-line arguments input wrong type, Input Type should be: double double String");
            return;
        }

    }
}
