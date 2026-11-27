package hiking.model;

import java.time.LocalDateTime;

public final class User {
    public int id; public String username, fullName; public Role role; public boolean active; public LocalDateTime lastLogin, createdAt;
}
