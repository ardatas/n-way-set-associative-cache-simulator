package simulator;

public class CacheSet {

    CacheLine[] lines;
    private int accessCounter = 0;
    private int fifoPointer = 0;

    public CacheSet(int associativity) {

        // construct the Cache Lines
        lines = new CacheLine[associativity];
        for (int i = 0; i < associativity ; i++) {
            lines[i] = new CacheLine();
        }
        this.accessCounter = 0;
    }

    public boolean access(Address address, String replacementStrategy) {
        accessCounter++;

        // Check for hit
        for (CacheLine line : lines) {
            if (!line.isEmpty() && line.getTag() == address.getTag()) {
                line.setLastUsed(accessCounter);
                address.increaseAddressCounter(1);
                line.increaseCount();
                return true;
            }
        }

        // Miss - need to load into cache
        loadLine(address, replacementStrategy);
        return false;
    }

    public void loadLine(Address address, String strategy) {

        // Check for empty line
        for(CacheLine line : lines) {
            if(line.isEmpty()) {
                line.setTag(address.getTag());
                line.setLastUsed(accessCounter);
                line.setEmpty(false);
                line.increaseCount();
                return;
            }
        }

        // If no empty line than a tag must be replaced

        switch(strategy) {
            case "LRU" :
                loadLineLRU(address);
                break;
            case "LFU" :
                loadLineLFU(address);
                break;
            case "FIFO" :
                loadLineFIFO(address);
                break;
            default: loadLineLRU(address);
        }

    }

    public void loadLineLRU(Address a) {
        CacheLine lineToBeReplaced = lines[0];
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].getLastUsed() < lineToBeReplaced.getLastUsed()) {
                lineToBeReplaced = lines[i];
            }
        }
        lineToBeReplaced.setTag(a.getTag());
        lineToBeReplaced.setLastUsed(accessCounter);
        lineToBeReplaced.setCount(1);
        lineToBeReplaced.setEmpty(false);

    }

    public void loadLineLFU(Address a) {
        CacheLine lineToBeReplaced = lines[0];
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].getCount() < lineToBeReplaced.getCount()) {
                lineToBeReplaced = lines[i];
            }
        }
        lineToBeReplaced.setTag(a.getTag());
        lineToBeReplaced.setLastUsed(accessCounter);
        lineToBeReplaced.setCount(1);
        lineToBeReplaced.setEmpty(false);

    }

    public void loadLineFIFO(Address a) {
        CacheLine lineToBeReplaced = lines[fifoPointer];

        lineToBeReplaced.setTag(a.getTag());
        lineToBeReplaced.setLastUsed(accessCounter);
        lineToBeReplaced.setCount(1);
        lineToBeReplaced.setEmpty(false);

        fifoPointer = (fifoPointer + 1) % lines.length;

    }

    public void loadLineRandom(Address a) {

    }

    public int getFifoPointer() {
        return fifoPointer;
    }

    public void setFifoPointer(int fifoPointer) {
        this.fifoPointer = fifoPointer;
    }

    public CacheLine[] getLines() {
        return lines;
    }
}
