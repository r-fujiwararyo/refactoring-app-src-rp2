package jp.co.sss.crud.util;

public class ConstantMsg {
	
	/** インスタンス化を禁止 */
	private ConstantMsg() {
	}
	
	public static final String QUALIFIED_PERSON_NOT_HERE = "該当者はいませんでした";
	
	public static final String EMPLOYEE_INFORMATION = "社員ID\\t社員名\\t性別\\t生年月日\\t部署名";
	
	public static final String NO_ANSWER_STRING = "回答なし" + "\t";
	
	public static final String GENTLEMAN_STRING = "男性" + "\t";
	
	public static final String WOMAN_STRING = "女性" + "\t";
	
	public static final String OTHERS_STRING = "その他" + "\t";
	
	public static final String SALES_DEPARTMENT_STRING = "営業部";
	
	public static final String ACCOUNTING_DEPARTMENT = "経理部";
	
	public static final String GENERAL_AFFAIRS_DEPARTMENT_STRING = "総務部";
	
	public static final String EMPLOYEE_DATA_INSERT_STRING = "社員情報を登録しました";
	
	public static final String EMPLOYEE_NAME_STRING = "社員名:";
	
	public static final String GENDER_STRING = "性別(0:回答しない, 1:男性, 2:女性,):";
	
	public static final String BIRTHDAY_STRING = "生年月日(西暦年/月/日)：";
	
	public static final String DEPARTMENT_ID_STRING = "部署ID(1：営業部、2：経理部、3：総務部)：";
	
	public static final String EMPLOYEE_DATA_DELETE_STRING = "社員情報を削除しました";
	
	public static final String EMPLOYEE_MANAGEMENT_SYSTEM_STRING = "=== 社員管理システム ===";
	
	public static final String EMPLOYEE_DATA_FIND_ALL_STRING = "1.全件表示";
	
	public static final String EMPLOYEE_DATA_FIND_BY_EMPLOYY_NAME_STRING = "2.社員名検索";
	
	public static final String EMPLOYEE_DATA_FIND_BY_DEPT_ID_STRING = "3.部署ID検索";
	
	public static final String EMPLOYEE_NEW_USER_INSERT_STRING = "4.新規登録";
	
	public static final String UPDATE_STRING = "6.削除";
	
	public static final String SYSTEM_SHUTDOWN_STRING = "7.終了";
	
	public static final String MENU_NO_INPUT_STRING = "メニュー番号を入力してください：";
	
	public static final String EMPLOYEE_DEPT_ID_UPDATE_INPUT_STRING = "更新する社員の社員IDを入力してください：";
	
	public static final String EMPLOYEE_UPDATE_STRING = "社員情報を更新しました";
	
	public static final String EMPLOYEE_DATE_DELETE_INPUT_STRING = "削除する社員の社員IDを入力してください：";
	
	public static final String SYSTEM_OUT = "システムを終了します。";
}
