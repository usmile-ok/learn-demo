package com.usmile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProblemTwo {

    public static void main(String[] args) throws InterruptedException {
        CachedData cachedData = new CachedData();

        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> cachedData.processCachedData("Cached Data" + Thread.currentThread().getName()));
            thread.start();
        }

       TimeUnit.MINUTES.sleep(1);
    }
}


class CachedData<T> {
    private T data;
    volatile boolean isCacheValid = false;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();


    public void processCachedData(T data) {
        readLock.lock();
        if (!isCacheValid) {
            readLock.unlock();
            writeLock.lock();
            if (!isCacheValid) {
                this.data = data;
                isCacheValid = true;
            }
            readLock.lock();
            writeLock.unlock();
        }
        getData();
        readLock.unlock();
    }


    public T getData() {
        System.out.println(data);
        return this.data;
    }
}