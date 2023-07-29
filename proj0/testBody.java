public class testBody {
    

    public static void main(String[] args){
        checkBody();
    }

    /** 
    * Check whether or not two double are equal and prints the result.
    *
    * @param expected   Expected double
    * @param actual     Double received
    * @param lavel      Label for the 'test' case
    * @param eps        Tolerance for the double comparison
    */
    private static void checkEquals(double actual, double expected, String label, double eps){
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     * Check Body class by comparing the correct pairwise force between two Body
     * 
     */
    public static void checkBody(){
        // Create two Body
        Body ball = new Body(0.0, 0.0, 0.0, 0.0, 100, "ball.gif");
        Body acorn = new Body(1.0, 1.0, 0.0, 0.0, 5, "acorn-1.gif");

        checkEquals(ball.calcForceExertedBy(acorn), 1.6675e-8, "CheckBody", 0.01);

    }
}
