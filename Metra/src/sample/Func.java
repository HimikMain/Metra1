package sample;

public class Func {
    int searchWord(String string, String word) {
        if(word.equals())
        int i = string.indexOf(word);
        int count = 0;
        while (i >= 0) {
            count++;
            i = string.indexOf(word, i + 1);
        }
        return count;
    }
}
