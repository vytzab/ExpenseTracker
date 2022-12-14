/**
 * 
 */
package lt.viko.eif.vytzab.expenseTrackerApi.services;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lt.viko.eif.vytzab.expenseTrackerApi.entity.Expense;

/**
 * @author Vytautas
 *
 *         Interface to outline operations with <Expense> objects.
 */
public interface IExpenseService {

	Expense createExpense(Expense expense);

	Page<Expense> getExpenses(Pageable page);

	Expense getExpenseById(Long id);

	Expense updateExpense(Long id, Expense expense);

	void deleteExpenseById(Long id);

	List<Expense> readByCategory(String category, Pageable page);

	List<Expense> readByName(String name, Pageable page);

	List<Expense> readByDate(Date startDate, Date endDate, Pageable page);
}
