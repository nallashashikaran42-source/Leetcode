class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            long gcd = nums[i];
            long lcm = nums[i];

            for (int j = i; j < n; j++) {
                product *= nums[j];

                if (j > i) {
                    gcd = gcd(gcd, nums[j]);
                    lcm = lcm / gcd(lcm, nums[j]) * nums[j];
                }

                if (product == gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}