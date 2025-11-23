package simulator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    private static final String LINE = "|";
    private static final String TAB = "\t";
    private static final String SPACE = "  ";
    private static final String TRIM = "-------";
    private static final int TAG_WIDTH = 12; // Fixed width for tag columns

    private static int hitCounter = 0;
    private static int missCounter = 0;

    public static void main(String[] args) {

        String[] addressArr = {
                "0xbfffff00",
                "0x440",
                "0xbffffef0",
                "0xbffffee0",
                "0xbffffed0",
                "0xbffffec4",
                "0xbffffec0",
                "0xbffffec8",
                "0x4c4",
                "0x440",
                "0x54f",
                "0x4d7",
                "0x440",
                "0x5c3",
                "0x55555555",
                "0x5a0",
                "0xbffffec4",
                "0xbffffed0",
                "0xbffffee0",
                "0xbffffef0",
                "0xbfffff00",
        };

        Cache firstCache = new Cache(32, 4, 4 , "LRU");
        Cache secondCache = new Cache(32, 4, 4, "LFU");
        Cache thirdCache = new Cache(32, 4, 4 , "FIFO");
        Cache fourthCache = new Cache(16, 8, 2 ,"LRU");

        runSimulation(firstCache, addressArr);
        printOutcomeBoard(firstCache, addressArr);
    }

    public static void runSimulation(Cache cache, String[] arr) {
        hitCounter = 0;
        missCounter = 0;

        Address[] addresses = cache.returnAddresses(arr);
        for (Address address : addresses) {
            boolean isHit = cache.cache(address);
            if (isHit) {
                hitCounter++;
            } else {
                missCounter++;
            }
        }
    }

    public static void printOutcomeBoard (Cache cache, String[] arr) {
        
        System.out.println();
        
        // Print header row
        System.out.print(LINE + String.format("%-6s", " Set") + LINE);
        for (int i = 0; i < cache.getAssociativity(); i++) {
            String tagName = "Tag " + (i+1);
            System.out.print(String.format("%-" + TAG_WIDTH + "s", " " + tagName) + LINE);
        }
        System.out.println();

        System.out.print(LINE + String.format("%-6s", "------") + LINE);
        for (int i = 0; i < cache.getAssociativity(); i++) {
            System.out.print(String.format("%-" + TAG_WIDTH + "s", "------------") + LINE);
        }
        System.out.println();

        for (int i = 0; i < cache.getNumberOfSets(); i++) {
            System.out.print(LINE + String.format("%-6s", "  " + i) + LINE);
            for (int j = 0; j < cache.getAssociativity(); j++) {
                CacheLine line = cache.getSets()[i].getLines()[j];
                String content;
                if (line.isEmpty()) {
                    content = " ---";
                } else {
                    content = " 0x" + Long.toHexString(line.getTag());
                }
                System.out.print(String.format("%-" + TAG_WIDTH + "s", content) + LINE);
            }
            System.out.println();
        }
        System.out.println();
        
        // statistics
        int totalAccess = hitCounter + missCounter;
        double hitRate = (hitCounter * 100.0) / totalAccess;
        double missRate = (missCounter * 100.0) / totalAccess;
        
        System.out.println("Hits: " + hitCounter + " | Misses: " + missCounter + 
                           " | Hit Rate: " + String.format("%.1f%%", hitRate) + 
                           " | Miss Rate: " + String.format("%.1f%%", missRate));
        System.out.println();
    }

}