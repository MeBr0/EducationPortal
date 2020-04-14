package com.mebr0.intranet.session.base;

import com.mebr0.intranet.session.mark.Level;
import com.mebr0.user.base.User;

import static com.mebr0.intranet.util.Printer.error;
import static com.mebr0.intranet.util.Printer.print;
import static com.mebr0.intranet.util.Scanner.ask;

/**
 * Interface for {@link User} sessions
 * Have general methods for {@link User}
 *
 * @author A.Yergali
 * @version 1.2
 */
public interface UserSession extends Session {

    /**
     * Method for changing password
     *
     * @param user whose password change
     * @return result of changing password
     */
    default boolean changePassword(User user) {
        String currentPassword = ask("Enter current password");
        String newPassword = ask("Enter new password");
        String newPassword2 = ask("Enter new password again");

        if (newPassword.equals(newPassword2) && user.checkCredentials(user.getLogin(), currentPassword)) {
            user.setPassword(newPassword);
            return true;
        }

        return false;
    }
}
