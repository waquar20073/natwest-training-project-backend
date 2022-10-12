package tk.bankofapisgroup6.banks.transfer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TransferRequest {
    private long accountNo;
    private Double amount;
}
