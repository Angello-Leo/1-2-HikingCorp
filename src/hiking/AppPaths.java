package hiking;

import java.io.File;

/** Resolves the application's base folder (the one that contains data/, models/ and config/). */
public final class AppPaths {
    private static File base;
    private AppPaths() {}

    public static synchronized File base() {
        if (base != null) return base;
        File cwd = new File(System.getProperty("user.dir"));
        if (new File(cwd, "data").isDirectory()) return base = cwd;
        try {   // running from dist/HiKingApp.jar : base is the parent of dist/
            File jar = new File(AppPaths.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File dir = jar.isFile() ? jar.getParentFile() : jar;
            for (int i = 0; i < 4 && dir != null; i++, dir = dir.getParentFile())
                if (new File(dir, "data").isDirectory()) return base = dir;
        } catch (Exception ignored) { }
        return base = cwd;
    }
    public static File data()   { return new File(base(), "data"); }
    public static File models() { File f = new File(base(), "models"); f.mkdirs(); return f; }
    public static File config() { File f = new File(base(), "config"); f.mkdirs(); return f; }
}
