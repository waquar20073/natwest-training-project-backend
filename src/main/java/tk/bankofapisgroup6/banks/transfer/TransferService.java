package tk.bankofapisgroup6.banks.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.bankofapisgroup6.banks.accounts.AccountService;

@Service
public class TransferService {

    @Autowired
    private AccountService accountService;

    public String credit(TransferRequest request) {
        try{
            int status = accountService.creditAmount(request.getAccountNo(), request.getAmount());
            System.out.println(status);
            if(status > 0 ){
                return "Credit Success";
            }else{
                return "Credit Failed";
            }

        }
        catch(Exception exception){
            System.out.println(exception);
            return "Credit Failed";
        }


    }

    public String debit(TransferRequest request) {
        try{
            int status = accountService.debitAmount(request.getAccountNo(), request.getAmount());
            System.out.println(status);
            return "Debit Success";
        }
        catch(Exception exception){
            System.out.println(exception);
            return "Debit Failed";
        }
    }
}
