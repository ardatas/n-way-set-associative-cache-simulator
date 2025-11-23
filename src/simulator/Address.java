package simulator;

public class Address {

    // attributes of an address
    private long offset;
    private long index;
    private long tag;
    private int addressCounter;

    public Address(String address, int offsetBits, int indexBits) {
        // Convert hex to long directly, no need to convert to binary string first
        long binaryAddress = hexToLong(address);
        this.offset = binaryAddress & ((1L << offsetBits) - 1);

        long indexMask =  (1L << indexBits) - 1;
        this.index = (binaryAddress >> offsetBits) & indexMask;

        this.tag = binaryAddress >> (offsetBits + indexBits);

        this.addressCounter = 0;
    }

    private long hexToLong(String hex) {
        if (hex.startsWith("0X") || (hex.startsWith("0x"))) {
            hex = hex.substring(2);
        }

        return Long.parseUnsignedLong(hex, 16);
    }

    public long getOffset() {
        return offset;
    }

    public long getIndex() {
        return index;
    }

    public long getTag() {
        return tag;
    }

    public void increaseAddressCounter(int addressCounter) {
        this.addressCounter += addressCounter;
    }

    public void setAddressCounterZero() {
        this.addressCounter = 0;
    }


}
