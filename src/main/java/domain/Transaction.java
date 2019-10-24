package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Transaction {

    private final String transactionId;
    private final String timeStamp;
    private final double amount;
    private final String transmitter;
    private final String receiver;

}
