package hiking.security;

import hiking.model.Perm;
import hiking.model.User;

/** Holds the logged-in user and performs permission checks for the data-access layer. */
public final class Session {
    private static volatile User user;
    private Session() {}
    public static User user() { return user; }
    public static void start(User u) { user = u; }
    public static void end() { user = null; }
    public static boolean can(Perm p) { return user != null && user.role.can(p); }
    public static void require(Perm p) {
        if (user == null) throw new SecurityException("Please sign in first.");
        if (!user.role.can(p)) throw new SecurityException("Your user level (" + user.role + ") is not allowed to do this.");
    }
    public static int id() { return user == null ? 0 : user.id; }
    public static String name() { return user == null ? "system" : user.username; }
}
