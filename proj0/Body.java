public class Body{
    public static final double G = 6.67e-11;

    
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    String imgFileName;
    
    
    /**
     * Constructor of class Body that set value for all instance variable.
     * @param xP x-coordinate position
     * @param yP y-coordinate position
     * @param xV x-coordinate velocity
     * @param yV y-coordinate velocity
     * @param m mass of the object
     * @param img image file name
     */
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * Constructor that take in a Body object and initialize an identical Body object.
     * @param Body b
     */
    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    /**
     * calcDistance method calculate the distance between the object this Body and the supplied Body
     * @param b supplied Body
     */
    public double calcDistance(Body b){
        double dx, dy;

        // calculate dx, dy
        if (this.xxPos > b.xxPos){
            dx = this.xxPos - b.xxPos;
        } else {
            dx = b.xxPos - this.xxPos;
        }

        if (this.yyPos > b.yyPos){
            dy = this.yyPos - b.yyPos;
        } else {
            dy = b.yyPos - this.yyPos;
        }

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Calculate the force exeted on this body by the given body
     * @param b given body
     */
    public double calcForceExertedBy(Body b){

        double dis = this.calcDistance(b);
        
        double F = (G * this.mass * b.mass) / Math.pow(dis, 2);

        return F;
        
    }
        
    /**
     * Calculate the force exeted on this body by the given body in X direction
     * @param b Body
     */
    public double calcForceExertedByX(Body b){
        double dx;
        if (this.xxPos > b.xxPos){
            dx = -1 * (this.xxPos - b.xxPos);
        } else{
            dx = b.xxPos - this.xxPos;
        }

        double Fx = (this.calcForceExertedBy(b) * dx) / this.calcDistance(b);

        return Fx;
    }

     /**
     * Calculate the force exeted on this body by the given body in Y direction
     * @param b Body
     */
    public double calcForceExertedByY(Body b){
        double dy;
        if (this.yyPos > b.yyPos){
            dy = -1 * (this.yyPos - b.yyPos);
        } else{
            dy = b.yyPos - this.yyPos;
        }

        double Fy = (this.calcForceExertedBy(b) * dy) / this.calcDistance(b);

        return Fy;
                    }
                
     /**
     * Update Body's velocity and position in a small period of time dt
     * @param dt time of the movement
     * @param fx x-force
     * @param fy y-force
     */
    public void update(double dt, double fx, double fy){
        // 1. Calculate the acceleration using provided x- and y-force.
        double ax = fx / mass;
        double ay = fy / mass;

        // 2. Calculate new velocity by using the ax ay and current velocity
        xxVel = xxVel + (dt * ax);
        yyVel = yyVel + (dt * ay);

        // 3. Calculate new Position
        xxPos = xxPos + (dt * xxVel);
        yyPos = yyPos + (dt * yyVel);
    }
}