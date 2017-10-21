import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Collections;

/*
Description：メイン
*/
class runAlg{
	public static void main(String args[]){
		out.println(clsConst.TITLE + clsConst.NEW_LINE);

		// 処理クラスを設定
		clsProcess[] clsP = new clsProcess[2];
		clsP[0] = new clsProcess0();  // アルゴリズム用乱数生成
		clsP[1] = new clsProcess1();  // 線形探索法（リニアサーチ）
		
		// パラメータチェック
		boolean bPara =chkPara(args);
		if (bPara == false){
			for (int i = 0; i <= clsConst.MAX_PROCESS; i++){
				out.println(i + "：" + clsP[i].strPro_Name);
			}
			return;
		}

		// 処理呼び出し
		int iNum = Integer.valueOf(args[0]);
		if (iNum <= clsConst.MAX_PROCESS){
			clsP[iNum].args = args;
			clsP[iNum].subMain();
		} else {
			out.println("対応する処理がありません。");
			return;
		}
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
Description：各処理のインターフェース
*/
interface itfProcess{
	// メイン処理を実装する
	void subMain();
}

/*
Description：各処理の抽象クラス（各処理のインターフェースを実装）
*/
abstract class clsProcess implements itfProcess{
	// パラメータ受取り用
	String args[];
	// 処理毎変数初期セット
	int intParaCnt;
	String strParaGuide = "ガイド未設定";
	String strPro_Name = "処理：未実装";
	String strPro_Desc = "処理概要：未実装";
	// 定番アルゴリズムで使用する配列数（どの処理でも使うので抽象クラスで設定しておく）
	ArrayList<Integer> lArray = new ArrayList<Integer>();

	// メイン開始
	public void subMain(){
		// タイトル表示
		out.println(strPro_Name + clsConst.NEW_LINE + strPro_Desc + clsConst.NEW_LINE);

		// パラメータチェック
		if (chkPara() == false){
			return;
		}
		// 処理開始
		// 事前準備としてパラメータで指定された数の配列を作る
		for(int i = 0 ; i <= Integer.parseInt(args[1]) ; i++) {
			lArray.add(i);
		}
		// 配列をシャッフルして、ランダムにする
		Collections.shuffle(lArray);
		
		// 各アルゴリズムのメイン処理
		subMainOriginal();
	}

	// パラメータチェック（第一パラメータチェック）
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

	// 各アルゴリズムの個別チェック処理
	abstract protected boolean chkParaOriginal();

	// 各アルゴリズムのメイン処理
	abstract protected void subMainOriginal();
}

/*
Description：処理０ アルゴリズム用乱数生成（乱数を表示させるだけ）
*/
class clsProcess0 extends clsProcess{
	// コンストラクタ
	clsProcess0(){
		// 処理毎変数セット
		intParaCnt = 2;
		strParaGuide = "パラメータ１：処理番号（0固定）" + clsConst.NEW_LINE;
		strParaGuide += "パラメータ２：数値（乱数上限）" + clsConst.NEW_LINE;
		strPro_Name = "アルゴリズム用乱数生成（乱数を表示させるだけ）";
		strPro_Desc = "アルゴリズム処理用の乱数を作成する処理（乱数を表示させるだけ）";
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
Description：処理１ 未実装：線形探索法（リニアサーチ）
*/
class clsProcess1 extends clsProcess{
	// コンストラクタ
	clsProcess1(){
		// 処理毎変数セット
		intParaCnt = 3;
		strParaGuide = "パラメータ１：処理番号（0固定）" + clsConst.NEW_LINE;
		strParaGuide += "パラメータ２：数値（乱数上限）" + clsConst.NEW_LINE;
		strParaGuide += "パラメータ３：数値（検索数値）" + clsConst.NEW_LINE;
		strPro_Name = "未実装：線形探索法（リニアサーチ）";
		strPro_Desc = "未実装：線形探索法のプログラム演習";
	}
	// 個別チェック処理
	protected boolean chkParaOriginal(){
		boolean bPara = true;
		if (clsCommon.chkNumber(args[1]) == false || clsCommon.chkNumber(args[2]) == false){
			out.println("パラメータ２、３には数字を指定してください。");
			bPara = false;
		}
		return bPara;
	}
	// メイン処理
	protected void subMainOriginal(){
		// シャッフルした結果を表示させる
		System.out.println("未実装");
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
	public static final int MAX_PROCESS = 1;
}
