package actions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ApprovalView;
import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ApprovalService;

public class ApprovalAction extends ActionBase {

    private ApprovalService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new ApprovalService();

        //
        invoke();
        service.close();
    }


    /*
     * 一覧画面に表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException,IOException{

        int page =getPage();
        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
        List<ReportView> reports = service.getApprovPerPage(loginEmployee,page);
        long reportsCount = service.countAll();
        long reportsApprovalCount = service.countAllApprov();


        putRequestScope(AttributeConst.REPORTS,reports);//取得した日報データ
        putRequestScope(AttributeConst.REP_COUNT, reportsCount); //全ての日報データの件数
        putRequestScope(AttributeConst.REP_APPROV_COUNT, reportsApprovalCount); //全ての未承認日報データの件数
        putRequestScope(AttributeConst.PAGE,page);//ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH,flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        //
        forward(ForwardConst.FW_APPROV_INDEX);
    }

    /**
     * 詳細画面を表示する
     * @throws Servletexception
     * @throws IOException
     */
    public void show() throws ServletException,IOException{

        //テスト追加
        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        //

        //idを条件に日報データを取得する
        ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        ApprovalView apv = service.getApprovalData(rv);
        if(rv == null) {
            //該当の日報データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.REPORT, rv); //取得した日報データ
            putSessionScope(AttributeConst.REP_ID,rv);//テスト追加
            putRequestScope(AttributeConst.APPROV,apv);

            //詳細画面を表示
            forward(ForwardConst.FW_APPROV_SHOW);

        }
    }
    /*
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に日報データを取得する
        ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.REPORT, rv); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_APPROV_EDIT);
        }



    /*
     * 新規登録画面を表示する
     */
    public void entryNew() throws ServletException,IOException{
        putRequestScope(AttributeConst.TOKEN,getTokenId());//CSRF対策用トークン

        //idを条件に日報データを取得する
        ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.REPORT, rv); //取得した日報データ

        //新規登録画面を表示
        forward(ForwardConst.FW_APPROV_NEW);

    }


    /*
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {
        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に日報データを取得する
            ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
            //日報データを更新する
            rv.setApprovalFlag(toNumber(getRequestParam(AttributeConst.REP_APPROV_FLAG)));
            List<String> errors = service.update(rv);

            //承認情報のテーブルに新規登録
            LocalDateTime ldt =LocalDateTime.now();
            ApprovalView apv = new ApprovalView(
                    null,
                    rv,
                    ldt,
                    ldt);
            service.createApprov(apv);


            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.REPORT, rv); //入力された日報情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_APPROV_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_APPROV, ForwardConst.CMD_INDEX);

            }
        }
    }


    public void update() throws ServletException, IOException {

    //CSRF対策 tokenのチェック
    if (checkToken()) {

        //idを条件に日報データを取得する
        ReportView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        //日報データを更新する
        rv.setApprovalFlag(toNumber(getRequestParam(AttributeConst.REP_APPROV_FLAG)));
        List<String> errors = service.update(rv);

        ApprovalView apv = service.getApprovalData(rv);
        service.updateApprov(apv,rv);

        if (errors.size() > 0) {
            //更新中にエラーが発生した場合

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.REPORT, rv); //入力された日報情報
            putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

            //編集画面を再表示
            forward(ForwardConst.FW_APPROV_EDIT);
        } else {
            //更新中にエラーがなかった場合

            //セッションに更新完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_APPROV, ForwardConst.CMD_INDEX);

        }
    }

    }

    /*
     * 一覧画面に表示する
     * @throws ServletException
     * @throws IOException
     */
    public void debugIndex() throws ServletException,IOException{

        int page =getPage();
        List<ReportView> reports = service.getAllFlag(page);
        long reportsCount = service.countAll();
        long reportsApprovalCount = service.countAllApprov();


        putRequestScope(AttributeConst.REPORTS,reports);//取得した日報データ
        putRequestScope(AttributeConst.REP_COUNT, reportsCount); //全ての日報データの件数
        putRequestScope(AttributeConst.REP_APPROV_COUNT, reportsApprovalCount); //全ての未承認日報データの件数
        putRequestScope(AttributeConst.PAGE,page);//ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH,flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        //
        forward(ForwardConst.FW_APPROV_DEBUG);

    }

}

