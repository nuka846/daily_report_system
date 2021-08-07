package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Role;

public class RoleConverter {

    public static Role toModel(RoleView rov) {
        return new Role(
                rov.getId(),
                EmployeeConverter.toModel(rov.getEmployee()),
                rov.getRoleFlag(),
                rov.getDepartmentGr());
    }

    public static RoleView toView(Role ro) {
        if (ro == null) {
            return null;
        }
        return new RoleView(
                ro.getId(),
                EmployeeConverter.toView(ro.getEmployee()),
                ro.getRoleFlag(),
                ro.getDepartmentGr());
    }

    public static List<RoleView> toViewList(List<Role> list){
        List<RoleView> rovs = new ArrayList<>();

        for (Role ro : list) {
            rovs.add(toView(ro));
        }

        return rovs;
    }

    public static void copyViewToModel(Role ro,RoleView rov) {
        ro.setId(rov.getId());
        ro.setEmployee(EmployeeConverter.toModel(rov.getEmployee()));
        ro.setRoleFlag(rov.getRoleFlag());
        ro.setDepartmentGr(rov.getDepartmentGr());
    }

    public static void copyModelToView(Role ro,RoleView rov) {
        rov.setId(ro.getId());
        rov.setEmployee(EmployeeConverter.toView(ro.getEmployee()));
        rov.setRoleFlag(ro.getRoleFlag());
        rov.setDepartmentGr(ro.getDepartmentGr());
    }
}
