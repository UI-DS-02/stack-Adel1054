import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static class Stack<T> {
        private final int maxSize;
        private int top;
        private final T[] stackArray;

        public Stack(int size) {
            maxSize = size;
            stackArray = (T[]) new Object[maxSize];
            top = -1;
        }

        public T top() {
            if (top >= 0) {
                return stackArray[top];
            } else return null;
        }

        public void push(T value) {
            if (top < maxSize - 1) {
                top++;
                stackArray[top] = value;
            }
        }

        public T pop() {
            if (top >= 0) {
                T value = stackArray[top];
                top--;
                return value;
            } else return null;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }
        public int size(){
            return top+1;
        }
    }

    public static double operate(char operator, double a, double b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if(b==0){
                    return -100000000;
                }
                return a / b;
            case '^':
                return Math.pow(a, b);
        }
        return -100000000;
    }

    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
    public static boolean isValid(String input){
        if(input.startsWith(")")||input.endsWith("(")){
            return false;
        }
        Pattern pattern=Pattern.compile(".*[\\^*/]{2,}.*|.*[+\\-*/^]$|.*[^0-9()+\\-*/^!].*");
        return !pattern.matcher(input).matches();
    }
    public static double getAnswer(String input) {
        int size = input.length();
        char[] charArray = input.toCharArray();
        Stack<Character> operators = new Stack<>(size);
        Stack<Double> operands = new Stack<>(size);
        int openedCount=0;
        int closedCount=0;
        boolean numCameBefore=false;
        for (int i=0;i<size;i++) {
            char c=input.charAt(i);
            if (c == '(') {
                openedCount++;
                numCameBefore=false;
            } else if (Character.isDigit(c)) {
                double num=(double) c - '0';
                if(numCameBefore){
                    num=operands.pop()*10+num;
                }
                operands.push(num);
                numCameBefore=true;
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                operators.push(c);
                numCameBefore=false;
            } else if (c == ')') {
                numCameBefore=false;
                closedCount++;
                if(operands.size()<2){
                    return -100000000;
                }
                char operator = operators.pop();
                double operand2 = operands.pop();
                double operand1 = operands.pop();
                double result = operate(operator, operand1, operand2);
                if(result==-100000000){
                    return result;
                }
                operands.push(result);
            } else if (c == '!') {
                numCameBefore=false;
                double operand = operands.pop();
                if(operand>16){
                    return -100000000;
                }
                operands.push((double) factorial((int) operand));
            }
        }
        if(openedCount!=closedCount){
            return -100000000;
        }
        if(input.charAt(0)=='-'){
            return -operands.pop();
        }else {
            return operands.pop();
        }
    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        double answer=getAnswer(input.nextLine());
        if(answer!=-100000000){
            System.out.println(answer);
        }
    }
}