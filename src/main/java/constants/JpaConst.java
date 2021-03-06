package constants;

/*
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数はpublic static final 修飾子がついているとみなされる
 */

public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "daily_report_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //日報テーブル
    String TABLE_REP = "reports"; //テーブル名
    //日報テーブルカラム
    String REP_COL_ID = "id"; //id
    String REP_COL_EMP = "employee_id"; //日報を作成した従業員のid
    String REP_COL_REP_DATE = "report_date"; //いつの日報かを示す日付
    String REP_COL_TITLE = "title"; //日報のタイトル
    String REP_COL_CONTENT = "content"; //日報の内容
    String REP_COL_CREATED_AT = "created_at"; //登録日時
    String REP_COL_UPDATED_AT = "updated_at"; //更新日時

    String REP_COL_APPROV_FLAG = "approval_flag";
    int APPROV_ON =1;//承認済み
    int APPROV_OFF = 0;//承認未完了

    //リアクションテーブル
    String TABLE_REACT = "reactions"; //
    //リアクションテーブルカラム
    String REACT_COL_ID = "id";//id
    String REACT_COL_EMP_ID = "employee_id";//リアクションした従業員ID
    String REACT_COL_REP_ID = "report_id";//リアクションされた日報ID
    String REACT_COL_REACT_TYPE = "reaction_type";//リアクションの種類
    String REACT_COL_CREATED_AT = "created_at"; //登録日時

    //役職テーブル
    String TABLE_ROLE = "roles"; //
    //カラム
    String ROLE_ID = "id";//id
    String ROLE_EMP_ID = "employee_id";//従業員ID
    String ROLE_FLAG = "role_flag";//承認権限の有無
    String DEPARTMENT_GR = "department_gr";//所属部署

    //承認テーブル
    String TABLE_APPROV = "approvals"; //
    //カラム
    String APPROV_ID = "id";//id
    String APPROV_REP_ID = "report_id";//日報ID
    String APPROV_FLAG = "approv_flag";//承認フラグ
    String APPROV_CREATED_AT = "created_at";//承認日時
    String APPROV_UPDATED_AT = "updated_at";//修正日時

    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_REP = "report"; //日報
    String ENTITY_REACT = "reaction";//リアクション
    String ENTITY_APPROV = "appproval";
    String ENTITY_ROLE = "role";//役職



    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; //従業員
    String JPQL_PARM_REPORT = "report";
    String JPQL_PARM_REACTION = "reaction";

    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll"; //name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_RESISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;

    //全ての日報をidの降順に取得する
    String Q_REP_GET_ALL = ENTITY_REP + ".getAll";
    String Q_REP_GET_ALL_DEF = "SELECT r FROM Report AS r ORDER BY r.id DESC";
    //全ての日報の件数を取得する
    String Q_REP_COUNT = ENTITY_REP + ".count";
    String Q_REP_COUNT_DEF = "SELECT COUNT(r) FROM Report AS r";
    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_REP_GET_ALL_MINE = ENTITY_REP + ".getAllMine";
    String Q_REP_GET_ALL_MINE_DEF = "SELECT r FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    //指定した従業員が作成した日報の件数を取得する
    String Q_REP_COUNT_ALL_MINE = ENTITY_REP + ".countAllMine";
    String Q_REP_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;

    //全てのリアクションをidの降順に取得する
    String Q_REACT_GET_ALL = ENTITY_REACT + ".getAll";
    String Q_REACT_GET_ALL_DEF = "SELECT ract FROM Report AS ract ORDER BY ract.id DESC";
    //全てのリアクションの件数を取得する
    String Q_REACT_COUNT = ENTITY_REACT + ".count";
    String Q_REACT_COUNT_DEF = "SELECT COUNT(ract) FROM Report AS ract";
    //指定した日報に紐付いたリアクションの件数を取得する
    String Q_REACT_COUNT_ALL_REACT = ENTITY_REACT + ".countAllReaction";
    String Q_REACT_COUNT_ALL_REACT_DEF = "SELECT COUNT(ract) FROM Reaction AS ract WHERE ract.report = :" + JPQL_PARM_REPORT;

    //指定した日報に紐付いた,ログイン者がしたリアクションの件数を取得する
    String Q_REACT_COUNT_ALL_LOGIN_REACT = ENTITY_REACT + ".countLoginReaction";
    String Q_REACT_COUNT_ALL_LOGIN_REACT_DEF = "SELECT COUNT(ract) FROM Reaction AS ract WHERE ract.report = :" + JPQL_PARM_REPORT +" AND ract.employee = :" + JPQL_PARM_EMPLOYEE;

    //未承認の日報全てを取得
    String Q_REP_GET_ALL_NO_APPROV = ENTITY_REP + ".getAllNoApprov";
    String Q_REP_GET_ALL_NO_APPROV_DEF = "SELECT r FROM Report AS r INNER JOIN Role ro ON r.employee = ro.employee WHERE r.approvalFlag = 0 AND ro.departmentGr = (SELECT ro.departmentGr FROM ro WHERE ro.employee = :" + JPQL_PARM_EMPLOYEE + ") AND ro.roleFlag < (SELECT ro.roleFlag FROM ro WHERE ro.employee = :" + JPQL_PARM_EMPLOYEE + ") ";
    //未承認の日報全ての件数を取得
    String Q_REP_COUNT_APPROV = ENTITY_REP + ".countApprov";
    String Q_REP_COUNT_APPROV_DEF = "SELECT COUNT(r) FROM Report AS r INNER JOIN Role ro ON r.employee = ro.employee WHERE r.approvalFlag = 0";

    //指定した日報に紐付いた承認詳細情報を取得する
    String Q_APPROV_DATA = ENTITY_APPROV + ".approvalData";
    String Q_APPROV_DATA_DEF = "SELECT ap FROM Approval AS ap WHERE ap.report = :" + JPQL_PARM_REPORT;

    //承認済の日報全てを取得（管理者用コマンド）
    String Q_REP_GET_ALL_APPROV = ENTITY_REP + ".getAllApprov";
    String Q_REP_GET_ALL_APPROV_DEF = "SELECT r FROM Report AS r WHERE r.approvalFlag = 1";


  //指定した従業員idに紐付いた役職情報を取得する
    String Q_ROLE_DATA = ENTITY_ROLE + ".roleData";
    String Q_ROLE_DATA_DEF = "SELECT rl FROM Role AS rl WHERE rl.employee = :" + JPQL_PARM_EMPLOYEE;

  //社員番号を条件に従業員データを取得する
    String Q_ROLE_GET_EMP_BY_CODE = ENTITY_ROLE + ".getByCode";
    String Q_ROLE_GET_EMP_BY_CODE_DEF = "SELECT e FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;


}
