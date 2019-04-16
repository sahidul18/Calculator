import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application 
{
    String number1 = "";
    String number2 = "";
    BigDecimal bigDecimal1;
    BigDecimal bigDecimal2;
    boolean equalPressed = false;
    boolean operatorPressed = false;

    

    String operator = "";
    String sqrSymbol = "x" + "\u00B2";
    String cubeSymbol = "x" + "\u00B3";
    String sqrtSymbol = "\u00B2" + "\u221A";
    String cubeRtSymbol = "\u221B";
    String nPowerSymbol = ("x" + "\u207F");
    String modular = "%";
    
    Label lblFinalResult = new Label("");
    
    String[] superScript = {"\u2070", "\u00B9", "\u00B2", "\u00B3", "\u2074",
            "\u2075", "\u2076", "\u2077", "\u2078", "\u2079"
        };

   Button btnZero = new Button("0");
   Button btnOne = new Button("1");
   Button btnTwo = new Button("2");
   Button btnThree = new Button("3");
   Button btnFour = new Button("4");
   Button btnFive = new Button("5");
   Button btnSix = new Button("6");
   Button btnSeven = new Button("7");
   Button btnEight = new Button("8");
   Button btnNine = new Button("9");
   Button btnDecimal = new Button(".");

   //operators
   Button btnTimes = new Button("x");
   Button btnMinus = new Button("-");
   Button btnAdd = new Button("+");
   Button btnDivide = new Button("\u00F7");
   Button btnMod = new Button("%");
   Button btnFactorial = new Button("x!");
   Button btnSquaredRoot = new Button(sqrtSymbol);
   Button btnCubedRoot = new Button("\u221B"); 
   Button btnXsquared = new Button("x" + "\u00B2");
   Button btnXcubed= new Button("x" + "\u00B3");
   Button btnXtoTheN = new Button("x" + "\u207F");

   Button btnEqual = new Button("=");
   Button btnClear = new Button("Clear");
   Button btnPI = new Button("\u03C0");

   BorderPane borderPane = new BorderPane();
   TextField txtFieldInputWindow = new TextField();
   GridPane calculatorPane = new GridPane();

    @Override
    public void start(Stage primaryStage) 
    {      
        lblFinalResult.setFont(Font.font(20));
        lblFinalResult.setPadding(new Insets(10));
        txtFieldInputWindow.setFont(Font.font(20));

        setUpCalculator();
       
        // Reset everything when clear button is pressed. 
        btnClear.setOnAction(e ->{
            txtFieldInputWindow.setText("");
            lblFinalResult.setText("");
            number1 = number2 = "";
            equalPressed = false;
            operatorPressed = false;
        });
      
        Scene scene = new Scene(borderPane, 453, 375);
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static String checkIfZeroAfter (String arr)
    {   
        String retString = "";
        
        if(arr.matches("[0-9]{1,}.0+"))
         {
            String [] array = arr.split("");
            boolean found = false;
            
            for(int i = 0; i < arr.length(); i++)
            {
                if(array[i].equals("."))
                   found = true;

                if(found == true)
                   array[i] = "";
            }
      
            for (String array1 : array) {
                retString += array1;
            }
        }
        else 
            return arr;
        
       return retString; 
    }

    public void setUpCalculator()
    {
       // Creating Event Handler Class Objects
       NumberHandler numberHandler = new NumberHandler();
       OperatorsHandler operatorHandler = new OperatorsHandler();
       EqualHandler equalHandler = new EqualHandler();
       SpecialOperationHandler specialHandler = new SpecialOperationHandler();
       
       // Adding action to the buttons
       btnZero.setOnAction(numberHandler);
       btnOne.setOnAction(numberHandler);
       btnTwo.setOnAction(numberHandler);
       btnThree.setOnAction(numberHandler);
       btnFour.setOnAction(numberHandler);
       btnFive.setOnAction(numberHandler);
       btnSix.setOnAction(numberHandler);
       btnSeven.setOnAction(numberHandler);
       btnEight.setOnAction(numberHandler);
       btnNine.setOnAction(numberHandler);
       btnDecimal.setOnAction(numberHandler);
       
       // Operators handler
       btnAdd.setOnAction(operatorHandler); 
       btnMinus.setOnAction(operatorHandler);
       btnDivide.setOnAction(operatorHandler);
       btnTimes.setOnAction(operatorHandler);
       btnMod.setOnAction(operatorHandler);
       btnXtoTheN.setOnAction(operatorHandler);
       btnEqual.setOnAction(equalHandler); 
       
       // handles n!, sqr root, and other special operations
       btnXsquared.setOnAction(specialHandler);
       btnXcubed.setOnAction(specialHandler);
       btnSquaredRoot.setOnAction(specialHandler);
       btnCubedRoot.setOnAction(specialHandler);
       btnFactorial.setOnAction(specialHandler);
     
       styleButtons(); 
       
       calculatorPane.setStyle("-fx-background-color:  linear-gradient(to bottom, #003366 0%, #009999 100%);");
    
       txtFieldInputWindow.setPrefHeight(35);
       lblFinalResult.setPrefHeight(36);
       txtFieldInputWindow.setPrefColumnCount(49);
      // tfInputWindow.setText("0");
      
       
       // Taking input from keyboard
       txtFieldInputWindow.setOnKeyPressed(e ->{
            if(e.getText().matches("[0-9.]")){
                if(operatorPressed == false){
                number1 += e.getText();
                }
                else{
                    number2 += e.getText();
                }
            }
         
        }); // tfInputWindow.setOnKeyPressed ends
       
       VBox topBox = new VBox(5);
       topBox.getChildren().addAll(lblFinalResult, txtFieldInputWindow);
       borderPane.setTop(topBox);
       
       borderPane.setCenter(calculatorPane);
       
       //button components are added to the grid pane
       calculatorPane.setPadding(new Insets(10));
       calculatorPane.setHgap(14);
       calculatorPane.setVgap(9);
       calculatorPane.setHgap(14);
       calculatorPane.setVgap(9);
       
       //1st column from the left
       calculatorPane.add(btnSeven, 0, 0);
       calculatorPane.add(btnFour, 0, 1);
       calculatorPane.add(btnOne, 0, 2);
       calculatorPane.add(btnClear, 0, 3);
       //2nd column from left
       calculatorPane.add(btnEight, 1, 0);
       calculatorPane.add(btnFive, 1, 1);
       calculatorPane.add(btnTwo, 1, 2);
       calculatorPane.add(btnZero, 1, 3);
       //3rd column from left
       calculatorPane.add(btnNine, 2, 0);
       calculatorPane.add(btnSix, 2, 1);
       calculatorPane.add(btnThree, 2, 2);
       calculatorPane.add(btnEqual, 2, 3);
       //4th row from left, operators
       calculatorPane.add(btnAdd, 3, 0);
       calculatorPane.add(btnMinus, 3, 1);
       calculatorPane.add(btnDivide, 3, 2);
       calculatorPane.add(btnDecimal, 3, 3);
       //5th row from left
       calculatorPane.add(btnTimes, 4, 0);
       calculatorPane.add(btnSquaredRoot, 4, 2);
       calculatorPane.add(btnXsquared, 4, 1);
       calculatorPane.add(btnMod, 4, 3);
       //6th row from left
       calculatorPane.add(btnXtoTheN, 5, 0);
       calculatorPane.add(btnXcubed, 5, 1);
       calculatorPane.add(btnCubedRoot, 5, 2);
       calculatorPane.add(btnFactorial, 5, 3);
      
    }
    
    public void styleButtons()
    {
    	Group groupNumbers = new Group();
    	Group groupSymbols = new Group(); 

        groupNumbers.getChildren().addAll
        (
            btnZero,btnOne,btnTwo,btnThree,
            btnFour,btnFive,btnSix,btnSeven,btnEight,btnNine
        );
        
        groupSymbols.getChildren().addAll
        (       
            btnTimes,btnMinus,
            btnAdd,btnDivide,btnMod,btnFactorial,btnCubedRoot,btnSquaredRoot,
            btnXsquared, btnXcubed, btnXtoTheN, btnClear, btnEqual, btnDecimal, btnPI
        );
        
        ObservableList<?> lstOfNumbers = groupNumbers.getChildren();
        ObservableList<?> lstOfSymbols = groupSymbols.getChildren();
        
        // Styling the numbers
        for(int i  = 0; i < lstOfNumbers.size(); i++)
        {
            Button btnStyleNumbers = (Button)lstOfNumbers.get(i);
            btnStyleNumbers.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #f2f2f2"
                        ); //end style 
            
            btnStyleNumbers.setOnMousePressed(e -> {
            
                btnStyleNumbers.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #3d5c5c"
                        ); //end style 
                
            btnStyleNumbers.setTextFill(Color.WHITE);
            //bt.setFont(Font.font(24));
            
            });
            
            btnStyleNumbers.setOnMouseReleased(e -> {
            
             btnStyleNumbers.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #f2f2f2"
                        ); //end style 
             
              btnStyleNumbers.setTextFill(Color.BLACK);
            
            });
            
        }
     
       // Styling the symbols 
      for(int j  = 0; j < lstOfSymbols.size(); j++)
        {
            Button btnStyleSymbols = (Button)lstOfSymbols.get(j);
            btnStyleSymbols.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #3d5c5c"
                        ); //end style 
            btnStyleSymbols.setTextFill(Color.WHITE);
            btnStyleSymbols.setFont(Font.font(24));
            
             btnStyleSymbols.setOnMousePressed(e -> {
             btnStyleSymbols.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #f2f2f2"
                        ); //end style 
             
              btnStyleSymbols.setTextFill(Color.BLACK);
            
            });
            
            btnStyleSymbols.setOnMouseReleased(e -> {
            
           btnStyleSymbols.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #3d5c5c"
                        ); //end style 
            btnStyleSymbols.setTextFill(Color.WHITE);
            });
            
        }
      
      btnCubedRoot.setFont(Font.font(22));
      btnSquaredRoot.setFont(Font.font(22));
      btnClear.setFont(Font.font(14));
      
    }
    
    // This class handles the numbers
    class NumberHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            String numberValue = ((Button)e.getSource()).getText();
            if(operatorPressed == false)
            {
                number1 += numberValue;
                txtFieldInputWindow.setText(number1);
            }
            else
            {
                
                if(!numberValue.matches("[0-9.]"))
                {
                    lblFinalResult.setText("Error");
                    number1 = "";
                    operatorPressed = false;
                }
                else
                {
                    number2 += numberValue;
                   txtFieldInputWindow.setText(number2);
                }
            }
        } // End of handle method
        
    } // End of NumberHandler class
    
    // This inner class handles operators: +, -, *, /, %, exponent
    class OperatorsHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            if(number1.length() == 0 || operatorPressed == true)
            {
                lblFinalResult.setText("Error");
                number1 = number2 = "";
                txtFieldInputWindow.setText("");
                operatorPressed = false;
            }
            else
            {  
                operatorPressed = true;
                operator = ((Button)e.getSource()).getText();
                txtFieldInputWindow.setText("");
            }
        } // End of handle method
        
    } // End of OperatorsHandler class
    
    // This class calculates +, -, *, /, %, exponent
    class EqualHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            MathContext mathContext = new MathContext(50);
            equalPressed = true;
            lblFinalResult.setText("");
            if(number1.equals("") || number2.equals("") || number1.equals(".") || number2.equals("."))
            {
                lblFinalResult.setText("Error");
                number1 = number2 = "";
            }
            else
            {
                bigDecimal1 = new BigDecimal(number1);
                bigDecimal2 = new BigDecimal(number2);
                String strResult = "";
                
                if(operator.equals("+"))
                { 
                   strResult = bigDecimal1.add(bigDecimal2) + "";
                   strResult = checkIfZeroAfter ( strResult );
                   
                }
                else if(operator.equals("-"))
                {
                    strResult = bigDecimal1.subtract(bigDecimal2) + "";
                    strResult = checkIfZeroAfter ( strResult );
                }
                else if(operator.equals("x"))
                {
                    strResult = bigDecimal1.multiply(bigDecimal2) + "";
                    strResult = checkIfZeroAfter ( strResult );
                }
                else if(operator.equals("\u00F7"))
                {
                    if(number2.equals("0") || number2.equals("."))
                        strResult = "Error";
                    else
                        strResult = bigDecimal1.divide(bigDecimal2, mathContext) + "";
                }
                else if(operator.equals("%"))
                {
                    if(number2.equals("0"))
                        strResult = "Error";
                    else
                    strResult = bigDecimal1.remainder(bigDecimal2) + "";              
                }
                else if(operator.equals(nPowerSymbol))
                { 
                    
                    if(number2.contains(".") || Double.parseDouble(number2) > 999999999)
                        strResult = "Error";
                   
                    else
                    {
                        //trims leading zeros for neatness, avoids octal conversions
                        number2 = number2.replaceFirst("^0+(?!$)", "");
                        int n = Integer.valueOf(number2);
                        BigDecimal exponentValue = bigDecimal1.pow(n);
                        strResult = checkIfZeroAfter(exponentValue + "");
                    }  
                    
                }
                
                if(strResult.length() + number1.length() + number2.length() > 40)
                {

                    if(strResult.equals("Error"))
                        lblFinalResult.setText("Error");
                    else{
                        Label label2 = new Label("");
                        label2.setWrapText(true);

                        if(operator.equals(nPowerSymbol)){
                            String tempNum2 = "";
                            for(int i = 0; i < number2.length(); i++){
                                int index = Integer.parseInt(number2.charAt(i)+"");
                                tempNum2 += superScript[index];
                            }
                            label2.setText(number1 + "" + tempNum2 + " = " + strResult);
                        }
                        else if(operator.equals("X"))
                            label2.setText(number1 + " * " + number2 + " = " + strResult);    
                        else
                            label2.setText(number1 + " " + operator + " " + number2 + " = " + strResult);               

                        lblFinalResult.setText("");

                        VBox vBoxSecondScene = new VBox(5);
                        vBoxSecondScene.getChildren().addAll(label2);

                        Scene scene2 = new Scene(vBoxSecondScene, 300, 210);
                        Stage stage2 = new Stage();
                        stage2.setScene(scene2);
                        stage2.show();
                    }
                }
                else
                {
                    if(strResult.equals("Error")) 
                        lblFinalResult.setText("Error");
                    else
                    {
                        if(operator.equals(nPowerSymbol))
                        {
                            String tempNum2 = "";
                            
                            for(int i = 0; i < number2.length(); i++)
                            {
                                int index = Integer.parseInt(number2.charAt(i)+"");
                                tempNum2 += superScript[index];
                            }
                            
                            lblFinalResult.setText(number1 + "" + tempNum2 + " = " + strResult);
                        }
                        else if(operator.equals("X"))
                            lblFinalResult.setText(number1 + " * " + number2 + " = " + strResult);
                        
                        else if(operator.equals("÷") && strResult.equals("Error"))
                            lblFinalResult.setText("Error");
                        else
                            lblFinalResult.setText(number1 + " " + operator + " " + number2 + " = " + strResult);
                        
                    } 
                }
            }
            
            txtFieldInputWindow.setText("");
            number1 = "";
            number2 = "";
            operator = "";
            operatorPressed = equalPressed = false;
            
        }
    } // End of EqualHandler class
    
    // This class calculates sqrt, cubeRt, squared, cubed, x!
    class SpecialOperationHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
         
         String strResult = "";
         lblFinalResult.setText("");
            
            if(number1.equals("") || number1.equals("."))
            {
                lblFinalResult.setText("0");
                txtFieldInputWindow.setText("");
                number1 = "";
            }
            else if(number2.length() != 0 || operatorPressed == true)
            {
                lblFinalResult.setText("Error");
                number1 = number2 = "";
                operatorPressed = false;
            }
            else
            {
                String operation = ((Button)e.getSource()).getText();
                bigDecimal1 = new BigDecimal(number1);
                
                if(operation.equals(sqrSymbol))
                    strResult = bigDecimal1.multiply(bigDecimal1) + "";
                
                else if(operation.equals(cubeSymbol))
                    strResult = bigDecimal1.multiply(bigDecimal1.multiply(bigDecimal1))+""; 
                
                else if(operation.equals(sqrtSymbol))
                {
                    double sqrtNum = Math.sqrt(Double.parseDouble(number1));
                    BigDecimal sqrRootResult = new BigDecimal(sqrtNum + "");
                    String s = sqrRootResult + "";
                    
                    if(s.contains("."))
                    {
                        BigDecimal withoutZero = sqrRootResult.stripTrailingZeros();
                        strResult = withoutZero + "";
                    }
                    else
                      strResult = new BigDecimal(sqrtNum + "") + "";                     
                    
                }
                else if(operation.equals(cubeRtSymbol))
                {
                    double cubeRtNum = Math.cbrt(Double.parseDouble(number1));
                    BigDecimal cubeRootResult = new BigDecimal(cubeRtNum + "");
                    String s = cubeRootResult + "";
                    
                    if(s.contains("."))
                    {
                        BigDecimal withoutZero = cubeRootResult.stripTrailingZeros();
                        strResult = withoutZero + "";
                    }
                    else
                        strResult = cubeRootResult + "";                     
                    
                }
            else if(operation.equals("x!"))
            {           
                BigDecimal limit = new BigDecimal("100000");
                BigDecimal big1 = new BigDecimal(number1);
                
                if(big1.compareTo(limit) == 0 || big1.compareTo(limit) == -1)
                {
                    if(number1.contains(".")){
                            strResult = "Error";
                        }
                    else{
                        int intNum = Integer.parseInt(number1);
                        BigInteger sum = new BigInteger("1");
                        for(int i = 1; i <= intNum; i++) 
                            sum = sum.multiply(new BigInteger(i + ""));
                        
                        strResult = sum +"";
                    }
                }
                else
                    strResult = "Error";
                
            } 
                
                // Displays answer in new window if answer too big for Label
                if(strResult.length() + number1.length() > 40)
                {
                    Label label2 = new Label("");
                    label2.setWrapText(true);
                    
                    if(operation.equals(sqrtSymbol))
                        label2.setText(sqrtSymbol + "" + number1 + " = " + strResult);
                    
                    else if(operation.equals(cubeRtSymbol))
                        label2.setText(cubeRtSymbol + "" + number1 + " = " + strResult);
                    
                    else if(operation.equals("x!"))
                        label2.setText(number1 + "! = " + strResult);
                    
                    else if(operation.equals(sqrSymbol))
                        label2.setText(number1 + "" + superScript[2] + " = " + strResult);
                    
                    else if(operation.equals(cubeSymbol))
                        label2.setText(number1 + "" + superScript[3] + " = " + strResult);
                    

                    VBox vBoxSecondScene = new VBox(5);
                    vBoxSecondScene.getChildren().addAll(label2);

                    Scene scene2 = new Scene(vBoxSecondScene, 300, 210);
                    Stage stage2 = new Stage();
                    stage2.setScene(scene2);
                    stage2.show();
                    
                }
                else
                {
                    if(strResult.equals("Error"))
                        lblFinalResult.setText("Error");    
                    else if(operation.equals(sqrtSymbol))
                        lblFinalResult.setText(sqrtSymbol + "" + number1 + " = " + strResult);
                    else if(operation.equals(cubeRtSymbol))
                        lblFinalResult.setText(cubeRtSymbol + "" + number1 + " = " + strResult);
                    else if(operation.equals("x!"))
                        lblFinalResult.setText(number1 + "! = " + strResult);
                    else if(operation.equals(sqrSymbol))
                        lblFinalResult.setText(number1 + "" + superScript[2] + " = " + strResult);
                    else if(operation.equals(cubeSymbol))
                        lblFinalResult.setText(number1 + "" + superScript[3] + " = " + strResult);
                    
                }    
            }
            
            number1 = operator = "";
            txtFieldInputWindow.setText("");
    
        }
    } // End of SpecialOperationHandler class
    
      public static void main(String[] args) 
      {
        launch(args);
      }  
} // End of main Calculator class (end of program)
