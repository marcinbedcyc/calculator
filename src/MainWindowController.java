import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainWindowController {
    private double calcMemory;

    public VBox getMainVBox() {
        return mainVBox;
    }

    @FXML
    private VBox mainVBox;


    public TextField getResultTextField() {
        return resultTextField;
    }

    @FXML
    private TextField resultTextField;

    @FXML
    private Button addMemoryButton;

    @FXML
    private Button showMemoryButton;

    @FXML
    private Button memoryCleanButton;

    @FXML
    private Button cleanButton;

    @FXML
    private Button sevenButton;

    @FXML
    private Button eightButton;

    @FXML
    private Button nineButton;

    @FXML
    private Button plusButton;

    @FXML
    private Button fourButton;

    @FXML
    private Button fiveButton;

    @FXML
    private Button sixButton;

    @FXML
    private Button minusButton;

    @FXML
    private Button oneButton;

    @FXML
    private Button twoButton;

    @FXML
    private Button threeButton;

    @FXML
    private Button multipleButton;

    @FXML
    private Button equalButton;

    @FXML
    private Button zeroButton;

    @FXML
    private Button commaButton;

    @FXML
    private Button divideButton;

    @FXML
    void initialize() {
        calcMemory = 0;
        setOnKeysPressed();
        setPrefWidthButtons();
    }

    @FXML
    void addToMemory() {
        try {
            double memory = Double.parseDouble(resultTextField.getText());
            calcMemory += memory;
        }
        catch (NumberFormatException e){
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");

            try {
                calcMemory += Double.parseDouble(engine.eval(resultTextField.getText()).toString());
            } catch (ScriptException e1) {
                System.err.println(new String("Not complete expression"));
            }
        }
        catch (NullPointerException e){
            System.err.println(new String("Empty expression"));
        }
    }

    @FXML
    void cleanMemory() {
        calcMemory = 0;
    }

    @FXML
    void getFromMemory() {
        String result = resultTextField.getText();
        if(calcMemory != 0 && !Character.isDigit(result.charAt(result.length()-1)))
            resultTextField.appendText(String.valueOf(calcMemory));
    }

    @FXML
    void clean(){
        resultTextField.setText("");
    }

    @FXML
    void compute() {
        try {
            double result = Double.parseDouble(resultTextField.getText());
        }
        catch (NumberFormatException exception) {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            try {
                System.out.println(engine.eval(resultTextField.getText()));
                resultTextField.setText(engine.eval(resultTextField.getText()).toString());
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void putComma() {
        String result = resultTextField.getText();
        if(!result.isEmpty() && Character.isDigit(result.charAt(result.length()-1)))
            resultTextField.appendText(".");
    }

    @FXML
    void putDivide() {
        String result = resultTextField.getText();
        if(!result.isEmpty() && Character.isDigit(result.charAt(result.length()-1)))
            resultTextField.appendText("/");
    }

    @FXML
    void putEight() {
        resultTextField.appendText("8");
    }

    @FXML
    void putFive() {
        resultTextField.appendText("5");
    }

    @FXML
    void putFour() {
        resultTextField.appendText("4");
    }

    @FXML
    void putMinus() {
        String result = resultTextField.getText();
        if(!result.isEmpty() && Character.isDigit(result.charAt(result.length()-1)))
            resultTextField.appendText("-");
    }

    @FXML
    void putMultiple() {
        String result = resultTextField.getText();
        if(!result.isEmpty() && Character.isDigit(result.charAt(result.length()-1)))
            resultTextField.appendText("*");
    }

    @FXML
    void putNine() {
        resultTextField.appendText("9");
    }

    @FXML
    void putOne() {
        resultTextField.appendText("1");
    }

    @FXML
    void putPlus() {
        String result = resultTextField.getText();
        if(!result.isEmpty() && Character.isDigit(result.charAt(result.length()-1)))
            resultTextField.appendText("+");
    }

    @FXML
    void putSeven() {
        resultTextField.appendText("7");
    }

    @FXML
    void putSix() {
        resultTextField.appendText("6");
    }

    @FXML
    void putThree() {
        resultTextField.appendText("3");
    }

    @FXML
    void putTwo() {
        resultTextField.appendText("2");
    }

    @FXML
    void putZero() {
        String result = resultTextField.getText();
        if(result.isEmpty())
            resultTextField.appendText("0");
        else
            if(!result.substring(result.length()-1).equals("/"))
                resultTextField.appendText("0");
    }

    private void setOnKeysPressed(){
        mainVBox.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.DIGIT1)
                putOne();
            else if(event.getCode() == KeyCode.DIGIT2)
                putTwo();
            else if(event.getCode() == KeyCode.DIGIT3)
                putThree();
            else if(event.getCode() == KeyCode.DIGIT4)
                putFour();
            else if(event.getCode() == KeyCode.DIGIT5)
                putFive();
            else if(event.getCode() == KeyCode.DIGIT6)
                putSix();
            else if(event.getCode() == KeyCode.DIGIT7)
                putSeven();
            else if(event.getCode() == KeyCode.DIGIT8 && event.isShiftDown())
                putMultiple();
            else if(event.getCode() == KeyCode.DIGIT8)
                putEight();
            else if(event.getCode() == KeyCode.DIGIT9)
                putNine();
            else if(event.getCode() == KeyCode.DIGIT0)
                putZero();
            else if(event.getCode() == KeyCode.EQUALS && event.isShiftDown())
                putPlus();
            else if(event.getCode() == KeyCode.EQUALS || event.getCode() == KeyCode.ENTER)
                compute();
            else if(event.getCode() == KeyCode.MINUS)
                putMinus();
            else if(event.getCode() == KeyCode.SLASH)
                putDivide();
            else if(event.getCode() == KeyCode.PERIOD || event.getCode() == KeyCode.COMMA)
                putComma();
            else if(event.getCode() == KeyCode.BACK_SPACE && event.isShiftDown())
                clean();
            else if(event.getCode() == KeyCode.BACK_SPACE){
                String result = resultTextField.getText();
                if(!result.isEmpty())
                    resultTextField.setText(result.substring(0, result.length()-1));
            }
        });
    }

    private void setPrefWidthButtons(){
        memoryCleanButton.setPrefWidth(mainVBox.getWidth()/4);
        cleanButton.setPrefWidth(mainVBox.getWidth()/4);
        addMemoryButton.setPrefWidth(mainVBox.getWidth()/4);
        showMemoryButton.setPrefWidth(mainVBox.getWidth()/4);
        oneButton.setPrefWidth(mainVBox.getWidth()/4);
        twoButton.setPrefWidth(mainVBox.getWidth()/4);
        threeButton.setPrefWidth(mainVBox.getWidth()/4);
        fourButton.setPrefWidth(mainVBox.getWidth()/4);
        fiveButton.setPrefWidth(mainVBox.getWidth()/4);
        sixButton.setPrefWidth(mainVBox.getWidth()/4);
        sevenButton.setPrefWidth(mainVBox.getWidth()/4);
        eightButton.setPrefWidth(mainVBox.getWidth()/4);
        nineButton.setPrefWidth(mainVBox.getWidth()/4);
        zeroButton.setPrefWidth(mainVBox.getWidth()/4);
        plusButton.setPrefWidth(mainVBox.getWidth()/4);
        minusButton.setPrefWidth(mainVBox.getWidth()/4);
        multipleButton.setPrefWidth(mainVBox.getWidth()/4);
        divideButton.setPrefWidth(mainVBox.getWidth()/4);
        commaButton.setPrefWidth(mainVBox.getWidth()/4);
        equalButton.setPrefWidth(mainVBox.getWidth()/4);
    }

    public void focus(){
        mainVBox.requestFocus();
    }
}
