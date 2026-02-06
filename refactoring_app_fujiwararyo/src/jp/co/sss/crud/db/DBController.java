package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantSQL;
import jp.co.sss.crud.util.ConstantValue;

/**
 * DB操作処理用のクラス
 *
 * @author System Shared
 */
public class DBController {

	/** インスタンス化を禁止 */
	private DBController() {
	}

	/**
	 * 全ての社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 */
	public static void employeeDataFindAll() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_ALL_SELECT);

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			//resultSetの結果Setがない場合はfalse
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.QUALIFIED_PERSON_NOT_HERE);
				return;
			}

			// レコードを出力
			System.out.println(ConstantMsg.EMPLOYEE_INFORMATION);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id") + "\t");
				System.out.print(resultSet.getString("emp_name") + "\t");

				int gender = Integer.parseInt(resultSet.getString("gender"));
				switch (gender) {
				case ConstantValue.NO_ANSWER:
					System.out.print(ConstantMsg.NO_ANSWER_STRING);
					break;
				case ConstantValue.GENTLEMAN:
					System.out.print(ConstantMsg.GENTLEMAN_STRING);
					break;
				case ConstantValue.WOMAN:
					System.out.print(ConstantMsg.WOMAN_STRING);
					break;
				case ConstantValue.OTHERS:
					System.out.print(ConstantMsg.OTHERS_STRING);
				}
				
				
//				if (gender == 0) {
//					System.out.print("回答なし" + "\t");
//				} else if (gender == 1) {
//					System.out.print("男性" + "\t");
//
//				} else if (gender == 2) {
//					System.out.print("女性" + "\t");
//
//				} else if (gender == 9) {
//					System.out.print("その他" + "\t");
//
//				}

				System.out.print(resultSet.getString("birthday") + "\t");
				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println("");
		} finally {
			// ResultSetをクローズ
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員名に該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void employeeDataFindByEmpName() throws ClassNotFoundException, SQLException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 検索ワード
		String searchWord = br.readLine();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer preparationSql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			preparationSql.append(ConstantSQL.SQL_SELECT_BY_EMP_NAME);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(preparationSql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setString(1, "%" + searchWord + "%");

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.QUALIFIED_PERSON_NOT_HERE);
				return;
			}

			System.out.println(ConstantMsg.EMPLOYEE_INFORMATION);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);
				
				switch (gender) {
				case ConstantValue.NO_ANSWER:
					System.out.print(ConstantMsg.NO_ANSWER_STRING);
					break;
				case ConstantValue.GENTLEMAN:
					System.out.print(ConstantMsg.GENTLEMAN_STRING);
					break;
				case ConstantValue.WOMAN:
					System.out.print(ConstantMsg.WOMAN_STRING);
					break;
				case ConstantValue.OTHERS:
					System.out.print(ConstantMsg.OTHERS_STRING);
				}
//				if (gender == 0) {
//					System.out.print("回答なし");
//				} else if (gender == 1) {
//					System.out.print("男性");
//
//				} else if (gender == 2) {
//					System.out.print("女性");
//
//				} else if (gender == 9) {
//					System.out.print("その他");
//
//				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				System.out.println(resultSet.getString("dept_name"));
			}

			System.out.println("");

		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 部署IDに該当する社員情報を検索
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void employeeDataFindByDeptId(String deptId) throws ClassNotFoundException, SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// DBに接続
			connection = DBManager.getConnection();

			// SQL文を準備
			StringBuffer preparationSql = new StringBuffer(ConstantSQL.SQL_SELECT_BASIC);
			preparationSql.append(ConstantSQL.SQL_SELECT_BY_DEPT_ID);

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(preparationSql.toString());

			// 検索条件となる値をバインド
			preparedStatement.setInt(1, Integer.parseInt(deptId));

			// SQL文を実行
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println(ConstantMsg.QUALIFIED_PERSON_NOT_HERE);
				return;
			}

			System.out.println(ConstantMsg.EMPLOYEE_INFORMATION);
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);	
				
				switch (gender) {
				case ConstantValue.NO_ANSWER:
					System.out.print(ConstantMsg.NO_ANSWER_STRING);
					break;
				case ConstantValue.GENTLEMAN:
					System.out.print(ConstantMsg.GENTLEMAN_STRING);
					break;
				case ConstantValue.WOMAN:
					System.out.print(ConstantMsg.WOMAN_STRING);
					break;
				case ConstantValue.OTHERS:
					System.out.print(ConstantMsg.OTHERS_STRING);
				}
//				if (gender == 0) {
//					System.out.print("回答なし");
//				} else if (gender == 1) {
//					System.out.print("男性");
//
//				} else if (gender == 2) {
//					System.out.print("女性");
//
//				} else if (gender == 9) {
//					System.out.print("その他");
//
//				}

				System.out.print("\t");
				System.out.print(resultSet.getString("birthday"));
				System.out.print("\t");

				String deptIdString = resultSet.getString("dept_id");
				int findByDeptId = Integer.parseInt(deptIdString);
				if (findByDeptId == ConstantValue.SALES_DEPARTMENT) {
					System.out.println(ConstantMsg.SALES_DEPARTMENT_STRING);
				} else if (findByDeptId == ConstantValue.ACCOUNTING_DEPARTMENT) {
					System.out.println(ConstantMsg.ACCOUNTING_DEPARTMENT);
				} else if (findByDeptId == ConstantValue.GENERAL_AFFAIRS_DEPARTMENT) {
					System.out.println(ConstantMsg.GENERAL_AFFAIRS_DEPARTMENT_STRING);

				}
			}

			System.out.println("");
		} finally {
			// クローズ処理
			DBManager.close(resultSet);
			// Statementをクローズ
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件登録
	 * 
	 * @param empName 社員名
	 * @param gender 性別
	 * @param birthday 生年月日
	 * @param deptId 部署ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public static void employeeDataInsert(String empName, String gender, String birthday, String deptId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// DBに接続
			connection = DBManager.getConnection();

			// ステートメントを作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_INSERT);

			// 入力値をバインド
			preparedStatement.setString(1, empName);
			preparedStatement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat birthDate  = new SimpleDateFormat("yyyy/MM/dd");
			preparedStatement.setObject(3, birthDate.parse(birthday), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(deptId));

			// SQL文を実行
			preparedStatement.executeUpdate();

			// 登録完了メッセージを出力
			System.out.println(ConstantMsg.EMPLOYEE_DATA_INSERT_STRING);
		} finally {
			DBManager.close(preparedStatement);
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件更新
	 * 
	 * @param empId 社員ID
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException            DB処理でエラーが発生した場合に送出
	 * @throws IOException             入力処理でエラーが発生した場合に送出
	 * @throws ParseException 
	 */
	public static void employeeDataUpdata(String employeeId)
			throws ClassNotFoundException, SQLException, IOException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_UPDATE);

			System.out.print(ConstantMsg.EMPLOYEE_NAME_STRING);
			String employeeName = br.readLine();
			// 性別を入力
			System.out.print(ConstantMsg.GENDER_INPUT_STRING);
			String gender = br.readLine();
			// 誕生日を入力
			System.out.print(ConstantMsg.BIRTHDAY_STRING);
			String birthday = br.readLine();

			// 部署IDを入力
			System.out.print(ConstantMsg.DEPARTMENT_ID_STRING);
			String departmentId = br.readLine();

			// 入力値をバインド
			preparedStatement.setString(1, employeeName);
			preparedStatement.setInt(2, Integer.parseInt(gender));
			SimpleDateFormat birthDate  = new SimpleDateFormat("yyyy/MM/dd");
			preparedStatement.setObject(3, birthDate.parse(birthday), Types.DATE);
			preparedStatement.setInt(4, Integer.parseInt(departmentId));
			preparedStatement.setInt(5, Integer.parseInt(employeeId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

		} finally {
			// クローズ処理
			DBManager.close(preparedStatement);
			// DBとの接続を切断
			DBManager.close(connection);
		}
	}

	/**
	 * 社員情報を1件削除
	 *
	 * @throws ClassNotFoundException ドライバクラスが不在の場合に送出
	 * @throws SQLException           DB処理でエラーが発生した場合に送出
	 * @throws IOException            入力処理でエラーが発生した場合に送出
	 */
	public static void employeeDataDelete() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			// データベースに接続
			connection = DBManager.getConnection();
			String employeeId = br.readLine();

			// ステートメントの作成
			preparedStatement = connection.prepareStatement(ConstantSQL.SQL_DELETE);

			// 社員IDをバインド
			preparedStatement.setInt(1, Integer.parseInt(employeeId));

			// SQL文の実行(失敗時は戻り値0)
			preparedStatement.executeUpdate();

			System.out.println(ConstantMsg.EMPLOYEE_DATA_DELETE_STRING);

		} catch (Exception e) {
			e.printStackTrace();

		}

		finally {
			// Statementをクローズ
			try {
				DBManager.close(preparedStatement);
				DBManager.close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// DBとの接続を切断
		}
	}
}
