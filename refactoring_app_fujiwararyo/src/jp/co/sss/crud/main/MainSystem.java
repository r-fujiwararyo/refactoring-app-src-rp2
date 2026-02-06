package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;
import jp.co.sss.crud.util.ConstantMsg;
import jp.co.sss.crud.util.ConstantValue;

/**
 * 社員情報管理システム開始クラス 社員情報管理システムはこのクラスから始まる。<br/>
 * メニュー画面を表示する。
 *
 * @author System Shared
 *
 */
public class MainSystem {
	/**
	 * 社員管理システムを起動
	 *
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int menuNo;

		do {
			// メニューの表示
			System.out.println(ConstantMsg.EMPLOYEE_MANAGEMENT_SYSTEM_STRING);
			System.out.println(ConstantMsg.EMPLOYEE_DATA_FIND_ALL_STRING);
			System.out.println(ConstantMsg.EMPLOYEE_DATA_FIND_BY_EMPLOYY_NAME_STRING);
			System.out.println(ConstantMsg.EMPLOYEE_DATA_FIND_BY_DEPT_ID_STRING);
			System.out.println(ConstantMsg.EMPLOYEE_NEW_USER_INSERT_STRING);
			System.out.println(ConstantMsg.UPDATE_STRING);
			System.out.println(ConstantMsg.EMPLOYEE_DELETE_STRING);
			System.out.println(ConstantMsg.SYSTEM_SHUTDOWN_STRING);
			System.out.print(ConstantMsg.MENU_NO_INPUT_STRING);

			// メニュー番号の入力
			String menuNoInput = br.readLine();
			menuNo = Integer.parseInt(menuNoInput);

			// 機能の呼出
			switch (menuNo) {
			case ConstantValue.EMPLOYEE_DATA_FIND_ALL:
				// 全件表示機能の呼出
				DBController.employeeDataFindAll();
				break;

			case ConstantValue.EMPLOYEE_DATA_FIND_BY_EMPLOYY_NAME:
				// 社員名検索
				System.out.print(ConstantMsg.EMPLOYEE_NAME_STRING);

				// 検索機能の呼出
				DBController.employeeDataFindByEmpName();
				break;

			case ConstantValue.EMPLOYEE_DATA_FIND_BY_DEPT_ID:
				// 検索する部署IDを入力
				System.out.print(ConstantMsg.EMPLOYEE_DEPT_ID_INPUT_STRING);
				String departmentInputId = br.readLine();

				// 検索機能の呼出
				DBController.employeeDataFindByDeptId(departmentInputId);
				break;

			case ConstantValue.EMPLOYEE_DATA_INSERT:
				// 登録する値を入力
				System.out.print(ConstantMsg.EMPLOYEE_NAME_STRING);
				String employeeName = br.readLine();
				System.out.print(ConstantMsg.GENDER_INPUT_STRING);
				String gender = br.readLine();
				System.out.print(ConstantMsg.BIRTHDAY_STRING);
				String birthday = br.readLine();
				System.out.print(ConstantMsg.DEPARTMENT_ID_STRING);
				String departmentId = br.readLine();

				// 登録機能の呼出
				DBController.employeeDataInsert(employeeName, gender, birthday, departmentId);
				break;

			case ConstantValue.EMPLOYEE_DATA_UPDATA:
				// 更新する社員IDを入力
				System.out.print(ConstantMsg.EMPLOYEE_DEPT_ID_UPDATE_INPUT_STRING);

				// 更新する値を入力する
				String employeeInputIdUpdata = br.readLine();
				Integer.parseInt(employeeInputIdUpdata);

				// 更新機能の呼出
				DBController.employeeDataUpdata(employeeInputIdUpdata);
				System.out.println(ConstantMsg.EMPLOYEE_UPDATE_STRING);

				break;

			case ConstantValue.EMPLOYEE_DATA_DELETE:
				// 削除する社員IDを入力
				System.out.print(ConstantMsg.EMPLOYEE_DATE_DELETE_INPUT_STRING);

				// 削除機能の呼出
				DBController.employeeDataDelete();
				break;

			}
		} while (menuNo != ConstantValue.SYSTEM_SHUTDOWN);
		System.out.println(ConstantMsg.SYSTEM_OUT);
	}
}
