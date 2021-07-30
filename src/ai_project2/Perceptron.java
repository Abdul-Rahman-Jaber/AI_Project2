
package ai_project2;

import java.util.Random;


class SinglePerceptron{
    
    private double inputs[];
    private double weights[];
    private double output;
    private double desiredOutput;
    private double threshold;
    private double alpha;
    private double error=0;
    private int maxIteration;
    
    
    public SinglePerceptron(int numberOfInputs){
        weights=new double[numberOfInputs];
    }
    
    
    public SinglePerceptron(double alpha,int Max_Number_Of_Iterations,double threshold,int numberOfInputs,boolean flag){

        
       this.alpha=alpha;
       this.maxIteration = Max_Number_Of_Iterations;
        
        this.threshold=threshold;
        
        weights=new double[numberOfInputs];
        
        randomizeWeights();
    }
    
    
    
    private void randomizeWeights(){
        for(int i=0;i<weights.length;i++)weights[i]=Math.random();
    }
    
    
    public void trainWeights(){
        calculateActualOutput();
        calculateOutputsDifference();
        adjustWeights();
    }
    
    
    
    private void calculateOutputsDifference(){
        error=desiredOutput-output;
    }
    
    public void calculateActualOutput(){    
        double weightedSum=0;
        for(int i=0;i<inputs.length;i++){
            weightedSum+=(weights[i]*inputs[i]);
        }
        //this.weightedSum=weightedSum;
        double activationFunctionInput=weightedSum-threshold;
        output=activationFunction(activationFunctionInput);
    }
    
    private double activationFunction(double input){


        if(input >= 0){
            return 1;
        }
        else{
            return -1;
        }

    }
   
    
    private void adjustWeights(){
        for(int i=0;i<weights.length;i++){
            weights[i]+=(alpha*error*inputs[i]);
        }
    }
    
    
    public void printWeights(){
        for(int i=0;i<weights.length;i++)System.out.println("Weight "+(i+1)+" equals "+weights[i]);
        System.out.println("Threshold equals "+threshold);
        System.out.println("Alpha equals "+alpha);
    }
    
    
    public void resetPerceptron(){}
    
    public void setInputs(double inputs[]){
        this.inputs=inputs;    
    }
    
    
    public double getError(){
        return error;
    }
    
    public void setOutput(double desiredOutput){
        this.desiredOutput=desiredOutput;
    }
    
    public void setWeight(int index,double value){
        this.weights[index]=value;
    }
    
    public void setThresh(double value){
        this.threshold=value;
    }
    
    public void setAlpha(double value){
        alpha=value;
    }
    
    public double getOutput(){
        return output;
    }
    
    public double[] getWeights(){
    return weights;
    }
    
    
    public double getThreshold(){
        return threshold;
    }
    
}