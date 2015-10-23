package KataBank;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import Mockist.Collaborator;
import Mockist.MyClass;

public class AccountServiceImplTest {
	
	/** Servicio de cuentas */
	private AccountService account;
	
	private TreasuryService treasuryService;
	
	private AuditService auditService;
	
	private ConsoleService consoleService;
	
	@Before
	public void setUp() throws Exception {
		treasuryService = mock(TreasuryService.class);	
		auditService = mock(AuditService.class);
		consoleService = mock(ConsoleService.class);
		account = new AccountServiceImpl(treasuryService,auditService,consoleService);
	}

	@Test
	public void testAllowedDeposit() {	
		
		when(auditService.requestPermission(100)).thenReturn(Boolean.TRUE);
		account.deposit(100);
		
		verify(auditService).requestPermission(100);
		verify(treasuryService).modifyBalance(100);
	}
	
	@Test
	public void testBasicWithdraw(){	
		when(auditService.requestPermission(-100)).thenReturn(true);
		account.withdraw(100);
		
		verify(auditService).requestPermission(-100);
		verify(treasuryService).modifyBalance(-100);
	}
	
	@Test
	public void should_print_statement_containing_all_transactions() {
	    
		account.deposit(1000);
	    account.withdraw(100);
	    account.deposit(500);

	    account.printStatement();

	    verify(consoleService).printLine("DATE | AMOUNT | BALANCE");
	    /*verify(console).printLine("10/04/2014 | 500 | 1400");
	    verify(console).printLine("02/04/2014 | -100 | 900");
	    verify(console).printLine("01/04/2014 | 1000 | 1000");
	    */
	}
}
