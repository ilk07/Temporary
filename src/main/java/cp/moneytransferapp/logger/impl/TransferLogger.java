package cp.moneytransferapp.logger.impl;

import cp.moneytransferapp.logger.TransferJournal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Getter
@Setter
@Component
public class TransferLogger implements TransferJournal {

//    @Autowired
//    private ApplicationContext appContext;

    private static TransferLogger INSTANCE = null;
    private String filename;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private TransferLogger() {
        this.filename = getFileNameFromPropertyFile();
    }

    public static TransferLogger getInstance() {
        if (INSTANCE == null) {
            synchronized (TransferLogger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TransferLogger();
                }
            }
        }
        return INSTANCE;
    }

    private String createLogMessage(String msg) {
        return LocalDateTime.now().format(format) + "[Transfer->" + msg + "]";
    }

    private void writeToFile(String msg) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(createLogMessage(msg));
            bw.write('\n');
            bw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getFileNameFromPropertyFile() {
        try (InputStream input = new FileInputStream("./src/main/resources/application.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("transfer.logFilename");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }


    @Override
    public boolean logToJournal(String msg) {
        writeToFile(createLogMessage(msg));
        return true;
    }
}

