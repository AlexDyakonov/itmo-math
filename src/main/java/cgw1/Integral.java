package cgw1;

import java.util.Scanner;
import java.util.stream.DoubleStream;

public class Integral {
    int n; //количество точек минимум 2
    float a; //начальная точка
    float b; //конечная точка
    int t; //способ выбора точек внутри промежутка: 1-правые, 2-левые, 3-средние, 4-случайные, 0-свое значение
    // double j;  параметр, определяющий положение точек внутри элементарных отрезков от 0 до 1

    public Integral(int n, float a, float b, int t) {
        this.n = n;
        this.a = a;
        this.b = b;
        this.t = t;
    }
    private static double getDoubleId(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine();
            try {
                double l = Double.parseDouble(s);
                if (l > 0) {
                    return l;
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input!");
            }
        }}

    public double calculate() {
        double[] arr = new double[n-1];
        if (n < 2) {
            System.out.println("Incorrect input");
            System.exit(1);
        }
        if (t == 1){
            double j = 0;
            return counting(arr, j);
        }
        else if (t == 2) {
            double j = 1;
            return counting(arr, j);
        }
        else if (t == 3){
            double j = 0.5;
            return counting(arr, j);
        }
        else if (t == 4) {
            double j = Math.random();
            return counting(arr, j);
        }
        else if (t == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите своё значение j от 0 до 1");
            double j =  getDoubleId(scanner);
            if (j < 0 || j > 1) {
                System.out.println("Incorrect input");
                System.exit(1); }
            else {
                return counting(arr, j);
            }
        }
        return 0;
    }

    private double counting(double[] arr, double j) {
        for (int i = 0; i<arr.length; i++) {
            arr[i] = -Math.cos((a + i * (b-a)/(n-1) + j * (b-a)/(n-1)));
        }
        double sum = DoubleStream.of(arr).sum();
        return sum*(b-a)/(n-1);
    }

    public static void main(String[] args) {
        while (true){
            Integral x = new Integral(2,  0, (float) Math.PI, 0);
            System.out.println(x.calculate());
        }
    }
}