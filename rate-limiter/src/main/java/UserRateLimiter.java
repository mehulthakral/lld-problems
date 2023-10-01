import java.util.HashMap;
import java.util.Map;

public class UserRateLimiter {

    Map<Integer, RateLimiter> rateLimiterMap = new HashMap<>();

    public String checkAndServeRequest(int id) {
        if (!rateLimiterMap.containsKey(id)) {
            rateLimiterMap.put(id, new SlidingWindowRateLimiter(5, 60));
        }
        if (rateLimiterMap.get(id).grantAccess()) {
            return "Request served successfully!";
        } else {
            return "Too many requests";
        }
    }

}
