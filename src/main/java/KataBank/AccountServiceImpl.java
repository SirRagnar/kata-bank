package KataBank;

public class AccountServiceImpl implements AccountService {

	private TreasuryService treasuryService;
	
	private AuditService auditService;
	
	private ConsoleService consoleService;

	public AccountServiceImpl(TreasuryService treasuryService, AuditService auditService, ConsoleService consoleService){
		this.treasuryService = treasuryService;
		this.auditService = auditService;
		this.consoleService = consoleService;
	}
	public void deposit(int amount) {
		boolean permisionGranted = auditService.requestPermission(amount);
		if(permisionGranted){
			treasuryService.modifyBalance(amount);
		}
	}

	public void withdraw(int amount) {
		int invertedAmount = -amount;
		boolean permisionGranted = auditService.requestPermission(invertedAmount);
		if(permisionGranted){
			treasuryService.modifyBalance(invertedAmount);
		}
	}

	public void printStatement() {
		String header = treasuryService.getStatementHeader();
		//List<>

	}

}
