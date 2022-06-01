package com.example.user.test;

/**
 *
 * 第1个人10，第2个比第1个人大2岁，依次递推，请用递归方式计算出第8个人多大?
 *
 * @author wangns
 *
 */
public class Test {


    public static void main(String[] args) {
        System.out.println(getComputeAge(8));
    }

    public static int getComputeAge(int n) {
        int age = 0;
        if (n == 1) {
            age = 10;
        } else {
            age = getComputeAge(n - 1) + 2;
        }
        return age;
    }


}
