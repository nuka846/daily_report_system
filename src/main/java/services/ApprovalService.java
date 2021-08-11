package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.ApprovalConverter;
import actions.views.ApprovalView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Approval;
import models.Report;
import models.validators.ReportValidator;

public class ApprovalService extends ServiceBase {
     /*
     * 未承認日報データを指定されたページ数の一覧画面に表示する分取得しReportViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ReportView> getApprovPerPage(EmployeeView employee,int page){
        List<Report> reports = em.createNamedQuery(JpaConst.Q_REP_GET_ALL_NO_APPROV,Report.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE,EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ReportConverter.toViewList(reports);
    }

    /*
     * 日報テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long reports_count = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT_APPROV,Long.class)
                .getSingleResult();
        return reports_count;
    }
    public long countAllApprov() {
        long reports_count_approval = (long) em.createNamedQuery(JpaConst.Q_REP_COUNT_APPROV,Long.class)
                .getSingleResult();
        return reports_count_approval;
    }

    /*
    * idを条件に取得したデータをReportViewのインスタンスで返却する
    * @param id
    * @return 取得データのインスタンス
    */
   public ReportView findOne(int id) {
       return ReportConverter.toView(findOneInternal(id));
   }

    /*
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Report findOneInternal(int id) {
        return em.find(Report.class, id);
    }



    /*
     * 画面から入録された日報の登録内容を元に日報データを更新する
     * @param rv 日報の更新内容
     * @return バリデーションで発生したエラーリスト
     */
    public List<String> update(ReportView rv){

        //バリデーションを行う
        List<String> errors = ReportValidator.validate(rv);
            if(errors.size() == 0) {
                updateInternal(rv);
            }

            //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
            return errors;
        }
    private void updateInternal(ReportView rv) {

        em.getTransaction().begin();
        Report r = findOneInternal(rv.getId());
        ReportConverter.copyViewToModel(r, rv);
        em.getTransaction().commit();

    }

    //承認詳細情報を1件登録
    public void createApprov(ApprovalView apv) {
        em.getTransaction().begin();
        em.persist(ApprovalConverter.toModel(apv));
        em.getTransaction().commit();
    }
    //詳細情報を1件更新する
    public void updateApprov(ApprovalView apv,ReportView rv) {
        LocalDateTime ldt = LocalDateTime.now();
        apv.setUpdatedAt(ldt);
        em.getTransaction().begin();
        Approval ap = getApprovalDataUpd(rv);
        ApprovalConverter.copyViewToModel(ap, apv);
        em.getTransaction().commit();

    }
    //日報データを参照し、承認詳細情報を取得する。
    //データ検索時、空データによるエラーが発生した場合、架空のインスタンス生成し返却。
    public ApprovalView getApprovalData(ReportView rv){
        try {
        Approval apv = em.createNamedQuery(JpaConst.Q_APPROV_DATA,Approval.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT,ReportConverter.toModel(rv))
                .getSingleResult();
        return ApprovalConverter.toView(apv);

        }catch(Exception e){
            ApprovalView apv = new ApprovalView(null,null,null,null);
            return apv;
        }
    }
    //日報データに紐づく詳細情報を取得。
    public Approval getApprovalDataUpd(ReportView rv){
        Approval ap = em.createNamedQuery(JpaConst.Q_APPROV_DATA,Approval.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT,ReportConverter.toModel(rv))
                .getSingleResult();
        return ap;


    }

    //管理用index用
    /*
     * 指定されたページ数の一覧画面に表示する日報データを取得し、ReportViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ReportView> getAllFlag(int page){
            List<Report> reports = em.createNamedQuery(JpaConst.Q_REP_GET_ALL_APPROV,Report.class)
                    .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                    .setMaxResults(JpaConst.ROW_PER_PAGE)
                    .getResultList();
            return ReportConverter.toViewList(reports);
    }
}
