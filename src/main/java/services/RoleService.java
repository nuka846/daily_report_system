package services;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.RoleConverter;
import actions.views.RoleView;
import constants.JpaConst;
import models.Role;

public class RoleService extends ServiceBase {

    //役職情報を1件登録
    public void createRole(RoleView rlv) {
        em.getTransaction().begin();
        em.persist(RoleConverter.toModel(rlv));
        em.getTransaction().commit();
    }
    //役職情報を1件更新する
    public void updateRole(RoleView rlv,EmployeeView ev) {
        em.getTransaction().begin();
        Role rl = getRoleDataUpd(ev);
        RoleConverter.copyViewToModel(rl, rlv);
        em.getTransaction().commit();

    }
    //従業員データを参照し、役職情報を取得する。
    //データ検索時、空データによるエラーが発生した場合、架空のインスタンス生成し返却。
    public RoleView getRoleData(EmployeeView ev){
        try {
        Role rlv = em.createNamedQuery(JpaConst.Q_ROLE_DATA,Role.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE,EmployeeConverter.toModel(ev))
                .getSingleResult();
        return RoleConverter.toView(rlv);

        }catch(Exception e){
            RoleView rlv = new RoleView(null,null,null,null);
            return rlv;
        }
    }
    //従業員データを参照し、役職情報を取得する。
    public Role getRoleDataUpd(EmployeeView ev){

        Role rl = em.createNamedQuery(JpaConst.Q_ROLE_DATA,Role.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE,EmployeeConverter.toModel(ev))
                .getSingleResult();
        return rl;
    }


}
