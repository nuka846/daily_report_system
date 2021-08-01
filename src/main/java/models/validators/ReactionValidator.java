package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ReactionView;

public class ReactionValidator {
    /*
     */
    public static List<String> validate(ReactionView ractv){
        List<String> errors = new ArrayList<String>();

        //タイトルのチェック
        String titleError = "";
        if(!titleError.equals("")) {
            errors.add(titleError);
        }

        return errors;
    }
}
