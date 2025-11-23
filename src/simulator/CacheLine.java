package simulator;

public class CacheLine {

    private long tag;
    private boolean isEmpty;
    private int lastUsed;
    private int count;

    public CacheLine() {
        this.tag = 0;
        this.isEmpty = true;
        this.lastUsed = 0;
        this.count = 0;
    }

    public long getTag() {
        return tag;
    }

    public void setTag(long tag) {
        this.tag = tag;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(int lastUsed) {
        this.lastUsed = lastUsed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increaseCount() {
        this.count++;
    }

}
