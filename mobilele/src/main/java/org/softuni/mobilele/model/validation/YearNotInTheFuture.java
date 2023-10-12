package org.softuni.mobilele.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = YearNotInTheFutureValidator.class)
public @interface YearNotInTheFuture {

  String message() default "{org.softuni.mobilele.model.validation.YearNotInTheFuture}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};


}
