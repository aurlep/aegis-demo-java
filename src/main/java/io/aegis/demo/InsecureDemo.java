package io.aegis.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * INTENTIONALLY INSECURE — a target for SpotBugs / Find Security Bugs and
 * Semgrep. Not wired into the running app; it exists so the generated pipeline
 * has real findings. Do not copy any of this into real code.
 */
public final class InsecureDemo {

    // Hardcoded credentials — secret scanners (Gitleaks, TruffleHog) should flag these.
    // Deliberately generic (not a real provider format) so GitHub push protection
    // does not block the commit, while pattern/entropy scanners still catch them.
    static final String DB_PASSWORD = "SuperSecret123!";
    static final String API_KEY = "a3f8b1c9d7e2f4a6b8c0d2e4f6a8b0c2e1d3f5a7";

    private InsecureDemo() {
    }

    /** Broken hashing: MD5 for a password digest. */
    public static byte[] weakHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(password.getBytes());
    }

    /** Command injection: untrusted input into a shell command. */
    public static Process runCommand(String userInput) throws Exception {
        return Runtime.getRuntime().exec("ping " + userInput);
    }

    /** SQL injection: query built by string concatenation. */
    public static String buildQuery(String user) {
        return "SELECT * FROM users WHERE name = '" + user + "'";
    }
}
