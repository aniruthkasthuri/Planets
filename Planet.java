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
        return Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));        
    } 
    public double calcForceExertedBy(Planet p){
        double g = 6.67*Math.pow(10,-11);
        return g*this.mass*p.mass/((calcDistance(p))*(calcDistance(p)));
    }    
    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double dx = p.xxPos-this.xxPos;
        double r = calcDistance(p);
        return force*dx/r;
    }
    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double dy = p.yyPos-this.yyPos;
        double r = calcDistance(p);
        return force*dy/r;
    }
    public double calcNetForceExertedByX(Planet[] p){
        double netforce = 0.0;
        for (int x = 0; x < p.length; x++){
            if(this.equals(p[x]) == false){
                netforce += calcForceExertedByX(p[x]);
            }
        }
        return netforce;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double netforce = 0.0;
        for (int x = 0; x < p.length; x++){
            if(this.equals(p[x]) == false){   
                netforce += calcForceExertedByY(p[x]);
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
