/**
 * Created by Caitlyn on 4/11/17.
 */
public class Planet {

    private String img;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double m;
    private double Fx;
    private double Fy;
    private double G =6.67e-11;
    private double dt = 25000; //time delta



    public Planet (String i,double x, double y, double vx, double vy, double m){
        this.img = i;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.m = m;
    }

    public String toString() {
        return "Planet: " + this.img + " " + this.x + " " + this.y + " " + this.vx + " " + this.vy + " " + this.m;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getM(){
        return this.m;
    }

    public String getIMG(){
        return this.img;
    }


    public void resetForces() {
        this.Fx = 0;
        this.Fy = 0;
    }

    public void accumulateForces(Planet p) {
        double dx = p.getX()-this.x;
        double dy = p.getY()-this.y;
        double r = Math.sqrt(Math.pow((dx), 2) + Math.pow(dy, 2));
        double F = (G * p.getM() * this.m) / Math.pow(r, 2);
        this.Fx += F * ((dx) / r);
        this.Fy += F * ((dy) / r);
        //return this.Fx;
    }

    public void update(){
        double ax = this.Fx/this.m;
        this.vx = this.vx +(dt*ax);
        this.x = this.x + (dt*vx);

        double ay = this.Fy/ this.m;
        this.vy = this.vy +(dt*ay);
        this.y = this.y + (dt*vy);

        StdDraw.picture(this.x, this.y, "img/"+this.img);
    }


}
