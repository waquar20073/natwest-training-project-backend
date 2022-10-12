package tk.bankofapisgroup6.banks.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.bankofapisgroup6.banks.accounts.AccountService;

@Service
public class TransferService {

    @Autowired
    private AccountService accountService;
    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    public String credit(TransferRequest request) {
        try{
            int status = accountService.creditAmount(request.getAccountNo(), request.getAmount());
            logger.info(String.valueOf(status));
            if(status > 0 ){
                return "Credit Success";
            }else{
                return "Credit Failed";
            }
        }
        catch(Exception exception){
            exception.printStackTrace();
            return "Credit Failed";
        }


    }

    public String debit(TransferRequest request) {
        try{
            int status = accountService.debitAmount(request.getAccountNo(), request.getAmount());
            logger.info(String.valueOf(status));
            return "Debit Success";
        }
        catch(Exception exception){
            exception.printStackTrace();
            return "Debit Failed";
        }
    }
}
