package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.ReactionConverter;
import actions.views.ReactionView;
import models.validators.ReactionValidator;

public class ReactionService extends ServiceBase{

    /**
     * 画面から入力されたデータで1件作成登録する
     */
    public List<String> create(ReactionView ractv) {
        List<String> errors = ReactionValidator.validate(ractv);
        if (errors.size() == 0) {
            LocalDateTime ldt =LocalDateTime.now();
            ractv.setCreatedAt(ldt);
            createInternal(ractv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        //空リスト強制返しに改造中
        return errors;
    }


    /**
     * リアクションデータを1件登録する
     */
    private void createInternal(ReactionView ractv) {

        em.getTransaction().begin();
        em.persist(ReactionConverter.toModel(ractv));
        em.getTransaction().commit();

    }
}
