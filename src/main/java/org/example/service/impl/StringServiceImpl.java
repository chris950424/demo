package org.example.service.impl;

import org.example.service.StringService;

import java.util.ArrayList;
import java.util.List;

public class StringServiceImpl implements StringService {

    public static void main(String[] args) {
        String s = "中国人";
        StringService stringService = new StringServiceImpl();
        System.out.println(s.length());
        s = "abc 中国人是世界上最厉害的人。";
        System.out.println(s.length());
        List<String> list = stringService.formatString(s, 10);
        System.out.println(list);
    }

    @Override
    public List<String> formatString(String source, int width) {
        List<String> targetList = new ArrayList<>();
        String target = null;
        source = "  "+source;
        int length = source.length();
        int start = 0; int end =0;
        while (true){
            if (target !=null){
                start = start+target.length();
            }
            if (end >=length){
                break;
            }
            if (end+width>length){
                end = length;
            }else {
                end = width;
            }
            target = format(source,start,end, width);
            targetList.add(target);
        }
        return targetList;
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
            if (isPunctuation(c) && i==end){
                target.substring(target.length()-lastAppend.length(),target.length());
                break;
            }
            target.append(c);
            lastAppend = String.valueOf(c);
        }
        return String.valueOf(target);
    }

    public static boolean isPunctuation(char c) {
        return c >= 33 && c <= 47 || c >= 58 && c <= 64 || c >= 91 && c <= 96 || c >= 123 && c <= 126;
    }
}
