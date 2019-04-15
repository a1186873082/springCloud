package com.example.user.util;

public class NiukeUtils {
    public boolean checkDifferent(String iniString) {
        // write code here
        boolean b = true;
        String[] s = iniString.split("");
        for(int i = 0; i < s.length; i++){
            for(int j = i+1; j < s.length;j++){
                if(s[i] != s[j]){
                    b = true;
                }else{
                    return false;
                }
            }
        }
        return b;
    }
    public static void main(String[] args) {
        new NiukeUtils().checkDifferent("fbcdbea");
    }
}
