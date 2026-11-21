
import java.util.*;

class Solution {
    int[] ans;
    List<Integer>[] adj;
    List<Integer>[] path;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        this.s = s;
        ans = new int[n];

        adj = new ArrayList[n];
        path = new ArrayList[26];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < 26; i++) {
            path[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        dfs(0, -1);

        return ans;
    }

    void dfs(int u, int p) {
        ans[u] = 1;

        int c = s.charAt(u) - 'a';
        path[c].add(u);

        for (int v : adj[u]) {
            dfs(v, u);
        }

        int newParent = p;

        if (path[c].size() > 1) {
            newParent = path[c].get(path[c].size() - 2);
        }

        if (newParent != -1) {
            ans[newParent] += ans[u];
        }

        path[c].remove(path[c].size() - 1);
    }
}
