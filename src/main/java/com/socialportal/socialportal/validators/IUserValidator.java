package com.socialportal.socialportal.validators;

import com.socialportal.socialportal.errors.DifferentPasswordException;
import com.socialportal.socialportal.errors.ExistingEmailException;
import com.socialportal.socialportal.models.User;

public interface IUserValidator {
    void validateUser(User user) throws DifferentPasswordException, ExistingEmailException;

    void checkEmail(User user) throws ExistingEmailException;

    void checkPassword(User user) throws DifferentPasswordException;
}
