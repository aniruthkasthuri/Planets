public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);

        in.readDouble();
        return in.readDouble();        
    }
    public static Planet[] readPlanets(String filename){
        In reader = new In(filename);
        int numplanets = reader.readInt();
        Planet[] planets = new Planet[numplanets];  
        reader.readDouble();

        for(int x = 0; x < numplanets; x++){
             planets[x] = new Planet(reader.readDouble(),reader.readDouble(),reader.readDouble(),reader.readDouble(),reader.readDouble(),reader.readString());    
        }
        return planets;
    }
    public static void main(String[] args){
          double T = Double.parseDouble(args[0]);
          double dT = Double.parseDouble(args[1]);
          String filename = args[2];
          double radius = readRadius(filename);
          Planet[] planets = readPlanets(filename);
          StdDraw.setScale(-radius,radius);
          StdDraw.picture(0,0,"./images/starfield.jpg",2*radius,2*radius);
          for(int i = 0; i < planets.length; i++){
              planets[i].draw();
          }
          double time;

          StdAudio.play("./audio/HotelCalifornia.wav");
          for(time = 0.0; time < T; time+=dT){
              double[] xForces = new double[planets.length];
              double[] yForces = new double[planets.length];

              for(int y = 0; y < planets.length; y++){
                  xForces[y] = planets[y].calcNetForceExertedByX(planets);
                  yForces[y] = planets[y].calcNetForceExertedByY(planets);
              }
              for(int z = 0; z < planets.length; z++){
                 planets[z].update(dT,xForces[z],yForces[z]);
              }
              StdDraw.picture(0,0,"./images/starfield.jpg",2*radius,2*radius);

              for(int i = 0; i < planets.length; i++){
                  planets[i].draw();
              }
              StdDraw.show(10);
              StdDraw.pause(10);
          }
          System.out.println(planets.length);
          System.out.println(radius);
          for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                       planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
                }

     }

}
