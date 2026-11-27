package hiking.model;

import java.util.EnumSet;
import java.util.Set;

/** User levels and what each one is allowed to do (enforced in the UI menu AND in the data-access layer). */
public enum Role {
    ADMIN("Administrator", EnumSet.of(Perm.DASH_FULL, Perm.USERS_MANAGE, Perm.AUDIT_VIEW, Perm.ANALYSIS_PREDICT, Perm.INVENTORY_MANAGE, Perm.PRODUCT_PURGE)),
INVENTORY_CLERK("Inventory Clerk", EnumSet.of(Perm.DASH_INVENTORY, Perm.INVENTORY_MANAGE, Perm.PURGE_REQUEST)),
    SALES_PERSONNEL("Sales Personnel", EnumSet.of(Perm.DASH_SALES, Perm.SALES_CREATE, Perm.SALES_VIEW)),
    INSTRUCTOR("Instructor", EnumSet.of(Perm.DASH_FULL, Perm.ANALYSIS_PREDICT, Perm.ANALYSIS_DETAILS, Perm.MODEL_RETRAIN)),
    /** Default level for everyone who signs up: view-only stock overview + own account. An administrator promotes them (Users page). */
    STAFF("Staff", EnumSet.of(Perm.DASH_INVENTORY));

    public final String label; private final Set<Perm> perms;
    Role(String label, Set<Perm> p) { this.label = label; this.perms = p; }
    public boolean can(Perm p) { return perms.contains(p); }
    @Override public String toString() { return label; }
}
