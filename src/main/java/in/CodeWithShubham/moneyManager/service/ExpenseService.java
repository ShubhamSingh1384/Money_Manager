package in.CodeWithShubham.moneyManager.service;

import in.CodeWithShubham.moneyManager.dto.ExpenseDTO;
import in.CodeWithShubham.moneyManager.entity.CategoryEntity;
import in.CodeWithShubham.moneyManager.entity.ExpenseEntity;
import in.CodeWithShubham.moneyManager.entity.ProfileEntity;
import in.CodeWithShubham.moneyManager.repository.CategoryRepository;
import in.CodeWithShubham.moneyManager.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;

    // Add new Expense to DB
    public ExpenseDTO addExpense(ExpenseDTO expenseDTO){
        ProfileEntity profile = profileService.getCurrentProfile();
        CategoryEntity category = categoryRepository.findById(expenseDTO.getCategoryId())
                .orElseThrow(()-> new RuntimeException("category not found"));
        ExpenseEntity newExpense = toEntity(expenseDTO, profile, category);
        newExpense = expenseRepository.save(newExpense);

        return toDTO(newExpense);
    }

    // Retrieves all expenses for current month/based on the start date and end date


    // helper function

    public ExpenseEntity toEntity(ExpenseDTO dto, ProfileEntity profile, CategoryEntity category){
        return ExpenseEntity.builder()
                .name(dto.getName())
                .icon(dto.getIcon())
                .date(dto.getDate())
                .amount(dto.getAmount())
                .profile(profile)
                .category(category)
                .build();
    }

    public ExpenseDTO toDTO(ExpenseEntity entity){
        return ExpenseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .icon(entity.getIcon())
                .categoryName(entity.getCategory() != null ? entity.getCategory().getName() : "N/A")
                .categoryId(entity.getCategory() != null ? entity.getCategory().getId() : null)
                .amount(entity.getAmount())
                .date(entity.getDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
