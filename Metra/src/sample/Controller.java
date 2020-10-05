package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Controller {

    public int operators;
    public int operands;

    public int numOperators;
    public int numOperands;

    public int numProgram;
    public int lengthProgram;
    public double volProgram;

    private ObservableList<Operators> operator = FXCollections.observableArrayList();
    private ObservableList<Operands> operand = FXCollections.observableArrayList();

    @FXML
    private Pane pane;

    @FXML
    private TextArea scanCode;

    @FXML
    private Button btnScan;

    @FXML
    private TableView<Operators> tableOperator;

    @FXML
    private TableColumn<Operators, Integer> jColumn;

    @FXML
    private TableColumn<Operators, String> operatorColumn;

    @FXML
    private TableColumn<Operators, Integer> f1jColumn;

    @FXML
    private TableView<Operands> tableOperand;

    @FXML
    private TableColumn<Operands, Integer> iColumn;

    @FXML
    private TableColumn<Operands, String> operandColumn;

    @FXML
    private TableColumn<Operands, Integer> f2iColumn;

    @FXML
    private TextArea info;


    @FXML
    void initialize() {
        jColumn.setCellValueFactory(new PropertyValueFactory<Operators, Integer>("j"));
        operatorColumn.setCellValueFactory(new PropertyValueFactory<Operators, String>("operator"));
        f1jColumn.setCellValueFactory(new PropertyValueFactory<Operators, Integer>("f1j"));

        iColumn.setCellValueFactory(new PropertyValueFactory< Operands, Integer>("i"));
        operandColumn.setCellValueFactory(new PropertyValueFactory<Operands, String>("operand"));
        f2iColumn.setCellValueFactory(new PropertyValueFactory<Operands, Integer>("f2i"));

        Func func = new Func();

        String[] allOperators = {" + ", " - ", " * ", "/", "%", "++", "--", "**", " = ", " == ", "===", " != ", "!==", "<>", " & ", "^", " | ", "~", "<<", ">>", " < ", " > ",
                                 "<= ",">=", "<=>", "and", " or", "xor", "&&", "||", "!", ".", ".= ", "(", "{", " if", " else", " elseif", ",", "+=", "-=", "/=", "%=", "*="};

        btnScan.setOnAction(event -> {
            tableOperator.getItems().clear();
            tableOperand.getItems().clear();

            operators = 0;
            operands = 0;

            numOperators = 0;
            numOperands = 0;

            numProgram = 0;
            lengthProgram = 0;
            volProgram = 0;

            String newString;

            newString = scanCode.getText();

            boolean proverkaOdno = true, proverkaMnogo = true;

            char[] mat = newString.toCharArray();
            char[] stringWithoutComment = new char[mat.length];

            for (int i = 0; i < mat.length; i++) {
                if (i != mat.length - 1 && mat[i] == '/' && mat[i + 1] == '/') {
                    proverkaOdno = false;
                }
                if (!proverkaOdno && mat[i] == '\n') {
                    proverkaOdno = true;
                }
                if (i != mat.length - 1 && mat[i] == '/' && mat[i + 1] == '*') {
                    proverkaMnogo = false;
                }
                if (i != 0 && !proverkaMnogo && mat[i - 1] == '*' && mat[i] == '/') {
                    proverkaMnogo = true;
                }
                if (proverkaOdno && proverkaMnogo) {
                    stringWithoutComment[i] = mat[i];
                }
            }

            String newStringWithout = "";

            for (int i = 0; i < stringWithoutComment.length; i++){
                newStringWithout += stringWithoutComment[i];
            }

            char[] charNewString = newStringWithout.toCharArray();

            String[] allOperands = new String[newStringWithout.split("\n").length];

            int cutStart = 0, cutEnd = 0;
            boolean cut = true;
            int allOperandsLength = 0;

            for (int i = 0; i < charNewString.length; i++){
                if (charNewString[i] == '$' && cut){
                    cutStart = i;
                    cut = false;
                }
                if ((charNewString[i] == ' ' || charNewString[i] == ')' || charNewString[i] == '(' || charNewString[i] == '[' || charNewString[i] == ',' || charNewString[i] == '-' || charNewString[i] == ';' || charNewString[i] == '\'' || charNewString[i] == '+' || charNewString[i] == ']' || charNewString[i] == '.' || charNewString[i] == '\"') && !cut){
                    cutEnd = i;
                    allOperands[allOperandsLength] = newStringWithout.substring(cutStart, cutEnd);
                    allOperandsLength++;
                    cut = true;
                }
            }

            int count = 0;

            for (int i = 0; i < allOperandsLength; i++){
                if (allOperands[i] != "null"){
                    count++;
                }
            }

            String newAllOperands[] = new String[count];
            for (int i = 0; i < count; i++){
                newAllOperands[i] = allOperands[i];
            }

            for (int i = 0; i < allOperators.length; i++){
                if (newStringWithout.contains(allOperators[i])){
                    operators++;
                }
            }

            String[] operatorsProgram = new String[operators];

            Set<String> set = new HashSet<String>(Arrays.asList(newAllOperands));
            String[] operandsProgram = set.toArray(new String[set.size()]);

            operands = operandsProgram.length;

            int k = 0;
            for (int i = 0; i < allOperators.length; i++){
                if (newStringWithout.contains(allOperators[i])){
                    operatorsProgram[k] = allOperators[i];
                    k++;
                }
            }

            for (int i = 0; i < operators; i++){
                numOperators += func.searchWord(newStringWithout, operatorsProgram[i]);
            }

            for (int i = 0; i < operands; i++){
                numOperands += func.searchWord(newStringWithout, operandsProgram[i]);
            }

            int num;

            for (int i = 0; i < operators; i++){
                num = func.searchWord(newStringWithout, operatorsProgram[i]);
                if (operatorsProgram[i].equals("(")){
                    operator.add(new Operators(i + 1, "(...)", num));
                } else if (operatorsProgram[i].equals("{")){
                    operator.add(new Operators(i + 1, "{...}", num));
                } else {
                    operator.add(new Operators(i + 1, operatorsProgram[i], num));
                }
            }

            for (int i = 0; i < operands; i++){
                num = func.searchWord(newStringWithout, operandsProgram[i]);
                operand.add(new Operands(i + 1, operandsProgram[i], num));
            }

            tableOperator.setItems(operator);
            tableOperand.setItems(operand);

            tableOperator.setItems(operator);

            numProgram = operators + operands;
            lengthProgram = numOperators + numOperands;
            volProgram = lengthProgram * (Math.log(numProgram) / Math.log(2));

            long newVolProgram = Math.round(volProgram);

            String outText;
            outText = "Количество уникальных операторов = " + operators;
            outText += "\nКоличество уникальных операндов = " + operands;
            outText += "\nОбщее число операторов = " + numOperators;
            outText += "\nОбщее число операндов = " + numOperands;
            outText += "\nСловарь программы = " + numProgram;
            outText += "\nДлина программы = " + lengthProgram;
            outText += "\nОбъём программы = " + newVolProgram;

            info.setText(outText);
        });
    }
}
