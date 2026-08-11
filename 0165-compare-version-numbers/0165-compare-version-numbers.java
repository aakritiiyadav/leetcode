
class Solution {
    public int compareVersion(String version1, String version2) {

        int m = version1.length();
        int n = version2.length();

        int i = 0;
        int j = 0;

        while (i < m || j < n) {

            int a = 0;
            int b = 0;

            // Read current revision from version1
            while (i < m && version1.charAt(i) != '.') {
                a = a * 10 + (version1.charAt(i) - '0');
                i++;
            }

            // Read current revision from version2
            while (j < n && version2.charAt(j) != '.') {
                b = b * 10 + (version2.charAt(j) - '0');
                j++;
            }

            // Compare revisions
            if (a < b) {
                return -1;
            }

            if (a > b) {
                return 1;
            }

            // Skip '.'
            i++;
            j++;
        }

        return 0;
    }
}

