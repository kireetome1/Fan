package fan;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//----------------------------------------------------------------------------
//  扇風機のタイマーにかかわる処理を行うクラス「Timer」
//----------------------------------------------------------------------------
public class Timer {



	//----------------------------------------------------------------------------
	//  フィールド
	//----------------------------------------------------------------------------

	//タイマー状態を保持するフィールド
	private static int sTimer;

	//経過時間を保持するフィールド
	private static int elap;

	//初期化(タイマー＝0、経過時間＝0）
	static{
		sTimer = 0;
		elap = 0;
	}



	//----------------------------------------------------------------------------
	//  経過時間を計算するメソッド
	//----------------------------------------------------------------------------
	private int calcElap() throws IOException {

		System.out.println("\n経過時間の入力をしてください（分）");

		//入力
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		int num;

		while(true){

			try{
				str = br.readLine();
				num = Integer.parseInt(str);
				if(num < 0){
					System.out.println("正しい数値を入力してください。");
					continue;
				}else{
					break;
				}
			}catch(Exception e){
				System.out.println("正しい数値を入力してください。");
				continue;
			}
		}
		//経過時間の計算
		elap += num;

		//経過時間を返す
		return elap;
	}



	//----------------------------------------------------------------------------
	//  現在のタイマー情報を取得するメソッド
	//----------------------------------------------------------------------------
	public int getTimer() {

		//タイマー状態を返す
		return sTimer;
	}



	//----------------------------------------------------------------------------
	//  タイマー情報をサイクリックに変更するメソッド
	//----------------------------------------------------------------------------
	public void changeTimer() {


		Power power1 = new Power ();

		//電源状態に応じて処理を分岐
		if(power1.getPower()){

			//電源ONの場合
			switch(getTimer()){

			//タイマーOFF（電源ON）のため1時間に設定
			case 1:
				sTimer = 60;
				System.out.println("タイマーが1時間に設定されました。");
				elap = 0;
				break;

			//タイマー1時間のため、2時間に設定
			case 60:
				sTimer = 120;
				System.out.println("タイマーが2時間に設定されました。");
				elap = 0;
				break;

			//タイマーOFF（電源OFF）なのでタイマーOFF（電源ON）に設定
			case 0:
				sTimer = 1;
				break;

			//タイマー2時間設定のため、タイマーOFF（電源ON）に設定
			case 120:
				sTimer = 1;
				System.out.println("タイマーがOFFになりました。");
				break;
			}

		//電源OFFのため、タイマーOFF（電源OFF）に設定
		}else {
			sTimer = 0;
		}
	}



	//----------------------------------------------------------------------------
	//  タイマー時間を迎えたかを判定するメソッド
	//----------------------------------------------------------------------------
	public boolean checkTimer() throws IOException {

		//タイマーがONかOFFかで分岐
		if(getTimer() == 0 || getTimer() == 1){
			//タイマーOFFのため処理終了
			return false;
		}else{

			//経過時間の計算
			calcElap();

			//経過時間とタイマー時間の比較
			if(getTimer() <= elap ){
				//タイマー時間経過処理
				System.out.println("タイマー時間が経過しました。");
				return true;
			}else{
				//タイマー時間未経過処理
				System.out.println("タイマー時間まだです。（" + elap + "分経過）");
				return false;
			}

		}

	}

}

