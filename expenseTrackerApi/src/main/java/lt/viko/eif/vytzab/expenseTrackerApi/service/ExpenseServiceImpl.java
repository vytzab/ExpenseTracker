package lt.viko.eif.vytzab.expenseTrackerApi.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lt.viko.eif.vytzab.expenseTrackerApi.entity.Expense;
import lt.viko.eif.vytzab.expenseTrackerApi.exceptions.ResourceNotFoundException;
import lt.viko.eif.vytzab.expenseTrackerApi.repository.IExpenseRepository;

@Service
public class ExpenseServiceImpl implements IExpenseService {

	@Autowired
	private IExpenseRepository expenseRepo;

	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		return expenseRepo.findAll(page);
	}

	@Override
	public Expense getExpenseById(Long id) {
		Optional<Expense> expense = expenseRepo.findById(id);
		if (expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFoundException("Expense is not found for the id " + id);
	}

	@Override
	public void deleteExpenseById(Long id) {
		expenseRepo.deleteById(id);
	}

	@Override
	public Expense saveExpense(Expense expense) {
		return expenseRepo.save(expense);
	}

	@Override
	public Expense updateExpense(Long id, Expense expense) {
		Expense existingExpense = getExpenseById(id);
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(
				expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense
				.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		return expenseRepo.save(existingExpense);
	}

	@Override
	public List<Expense> readByCategory(String category, Pageable page) {
		return expenseRepo.findByCategory(category, page).toList();
	}

	@Override
	public List<Expense> readByName(String name, Pageable page) {
		return expenseRepo.findByNameContaining(name, page).toList();
	}

	@Override
	public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
		if (startDate == null) {
			startDate = new Date (0);
		}
		if (endDate == null) {
			endDate = new Date (System.currentTimeMillis());
		}
		
		return expenseRepo.findByDateBetween(startDate, endDate, page).toList();
	}
}
