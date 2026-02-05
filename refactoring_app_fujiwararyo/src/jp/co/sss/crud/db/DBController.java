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

import jp.co.sss.crud.util.ConstantSQL;

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
				System.out.println("該当者はいませんでした");
				return;
			}

			// レコードを出力
			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id") + "\t");
				System.out.print(resultSet.getString("emp_name") + "\t");

				int gender = Integer.parseInt(resultSet.getString("gender"));
				switch (gender) {
				case 0:
					System.out.print("回答なし" + "\t");
					break;
				case 1:
					System.out.print("男性" + "\t");
					break;
				case 2:
					System.out.print("女性" + "\t");
					break;
				case 9:
					System.out.print("その他" + "\t");
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
				System.out.println("該当者はいませんでした");
				return;
			}

			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);
				
				switch (gender) {
				case 0:
					System.out.print("回答なし" + "\t");
					break;
				case 1:
					System.out.print("男性" + "\t");
					break;
				case 2:
					System.out.print("女性" + "\t");
					break;

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

//				System.out.print("\t");
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
				System.out.println("該当者はいませんでした");
				return;
			}

			System.out.println("社員ID\t社員名\t性別\t生年月日\t部署名");
			while (resultSet.next()) {
				System.out.print(resultSet.getString("emp_id"));
				System.out.print("\t");

				System.out.print(resultSet.getString("emp_name"));
				System.out.print("\t");

				String genderString = resultSet.getString("gender");
				int gender = Integer.parseInt(genderString);	
				
				switch (gender) {
				case 0:
					System.out.print("回答なし" + "\t");
					break;
				case 1:
					System.out.print("男性" + "\t");
					break;
				case 2:
					System.out.print("女性" + "\t");
					break;

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
				if (findByDeptId == 1) {
					System.out.println("営業部");
				} else if (findByDeptId == 2) {
					System.out.println("経理部");
				} else if (findByDeptId == 3) {
					System.out.println("総務部");

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
			System.out.println("社員情報を登録しました");
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

			System.out.print("社員名：");
			String employeeName = br.readLine();
			// 性別を入力
			System.out.print("性別(0:回答しない, 1:男性, 2:女性,):");
			String gender = br.readLine();
			// 誕生日を入力
			System.out.print("生年月日(西暦年/月/日)：");
			String birthday = br.readLine();

			// 部署IDを入力
			System.out.print("部署ID(1：営業部、2：経理部、3：総務部)：");
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

			System.out.println("社員情報を削除しました");

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
