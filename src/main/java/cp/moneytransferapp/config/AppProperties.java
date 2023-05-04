package cp.moneytransferapp.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppProperties {
    private String logFilename;
    private double taxFee;
}