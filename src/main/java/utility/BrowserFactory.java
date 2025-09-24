package utility;

public class BrowserFactory {
    private static ThreadLocal<String> tlBrowser=new ThreadLocal<>();

    public static void setBrowser(String browser){
        tlBrowser.set(browser);
    }

    public static String getBrowser(){
        return tlBrowser.get();
    }

}
/*
System.setProperty("browser", value) is JVM-wide (shared for all threads).
When TestNG spins up parallel tests (thread-count=2),
the second test overwrites the first test’s browser property
→ every scenario ends up reading the same value.
 */
