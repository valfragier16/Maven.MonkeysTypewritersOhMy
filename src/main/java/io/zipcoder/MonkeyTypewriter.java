package io.zipcoder;

import java.util.ArrayList;
import java.util.List;

public class MonkeyTypewriter {
    Copier copier;
    Copier safeCopier;
    String introduction;

    public MonkeyTypewriter(){
        this.introduction = "It was the best of times,\n" +
                "it was the blurst of times,\n" +
                "it was the age of wisdom,\n" +
                "it was the age of foolishness,\n" +
                "it was the epoch of belief,\n" +
                "it was the epoch of incredulity,\n" +
                "it was the season of Light,\n" +
                "it was the season of Darkness,\n" +
                "it was the spring of hope,\n" +
                "it was the winter of despair,\n" +
                "we had everything before us,\n" +
                "we had nothing before us,\n" +
                "we were all going direct to Heaven,\n" +
                "we were all going direct the other way--\n" +
                "in short, the period was so far like the present period, that some of\n" +
                "its noisiest authorities insisted on its being received, for good or for\n" +
                "evil, in the superlative degree of comparison only.";

        copier = new UnsafeCopier(introduction);
        safeCopier = new SafeCopier(introduction);

    }


    public static void main(String[] args) {
        MonkeyTypewriter mt = new MonkeyTypewriter();
        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.

        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.
        mt.runExperiment();


        System.out.println("\n\nUncoordinated Monkeys:\n\n" + mt.copier.copied);
        System.out.println("\n\nCohorting Monkeys(5.2):\n\n" + mt.safeCopier.copied);
        // Print out the copied versions here.
    }


    void runExperiment() {
        List<Thread> uncoordinatedMonkeys = new ArrayList<Thread>();
        List<Thread> coordinatedMonkeys = new ArrayList<Thread>();

        for (int i = 0; i < 5; i++) {
            uncoordinatedMonkeys.add(new Thread(this.copier));
            coordinatedMonkeys.add(new Thread(this.safeCopier));
        }
        for(Thread monkey:uncoordinatedMonkeys) monkey.start();
        for(Thread monkey:coordinatedMonkeys) monkey.start();
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }
    }

}