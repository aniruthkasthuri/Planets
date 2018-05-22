import java.util.*;
public class NBody{
     public static String direction = "";
     public static int changer = 0;
     public static double readRadius(String file){
          In reader = new In(file);
          reader.readDouble();
          return reader.readDouble();         
     }
     public static Planet[] readPlanets(String file){
          In reader = new In(file);
          Planet[] planets = new Planet[reader.readInt()];
          reader.readDouble();

          while (reader.hasNextLine()){
              try{
                 for (int x = 0; x < planets.length; x++){
                     planets[x] = new Planet(reader.readDouble(),reader.readDouble(),reader.readDouble(),reader.readDouble(),reader.readDouble(),reader.readString());
                 }
              }
              catch(InputMismatchException e){
                 reader.readAllStrings();
              }
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
          String dir = "";
          StdAudio.play("./audio/2001.mid");
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
              if (StdDraw.isKeyPressed('W')){
                   planets[planets.length-2].yyPos += 1000000000.0;
              }
               if (StdDraw.isKeyPressed('D')){
                   planets[planets.length-2].xxPos += 1000000000.0;
              }     
              if (StdDraw.isKeyPressed('S')){
                   planets[planets.length-2].yyPos -= 1000000000.0;
              }
               if (StdDraw.isKeyPressed('A')){
                   planets[planets.length-2].xxPos -= 1000000000.0;
              }        
              StdDraw.picture(0,0,"./images/starfield.jpg",2*radius,2*radius);

              for(int i = 0; i < planets.length; i++){
                  planets[i].draw();
              }
              StdDraw.show(10);
          } 
          System.out.println(planets.length);
          System.out.println(radius);
          for (int i = 0; i < planets.length; i++) {
	        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		       planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
                }		
          
     }
}

