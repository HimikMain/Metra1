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

        String[] allOperators = {"+", "-", "*", "/", "%", "++", "--", "**", "=", "==", "===", "!=", "!==", "<>", "&", "^", "|", "~", "<<", ">>",
                                 "<", ">", "<=", ">=", "<=>", "and", "or", "xor", "&&", "||", "!", ".", ".=", "(", "{", "if", "else", "elseif" };

        btnScan.setOnAction(event -> {
            tableOperator.getItems().clear();

            operators = 0;
            operands = 0;

            numOperators = 0;
            numOperands = 0;

            numProgram = 0;
            lengthProgram = 0;
            volProgram = 0;

            String newString;

            newString = scanCode.getText();

            for (int i = 0; i < allOperators.length; i++){
                if (newString.contains(allOperators[i])){
                    operators++;
                }
            }

            String[] operatorsProgram = new String[operators];
            String[] operandsProgram = new String[operands];

            int k = 0;
            for (int i = 0; i < allOperators.length; i++){
                if (newString.contains(allOperators[i])){
                    operatorsProgram[k] = allOperators[i];
                    k++;
                }
            }

            for (int i = 0; i < operators; i++){
                numOperators += func.searchWord(newString, operatorsProgram[i]);
            }
            /*
            char[] check = {'='};

            boolean proverkaOdno = true;
            boolean proverkaMnogo = true;

            char[] mat = newString.toCharArray();

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
                if (i != mat.length - 1 && !proverkaMnogo && mat[i] == '*' && mat[i + 1] == '/') {
                    proverkaMnogo = true;
                }
                for (int j = 0; j < check.length && proverkaOdno && proverkaMnogo; j++) {
                    if (check[j] == mat[i]) {

                    }
                }
            }*/

            int num;

            for (int i = 0; i < operators; i++){
                num = func.searchWord(newString, operatorsProgram[i]);
                operator.add(new Operators(i + 1, operatorsProgram[i], num));
            }

            tableOperator.setItems(operator);

            for (int i = 0; i < operands; i++){
                num = func.searchWord(newString, operandsProgram[i]);
                operand.add(new Operands(i + 1, operandsProgram[i], num));
            }

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
