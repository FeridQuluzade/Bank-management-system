package bank.system.manager.account.application.dto;

public class AccountDto {
    private long accId;
    private double sum;
    private long ownerId;
    private double year;

    public AccountDto() {
    }

    public AccountDto(long accId, double sum, long ownerId, double year) {
        this.accId = accId;
        this.sum = sum;
        this.ownerId = ownerId;
        this.year = year;
    }

    public long getAccId() {
        return accId;
    }

    public void setAccId(long accId) {
        this.accId = accId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }
}
