package me.antiddos;

public final class RateLimit {
    private final int max;
    private final long window;
    private int count;
    private long start = System.currentTimeMillis();
    public RateLimit(int max, long window) { this.max = max; this.window = window; }
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - start >= window) { start = now; count = 0; }
        return ++count <= max;
    }
}
