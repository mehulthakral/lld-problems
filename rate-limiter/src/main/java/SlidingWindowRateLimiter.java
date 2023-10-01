import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter {

    Queue<Long> slidingWindow;
    int thresholdCap;
    int windowDuration;

    public SlidingWindowRateLimiter(int thresholdCap, int windowDuration) {
        this.thresholdCap = thresholdCap;
        this.windowDuration = windowDuration;
        this.slidingWindow = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        updateQueue(currentTime);
        if (slidingWindow.size() < thresholdCap) {
            slidingWindow.offer(currentTime);
            return true;
        } else {
            return false;
        }
    }

    private void updateQueue(long currentTime) {
//        if (slidingWindow.isEmpty()) {
//            return;
//        }
//        long time = (currentTime - slidingWindow.peek())/1000;
//        while (time > windowDuration) {
//            slidingWindow.poll();
//            if (slidingWindow.isEmpty()) {
//                break;
//            }
//            time = (currentTime - slidingWindow.peek())/1000;
//        }

        while(!slidingWindow.isEmpty() && (currentTime - slidingWindow.peek())/1000 > windowDuration) {
            System.out.printf("Deleting %d%n", slidingWindow.peek());
            slidingWindow.poll();
        }
    }
}
