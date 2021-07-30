/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project2;

class Point{
   private double x; 
   private double y;
   private int Type_Of_Class;
   private int Desired_Output = 0;
   
   
   public double getX(){
       return x;
   }
   public double getY(){
       return y;
   }
   
   public void setX(double x){
       this.x = x ;
   }
   public void setY(double y){
       this.y = y ;
   }
   
    public void setDesiredOutput(int Desired_Output){
       this.Desired_Output = Desired_Output ;
   }
   public int getDesiredOutput(){
       return Desired_Output ;
   }
   
   public void setTyoeOfClass(int Type_Of_Class){
       this.Type_Of_Class = Type_Of_Class ;
   }
   public int getTyoeOfClass(){
       return Type_Of_Class ;
   }
   
    public Point(){
 
   }
    
   public Point(double x , double y , int Desired_Output){
       this.x = x ;
       this.y = y ;
       this.Desired_Output = Desired_Output;
   }
   
}