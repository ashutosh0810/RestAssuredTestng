package reporting;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    static Logger log = LogManager.getLogger(ExtentManager.class);
    private static ExtentReports extentReports;
    static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    private static String reportFilename = "Booking Api Automation";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
    private static String reportFileLocation = reportFilepath + fileSeperator + reportFilename.concat(timeStamp) + ".html";


    public static ExtentReports getInstance() {
        if (extentReports == null) {
            createInstance();
        }
        return extentReports;
    }

    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);

        extentSparkReporter.config().setReportName(reportFilename);

        extentSparkReporter.config().setDocumentTitle(reportFilename);
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setTimelineEnabled(true);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("AUT", "QA");

        return extentReports;
    }

    public static String getReportPath(String path) {
        File reportDir = new File(path);
        if (!reportDir.exists()) {
            log.info(" Directory does not exists");
            if (reportDir.mkdir()) {
                log.debug(" Directory " + path + " created");
                return reportFileLocation;
            } else {
                log.error(" Fail to create report directory : " + path);
            }

        } else {
            log.info(" Report Directory already exits " + path);
        }
        return reportFileLocation;
    }
}
