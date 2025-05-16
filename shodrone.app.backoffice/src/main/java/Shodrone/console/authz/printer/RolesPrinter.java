package Shodrone.console.authz.printer;

import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.visitor.Visitor;

public class RolesPrinter implements Visitor<Role> {
    @Override
    public void visit(Role visitee) {
        System.out.print(visitee.toString());
    }
}
