package jp.co.sss.crud.util;

/**
 * 設定値をまとめたクラス(SQL関連)
 *
 * @author System Shared
 *
 */
public class ConstantSQL {

	/** インスタンス化を禁止 */
	private ConstantSQL() {
	}

	// ***** DB接続のための設定値 *****

	/** Oracle DBドライバー */
	//	public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** DB接続用文字列 */
	//	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続用ユーザー */
	//	public static final String USER_NAME = "console_crud_user";

	/** DB接続用パスワード */
	//	public static final String PASSWORD = "systemsss";

	/** Postgres DBドライバー */
	public static final String JDBC_DRIVER = "org.postgresql.Driver";

	/** DB接続用文字列 */
	public static final String DB_URL = "jdbc:postgresql://localhost:5432/rp2db";

	/** DB接続用ユーザー */
	public static final String USER_NAME = "console_crud_user";

	/** DB接続用パスワード */
	public static final String PASSWORD = "systemsss";

	// ***** SQL文 *****

	/** 全件表示 */
	public static final String SQL_ALL_SELECT = "SELECT e.emp_id, e.emp_name,e.gender, TO_CHAR(birthday, 'yyyy/MM/dd') AS birthday, e.birthday, d.dept_name "
			+ "FROM employee e INNER JOIN department d "
			+ "ON e.dept_id = d.dept_id " + "ORDER BY e.emp_id";

	/** 条件検索(ベース) */
	public static final String SQL_SELECT_BASIC = "SELECT e.emp_id, e.emp_name,e.gender, TO_CHAR(birthday, 'yyyy/MM/dd') AS birthday, e.birthday, d.dept_name, d.dept_id "
			+ "FROM employee e INNER JOIN department d "
			+ "ON e.dept_id = d.dept_id ";

	/** 条件検索(社員名による条件) */
	public static final String SQL_SELECT_BY_EMP_NAME = "WHERE e.emp_name LIKE ? ORDER BY e.emp_id";

	/** 条件検索(部署IDによる条件) */
	public static final String SQL_SELECT_BY_DEPT_ID = "WHERE e.dept_id = ? ORDER BY e.emp_id";

	/** 登録 */
	public static final String SQL_INSERT = "INSERT INTO employee VALUES(nextval('seq_emp'), ?, ?, ?, ?)";

	/** 更新 */
	public static final String SQL_UPDATE = "UPDATE employee SET emp_name = ?, gender = ?, "
			+ "birthday = ?, dept_id = ? " + "WHERE emp_id = ?";

	/** 削除 */
	public static final String SQL_DELETE = "DELETE FROM employee WHERE emp_id = ?";

}
