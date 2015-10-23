package KataBank;
/**
 * Servicio de cuentas
 * @author Carlos Castillo
 *
 */
public interface AccountService {
    public void deposit(int amount);

    public void withdraw(int amount);

    public void printStatement();
}
