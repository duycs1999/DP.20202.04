package subsystem.interbank;

/**
 * @author
 */
public class InterbankConfigs {

    public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
    public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
    public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
    public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";

    static final String PUBLIC_KEY = "AQzdE8O/fR8=";
    static final String SECRET_KEY = "BUXj/7/gHHI=";
    static final String PAY_COMMAND = "pay";
    static final String VERSION = "1.0.0";
}
