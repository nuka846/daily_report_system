package constants;

/*
 * 画面の項目値などを定義するEnumクラス
 */

public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中の従業員
    LOGIN_EMP("login_employee"),
    LOGIN_ROLE("login_role"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //従業員管理
    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_ID("id"),
    EMP_CODE("code"),
    EMP_PASS("password"),
    EMP_NAME("name"),
    EMP_ADMIN_FLG("admin_flag"),

    //管理者フラグ
    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //日報管理
    REPORT("report"),
    REPORTS("reports"),
    REP_COUNT("reports_count"),
    REP_ID("id"),
    REP_DATE("report_date"),
    REP_TITLE("title"),
    REP_CONTENT("content"),
    REP_APPROV_FLAG("approve_flag"),
    REP_APPROV_COUNT("reports_count_approval"),
    //承認フラグ
    APPROV_ON(1),
    APPROV_OFF(0),

    //リアクション
    REACTION("reaction"),
    REACTIONS("reactions"),
    REACT_COUNT("reactions_count"),
    REACT_DATE("reaction_date"),
    REACT_LOG_COUNT("reaction_login"),
    //承認
    APPROV("approval"),
    ROLE("role"),
    ROLE_FLAG("role_flag"),
    DEPART_GR("department_gr"),
    //所属部署
    GR_GENERAL(0),//総務
    GR_SALES(1),//営業
    GR_PUBLIC(2),//広報
    //役職
    ROLE_ID("role_id"),
    ROLE_MEMBER(0),
    ROLE_MANAGER(1),
    ROLE_DIRECTER(2);


    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }
}
