package Model;

import java.util.ArrayList;

public interface TransactionDTO {
	int addIncome(Transaction T);
	int addExpense(Transaction T);
	ArrayList<Transaction> display(User user);
	Transaction getDetail(Transaction T);
	int login(User user);
	int register(User user);
	ArrayList<Transaction> month(Transaction t);
	ArrayList<Transaction> today(Transaction t);
}
