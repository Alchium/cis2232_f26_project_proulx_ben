package ca.hccis.files;

import ca.hccis.files.data.MatchStore;
import ca.hccis.files.thread.ConsoleRunner;
import ca.hccis.files.thread.SwingRunner;

/**
 * Starts the application in two separate threads that share the same data:
 * one works completely in the console, the other completely with JOptionPane.
 *
 * @author BP / Claude
 * @since 20261012
 */
public class Controller {

    public static void main(String[] args) {

        MatchStore.initialize();

        Thread consoleThread = new ConsoleRunner();
        Thread swingThread = new Thread(new SwingRunner(), "JOptionPane-Thread");

        consoleThread.start();
        swingThread.start();

        try {
            consoleThread.join();
            swingThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted while waiting for threads to finish.");
        }
        System.out.println("Both threads finished. " + MatchStore.getAccumulatorSummary());
    }
}
