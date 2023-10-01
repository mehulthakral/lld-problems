import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RateLimiterTest {

    @Test
    public void testRateLimiterBelowThreshold() {
        UserRateLimiter userRateLimiter = new UserRateLimiter();
        for (int i=0; i<5; i++) {
            assertEquals("Request served successfully!", userRateLimiter.checkAndServeRequest(1));
        }
    }

    @Test
    public void testRateLimiterAboveThreshold() {
        UserRateLimiter userRateLimiter = new UserRateLimiter();
        for (int i=0; i<5; i++) {
            assertEquals("Request served successfully!", userRateLimiter.checkAndServeRequest(1));
        }
        assertNotEquals("Request served successfully!", userRateLimiter.checkAndServeRequest(1));
    }

    @Test
    public void testRateLimiterAboveThresholdWithWait() throws InterruptedException {
        UserRateLimiter userRateLimiter = new UserRateLimiter();
        for (int i=0; i<5; i++) {
            assertEquals("Request served successfully!", userRateLimiter.checkAndServeRequest(1));
        }
        Thread.sleep(61000);
        assertEquals("Request served successfully!", userRateLimiter.checkAndServeRequest(1));
    }

}
