package org.example.boardproject.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.boardproject.domain.Board;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValdation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Board board = new Board();
        board.setName(" ");

        Set<ConstraintViolation<Board>> validate = validator.validate(board);
        System.out.println("validate = " + validate);

    }
}
