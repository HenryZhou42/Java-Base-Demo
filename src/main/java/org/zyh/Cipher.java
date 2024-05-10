package org.zyh;

import java.util.Scanner;

/**
 *
 * 本程序是一个简单的加密解密工具，基于经典的凯撒密码算法实现。凯撒密码是一种替换式加密技术，
 * 通过将字母表中的每个字母按照一个固定数目偏移来实现加密和解密。
 * 本程序提供了命令行界面，允许用户选择加密或解密文本，并可自定义偏移量（shift值）
 *
 */

public class Cipher {
    // 加密方法，接收明文和位移量作为参数，返回加密后的密文
    public static String encrypt(String plainText, int shift) {
        StringBuilder cipherText = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) (((ch - base + shift) % 26) + base);
            }else if (Character.isDigit(ch)) { // 新增：处理数字
                int digitShift = (ch - '0' + shift) % 10;
                ch = (char) (digitShift + '0');
            }
            cipherText.append(ch);
        }
        return cipherText.toString();
    }

    // 解密方法，实质上是调用加密方法并使用26减去位移量作为新位移量
    public static String decrypt(String cipherText, int shift) {
        StringBuilder plainText = new StringBuilder();
        for (char ch : cipherText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) (((ch - base - shift + 26) % 26) + base);
            } else if (Character.isDigit(ch)) {
                int digitShift = ((ch - '0' - shift + 10) % 10); // 数字解密逻辑，调整确保结果为正
                ch = (char) (digitShift + '0');
            }
            plainText.append(ch);
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;// 控制程序循环的标志位

        // 主循环，持续运行直到用户选择退出
        while (run){
            System.out.println("\nPlease select an option:");
            System.out.println("1. Encryption");
            System.out.println("2. Decryption");
            System.out.println("3. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1: {
                    System.out.println("Please enter your text to encrypt:");
                    String plainText = scanner.nextLine();
                    System.out.println("Please enter your shift:");
                    int shift = scanner.nextInt();
                    String encryptedText = encrypt(plainText,shift);
                    System.out.println("Your text is: " + encryptedText);
                    break;
                }
                case 2: {
                    System.out.println("Please enter your text to decrypt:");
                    String cipherText = scanner.nextLine();
                    System.out.println("Please enter your shift:");
                    int shift = scanner.nextInt();
                    String decryptedText = decrypt(cipherText,shift);
                    System.out.println("Your text is: " + decryptedText);
                    break;
                }
                case 3: {
                    run = false;// 设置标志位为false，准备退出循环
                    System.out.println("Goodbye!");
                    break;
                }
                default:{
                    System.out.println("Invalid option! Please try again!");
                    break;
                }
            }
        }
        // 确保在程序结束前关闭scanner
        scanner.close();
    }
}
