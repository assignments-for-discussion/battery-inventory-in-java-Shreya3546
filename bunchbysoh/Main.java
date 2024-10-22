package bunchbysoh;

public class Main {
    // Class to hold the counts of battery classifications
    static class CountsBySoH {
        public int healthy = 0;
        public int exchange = 0;
        public int failed = 0;
    }

    // Method to classify batteries based on their present capacities
    static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
        CountsBySoH counts = new CountsBySoH();
        final double ratedCapacity = 120.0; // Rated capacity is same for all batteries

        // Iterate through each present capacity to calculate SoH
        for (int presentCapacity : presentCapacities) {
            // Calculate state-of-health (SoH) as a percentage
            double stateOfHealth = (presentCapacity / ratedCapacity) * 100;

            // Classify based on SoH thresholds
            if (stateOfHealth > 83) {
                counts.healthy++;  // Healthy: SoH > 83%
            } else if (stateOfHealth >= 63) {
                counts.exchange++;  // Exchange: 63% ≤ SoH ≤ 83%
            } else {
                counts.failed++;  // Failed: SoH < 63%
            }
        }
        
        return counts;  // Return the counts of each classification
    }

    // Method to test the battery classification logic
    static void testBucketingByHealth() {
        System.out.println("Counting batteries by SoH...\n");
        int[] presentCapacities = {113, 116, 80, 95, 92, 70}; 
        CountsBySoH counts = countBatteriesByHealth(presentCapacities);
        
        // Assertions to validate the output
        assert(counts.healthy == 2) : "Healthy count mismatch";   // 113 and 116
        assert(counts.exchange == 3) : "Exchange count mismatch"; // 80, 95, 92
        assert(counts.failed == 1) : "Failed count mismatch";      // 70

        // Additional test cases for boundary conditions
        int[] boundaryCapacities = {120, 83, 82, 63, 62, 0};
        counts = countBatteriesByHealth(boundaryCapacities);
        assert(counts.healthy == 1) : "Healthy count mismatch (boundary)"; // 120 is healthy
        assert(counts.exchange == 2) : "Exchange count mismatch (boundary)"; // 83 and 82 are exchangeable
        assert(counts.failed == 3) : "Failed count mismatch (boundary)"; // 63, 62, 0 are failed

        System.out.println("Done counting :)\n");
    }

    public static void main(String[] args) {
        testBucketingByHealth();  
    }
}
