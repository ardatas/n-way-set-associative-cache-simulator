package simulator;

public class Cache {

    CacheSet[] sets;
    Address[] addresses;

    private int cacheLineSize;
    private int numberOfSets;
    private int associativity;
    private String replacementStrategy;

    private int offsetBits;
    private int indexBits;
    private int address;

    public Cache(int cacheLineSize, int numberOfSets, int associativity, String replacementStrategy) {

        this.cacheLineSize = cacheLineSize;
        this.numberOfSets = numberOfSets;
        this.associativity = associativity;
        this.replacementStrategy = replacementStrategy;

        // initialize a cache set
        sets = new CacheSet[numberOfSets];
        for (int i = 0; i < numberOfSets; i++) {
            sets[i] = new CacheSet(associativity);
        }

        // find bits
        this.offsetBits = binlog(cacheLineSize);
        this.indexBits = binlog(numberOfSets);
    }

    public boolean cache(Address address) {
        int index = (int) address.getIndex();
        return sets[index].access(address, replacementStrategy);
    }


    // Helpers
    public Address[] returnAddresses(String[] arr) {
        addresses = new Address[arr.length]; // Initialize the array
        for (int i = 0; i < arr.length; i++) {
            addresses[i] = new Address(arr[i], this.offsetBits, this.indexBits);
        }
        return addresses;
    }

    public static int binlog( int bits ) // returns 0 for bits=0
    {
        int log = 0;
        if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
        if( bits >= 256 ) { bits >>>= 8; log += 8; }
        if( bits >= 16  ) { bits >>>= 4; log += 4; }
        if( bits >= 4   ) { bits >>>= 2; log += 2; }
        return log + ( bits >>> 1 );
    }

    public int getOffsetBits() {
        return offsetBits;
    }

    public int getIndexBits() {
        return indexBits;
    }

    public int getAddress() {
        return address;
    }

    public int getCacheLineSize() {
        return cacheLineSize;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public int getAssociativity() {
        return associativity;
    }

    public String getReplacementStrategy() {
        return replacementStrategy;
    }

    public CacheSet[] getSets() {
        return sets;
    }

}
