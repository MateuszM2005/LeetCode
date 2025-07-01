import java.util.Arrays;

class Solution {
    private class Union{
        int size;
        int[] connections;
        private Union(int size){
            this.size = size;
            connections = new int[size];
            for(int i = 0; i < size; i++) connections[i] = i;
        }
        protected int get(int id){
            if(id == connections[id]) return id;
            connections[id] = get(connections[id]);
            return connections[id];
        }
        protected void add(int i1, int i2){
            if(get(i1) > get(i2)){
                add(i2, i1);
                return;
            }
            connections[get(i2)] = get(i1);
        }
        protected boolean exists(int i1, int i2){
            return get(i1) == get(i2);
        }
        protected boolean isFull(){
            for(int i = 0; i < size; i++){
                if(get(i) != 0) return false;
            }
            return true;
        }
    }
    public int maxStability(int n, int[][] edges, int k) {
        int out1 = Integer.MAX_VALUE/2 - 1;
        int out0 = Integer.MAX_VALUE/2 - 1;
        int edgeCount = 0;
        Arrays.sort(edges, (a, b) -> Integer.compare(b[2], a[2]));
        Union u = new Union(n);
        for(int i = 0; i < edges.length; i++){
            if(edges[i][3] == 1){
                edgeCount++;
                out1 = Math.min(out1, edges[i][2]);
                u.add(edges[i][0], edges[i][1]);
            }
        }
        for(int i = 0; i < edges.length; i++){
            if(edges[i][3] == 0 && !u.exists(edges[i][0], edges[i][1])){
                edgeCount++;
                out0 = Math.min(out0, edges[i][2]);
                u.add(edges[i][0], edges[i][1]);
                edges[i][3] = 2;
            }
        }
        int i = edges.length - 1;
        int last = -1;
        while (i >= 0){
            if(edges[i][3] == 2) k--;
            if(k < 0){
                last = edges[i][2];
                break;
            }
            i--;
        }
        
        if(edgeCount != n - 1 || !u.isFull()) return -1;
        if(last == -1 ) return Math.min(2 * out0, out1);
        return Math.min(Math.min(2*out0, last), out1);

    }

}
