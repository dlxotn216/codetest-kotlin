package me.taesu.codetestkotlin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

class JavaTest {
    @ParameterizedTest
    @MethodSource("input")
    public void test(int[][] triangle) {
        System.out.println(getMaxSum(triangle));
    }

    private int getMaxSum(int[][] triangle) {
        for (int level = 0, levelLength = triangle.length; level < levelLength; level++) {
            for (int index = 0, length = triangle[level].length; index < length; index++) {
                triangle[level][index] += getMaxParent(triangle, level, index);
            }
        }
        return Arrays.stream(triangle[triangle.length - 1]).max().orElse(0);
    }

    private int getMaxParent(int[][] triangle, int level, int index) {
        return Integer.max(getOrZero(triangle, level - 1, index - 1), getOrZero(triangle, level - 1, index));
    }

    private int getOrZero(int[][] triangle, int level, int index) {
        try {
            return triangle[level][index];
        } catch (Exception e) {
            return 0;
        }
    }

    public static int[][][] input() {
        return new int[][][]{
                new int[][]{
                        new int[]{7},
                        new int[]{3, 8},    //, 10, 15
                        new int[]{8, 1, 0},
                        new int[]{2, 7, 4, 4},
                        new int[]{4, 5, 2, 6, 5},
                },
                new int[][]{
                        new int[]{2},
                        new int[]{3, 8},
                        new int[]{8, 1, 9},
                        new int[]{2, 7, 1, 4},
                        new int[]{4, 5, 2, 6, 5},
                }
        };
    }
}