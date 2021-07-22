package actions;

import java.io.IOException;
import java.util.List; //L17Cp11追記

import javax.servlet.ServletException;

import actions.views.EmployeeView; //L17Cp11追記
import actions.views.ReportView; //L17Cp11追記
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;  //L17Cp11追記
import services.ReportService;  //L17Cp11追記

/*
 * トップページに関する処理を行うActionクラス
 *
 */

public class TopAction extends ActionBase{

    private ReportService service;//L17Cp11追記

    /*
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException,IOException{

        service = new ReportService();//L17Cp11追記

        //メソッド実行
        invoke();
        service.close();//L17Cp11追記
    }
    /*
     * 一覧画面を表示する
     */
    public void index() throws ServletException,IOException{

        //以下L17Cp11追記

        //
        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //
        int page = getPage();
        List<ReportView> reports = service.getMinePerPage(loginEmployee, page);

        //
        long myReportsCount = service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.REPORTS, reports); //取得した日報データ
        putRequestScope(AttributeConst.REP_COUNT, myReportsCount); //ログイン中の従業員が作成した日報の数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        ////L17Cp11追記ここまで

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }

}
