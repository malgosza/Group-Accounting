package logic;

import java.math.BigDecimal;

public class SettlementResult {
    public BigDecimal amount;
    public SettlementDirection direction;

    public SettlementResult(BigDecimal amount, SettlementDirection direction){
        this.amount=amount.abs();
        this.direction=direction;
    }
}
