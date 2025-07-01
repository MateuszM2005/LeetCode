class Solution {
    public boolean isMatch(String s, String p) {
        if(s.isEmpty() && p.isEmpty()) return true;
        if(s.isEmpty() && p.length() > 1 && p.charAt(1) == '*') return isMatch(s,p.substring(2));
        if(s.isEmpty() || p.isEmpty()) return false;

        if(p.length() > 1 && p.charAt(1) == '*'){
            boolean out = false;
            char cmp = p.charAt(0);
            p = p.substring(1);
            while(p.length() > 0 && p.charAt(0) == '*') p = p.substring(1);
            out = isMatch(s, p);
            while(s.length() > 0 && compare(s.charAt(0),cmp)){
                s = s.substring(1);
                out = out || isMatch(s, p);
                if(out) break;
            }
            return out;
        }

        if(compare(s.charAt(0), p.charAt(0))){
            return isMatch(s.substring(1), p.substring(1));
        }
        return false;
    }
    
    public static boolean compare(char c1, char c2){
        if(c2 == '.') return true;
        return c1 == c2;
    }
}
