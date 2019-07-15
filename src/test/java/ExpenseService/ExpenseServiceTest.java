package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Test;

import static ExpenseService.ExpenseService.getExpenseCodeByProjectTypeAndName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseServiceTest {
  @Test
  void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
    ProjectType projectType = ProjectType.INTERNAL;
    Project project = new Project(projectType, "A");

    ExpenseType expenseType = getExpenseCodeByProjectTypeAndName(project);

    assertEquals(expenseType, ExpenseType.INTERNAL_PROJECT_EXPENSE);
  }

  @Test
  void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
    ProjectType projectType = ProjectType.EXTERNAL;
    Project project = new Project(projectType, "Project A");

    ExpenseType expenseType = getExpenseCodeByProjectTypeAndName(project);

    assertEquals(expenseType, ExpenseType.EXPENSE_TYPE_A);
  }

  @Test
  void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
    ProjectType projectType = ProjectType.EXTERNAL;
    Project project = new Project(projectType, "Project B");

    ExpenseType expenseType = getExpenseCodeByProjectTypeAndName(project);

    assertEquals(expenseType, ExpenseType.EXPENSE_TYPE_B);
  }

  @Test
  void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
    ProjectType projectType = ProjectType.EXTERNAL;
    Project project = new Project(projectType, "Project C");

    ExpenseType expenseType = getExpenseCodeByProjectTypeAndName(project);

    assertEquals(expenseType, ExpenseType.OTHER_EXPENSE);
  }

  @Test
  void should_throw_unexpected_project_exception_if_project_is_invalid() {
    ProjectType projectType = ProjectType.UNEXPECTED_PROJECT_TYPE;
    Project project = new Project(projectType, "Project D");

    assertThrows(UnexpectedProjectTypeException.class, () ->
        getExpenseCodeByProjectTypeAndName(project));
  }
}