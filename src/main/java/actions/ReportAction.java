package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.ReportService;


public class ReportAction extends ActionBase {

    private ReportService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new ReportService();

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

        //指定されたページ数の一覧画面に表示する日報データを取得
        int page = getPage();
        List<ReportView> reports = service.getAllPerPage(page);

        //
        long reportsCount = service.countAll();

        putRequestScope(AttributeConst.REPORTS, reports); //取得した日報データ
        putRequestScope(AttributeConst.REP_COUNT, reportsCount); //全ての日報データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //
        String flush = getSessionScope(AttributeConst.FLUSH);
                if(flush != null) {
                    putRequestScope(AttributeConst.FLUSH,flush);
                    removeSessionScope(AttributeConst.FLUSH);
                }
        //
        forward(ForwardConst.FW_REP_INDEX);
    }
    /*
     * 新規登録画面を表示する
     */
    public void entryNew() throws ServletException,IOException{
        putRequestScope(AttributeConst.TOKEN,getTokenId());//CSRF対策用トークン

        //日報情報の空インスタンスに日報の日付＝今日の日付を設定する
        ReportView rv = new ReportView();
        rv.setReportDate(LocalDate.now());
        putRequestScope(AttributeConst.REPORT,rv);//日付のみ設定済みの日報インスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_REP_NEW);
    }

}