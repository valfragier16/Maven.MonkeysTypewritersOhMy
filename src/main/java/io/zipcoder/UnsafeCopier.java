package io.zipcoder;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Modify the run function so that the monkeys each grab the next word and write it to the copy.
 */
public class UnsafeCopier extends Copier {
    ReentrantLock lock = new ReentrantLock();

    public UnsafeCopier(String toCopy) {

        super(toCopy);
    }

    public void run() {
        while(stringIterator.hasNext()){
            try{
                if(stringIterator.hasNext()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(stringIterator.next() + sb.append(" "));
                    //copied += stringIterator.next() + " ";
                    Thread.sleep(new Random().nextInt(100));
                    lock.lock();
                    copied += sb.toString();
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    }
