package fan;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//--------------------------------------------------------------------------------
//  扇風機システムのメインクラス「Fan」
//--------------------------------------------------------------------------------
public class Fan {

	//各種クラスを利用するためにオブジェクトを生成
	Blade blade1 = new Blade ();

	Power power1 = new Power ();

	Timer timer1 = new Timer ();



	//----------------------------------------------------------------------------
	//現在状態を表示するメソッド
	//----------------------------------------------------------------------------
	public void printSituation(){


		if(power1.getPower())						//電源状態を取得し、値に応じてON・OFFの出力
			System.out.println("電　源　：ON");		//trueは電源ON状態
		else
			System.out.println("電　源　：OFF");	//falseは電源OFF状態


		switch(blade1.getWind()){					//風量を取得し、風量状態の出力
		case 0:
			System.out.println("風　量　：停止");	//0は風停止状態
			break;
		case 1:
			System.out.println("風　量　：弱");		//1は風量弱状態
			break;
		case 2:
			System.out.println("風　量　：中");		//2は風量中状態
			break;
		case 3:
			System.out.println("風　量　：強");		//3は風量強状態
			break;
		}


		switch(timer1.getTimer()){					//タイマー状態を取得し、タイマー設定の出力
		case 0:
		case 1:
			System.out.println("タイマー：OFF");	//0は電源OFFのためタイマーOFF,1はタイマーOFF状態
			break;
		case 60:
			System.out.println("タイマー：1時間");	//60はタイマー1時間設定
			break;
		case 120:
			System.out.println("タイマー：2時間");	//120はタイマー2時間設定
			break;
		}

	}



	//----------------------------------------------------------------------------
	//  電源swが押された時の処理を行うメソッド
	//----------------------------------------------------------------------------
	public void pushPower (){

		power1.changePower();
	}



	//----------------------------------------------------------------------------
	//  風量swが押された時の処理を行うメソッド
	//----------------------------------------------------------------------------
	public void pushWind () {

		blade1.changeWind();

	}



	//----------------------------------------------------------------------------
	//  タイマーswが押された時の処理を行うメソッド
	//----------------------------------------------------------------------------
	public void pushTimer() {

		timer1.changeTimer();

	}



	//----------------------------------------------------------------------------
	//  扇風機のメイン処理メソッド
	//----------------------------------------------------------------------------
	public static void main (String[] args) throws IOException{

		//各クラスのオブジェクトを作成
		Fan fan1 = new Fan ();

		Timer timer1 = new Timer();

		Power power1 = new Power();

		Blade blade1 = new Blade();

		//入力処理用の変数の用意
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		int num;
		//無限ループ
		while(true){


			//風量を取得し、値に応じて扇風機AAの出力
			switch(blade1.getWind()){

			//羽根停止状態
			case 0:
				System.out.println("　　/|ヽ");
				System.out.println("　匚E||| ");
				System.out.println("　┃ﾋ|ノ ");
				System.out.println("┌┸┐");
				System.out.println("`￣￣");
				break;

			//風量弱状態
			case 1:
				System.out.println("　　/|ヽ～～");
				System.out.println("　匚E||| ～～");
				System.out.println("　┃ﾋ|ノ～～");
				System.out.println("┌┸┐");
				System.out.println("`￣￣");
				break;

			//風量中状態
			case 2:
				System.out.println("　　/|ヽーー");
				System.out.println("　匚E||| 二二");
				System.out.println("　┃ﾋ|ノーー");
				System.out.println("┌┸┐");
				System.out.println("`￣￣");
				break;

			//風量強状態
			case 3:
				System.out.println("　　/|ヽ三三");
				System.out.println("　匚E||| 三三");
				System.out.println("　┃ﾋ|ノ三三");
				System.out.println("┌┸┐");
				System.out.println("`￣￣");
				break;
			}

			//ユーザーに入力を指示
			System.out.println("あなたがこれから行いたい操作を選択してください");
			System.out.println("1:電源sw押下、2:風量sw押下、3:タイマーsw押下");
			System.out.println("4:現在の扇風機状態の取得、その他:何もしない");

			//入力処理
			try{
				str = br.readLine();
				num = Integer.parseInt(str);
			}catch(Exception e){
				//関係ない値が入った場合は5(何もしない)とする
				num = 5;
			}


			//値ごとに処理の分岐
			switch(num){

			//電源ボタン押下処理
			case 1:
				fan1.pushPower();
				break;

			//風量ボタン押下処理
			case 2:
				fan1.pushWind();
				break;

			//タイマーボタン押下処理
			case 3:
				fan1.pushTimer();
				break;

			//状態表示処理
			case 4:
				fan1.printSituation();
				break;

			//何もしない
			/*default :
				break;*/

			}

			//タイマーのチェック
			if(timer1.checkTimer()){
				power1.changePower();
			}

			//見やすくするための改行処理
			System.out.println("");

		}
	}
}
