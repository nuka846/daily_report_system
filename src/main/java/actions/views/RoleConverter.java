package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Role;

public class RoleConverter {

    public static Role toModel(RoleView rlv) {
        return new Role(
                rlv.getId(),
                EmployeeConverter.toModel(rlv.getEmployee()),
                rlv.getDepartmentGr(),
                rlv.getRoleFlag());
    }

    public static RoleView toView(Role rl) {
        if (rl == null) {
            return null;
        }
        return new RoleView(
                rl.getId(),
                EmployeeConverter.toView(rl.getEmployee()),
                rl.getDepartmentGr(),
                rl.getRoleFlag());
    }

    public static List<RoleView> toViewList(List<Role> list){
        List<RoleView> rlv = new ArrayList<>();

        for (Role rl : list) {
            rlv.add(toView(rl));
        }

        return rlv;
    }

    public static void copyViewToModel(Role rl,RoleView rlv) {
        rl.setId(rlv.getId());
        rl.setEmployee(EmployeeConverter.toModel(rlv.getEmployee()));
        rl.setDepartmentGr(rlv.getDepartmentGr());
        rl.setRoleFlag(rlv.getRoleFlag());
    }

    public static void copyModelToView(Role rl,RoleView rlv) {
        rlv.setId(rl.getId());
        rlv.setEmployee(EmployeeConverter.toView(rl.getEmployee()));
        rlv.setDepartmentGr(rl.getDepartmentGr());
        rlv.setRoleFlag(rl.getRoleFlag());
    }
}
