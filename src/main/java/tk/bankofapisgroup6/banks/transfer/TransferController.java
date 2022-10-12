package tk.bankofapisgroup6.banks.transfer;

import lombok.AllArgsConstructor;
import tk.bankofapisgroup6.banks.accounts.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/transfer")
@CrossOrigin
@AllArgsConstructor
public class TransferController {

    @Autowired
    private TransferService transferService;

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    @PostMapping("/credit")
    @CrossOrigin
    public ResponseEntity<String> credit(@RequestBody TransferRequest request){
        ResponseEntity<String> response = null;
        logger.info("Credit API Called");
        try {
            response = ResponseEntity.status(HttpStatus.OK).body(transferService.credit(request));
        }catch(IllegalStateException exception) {
            exception.printStackTrace();
            response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exception.getLocalizedMessage());
        }
        return response;
    }

    @PostMapping("/debit")
    @CrossOrigin
    public ResponseEntity<String> debit(@RequestBody TransferRequest request){
    	logger.info("Debit API Called");
        ResponseEntity<String> response = null;
        try {
            response = ResponseEntity.status(HttpStatus.OK).body(transferService.debit(request));
        }catch(IllegalStateException exception) {
            exception.printStackTrace();
            response = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exception.getLocalizedMessage());
        }
        return response;
    }
}
