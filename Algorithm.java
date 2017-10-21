import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Collections;

/*
Description：メイン
*/
class runAlg{
	public static void main(String args[]){
		out.println(clsConst.TITLE + clsConst.NEW_LINE);

		// パラメータチェック
		boolean bPara =chkPara(args);
		if (bPara == false){
			out.println("0：" + clsConst.PROCESS0_NAME);
			out.println("1：" + clsConst.PROCESS1_NAME);
			out.println("2：" + clsConst.PROCESS2_NAME);
			out.println("3：" + clsConst.PROCESS3_NAME);
			return;
		}

		// 処理呼び出し
		clsProcess clsP;
		switch (args[0]){
			case "0":
				clsP = new clsProcess0();
				break;
			default:
				out.println("対応する処理がありません。");
				return;
		}
		clsP.args = args;
		clsP.subMain();
	}
	// パラメータチェック処理
	private static boolean chkPara(String args[]){
		// パラメータチェック
		boolean bPara = true;
		if (args.length == 0){
			out.println("引数が指定してされてません。");
			out.println("処理に応じて下記引数を指定してください。");
			bPara = false;
		} else if (args.length >= 0){
			if (args[0].equals("?")){
				out.println("処理に応じて下記引数を指定してください。");
				bPara = false;
			} else if (clsCommon.chkNumber(args[0]) == false){
				out.println("第１パラメータには処理番号（数字）を入力してください。");
				bPara = false;
			}
		}
		return bPara;
	}
}

/*
Description：各処理の抽象クラス
*/
abstract class clsProcess{
	String args[];
	String strParaGuide = "ガイド未設定";
	int intParaCnt;
	String strTitle;
	ArrayList<Integer> lArray = new ArrayList<Integer>();

	// 処理開始
	public void subMain(){
		// タイトル表示
		out.println(strTitle + clsConst.NEW_LINE);

		// パラメータチェック
		if (chkPara() == false){
			return;
		}
		// メイン処理
		// 事前準備としてパラメータで指定された数の配列を作る
		for(int i = 0 ; i <= Integer.parseInt(args[1]) ; i++) {
			lArray.add(i);
		}
		// 配列をシャッフルして、ランダムにする
		Collections.shuffle(lArray);
		
		// 各処理のメイン処理
		subMainOriginal();
	}

	// パラメータチェック
	protected boolean chkPara(){
		boolean bPara;
		bPara = true;

		if (args.length > 1){
			if (args[1].equals("?")){
				// パラメータガイドメッセージ表示
				out.println(strParaGuide);
				bPara = false;
				return bPara;
			}
		}
		if (args.length != intParaCnt){
			out.println("パラメータ数が違います。");
			out.println("下記パラメータを設定してください。" + clsConst.NEW_LINE);
			// パラメータガイドメッセージ表示
			out.println(strParaGuide);
			bPara = false;
			return bPara;
		}
		return chkParaOriginal();
	}

	// パラメータチェック
	abstract protected boolean chkParaOriginal();

	// メイン処理
	abstract protected void subMainOriginal();
}

/*
Description：処理０ アルゴリズム用乱数生成（乱数を表示させるだけ）
*/
class clsProcess0 extends clsProcess{
	// コンストラクタ
	clsProcess0(){
		intParaCnt = 2;
		strTitle = clsConst.PROCESS0_NAME + clsConst.NEW_LINE + clsConst.PROCESS0_DESC;
		strParaGuide = "パラメータ１：処理番号（0固定）" + clsConst.NEW_LINE;
		strParaGuide += "パラメータ２：数値（乱数上限）" + clsConst.NEW_LINE;
	}
	// 個別チェック処理
	protected boolean chkParaOriginal(){
		boolean bPara = true;
		if (clsCommon.chkNumber(args[1]) == false){
			out.println("パラメータ２には数字を指定してください。");
			bPara = false;
		}
		return bPara;
	}
	// メイン処理
	protected void subMainOriginal(){
		// シャッフルした結果を表示させる
		System.out.println(lArray);
	}
}

/*
Description：共通関数群
*/
class clsCommon{
	// 数字チェック
	public static boolean chkNumber(String strNum){
		try{
			Integer.parseInt(strNum);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
}

/*
Description：定数クラス
*/
final class clsConst{
	// 全体共通
	public static final String NEW_LINE = "\r\n";  // 改行
	public static final String TITLE = "【Java勉強用】定番アルゴリズム実装プログラム";

	// 処理名 & 処理詳細
	public static final String PROCESS0_NAME = "アルゴリズム用乱数生成（乱数を表示させるだけ）";
	public static final String PROCESS0_DESC = "アルゴリズム処理用の乱数を作成する処理（乱数を表示させるだけ）";
	public static final String PROCESS1_NAME = "未実装：線形探索法（リニアサーチ）";
	public static final String PROCESS1_DESC = "未実装：線形探索法のプログラム演習";
	public static final String PROCESS2_NAME = "未実装：二分探索法（バイナリサーチ）";
	public static final String PROCESS2_DESC = "未実装：二分探索法のプログラム演習";
	public static final String PROCESS3_NAME = "未実装：ハッシュ探索法";
	public static final String PROCESS3_DESC = "未実装：ハッシュ探索法のプログラム演習";
}
