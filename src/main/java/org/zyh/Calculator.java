package org.zyh;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 本项目实现了一个简单的命令行计算器程序，使用Java语言编写。
 * 该计算器支持基本的算术运算，包括加法（+）、减法（-）、乘法（*）和除法（/），并且能够处理任意精度的数值计算，使用BigDecimal类型保证了高精度计算的需求。
 * 用户通过命令行输入两个数字和一个运算符，程序将输出计算结果。
 * 此外，程序具有良好的错误处理能力，能够识别无效的输入数字和不支持的运算符，并给予用户友好的提示。
 */

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Calculator");
        System.out.println("Please enter the first number");
        BigDecimal firstNumber= readBigDecimal(sc);
        System.out.println("Please enter the second number");
        BigDecimal secondNumber= readBigDecimal(sc);
        System.out.println("Please enter the operator (+, -, *, /):");
        String operator = sc.nextLine();
        BigDecimal result = performOperation(firstNumber, secondNumber, operator);
        if (result != null) {
            System.out.println("Your Result: " + result);
        } else {
            System.out.println("Invalid operator. Please use +, -, *, or /.");
        }
        sc.close();
    }

    /**
     * 读取用户输入的 BigDecimal 值。
     * @param scanner Scanner 对象用于读取输入。
     * @return 用户输入的有效 BigDecimal 值，或在输入无效时返回 null。
     */
    private static BigDecimal readBigDecimal(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine(); // 读取整行输入
                return new BigDecimal(input); // 尝试将输入转换为 BigDecimal
            } catch (NumberFormatException e) {
                System.out.println("Invalid number,please try again");
            }
        }
    }

    private static BigDecimal performOperation(BigDecimal firstNumber, BigDecimal secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber.add(secondNumber);
            case "-":
                return firstNumber.subtract(secondNumber);
            case "*":
                return firstNumber.multiply(secondNumber);
            case "/":
                // 防止除以零的错误
                if (secondNumber.compareTo(BigDecimal.ZERO) == 0) {
                    System.out.println("Error: Division by zero is not allowed.");
                    return null;
                }
                return firstNumber.divide(secondNumber);
            default:
                return null; // 如果运算符不合法，返回null
        }
    }
}
