package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import jp.co.sss.crud.db.DBController;

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
			System.out.println("=== 社員管理システム ===");
			System.out.println("1.全件表示");
			System.out.println("2.社員名検索");
			System.out.println("3.部署ID検索");
			System.out.println("4.新規登録");
			System.out.println("5.更新");
			System.out.println("6.削除");
			System.out.println("7.終了");
			System.out.print("メニュー番号を入力してください：");

			// メニュー番号の入力
			String menuNoInput = br.readLine();
			menuNo = Integer.parseInt(menuNoInput);

			// 機能の呼出
			switch (menuNo) {
			case 1:
				// 全件表示機能の呼出
				DBController.employeeDataFindAll();
				break;

			case 2:
				// 社員名検索
				System.out.print("社員名:");

				// 検索機能の呼出
				DBController.employeeDataFindByEmpName();
				break;

			case 3:
				// 検索する部署IDを入力
				System.out.print("部署ID(1:営業部、2:経理部、3:総務部)を入力してください:");
				String departmentInputId = br.readLine();

				// 検索機能の呼出
				DBController.employeeDataFindByDeptId(departmentInputId);
				break;

			case 4:
				// 登録する値を入力
				System.out.print("社員名:");
				String employeeName = br.readLine();
				System.out.print("性別(0:その他, 1:男性, 2:女性, 9:回答なし):");
				String gender = br.readLine();
				System.out.print("生年月日(西暦年/月/日):");
				String birthday = br.readLine();
				System.out.print("部署ID(1:営業部、2:経理部、3:総務部):");
				String departmentId = br.readLine();

				// 登録機能の呼出
				DBController.employeeDataInsert(employeeName, gender, birthday, departmentId);
				break;

			case 5:
				// 更新する社員IDを入力
				System.out.print("更新する社員の社員IDを入力してください：");

				// 更新する値を入力する
				String employeeInputIdUpdata = br.readLine();
				Integer.parseInt(employeeInputIdUpdata);

				// 更新機能の呼出
				DBController.employeeDataUpdata(employeeInputIdUpdata);
				System.out.println("社員情報を更新しました");

				break;

			case 6:
				// 削除する社員IDを入力
				System.out.print("削除する社員の社員IDを入力してください：");

				// 削除機能の呼出
				DBController.employeeDataDelete();
				break;

			}
		} while (menuNo != 7);
		System.out.println("システムを終了します。");
	}
}
