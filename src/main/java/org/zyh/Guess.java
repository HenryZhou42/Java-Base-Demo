package org.zyh;

import java.util.Random;
import java.util.Scanner;

/**
 * 本项目实现了一个简单的命令行猜数字游戏，用户需要猜测程序随机生成的一个介于1到100之间的整数。
 * 游戏会根据用户的猜测给出提示（太大、太小或猜中），直到用户猜中为止，并统计用户尝试的次数。
 * 项目主要目的是作为Java编程基础的学习实践，涵盖随机数生成、用户输入处理、控制流程等核心概念。
 */

public class Guess {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int randomNumber = random.nextInt(100) + 1;
        int attempts = 0;
        while (true) {
            System.out.print("Guess a number between 1 and 100: ");

            // 添加输入验证
            while (!scanner.hasNextInt()) {
                System.out.println("Not a valid integer, please try again");
                scanner.next(); // 清除错误的输入
            }
            int guess = scanner.nextInt();

            // 进一步验证数字是否在1到100之间
            if (guess < 1 || guess > 100) {
                System.out.println("Please input a number between 1 and 100");
                continue;
            }

            attempts++;

            // 检查用户的猜测
            if (guess < randomNumber) {
                System.out.println("Too small！");
            } else if (guess > randomNumber) {
                System.out.println("Too big！");
            } else {
                System.out.printf("Congrats! The number is %d，your attempts number is %d . %n", randomNumber, attempts);
                break;
            }
        }

        scanner.close();
    }
}
