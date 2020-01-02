package VertXTimeManagement.Storage;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueHandler implements Runnable {

    private BlockingQueue<SequentialRunnable> blockingQueue;

    public BlockingQueueHandler(BlockingQueue<SequentialRunnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while (true) {
            try {
                if (!this.blockingQueue.take().run()) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
