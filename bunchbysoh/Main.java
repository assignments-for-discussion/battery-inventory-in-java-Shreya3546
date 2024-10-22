package bunchbysoh;

public class Main {

    //Class which holds the counts of the battery classifications
    static class CountsBySoH {
        public int healthy = 0;
        public int exchange = 0;
        public int failed = 0;
    }

    // Method to classify the batteries
    static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
        CountsBySoH counts = new CountsBySoH();
        final double ratedCapacity = 120.0; 

        // Iterate through each present capacity to calculate the SoH  
        for (int presentCapacity : presentCapacities) {
            // Calculate SOH as a percentage
            double stateOfHealth = (presentCapacity / ratedCapacity) * 100;

            // Classify it based on the SoH thresholds
            if (stateOfHealth > 83) {
                counts.healthy++;  
            } else if (stateOfHealth >= 63) {
                counts.exchange++;  
            } else {
                counts.failed++;  
            }
        }
        
        return counts;  // Return the counts 
    }

    // Method to test the battery classification 
    static void testBucketingByHealth() {
        System.out.println("Counting batteries by SoH...\n");
        int[] presentCapacities = {113, 116, 80, 95, 92, 70}; 
        CountsBySoH counts = countBatteriesByHealth(presentCapacities);
        
        // Assertions to validate the output
        assert(counts.healthy == 3) : "Healthy count mismatch";   
        assert(counts.exchange == 2) : "Exchange count mismatch"; 
        assert(counts.failed == 1) : "Failed count mismatch";      

        // Additional test cases for boundary conditions
        int[] boundaryCapacities = {110, 83, 82, 63, 62, 0};
        counts = countBatteriesByHealth(boundaryCapacities);
        assert(counts.healthy == 1) : "Healthy count mismatch (boundary)";
        assert(counts.exchange == 2) : "Exchange count mismatch (boundary)"; 
        assert(counts.failed == 3) : "Failed count mismatch (boundary)"; 

        System.out.println("Done counting :)\n");
    }

    public static void main(String[] args) {
        testBucketingByHealth();  
    }
}
