package Backjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_2680 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(br.readLine());

        for (int i = 0; i < P; i++) {
            String input = br.readLine();
            int chrCnt = 0;
            StringBuilder ans = new StringBuilder();
            StringBuilder temp = new StringBuilder();

            // 1. blocks(16진수)을 2진수로 바꿔서 temp에 넣기
            for (int j = 0; j < input.length(); j += 2) {
                String hexStr = input.substring(j, j + 2);
                int hexVal = Integer.parseInt(hexStr, 16);
                temp.append(String.format("%8s", Integer.toBinaryString(hexVal)).replace(' ', '0'));
            }

            // 2. 데이터 코드 판단
            int idx = 0;
            while (idx < temp.length()) {
                if (idx + 4 > temp.length()) break;
                String mode = temp.substring(idx, idx += 4);
                if (mode.equals("0001")) {
                    if (idx + 10 > temp.length()) break;
                    String codeCntStr = temp.substring(idx, idx += 10);
                    int codeCntInt = Integer.parseInt(codeCntStr, 2);
                    chrCnt += codeCntInt;

                    int strBitsLen = (codeCntInt / 3) * 10 + (codeCntInt % 3 == 2 ? 7 : (codeCntInt % 3 == 1 ? 4 : 0));
                    if (idx + strBitsLen > temp.length()) break;
                    String modeStr = temp.substring(idx, idx += strBitsLen);

                    int k;
                    for (k = 0; k + 10 <= modeStr.length(); k += 10) {
                        ans.append(Integer.parseInt(modeStr.substring(k, k + 10), 2));
                    }
                    if (k < modeStr.length()) {
                        ans.append(Integer.parseInt(modeStr.substring(k), 2));
                    }
                } else if (mode.equals("0010")) {
                    String[] alphLst = {
                            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                            " ", "$", "%", "*", "+", "-", ".", "/", ":"
                    };

                    if (idx + 9 > temp.length()) break;
                    String codeCntStr = temp.substring(idx, idx += 9);
                    int codeCntInt = Integer.parseInt(codeCntStr, 2);
                    chrCnt += codeCntInt;

                    int strBitsLen = (codeCntInt / 2) * 11 + (codeCntInt % 2 > 0 ? 6 : 0);
                    if (idx + strBitsLen > temp.length()) break;
                    String modeStr = temp.substring(idx, idx += strBitsLen);

                    int k;
                    for (k = 0; k + 11 <= modeStr.length(); k += 11) {
                        int tmp = Integer.parseInt(modeStr.substring(k, k + 11), 2);
                        ans.append(alphLst[tmp / 45]).append(alphLst[tmp % 45]);
                    }
                    if (k < modeStr.length()) {
                        int tmp = Integer.parseInt(modeStr.substring(k), 2);
                        ans.append(alphLst[tmp]);
                    }

                } else if (mode.equals("0100")) {
                    if (idx + 8 > temp.length()) break;
                    String codeCntStr = temp.substring(idx, idx += 8);
                    int codeCntInt = Integer.parseInt(codeCntStr, 2);
                    chrCnt += codeCntInt;

                    int strBitsLen = codeCntInt * 8;
                    if (idx + strBitsLen > temp.length()) break;
                    String modeStr = temp.substring(idx, idx += strBitsLen);

                    int k;
                    for (k = 0; k + 8 <= modeStr.length(); k += 8) {
                        int tmp = Integer.parseInt(modeStr.substring(k, k + 8), 2);
                        if (tmp > 126 || tmp < 32) {
                            ans.append(String.format("\\%02x", tmp));
                        } else {
                            ans.append((char) tmp);
                        }
                    }
                } else if (mode.equals("1000")) {
                    if (idx + 8 > temp.length()) break;
                    String codeCntStr = temp.substring(idx, idx += 8);
                    int codeCntInt = Integer.parseInt(codeCntStr, 2);
                    chrCnt += codeCntInt;

                    int strBitsLen = codeCntInt * 13;
                    if (idx + strBitsLen > temp.length()) break;
                    String modeStr = temp.substring(idx, idx += strBitsLen);

                    int k;
                    for (k = 0; k + 13 <= modeStr.length(); k += 13) {
                        int tmp = Integer.parseInt(modeStr.substring(k, k + 13), 2);
                        ans.append(String.format("#%04x", tmp));
                    }
                } else {
                    break;
                }
            }
            if(chrCnt <= 0) {
                System.out.println(0);
            } else {
                System.out.println(chrCnt + " " + ans.toString().toUpperCase());
            }
        }
    }
}