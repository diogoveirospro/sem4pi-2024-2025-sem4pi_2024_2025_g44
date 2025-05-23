/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package shodrone.authz.ui;

import core.User.domain.ShodronePasswordPolicy;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import shodrone.exceptions.OperationCancelledException;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.Optional;

/**
 * UI for user login action.
 *
 */
@SuppressWarnings("squid:S106")
public class ChangePasswordUI extends AbstractFancyUI {

    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ShodronePasswordPolicy passwordPolicy = new ShodronePasswordPolicy();

    @Override
    protected boolean doShow() {

        final String oldPassword;
        final String newPassword;

        try{
            oldPassword = validateOldPassword();
            newPassword = validateNewPassword(oldPassword);
        } catch (OperationCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        }

        try {
            boolean toContinue;
            if (authenticationService.changePassword(oldPassword, newPassword)) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nPassword Successfully changed" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                toContinue = true;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.RED + "\nInvalid authentication" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                toContinue = false;
            }
            return toContinue;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn error has occurred> " + e.getLocalizedMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private String validateNewPassword(String oldPassword) {
        while (true){
            String newPassword = UtilsUI.readPassword(UtilsUI.BOLD + "\nNew Password (at least 6 characters, including a number): " + UtilsUI.RESET);

            if (newPassword.isEmpty()){
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Password cannot be empty! Try again!" + UtilsUI.RESET);
                continue;
            } else if (newPassword.equals(oldPassword)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "New password cannot be the same as the old password! Try again!" + UtilsUI.RESET);
                continue;
            } else if (!passwordPolicy.isSatisfiedBy(newPassword)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Password does not meet the requirements! Try again!" + UtilsUI.RESET);
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Password must contain at least 6 characters, including a number!" + UtilsUI.RESET);
                continue;
            }

            String confirmPassword = UtilsUI.readPassword(UtilsUI.BOLD + "\nConfirm New Password: " + UtilsUI.RESET);

            if (newPassword.equals(confirmPassword)){
                return newPassword;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nPasswords do not match! Try again!" + UtilsUI.RESET);
            }
        }
    }

    private String validateOldPassword() {
        Optional<UserSession> session = authz.session();

        if (session.isPresent()) {
            while (true){
                String oldPassword = UtilsUI.readPassword(UtilsUI.BOLD + "Old Password (or type 'cancel' to go back): " + UtilsUI.RESET);

                if (oldPassword.isEmpty()) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Password cannot be empty! Try again!\n" + UtilsUI.RESET);
                    continue;
                }

                if ("cancel".equalsIgnoreCase(oldPassword)) {
                    throw new OperationCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                // Get the authenticated user from the session
                SystemUser user = session.get().authenticatedUser();

                if (authenticationService.authenticate(user.username().toString(), oldPassword).isPresent()){
                    return oldPassword;
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Incorrect Password! Try again!\n" + UtilsUI.RESET);
                }
            }
        }

        return null;
    }

    @Override
    public String headline() {
        return "Change Password";
    }
}
