import java.util.Arrays;

class Solution {
    int[][] convTable = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {2, 0, 0, 0},
            {0, 0, 1, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 1},
            {3, 0, 0, 0},
            {0, 2, 0, 0}
    };

    public String smallestNumber(String num, long t) {
        int len = num.length();
        int[] factors = factorize(t);
        if(factors == null) return "-1";

        int[] in = new int[len];
        int tmp = 0;
        for(; tmp < len; tmp++){
            in[tmp] = num.charAt(tmp) - '0';
            if(in[tmp] == 0) break;
        }
        while(tmp < len) in[tmp++] = 1;
        if(minLen(factors) > len){
            return getNum(factors, 0);
        }

        for(int i = 0; i < len; i++){
            factors[0] -= convTable[in[i]][0];
            factors[1] -= convTable[in[i]][1];
            factors[2] -= convTable[in[i]][2];
            factors[3] -= convTable[in[i]][3];
        }
        if(factors[0] <= 0 && factors[1] <= 0 &&
                factors[2] <= 0 && factors[3] <= 0){
            StringBuilder ret = new StringBuilder();
            for(int i : in) ret.append(i);
            return ret.toString();
        }
        for(int i = len - 1; i >= 0; i--){
            factors[0] += convTable[in[i]][0];
            factors[1] += convTable[in[i]][1];
            factors[2] += convTable[in[i]][2];
            factors[3] += convTable[in[i]][3];
            int tmp1 = check(factors, in, i);
            if(tmp1 != 0) return get(factors, in, i, tmp1);
        }

        return getNum(factors, len + 1);
    }

    private int check(int[] factors, int[] num, int firstPos){
        for(int i = num[firstPos] + 1; i <= 9; i++){
            factors[0] -= convTable[i][0];
            factors[1] -= convTable[i][1];
            factors[2] -= convTable[i][2];
            factors[3] -= convTable[i][3];
            if(minLen(factors) < num.length - firstPos) return i;
            factors[0] += convTable[i][0];
            factors[1] += convTable[i][1];
            factors[2] += convTable[i][2];
            factors[3] += convTable[i][3];
        }
        return 0;
    }

    private String get(int[] factors, int[] num, int firstPos, int changedKept){
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < firstPos; i++){
            out.append(num[i]);
        }
        out.append(changedKept);
        out.append(getNum(factors, num.length - 1 - firstPos));
        return out.toString();
    }

    private String getNum(int[] factors, int minLen){
        StringBuilder out = new StringBuilder();
        while (factors[1] >= 2){
            out.append("9");
            factors[1] -= 2;
        }
        while (factors[0] >= 3){
            out.append("8");
            factors[0] -= 3;
        }
        while (factors[3] > 0){
            out.append("7");
            factors[3]--;
        }
        while (factors[0] > 0 && factors[1] > 0){
            out.append("6");
            factors[0]--;
            factors[1]--;
        }
        while (factors[2] > 0){
            out.append("5");
            factors[2]--;
        }
        while (factors[0] >= 2){
            out.append("4");
            factors[0] -= 2;
        }
        while (factors[1] > 0){
            out.append("3");
            factors[1]--;
        }
        while (factors[0] > 0){
            out.append("2");
            factors[0]--;
        }
        while(out.length() < minLen) out.append("1");
        out.reverse();
        return out.toString();
    }

    private int[] factorize(long t){
        int[] out = new int[4];
        while(t%2 == 0){
            t /= 2;
            out[0]++;
        }
        while(t%3 == 0){
            t /= 3;
            out[1]++;
        }
        while(t%5 == 0){
            t /= 5;
            out[2]++;
        }
        while(t%7 == 0){
            t /= 7;
            out[3]++;
        }
        if(t != 1) return null;
        return out;
    }

    private int minLen(int[] in){
        int i0 = in[0];
        int i1 = in[1];
        int i2 = in[2];
        int i3 = in[3];
        int out = 0;
        if(i2 > 0) out += i2;
        if(i3 > 0) out += i3;
        if(i0 >= 3){
            out += i0/3;
            i0 %= 3;
        }
        if(i0 == 2){
            out++;
            i0 = 0;
        }
        if(i1 >= 2){
            out += i1/2;
            i1 %= 2;
        }
        if(i1 == 1 && i0 == 1) return out + 1;
        if(i0 > 0) out++;
        if(i1 > 0) out++;
        return out;
    }
}
