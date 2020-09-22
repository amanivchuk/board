package com.manivchuk.board.validation.userrole;


import com.manivchuk.board.persistence.entity.user.UserRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class UserRoleSubsetValidator implements ConstraintValidator<UserRoleSubset, UserRole> {

   private UserRole[] subset;

   public void initialize(UserRoleSubset constraint) {
      this.subset = constraint.anyOf();
   }

   public boolean isValid(UserRole value, ConstraintValidatorContext context) {
      return value == null || subset == null || subset.length == 0 || Arrays.asList(subset).contains(value);
   }
}
