package com.socialportal.socialportal.validators;

import com.socialportal.socialportal.errors.*;
import com.socialportal.socialportal.models.User;
import org.springframework.ui.Model;

public interface IUserValidator {
    void validateUser(User user) throws DifferentPasswordException, ExistingEmailException;

    void checkEmail(User user) throws ExistingEmailException;

    void checkPassword(User user) throws DifferentPasswordException;

    void deletePrivilege(Long loggedUser, Long statusUser, Long profileUser) throws HasPrivilegeException;

    void editPrivilege(Long loggedUser, Long statusUser) throws HasPrivilegeException;

    void checkAddingFriend(Long loggedUser, Long addedFriend) throws SameUserException, HasThisFriendException;

    void checkSendInvitation(Long loggedUser, Long addedFriend) throws HasInvitationException, HasThisFriendException, SameUserException;

    void checkIfUserInvitation(Long invitationId) throws NotYourInvitationException;
}
