import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Created by Caitlyn on 4/11/17.
 */
public class SolarSystem {

    private Planet[] planets = new Planet[5];
    private int N;
    private double R;
    double G = 6.67e-11;
    double dt = 25000; //time delta

    //constructor
    public SolarSystem() throws Exception{

        Scanner s = new Scanner(new FileInputStream("data/planets.txt"));
        // number of planets
        this.N = s.nextInt();
        System.out.println(N);
        // radius of universe
        this.R = s.nextDouble();
        StdDraw.setXscale(-R,R);
        StdDraw.setYscale(-R,R);

        // data structures to store information related to the planets
        double[] x = new double[this.N];
        double[] y = new double[this.N];
        double[] vx = new double[this.N];
        double[] vy = new double[this.N];
        double[] m = new double[this.N];
        String[] img = new String[this.N];

        for (int i = 0; i <N; i++) {
            x[i]=s.nextDouble();
            y[i]=s.nextDouble();
            vx[i]=s.nextDouble();
            vy[i]=s.nextDouble();
            m[i]=s.nextDouble();
            img[i]=s.next();
        }

        //put into Planet[]
        for (int i = 0; i < planets.length; i++) {
            planets[i]= new Planet(img[i],x[i],y[i],vx[i],vy[i],m[i]);
        }

    }

    public void run(){

        while(true){
            for (int i = 0; i < N; i++) {
                planets[i].resetForces();
                for (int j = 0; j < N; j++) {
                    if(i!=j){
                        planets[i].accumulateForces(planets[j]);
                    }//if
                }//for j
            }//for i

            StdDraw.picture(0,0, "img/starfield.jpg");
            for (int i = 0; i < N; i++) {
                planets[i].update(); //ax, vx, x, and draws
 //               planets[i].calcY(fy[i]);
                //StdDraw.picture(planets[i].getX(),planets[i].getY(),"img/"+planets[i].getIMG());
            }
            //pause
            StdDraw.show(20);

        }//while
    }

    public static void main(String[] args) throws Exception{

        SolarSystem ss = new SolarSystem();

        ss.run();

    }

}
