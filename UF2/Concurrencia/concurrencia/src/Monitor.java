

public class Monitor {
    private boolean busy = false;
    public synchronized void openReading() {
        while (!canRead())
            waiting();
        busy = true;
    }
    private boolean canRead() {
        if (busy) return false;
        return true;
    }
    public synchronized void closeReading() {
        busy = false;
        notifyAll();
    }
    public synchronized void openWriting() {
        while (!canWrite())
            waiting();
        busy = true;
    }
    private boolean canWrite() {
        if (busy) return false;
        return true;
    }
    public synchronized void closeWriting() {
        busy = false;
        notifyAll();
    }

    private synchronized void waiting() {
        try {
            wait();
        } catch (InterruptedException ignored) {
        }
    }
}
