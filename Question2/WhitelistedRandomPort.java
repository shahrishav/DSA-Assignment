package Question2;
import java.util.*;

public class WhitelistedRandomPort {
    private Set<Integer> whitelist;
    private Random random;

    public WhitelistedRandomPort(int k, int[] blacklistedPorts) {
        whitelist = new HashSet<>();
        random = new Random();

        // Add all ports from 0 to k-1 to the whitelist
        for (int i = 0; i < k; i++) {
            whitelist.add(i);
        }

        // Remove blacklisted ports from the whitelist
        for (int port : blacklistedPorts) {
            whitelist.remove(port);
        }
    }

    public int get() {
        // Get a random index within the size of the whitelist
        int index = random.nextInt(whitelist.size());

        // Iterate through the whitelist and return the element at the randomly generated index
        Iterator<Integer> iterator = whitelist.iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }

        return iterator.next();
    }

    public static void main(String[] args) {
        // Example input
        int k = 7;
        int[] blacklistedPorts = {2, 3, 5};

        // Create an instance of WhitelistedRandomPort
        WhitelistedRandomPort p = new WhitelistedRandomPort(k, blacklistedPorts);

        // Call the get() function to get whitelisted random ports
        System.out.println(p.get()); // Returns 0
        System.out.println(p.get()); // Returns 4
    }
}
