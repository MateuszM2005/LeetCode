class Solution {

int mod = 1000000007;




public:

    int add(int i1, int i2){
        return (i1+i2)%mod;
    }

    bool checkHash(int h, int m){
        for(int i = 0; i < m - 1; i++){
            int div = pow(3,i);
            if((h/div)%3 == (h/div/3)%3) return false;
        }
        return true;
    }

    bool compare(int h1, int h2, int m){
        for(int i = 0; i < m; i++){
            int div = pow(3,i);
            if((h1/div)%3 == (h2/div)%3) return false;
        }
        return true;
    }


    int colorTheGrid(int m, int n) {
        int hashes = 3 * pow(2,m-1);
        vector<int> encoding(hashes, 0);

        int cnt = 0;
        int max = pow(3, m);
        for(int i = 0; i < max; i++)
            if(checkHash(i, m)) encoding[cnt++] = i;



        std::vector<std::vector<int>> dyntable(n, vector<int>(hashes, 0));
        for(int i = 0; i < hashes; i++){
            if(checkHash(encoding[i],m)) dyntable[0][i]++;
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < hashes; j++){ //at i-1
                for(int h = 0; h < hashes; h++){ //at i
                    if(compare(encoding[j],encoding[h],m)) 
                        dyntable[i][h] = add(dyntable[i][h], dyntable[i-1][j]);
                }
            }
        }
        
        int out = 0;
        for(int i = 0; i < hashes; i++) out = add(out, dyntable[n-1][i]);
        return out;
    }
};
