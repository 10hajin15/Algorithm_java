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
                String mode = temp.substring(idx, idx += 4);

                if (mode.equals("0001")) {
                    String codeCntStr = temp.substring(idx, idx += 10);
                    int codeCntInt = Integer.parseInt(codeCntStr, 2);
                    chrCnt += codeCntInt;

                    int strBitsLen = (codeCntInt / 3) * 10 + (codeCntInt % 3 == 2 ? 7 : 4);
                    String modeStr = temp.substring(idx, idx += strBitsLen);

                    int k;
                    for (k = 0; k + 10 <= modeStr.length(); k += 10) {
                        int decoding = Integer.parseInt(modeStr.substring(k, k + 10), 2);
                        ans.append(String.format("%3s", decoding).replace(' ', '0'));
                    }
                    if (k < modeStr.length()) {
                        int decoding = Integer.parseInt(modeStr.substring(k), 2);
                        int formatCnt = (codeCntInt%3)-Integer.toString(decoding).length();

                        if (formatCnt > 0) {
                            String format = "%" +formatCnt+"s";
                            ans.append(String.format(format, decoding).replace(' ', '0'));
                        } else {
                            ans.append(decoding);
                        }

                    }
                } else if (mode.equals("0010")) {
                    String[] alphLst = {
                            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                            " ", "$", "%", "*", "+", "-", ".", "/", ":"
                    };

                    String codeCntStr = temp.substring(idx, idx += 9);
                    int codeCntInt = Integer.parseInt(codeCntStr, 2);
                    chrCnt += codeCntInt;

                    int strBitsLen = (codeCntInt / 2) * 11 + (codeCntInt % 2 > 0 ? 6 : 0);
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
                    String codeCntStr = temp.substring(idx, idx += 8);
                    int codeCntInt = Integer.parseInt(codeCntStr, 2);
                    chrCnt += codeCntInt;

                    int strBitsLen = codeCntInt * 8;
                    String modeStr = temp.substring(idx, idx += strBitsLen);

                    int k;
                    for (k = 0; k + 8 <= modeStr.length(); k += 8) {
                        int tmp = Integer.parseInt(modeStr.substring(k, k + 8), 2);
                        if (tmp > 126 || tmp < 32) {
                            ans.append(String.format("\\%02x", tmp));
                        } else {
                            if(Character.isDigit((char)tmp)) {
                                ans.append(String.format("%02x", ((char)tmp)-'0'));
                            } else {
                                ans.append((char)tmp);
                            }
                        }
                    }
                } else if (mode.equals("1000")) {
                    String codeCntStr = temp.substring(idx, idx += 8);
                    int codeCntInt = Integer.parseInt(codeCntStr, 2);
                    chrCnt += codeCntInt;

                    int strBitsLen = codeCntInt * 13;
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