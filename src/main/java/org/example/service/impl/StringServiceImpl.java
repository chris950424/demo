package org.example.service.impl;

import org.example.service.StringService;

import java.util.ArrayList;
import java.util.List;

public class StringServiceImpl implements StringService {
    public static void main(String[] args) {
        String s = "中国人";
        StringService stringService = new StringServiceImpl();
        System.out.println(s.length());
        s = "abcd 中国人，中国人是世界上最厉害的人。";
        System.out.println(s.length());
        List<String> list = stringService.formatString(s, 10);
        for (String string:list){
            System.out.println(string);

        }
    }
    @Override
    public List<String> formatString(String source, int width) {
        List<String> targetList = new ArrayList<>();
        // 首行缩进
        source = "  "+source;
        // 按指定长度进行文本整理
        format(source, width, targetList);
        return targetList;
    }

    private void format(String source, int width, List<String> targetList) {
        int length = source.length();
        String target = null;
        int start = 0;
        int end =0;
        while (true){
            if (target !=null){
                start = start+ target.length();
            }
            if (end >= length){
                break;
            }
            end = Math.min(end + width, length);
            //获取每行的结果。
            target = format(source,start,end, width);
            targetList.add(target);
        }
    }

    private String format(String source,int start,int end , int width) {
        StringBuilder target = new StringBuilder();
        String lastAppend = "";
        int length = source.length();
        for (int i = start; i <= end&&i<length ; i++) {
            char c = source.charAt(i);
            StringBuilder word = new StringBuilder();
            if (Character.isLowerCase(c)||Character.isUpperCase(c)){
                int j = i;
                while (true) {
                    c = source.charAt(j);
                    if (!(Character.isLowerCase(c)||Character.isUpperCase(c))) {
                        break;
                    }
                    word.append(c);
                    j++;
                }
                if (target.length() + word.length() > width) {
                     break;
                }
                lastAppend = word.toString();
                target.append(word);
                i =  j-1;
                continue;
            }
            if (i==end){
                if (check(String.valueOf(c))){
                    return target.substring(start, target.length() - lastAppend.length());
                }
                break;
            }
            target.append(c);
            lastAppend = String.valueOf(c);
        }
        return String.valueOf(target);
    }

//    public static boolean isPunctuation(char c) {
//        return c >= 33 && c <= 47 || c >= 58 && c <= 64 || c >= 91 && c <= 96 || c >= 123 && c <= 126;
//    }

    public boolean check(String s) {
        boolean b = false;

        String tmp = s;
        tmp = tmp.replaceAll("\\p{P}", "");
        if (s.length() != tmp.length()) {
            b = true;
        }
        return b;
    }
}
