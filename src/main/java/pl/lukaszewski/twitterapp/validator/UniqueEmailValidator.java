package pl.lukaszewski.twitterapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lukaszewski.twitterapp.user.UserService;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

   @Autowired
   private UserService userService;

   public UniqueEmailValidator(UserService userService) {
   }

   public void initialize(UniqueEmail constraint) {
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
       return userService.validUserEmail(email);
   }

}