/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package core.User.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 *
 */
public final class ShodroneRoles {


    /**
     * System poweruser (this is a technical role).
     */
    public static final Role POWER_USER = Role.valueOf("POWER_USER");
    /**
     * System Administrator account.
     */
    public static final Role ADMIN = Role.valueOf("ADMIN");

    /**
     * System User account.
     */
    public static final Role USER = Role.valueOf("SHODRONE_USER");
    /**
     * CRM Collaborator account.
     */
    public static final Role COLLABORATOR = Role.valueOf("CRM_COLLABORATOR");

    /**
     * CRM Manager account.
     */
    public static final Role MANAGER = Role.valueOf("CRM_MANAGER");

    /**
     * Drone Tech account.
     */
    public static final Role DRONETECH = Role.valueOf("DRONE_TECH");

    /**
     * Show Designer account.
     */
    public static final Role SHOWDESIGNER = Role.valueOf("SHOW_DESIGNER");

    /**
     * Customer Representative account.
     */
    public static final Role CUSTOMERREPRESENTATIVE = Role.valueOf("CUSTOMER_REPRESENTATIVE");

    /**
     * Get available role types for cafeteria employees.
     *
     * @return employee roles
     */
    public static Role[] nonUserValues() {
        return new Role[] {
                ADMIN
                , COLLABORATOR
                , MANAGER
                , SHOWDESIGNER
                , CUSTOMERREPRESENTATIVE
                , DRONETECH
        };
    }

    /**
     * Checks if a role is one of the employee roles.
     *
     * @param role
     * @return {@code true} if a role is one of the employee roles
     */
    public boolean isCollaborator(final Role role) {
        return role != USER;
    }
}
