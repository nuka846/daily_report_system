package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReactionView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.ReactionService;

public class ReactionAction extends ActionBase {

    private ReactionService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new ReactionService();

        //
        invoke();
        service.close();
    }

    /*
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {


            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            //セッションから日報IDを取得
            ReportView rv = (ReportView) getSessionScope(AttributeConst.REP_ID);

            //パラメータの値をもとにインスタンスを作成する
            ReactionView ractv = new ReactionView(
                    null,//id
                    ev, //リアクションした従業員
                    rv, //日報データ
                    1,//リアクション種類
                    null);//作成日時

            //日報情報登録
            List<String> errors = service.create(ractv);
            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_REP_EDIT);
            }else {

                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
            }
        }
    }

}
