package com.github.earendil1412.leetcode403;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public boolean canCross(int[] stones) {
        if (stones.length < 2 || stones[0] != 0 || stones[1] != 1) {
            return false;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int index = 0; index < stones.length; index++) {
            if (index == 0) {
                map.put(stones[index + 1], new HashSet<Integer>() {{
                    add(1);
                }});
                continue;
            }
            if (!map.containsKey(stones[index])) {
                continue;
            }
            for (int i = index + 1; i < stones.length; i++) {
                Set<Integer> legalStep = map.get(stones[index]).stream().flatMap(x -> Stream.of(x - 1, x, x + 1)).collect(Collectors.toSet());
                Integer max = legalStep.stream().max(Comparator.comparing(Function.identity())).get();
                Integer pace = stones[i] - stones[index];
                if (pace > max) {
                    break;
                }
                if (legalStep.contains(stones[i] - stones[index])) {
                    if (map.containsKey(stones[i])) {
                        map.get(stones[i]).add(stones[i] - stones[index]);
                    } else {
                        map.put(stones[i], new HashSet<>());
                        map.get(stones[i]).add(stones[i] - stones[index]);
                    }
                }
            }
        }
        return map.containsKey(stones[stones.length - 1]);
    }
}
