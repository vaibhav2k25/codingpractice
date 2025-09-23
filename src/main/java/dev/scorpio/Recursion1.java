package dev.scorpio;

public class Recursion1 {
    public static void main(String[] args) {
        int num = 5;
        int fact = factorial(num);
        System.out.println(fact);
        for(int i=0;i<num;i++) {
            System.out.println(fibonacci(i));
        }
        int a = 2;
        int b = 5;
        int powerValue = myPower(a, b);
        System.out.println(powerValue);
    }

    private static int myPower(int a, int b) {
        if(b==0)
            return 1;
        return a*myPower(a,b-1);
    }

    private static int fibonacci(int num) {
        if(num==0 || num==1){
            return 1;
        }
        int result = fibonacci(num-1)+fibonacci(num-2);
        return result;
    }

    private static int factorial(int num) {
        if(num == 1)
            return 1;
        return num*factorial(num-1);
    }
}
