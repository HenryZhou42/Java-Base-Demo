package org.zyh;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * 本项目实现了一个基于记忆化递归方法来高效计算斐波那契数列第n项值的Java程序。
 * 斐波那契数列是一个经典的数学序列，其中每一项都是前两项之和，且序列通常以0和1开始。
 * 记忆化技术用于优化递归过程，避免了重复计算相同子问题，显著提升了性能。
 *
 */

public class Fibonacci {
    private static Map<Integer, Long> memory = new HashMap<>(); // 使用HashMap作为记忆化存储，键为斐波那契数列的位置n，值为对应的斐波那契数
    /**
     * 使用记忆化递归计算斐波那契数列的第n项。
     * @param n 斐波那契数列的位置，n >= 0
     * @return 第n项的值
     */

    public static long fibonacciMemory(int n){
        //如果n小于或等于1，那么就直接返回n，因为斐波那契数列的第一项和第二项是0和1
        if(n<=1){
            return n;
        }
        // 查看我们是否已经计算过当前位置n的斐波那契数
        // 如果存在，则直接从记忆化存储中返回，避免重复计算
        else if(memory.containsKey(n)){
            return memory.get(n);
        }

        // 如果没有计算过，递归计算前两项的和，并将结果存入记忆化存储中
        long result = fibonacciMemory(n-1) + fibonacciMemory(n-2);
        memory.put(n, result);

        return result;
    }

    private static int getUserInput(Scanner scanner) {
        int input;
        while (true) {
            System.out.print("Enter a positive integer to get the value at that position in the Fibonacci sequence: ");
            try {
                input = Integer.parseInt(scanner.nextLine()); // 尝试将输入转换为整数
                if (input >= 0) { // 确保输入为非负整数
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = getUserInput(scanner);
        System.out.println("The " + n + "th term of the Fibonacci sequence is: " + fibonacciMemory(n));
        scanner.close();
    }

}
