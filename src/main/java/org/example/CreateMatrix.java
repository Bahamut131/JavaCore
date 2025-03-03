package org.example;

import java.util.Random;
import java.util.Scanner;

public class CreateMatrix {
    private final Scanner scanner = new Scanner(System.in);
    private int[][] matrix;
    private final Random random = new Random();

    public void startMenu() {
        System.out.println("Введіть довжину та ширину бажанної матриці:");
        System.out.println("____________________________________________");
        while (true) {
            System.out.println("Висота: ");
            int height = scanner.nextInt();
            System.out.println("Довжина: ");
            int width = scanner.nextInt();
            if (height > 20 || width > 20) {
                System.out.println("Введіть розмір матриці максимум 20х20: ");
                System.out.println("____________________________________________");
            } else {
                createMatrix(height, width);
                break;
            }
        }

    }

    private void createMatrix(int height, int width) {
        matrix = new int[height][width];
        System.out.println("Оберіть будь ласка як хочете заповнити матрицю \n" +
                "1) Вручну \n" +
                "2) Рандомно заповнити матрицю");

        while (scanner.hasNextInt()) {
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    fillMatrixManually(scanner, matrix);
                    break;
                case 2:
                    fillMatrixRandomly(random, matrix);
                    break;
                default:
                    System.out.println("Некоректний ввод, введіть 1 або 2: ");
                    continue;
            }
            break;
        }

        printMatrix(matrix);
        endMenu();

    }

    private static void fillMatrixManually(Scanner scanner, int[][] matrix) {
        System.out.println("Введіть елементи матриці :");
        System.out.println("____________________________________________");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                while (true) {
                    System.out.printf("Введіть елемент [%d][%d]: ", i, j);
                    if (scanner.hasNextInt()) {
                        matrix[i][j] = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("Помилка! Введіть ціле число.");
                        scanner.next(); // Очистка некорректного ввода
                    }
                }
            }
        }
    }

    private static void fillMatrixRandomly(Random random, int[][] matrix) {
        System.out.println("Матриця заповненна випадковими ціліми числами:");
        System.out.println("____________________________________________");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(100); // Числа от 0 до 99
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println("____________________________________________");
        System.out.println("Матриця:");
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.printf("%4d", num);
            }
            System.out.println();
        }
        System.out.println("____________________________________________");
    }

    private void endMenu() {

        while (true) {
            System.out.println("Операції над створенною матрицею: \n" +
                    "1)Знайти максимальне та мінімальне значення матриці: 1\n" +
                    "2)Знайти середне арифметичне матриці: 2\n" +
                    "3)Створити іншу матрицю: 3\n" +
                    "4)Вийти з програми: 4");

            while (!scanner.hasNextInt()){
                System.out.println("Введіть коректне число!");
                scanner.next();
            }

            int userChoose = scanner.nextInt();
            switch (userChoose) {
                case 1:
                    findMinMax(matrix);
                    break;
                case 2:
                    calculateAverage(matrix);
                    break;
                case 3:
                    startMenu();
                    return;
                case 4:
                    System.out.println("Програма завершена.");
                    return;
                default:
                    System.out.println("Введіть коректне значення!");
            }
        }
    }


    private static void findMinMax(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            for (int num : row) {
                if (num < min) min = num;
                if (num > max) max = num;
            }
        }
        System.out.println("Минимальный элемент: " + min);
        System.out.println("Максимальный элемент: " + max);
    }

    private static void calculateAverage(int[][] matrix) {
        int sum = 0, count = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sum += num;
                count++;
            }
        }
        double average = (double) sum / count;
        System.out.printf("Среднее арифметическое: %.2f%n", average);
    }

}
