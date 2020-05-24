package utils.wrappers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class GenericWrappers {

	Process hub = null;
	List<Process> nodes = new ArrayList<Process>();
	static int nodeCount = 1;

	public void loadObjects() {

	}

	public void invokeApp(boolean remote, String remoteUrl, boolean headless) {
		try {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=C:\\Users\\" + System.getProperty("user.name").toLowerCase()
					+ "\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
			cap.setCapability("chrome.switches", Arrays.asList("--disable-notifications"));
//			cap.setCapability(ChromeOptions.CAPABILITY, options);

			Configuration.browserCapabilities = cap;
			Configuration.browser = "chrome";
			Configuration.timeout = 60000;
			Configuration.browserSize = "1900x1000";
//			Configuration.startMaximized = false;

			if (remote && !remoteUrl.isEmpty()) {
				Configuration.remote = remoteUrl;

			} else {
				setupSeleniumGrid();
				Configuration.remote = "http://localhost:4444/wd/hub";
			}

			if (headless) {
				Configuration.headless = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void deloadObjects(boolean remote) {
		if (!remote) {
			destroySeleniumGrid();
		}

	}

	public void setupSeleniumGrid() {

		// Initiate hub
		hub = initiateProcess("cmd /c START /MIN grid.bat");
		Selenide.sleep(3000);
		// Initiate nodes
		for (int i = 1; i <= nodeCount; i++) {
			nodes.add(initiateProcess("cmd /c START /MIN node.bat " + (9150 + i)));
			Selenide.sleep(1000);
		}
		Selenide.sleep(3000);
	}

	public void destroySeleniumGrid() {
		// Destroy nodes
		for (int i = 1; i <= nodeCount; i++) {
			deleteProcess("cmd /c FOR /F \"tokens=5 delims= \" %P IN ('netstat -a -n -o ^| findstr :" + (9150 + i)
					+ "') DO TaskKill.exe /PID %P /F");
			Selenide.sleep(1000);
		}

		// Destroy hub
		deleteProcess(
				"cmd /c FOR /F \"tokens=5 delims= \" %P IN ('netstat -a -n -o ^| findstr :4444') DO TaskKill.exe /PID %P /F");
		System.out.println("Destroyed hub successfully");

		// Close all command prompt
		deleteProcess("cmd /c taskkill /IM cmd.exe /F");
		System.out.println("Destroyed cmds successfully");

		// Remove chrome driver
		deleteProcess("cmd /c taskkill /IM chromedriver.exe /F");
		System.out.println("Destroyed drivers successfully");

	}

	public Process initiateProcess(String command) {
		String cwd = System.getProperty("user.dir");
		String file = cwd + "/src/main/java/utils/grid/";
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command, null, new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public Process deleteProcess(String command) {
		String cwd = System.getProperty("user.dir");
		String file = cwd + "/src/main/java/utils/grid/";
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command, null, new File(file));
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
}
