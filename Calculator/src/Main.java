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
    }

    public static double apply(char operator, double a, double b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '!':
                return factorial((int) a);
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

    public static void main(String[] args) {
        System.out.println(apply('.', 5, 4));
    }
}