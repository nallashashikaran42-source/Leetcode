
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];
        int[] count = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count all multiples
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                count[i] += freq[j];
            }
        }

        // Sort by price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long ans = 0;
        long remaining = budget;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            int free = count[factor] - 1;

            if (free <= 0) continue;

            // Value 2 is worthwhile only when price < 2 * minPrice
            if (price >= 2L * minPrice) break;

            long buy = Math.min(free, remaining / price);

            ans += buy * 2;
            remaining -= buy * price;
        }

        // Buy remaining items at the cheapest price
        ans += remaining / minPrice;

        return (int) ans;
    }
}
