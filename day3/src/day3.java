import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day3 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;

            String[] elements = null;
            int res = 0;

            while ((line = bufferedReader.readLine()) != null) {
                int indexMul = 0;
                int indexDo = 0;
                boolean tryingDo = false;

                boolean canDo = true;

                String mul1 = "";
                String mul2 = "";
                for (char c : line.toCharArray()) {

                    if (c == 'd' && indexDo == 0) {
                        indexMul = 0;
                        indexDo = 1;
                    } else if (c == 'o' && indexDo == 1) {
                        indexDo = 2;
                    } else if (c == '(' && indexDo == 2) {
                        indexDo = 3;
                        tryingDo = true;
                    } else if (c == 'n' && indexDo == 2) {
                        indexDo = 3;
                        tryingDo = false;
                    } else if (indexDo == 3 && tryingDo && c == ')') {
                        indexDo = 0;
                        canDo = true;
                    } else if (indexDo == 3 && !tryingDo && c == '\'') {
                        indexDo = 4;
                    } else if (indexDo == 4 && c == 't') {
                        indexDo = 5;
                    } else if (indexDo == 5 && c == '(') {
                        indexDo = 6;
                    } else if (indexDo == 6 && c == ')') {
                        indexDo = 0;
                        canDo = false;
                    } else {
                        indexDo = 0;
                    }
                    
                    if (c == 'm' && indexMul == 0) {
                        indexDo = 0;
                        indexMul = 1;
                    } else if (c == 'u' && indexMul == 1) {
                        indexMul = 2;
                    } else if (c == 'l' && indexMul == 2) {
                        indexMul = 3;
                    } else if (c == '(' && indexMul == 3) {
                        indexMul = 4;
                    } else if (isNumber(c) && indexMul == 4) {
                        mul1 += c;
                    } else if (!isNumber(c) && indexMul == 4) {
                        if (c == ',') {
                            indexMul = 5;
                        } else {
                            indexMul = 0;
                            mul1 = "";
                            mul2 = "";
                        }
                    } else if (isNumber(c) && indexMul == 5) {
                        mul2 += c;
                    } else if (!isNumber(c) && indexMul == 5) {
                        if (c == ')' && canDo) {
                            indexMul = 0;
                            res += Integer.parseInt(mul1) * Integer.parseInt(mul2);
                            System.out.println(mul1 + " * " + mul2 + " = " + Integer.parseInt(mul1) * Integer.parseInt(mul2));
                            mul1 = "";
                            mul2 = "";
                        } else {
                            indexMul = 0;
                            mul1 = "";
                            mul2 = "";
                        }
                    } else {
                        indexMul = 0;
                        mul1 = "";
                        mul2 = "";
                    }
                    
                }
            }

            System.out.println(res);
        }
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
}
