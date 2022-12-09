package com.tetris.batch.util;

import java.util.Set;

public final class Constants {
    public static final Set<Character> pieces = Set.of('Q', 'Z', 'S', 'T', 'I', 'L', 'J');
    // public static Map<Character, List<Integer>> pieces = Map.of({"Q", {{1, 1}, {1, 1}}});
    public static final int[][] Q = {{1, 1}, {1, 1}};
    public static final int[][] Z = {{1, 1, 0}, {0, 1, 1}};
    public static final int[][] S = {{0, 1, 1}, {1, 1, 0}};
    public static final int[][] T = {{1, 1, 1}, {0, 1, 1}};
    public static final int[][] I = {{1, 1, 1, 1}};
    public static final int[][] L = {{1, 0}, {1, 0}, {1, 1}};
    public static final int[][] J = {{0, 1}, {0, 1}, {1, 1}};
    private Constants(){}
}
