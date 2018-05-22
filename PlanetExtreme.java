public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass  = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this(p.xxPos,p.yyPos,p.xxVel,p.yyVel,p.mass,p.imgFileName);
    }
    public double calcDistance(Planet p){
        double xDist = this.xxPos-p.xxPos;
        double yDist = this.yyPos-p.yyPos;
        return Math.sqrt(Math.pow(xDist,2)+Math.pow(yDist,2));
    }
    public double calcForceExertedBy(Planet p){
        return (6.67*Math.pow(10,-11))*this.mass*p.mass/(Math.pow(this.calcDistance(p),2));
    }
    public double calcForceExertedByX(Planet p){
         return calcForceExertedBy(p)*(p.xxPos-this.xxPos)/calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
         return calcForceExertedBy(p)*(p.yyPos-this.yyPos)/calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] ps){
        double netforce = 0;
        for(int x = 0; x<ps.length; x++){
           if(this.equals(ps[x]) == false){
                netforce += calcForceExertedByX(ps[x]);
           }
        }
        return netforce; 
    }
    public double calcNetForceExertedByY(Planet[] ps){
        double netforce = 0;
        for(int x = 0; x<ps.length; x++){
           if(this.equals(ps[x]) == false){
               netforce += calcForceExertedByY(ps[x]);
           }
        }
        return netforce;
    }   
    public void update(double time, double xF, double yF){
        double xAcc = xF/mass;
        double yAcc = yF/mass;

        xxVel = xxVel + time*xAcc;
        yyVel = yyVel + time*yAcc;

        xxPos = xxPos + time*xxVel;
        yyPos = yyPos + time*yyVel;
    }
    public void draw(){
        String imgpath = "./images/"+imgFileName;
        StdDraw.picture(xxPos,yyPos,imgpath);
    }
}
