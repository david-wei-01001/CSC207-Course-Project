package constants;

import java.util.HashMap;
import java.util.Map;

public class Achievements {

    public static final String POST_ACHIEVEMENT = "Post Achievement";
    public static final int FIRST_POST_THRESHOLD = 1;
    public static final String FIRST_POST = "First Post";
    public static final int FIRST_POST_REWARD = 1;

    public static final int FIFTH_POST_THRESHOLD = 5;
    public static final String FIFTH_POST = "Fifth Post";
    public static final int FIFTH_POST_REWARD = 5;

    public static final int TENTH_POST_THRESHOLD = 10;
    public static final String TENTH_POST = "Tenth Post";
    public static final int TENTH_POST_REWARD = 10;

    public static final int[] ARRAY_OF_POST_THRESHOLDS = {FIRST_POST_THRESHOLD, FIFTH_POST_THRESHOLD, TENTH_POST_THRESHOLD};
    public static final Map<Integer, String> MAP_POST_THRESHOLDS_TO_ACHIEVEMENT = Map.ofEntries(
            Map.entry(FIRST_POST_THRESHOLD, FIRST_POST),
            Map.entry(FIFTH_POST_THRESHOLD, FIFTH_POST),
            Map.entry(TENTH_POST_THRESHOLD, TENTH_POST)
    );
    public static final Map<Integer, Integer> MAP_POST_THRESHOLDS_TO_REWARD = Map.ofEntries(
            Map.entry(FIRST_POST_THRESHOLD, FIRST_POST_REWARD),
            Map.entry(FIFTH_POST_THRESHOLD, FIFTH_POST_REWARD),
            Map.entry(TENTH_POST_THRESHOLD, TENTH_POST_REWARD)
    );


    public static final String LOGIN_ACHIEVEMENT = "Login Achievement";
    public static final int FIRST_LOGIN_THRESHOLD = 1;
    public static final String FIRST_LOGIN = "First Login";
    public static final int FIRST_LOGIN_REWARD = 1;

    public static final int[] ARRAY_OF_TOTAL_LOGINS_THRESHOLDS = {FIRST_LOGIN_THRESHOLD};
    public static final Map<Integer, String> MAP_TOTAL_LOGINS_THRESHOLDS_TO_ACHIEVEMENT = Map.ofEntries(
            Map.entry(FIRST_LOGIN_THRESHOLD, FIRST_LOGIN)
    );
    public static final Map<Integer, Integer> MAP_TOTAL_LOGINS_THRESHOLDS_TO_REWARD = Map.ofEntries(
            Map.entry(FIRST_LOGIN_THRESHOLD, FIRST_LOGIN_REWARD)
    );


    public static final String[] ARRAY_OF_ALL_ACHIEVEMENTS = {FIRST_POST, FIRST_LOGIN, FIFTH_POST, TENTH_POST, FIRST_LOGIN};

    //hello



}
