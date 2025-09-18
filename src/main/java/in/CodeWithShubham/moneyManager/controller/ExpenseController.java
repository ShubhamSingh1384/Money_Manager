package in.CodeWithShubham.moneyManager.controller;

import in.CodeWithShubham.moneyManager.dto.ExpenseDTO;
import in.CodeWithShubham.moneyManager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO dto){
        System.out.println("dto data is this" + dto);
        ExpenseDTO newExpense = expenseService.addExpense(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newExpense);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getExpense(){
        List<ExpenseDTO> expense = expenseService.getCurrentMonthExpensesForCurrentUser();

        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
