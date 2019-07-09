package com.hitotek.workflow.model.multipart;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Qicheng Peng
 * @version V1.0
 * @description
 * @date 2019/1/9
 */
public class KeyConverter {

    private static final String SPLIT_STRING = "_";

    /**
     * site_id  -> siteId
     * 下划线命名法 -> 驼峰命名法
     *
     * @param key 目标key
     * @return 驼峰命名法的key
     */
    public String convert(String key) {
        if (key.contains(SPLIT_STRING)) {
            StringBuilder builder = new StringBuilder();
            Deque<String> queue = new ArrayDeque<>();
            boolean flag = false;
            // 入队 -> 出队 全部完成后就完成了转化
            for (int i = 0; i < key.length(); i++) {
                String currentKey = key.charAt(i) + "";
                if (!SPLIT_STRING.equals(currentKey)) {
                    if (flag) {
                        queue.add(currentKey.toUpperCase());
                        flag = false;
                    } else {
                        queue.add(currentKey);
                    }
                } else {
                    flag = true;
                }
                if (queue.peek() != null) {
                    builder.append(queue.pop());
                }
            }
            return builder.toString();
        }
        return key;
    }

    public String convertLine(String key) {
        StringBuilder builder = new StringBuilder();
        Deque<String> queue = new ArrayDeque<>();
        // 入队 -> 出队 全部完成后就完成了转化
        for (int i = 0; i < key.length(); i++) {
            String currentKey = key.charAt(i) + "";
            if (Character.isUpperCase(key.charAt(i)) && i > 0) {
                queue.add(SPLIT_STRING);
                queue.add(currentKey.toLowerCase());
            } else {
                queue.add(currentKey);
            }
            while (queue.peek() != null) {
                builder.append(queue.pop());
            }
        }
        return builder.toString();
    }
}
