
package ai_project2;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;




public class Main extends javax.swing.JFrame {

 
    Icon iconData ;
    int Type_Of_Class;
       
    ArrayList<Point> Array_Of_Inputs = new ArrayList<Point>();
    ArrayList<Point> Testing_Data = new ArrayList<Point>();
    ArrayList<javax.swing.JLabel> Array_of_jLabels = new ArrayList<javax.swing.JLabel>();
    ArrayList<Final_Weights_And_Threshold> Final = new ArrayList<Final_Weights_And_Threshold>();
       
    int table_counter = 0 ;
    DefaultTableModel model ;
      
    boolean Train_flag = false;
    boolean Out_Of_Sample_flag = false;
       
    boolean Take_Testing_Data = false ;
    
    
    public Main() {
        initComponents();
        jTextField2.setEditable(false);
        jTextField7.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        
        panel3.setPreferredSize(new Dimension(500,500));
    }
    

       
    public double ScaleInputX(int x){
        double Xscaled = 0;
        
        if(x >=0 && x <= 250){
            Xscaled = ( x / 250.0) -1 ; 
        }
        else if (x > 250 && x <=500){
            Xscaled = (x - 250)/250.0;
        }
        return Xscaled;
    }
    
    public double ScaleInputY(int y){
        double Yscaled = 0;
        
        if(y >= 0 && y <= 250){
            Yscaled = ( (-1 * y) / 250.0) + 1;
        }
        else if (y > 250 && y <= 500){
            Yscaled = (250 - y)/250.0 ;    
        }
        return Yscaled; 
    }
    
    public int DescaleOutputX(double Xscaled){
        int x= 0;
        
        
        if(Xscaled <= 0){
            x = (int)((Xscaled + 1) * 250) ; 
        }
       
        else if (Xscaled > 0){
            x = (int)((250 * Xscaled) + 250);
        }
        return x;
    }
    
    
    public int DescaleOutputY(double Yscaled){
        int y = 0;
        
        
        if(Yscaled <= 0){    
            y = (int)(((Yscaled +1)*-250)+500);
        }
        
         else if (Yscaled > 0){    
            y = (int)((-250 * Yscaled)+250) ;    
        }
        return y; 
    }
    

    
    public void Take_Testing_Data(int Number_Of_Classes){
        
        float count_class_1 = 0 ;
        float count_class_2 = 0 ;
        float count_class_3 = 0 ;
        float count_class_4 = 0 ;
        
        
        for(Point p : Array_Of_Inputs){
            
            int Class = p.getTyoeOfClass();
            
            if(Class == 1)
                count_class_1++;
            else if (Class == 2)
                count_class_2++;
            else if (Class == 3)
                count_class_3++;
            else if (Class == 4)
                count_class_4++;
  
        }
        
        count_class_1 = Math.round(0.25 * count_class_1)  ;
        count_class_2 = Math.round(0.25 * count_class_2)  ;
        count_class_3 = Math.round(0.25 * count_class_3)  ;
        count_class_4 = Math.round(0.25 * count_class_4)  ;
        
        
        for(int i = 0 ; i < Array_Of_Inputs.size() ; i++){
            if(count_class_1 == 0 && count_class_2 == 0 && count_class_3 == 0 && count_class_4 == 0)
                break;
            
            if(Array_Of_Inputs.get(i).getTyoeOfClass() == 1 && count_class_1 > 0){
             Testing_Data.add(Array_Of_Inputs.get(i));
             Array_Of_Inputs.remove(i);
             panel3.remove(Array_of_jLabels.get(i));
             Array_of_jLabels.remove(i);
             count_class_1--;
            }
            else if (Array_Of_Inputs.get(i).getTyoeOfClass() == 2 && count_class_2 > 0){
             Testing_Data.add(Array_Of_Inputs.get(i));
             Array_Of_Inputs.remove(i);
             panel3.remove(Array_of_jLabels.get(i));
             Array_of_jLabels.remove(i);
             count_class_2--;
            }
            else if(Array_Of_Inputs.get(i).getTyoeOfClass() == 3 && count_class_3 > 0){
             Testing_Data.add(Array_Of_Inputs.get(i));
             Array_Of_Inputs.remove(i);
             panel3.remove(Array_of_jLabels.get(i));
             Array_of_jLabels.remove(i);
             count_class_3--;
            }
            else if (Array_Of_Inputs.get(i).getTyoeOfClass() == 4 && count_class_4 > 0){
             Testing_Data.add(Array_Of_Inputs.get(i));
             Array_Of_Inputs.remove(i);
             panel3.remove(Array_of_jLabels.get(i));
             Array_of_jLabels.remove(i);
             count_class_4--;
            }

        }
        
    }
    
    public void Caculate_Performance(){
        
        
        
      int Count_Of_True_Values = 0 ;
       
       for(int i = 0 ; i < Testing_Data.size() ; i++){
             SinglePerceptron Sp = new SinglePerceptron(2);
             double inputs[] = {Testing_Data.get(i).getX(),Testing_Data.get(i).getY()};
             Sp.setInputs(inputs);
             double output = 0 ;
             
             if(Calculate_Number_Of_Classes() == 2){
                
                Sp.setWeight(0,Final.get(0).Get_Weight_0());
                Sp.setWeight(1,Final.get(0).Get_Weight_1());
                Sp.setThresh(Final.get(0).Get_Threshold());
                Sp.calculateActualOutput();
                output = Sp.getOutput();
                
                if(Testing_Data.get(i).getTyoeOfClass() == Final.get(0).Get_Type_Of_Class() && output == -1){
                       Count_Of_True_Values++;
                }
                else if(Testing_Data.get(i).getTyoeOfClass() != Final.get(0).Get_Type_Of_Class() && output == 1){
                       Count_Of_True_Values++;
                }
                 
             }
                 
             else { 
             for(int j = 0 ; j < Final.size() ; j++){
                
                if(Final.get(j).Get_Type_Of_Class() == Testing_Data.get(i).getTyoeOfClass()){ 
                    Sp.setWeight(0,Final.get(j).Get_Weight_0());
                    Sp.setWeight(1,Final.get(j).Get_Weight_1());
                    Sp.setThresh(Final.get(j).Get_Threshold());
                    Sp.calculateActualOutput();
                    output = Sp.getOutput();
                    
                    break;
                }
                
             }
             
             if(output == -1)
                 Count_Of_True_Values++;
       }
       }
       
       double performance = ((double)Count_Of_True_Values / Testing_Data.size()) * 100 ;
       
       jTextField7.setText(performance + " %");
        
    }
   
      public void Set_The_Desired_Output(int Type_Of_Class){
         for(Point pt : Array_Of_Inputs){
             
             if(pt.getTyoeOfClass() == Type_Of_Class)
                 pt.setDesiredOutput(-1);
             
             else
                 pt.setDesiredOutput(1);
             
         }
    }
      
      
      
    private boolean findInstance(ArrayList<Point> items, int Type_Of_Class) {
      boolean tag = false; 
      for(Point p : items){
            if(p.getTyoeOfClass() == Type_Of_Class){
                tag = true;
            break;
            }
        }
    
      return tag;
}
 
    
    
    public int Calculate_Number_Of_Classes(){
        boolean Flag_For_Classes[] = new boolean[4];
        
        for(int i = 0 ; i < Flag_For_Classes.length ; i++){
            Flag_For_Classes[i] = false;    
        }
        
        for(Point p : Array_Of_Inputs){
            if(p.getTyoeOfClass() == 1)
                Flag_For_Classes[0] = true;
            
            if(p.getTyoeOfClass() == 2)
                Flag_For_Classes[1] = true;
            
            if(p.getTyoeOfClass() == 3)
                Flag_For_Classes[2] = true;
            
            if(p.getTyoeOfClass() == 4)
                Flag_For_Classes[3] = true;
        }
        
        int counter = 0 ;
         for(int i = 0 ; i < Flag_For_Classes.length ; i++){ 
            if(Flag_For_Classes[i]  == true){
                counter ++;
            }   
        }
        
        return counter;
    }
    
  
   
  
  
  
    private void onePerceptron(boolean negativeThreshold , double Learning_Rate , int Max_Number_Of_Iterations,int Number_Of_Classes,int Type_Of_Class){
        double MSE=0;      
        int outputs[] = new int [Array_Of_Inputs.size()] ;
         
        int index = 0 ;
        for(Point pt : Array_Of_Inputs){
            outputs[index] = pt.getDesiredOutput();
            index++;
        }
        
        
        double pthresh=Math.random();
        
        if(negativeThreshold)pthresh*=(-1);
        
        SinglePerceptron p=new SinglePerceptron(Learning_Rate , Max_Number_Of_Iterations, pthresh , 2 ,true);
        
        System.out.println("Original weights");
        p.printWeights();
        System.out.println("---------------------------------------------");
        
        int Epoch_cointer = 1 ;
        int j = 0 ;
        int i = 0;
        
        if(Array_Of_Inputs.size() == 0){
             JOptionPane.showMessageDialog(null, "Please enter the data", "Message" , JOptionPane.WARNING_MESSAGE);
             return;
        }
        
        
        for(;i<Max_Number_Of_Iterations;i++){
          jTextField3.setText((i+1)+"");
          
          
              if(j == Array_Of_Inputs.size()){
                j = 0 ; 
               
                jTextField5.setText(MSE+"");
              
                Epoch_cointer++;
                jTextField4.setText(Epoch_cointer + "");
               
              
              
              if((MSE/Array_Of_Inputs.size())<0.01){
                  break;
              }
              MSE=0;
      
             }
          
            
            double []Inputs = {Array_Of_Inputs.get(j).getX() , Array_Of_Inputs.get(j).getY()};
            p.setInputs(Inputs);
            p.setOutput(outputs[j]);
            p.trainWeights();
            MSE+=Math.pow(p.getError(), 2.0);
                  
           
           j++;
        }
        
        if(i<Max_Number_Of_Iterations){
          Train_flag = true;
        
        double weights[]=p.getWeights();
        double thresh=p.getThreshold();
        
        
        Final_Weights_And_Threshold F = new Final_Weights_And_Threshold();
        F.Set_Type_Of_Class(Type_Of_Class);
        F.Set_Weight_0(weights[0]);
        F.Set_Weight_1(weights[1]);
        F.Set_Threshold(thresh);
        
        Final.add(F);
        
        double p1N[]={-1,0};
        double p2N[]={1,0};

        
        p1N[1]=((thresh - (weights[0]*-1))/weights[1]);
        p2N[1]=((thresh - weights[0])/weights[1]);

        int p1[] = {0,0} ; 
        int p2[] = {0,0} ;
                
        p1[0] = DescaleOutputX(p1N[0]);
        p1[1] = DescaleOutputY(p1N[1]);
        
        p2[0] = DescaleOutputX(p2N[0]);
        p2[1] = DescaleOutputY(p2N[1]);


        panel3.drawPolyLine(p1, p2 , Type_Of_Class); 
        

        }
        
        
        else {
            JOptionPane.showMessageDialog(null, "The System can't classify your date", "Message" , JOptionPane.WARNING_MESSAGE);
        }
        
        
        System.out.println("Final weights");
        p.printWeights();
        System.out.println(MSE+" And i="+i);
        System.out.println("---------------------------------------------");
    }
       
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new ai_project2.panel();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        panel3 = new ai_project2.panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton5.setText("Learn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jSlider1.setMaximum(10);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel2.setText("Learning rate : ");

        jLabel1.setText("Max number of iterations :");

        jLabel4.setText("Number of classes :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "2", "3", "4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/square.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/triangle.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rectangle.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/hexagon.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Current statistics :"));

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel6.setText("Error :");

        jLabel5.setText("Current Epoch :");

        jLabel3.setText("Current Iteration : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(87, 87, 87)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jTextField5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Performace :"));

        jLabel8.setText("Performance :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "      X", "      Y", "Type of class"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Testing"));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/x.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel7.setText("Out of sample data : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     iconData = new ImageIcon(getClass().getResource("/pictures/triangle.png"));
     Type_Of_Class = 1;
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
     if(jComboBox1.getSelectedItem().toString().equals("")){
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);       
     }
     else{
     int value = Integer.parseInt(jComboBox1.getSelectedItem().toString());
     if(value == 2){
        jButton1.setVisible(true);
        jButton2.setVisible(true);
        jButton3.setVisible(false);
        jButton4.setVisible(false);    
     }
     else if(value ==3){
        jButton1.setVisible(true);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
        jButton4.setVisible(false);
     }
     else if (value ==4){
        jButton1.setVisible(true);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
        jButton4.setVisible(true);   
     }
     }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
       jTextField2.setText((jSlider1.getValue()/10.0)+"");
    }//GEN-LAST:event_jSlider1StateChanged

  
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        iconData = new ImageIcon(getClass().getResource("/pictures/square.png"));
        Type_Of_Class = 2;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       iconData = new ImageIcon(getClass().getResource("/pictures/rectangle.png"));
       Type_Of_Class = 3 ;

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       iconData = new ImageIcon(getClass().getResource("/pictures/hexagon.png"));        
       Type_Of_Class = 4 ;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(jTextField2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please choose the learning rate", "Message" , JOptionPane.WARNING_MESSAGE);
        }
        else if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please determine max number of iterations", "Message" , JOptionPane.WARNING_MESSAGE);
        }
        else if (jComboBox1.getSelectedItem().toString().equals("")){
            JOptionPane.showMessageDialog(null, "Please choose number of classes", "Message" , JOptionPane.WARNING_MESSAGE);            
        }
        else{
           
           Final.clear();
           panel3.clearGraph();
           
           int Number_Of_Classes  = Calculate_Number_Of_Classes();
           if(Number_Of_Classes == 1){
               JOptionPane.showMessageDialog(null, "You should insert at least two classes", "Message" , JOptionPane.WARNING_MESSAGE);    
               return;
           }
           
           
           if(Take_Testing_Data == false){
              Take_Testing_Data(Number_Of_Classes);
              Take_Testing_Data = true ;
           }
           
               
                for(int i = 1 ; i<= 4 ; i++){
                    if(findInstance(Array_Of_Inputs,i)){
                       Set_The_Desired_Output(i);
                       onePerceptron(true,Double.parseDouble(jTextField2.getText()),Integer.parseInt(jTextField1.getText()),Number_Of_Classes,i);
                       
                       if(Number_Of_Classes == 2)
                       break;
                    }        
                }
                
                Caculate_Performance();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

   
    
    private void panel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel3MousePressed
//This event is responsible for : 
//1- Display the classes on the Panel
//2- Display the position and the type of classes on the Table
//3- Store all the Inputs to the ArrayList<Point> called Array_Of_Inputs which store Xposition , Yposition , Type_Of_Class


        if(iconData == null){
            JOptionPane.showMessageDialog(null, "Please select a category", "Message" , JOptionPane.WARNING_MESSAGE);
        }
        
        else if(Out_Of_Sample_flag == true){
             Out_Of_Sample_flag = false;
             int x = evt.getX();
             int y = evt.getY();
             javax.swing.JLabel templateLabel = new javax.swing.JLabel() ;
             templateLabel.setText("");
             panel3.setLayout(null);
             templateLabel.setIcon(iconData);
             panel3.add(templateLabel);
             templateLabel.setBounds(x-10, y-10, 20,20);
        
             
            boolean flag = false;
            
            for(int i = 0 ; i < Final.size() ; i++){
                
                 SinglePerceptron Sp = new SinglePerceptron(2);
                 double inputs[] = {ScaleInputX(x),ScaleInputY(y)};
                 Sp.setInputs(inputs);
                 Sp.setWeight(0,Final.get(i).Get_Weight_0());
                 Sp.setWeight(1,Final.get(i).Get_Weight_1());
                 Sp.setThresh(Final.get(i).Get_Threshold());
                 Sp.calculateActualOutput();
                 double output = Sp.getOutput();
                 
                 
                  if(output == -1){
                      
                      if(Calculate_Number_Of_Classes() == 2)
                          flag = true;
                      
                      int Type = Final.get(i).Get_Type_Of_Class();
                      if(Type == 1)
                         JOptionPane.showMessageDialog(null, "This belongs to triangle class ");
                      else if(Type == 2)
                         JOptionPane.showMessageDialog(null, "This belongs to square class ");
                      else if (Type == 3)
                         JOptionPane.showMessageDialog(null, "This belongs to rectangle class ");
                      else if (Type == 4)
                         JOptionPane.showMessageDialog(null, "This belongs to hexagon class ");
            }
                
            }
            
            
            //This is for 2 classes
            if(flag == false && Calculate_Number_Of_Classes() == 2){
                int counter = 0 ; 
                int i ;
                for(i = 1 ; i <= 4 ; i++){
                    if(findInstance(Array_Of_Inputs,i)){
                        counter++;
                        
                        if(counter == 2)
                            break;
                        
                    }
                }
                  if(i == 1)
                         JOptionPane.showMessageDialog(null, "This belongs to triangle class ");
                      else if(i == 2)
                         JOptionPane.showMessageDialog(null, "This belongs to square class ");
                      else if (i == 3)
                         JOptionPane.showMessageDialog(null, "This belongs to rectangle class ");
                      else if (i == 4)
                         JOptionPane.showMessageDialog(null, "This belongs to hexagon class ");
            }
            
            
            templateLabel.setIcon(null);
            
        }
        else if(Type_Of_Class != 0){
            //Display the icon on the screen
            int x = evt.getX();
            int y = evt.getY();
            javax.swing.JLabel templateLabel = new javax.swing.JLabel() ;
            templateLabel.setText("");
            panel3.setLayout(null);
            templateLabel.setIcon(iconData);
            panel3.add(templateLabel);
            templateLabel.setBounds(x-10, y-10, 20,20);
            
            //Add the Label to the Array_of_jLabels
            Array_of_jLabels.add(templateLabel);
            
            //Create a point 
            Point p = new Point();
            p.setX(ScaleInputX(x));
            p.setY(ScaleInputY(y));
            p.setTyoeOfClass(Type_Of_Class);
            
            
            //Add the point to the Table
            model = (DefaultTableModel) table.getModel();
            model.insertRow(table_counter, new Object[] {p.getX() , p.getY() ,p.getTyoeOfClass() });
            table_counter++;
            
            //Add the point to the Array_Of_Inputs
            Array_Of_Inputs.add(p);

        }

    }//GEN-LAST:event_panel3MousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     panel3.clearGraph();
     
     jTextField2.setText("");
     jTextField1.setText("");
     jComboBox1.setSelectedIndex(0);
     jTextField3.setText("");
     jTextField4.setText("");
     jTextField5.setText("");
     jTextField7.setText("");
     
     for(javax.swing.JLabel L:Array_of_jLabels){
        panel3.remove(L);
     }
     
     Array_Of_Inputs.clear();
     Testing_Data.clear();
     Array_of_jLabels.clear();
     Final.clear();
     
     
     model.setRowCount(0);
     model.setRowCount(21);
     table_counter = 0 ;
     
     Train_flag = false;
     
     
     Take_Testing_Data = false ;

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      
    Type_Of_Class  = 0 ;
        
    if(Train_flag == false){
        JOptionPane.showMessageDialog(null, "Please train your data before testing", "Message" , JOptionPane.WARNING_MESSAGE);
    }
    else{
      iconData = new ImageIcon(getClass().getResource("/pictures/x.png"));
      Out_Of_Sample_flag = true;
    }

       
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private ai_project2.panel panel2;
    private ai_project2.panel panel3;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
