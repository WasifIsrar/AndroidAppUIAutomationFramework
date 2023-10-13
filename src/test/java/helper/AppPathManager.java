package helper;

public class AppPathManager {
	private static AppPathManager instance;
    private String appPath;

    private AppPathManager() {
        // Load app path from properties file or configuration
        appPath = "C:\\...\\src\\test\\java\\resources\\app-release.apk";
    }

    public static AppPathManager getInstance() {
        if (instance == null) {
            instance = new AppPathManager();
        }
        return instance;
    }

    public String getAppPath() {
        return appPath;
    }
}
