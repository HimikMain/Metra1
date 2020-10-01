package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private TextArea scanCode;

    @FXML
    private Button btnScan;

   @FXML
    void initialize() {

        String operators[] = {"=", "+", "==", "-", "*", "\\\\", "%", "**", "!=", "<>", "!==", "&", "^", "<<",
                ">>", "<", ">", "<=", ">=", "<=>", "''", "@", "++", "--", "&&", "||", "!", "and",
                "xor", "or", ".", "instanceof", "?:", "?", "??", "clone new", "yield from", "yield", "print", "abs",
                "round", "ceil", "floor", "srand", "getrandmax", "rand", "base_convert", "bindec", "decbin", "dechex",
                "min", "max", "sqrt", "log", "pow", "sin", "cos", "acos", "if", "else", "elseif", "switch", "for", "while",
                "do", "foreach", "checkdate", "date", "getdate", "is_null", "is_integer", "is_string",
                "gettype", "empty", "isset", };
        //берем строку, сплитуем по пробелу, затем



        btnScan.setOnAction(event -> {
            String newString;

            newString = scanCode.getText();

            newString.split(" ");

            int operat;
            int operand;
            for (int i = 0; i < operators.length; i++){
                
            }

            System.out.println(newString);
        });


    }
}
