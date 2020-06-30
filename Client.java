import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Arrays;
 
public class Client {
 
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		try{
			//送信先のIPアドレス(ドメインなどの名前)とポートを指定
			Socket sock = new Socket("localhost",10000);
			
			//送信ストリームの取得
			OutputStream out = sock.getOutputStream();
			
			//送信データ
            //String sendData = "てすとですよ";
            System.out.println("質問を入力してください。");
            String sendData = sc.nextLine();
			
			//文字列をUTF-8形式のバイト配列に変換して送信
			out.write(sendData.getBytes("UTF-8"));
			
			//送信データの表示
			System.out.println("「"+sendData+"」を送信しました。");
			
			//送信ストリームを表示
            out.close();

            //終了
            sock.close();
            sc.close();
            
            //サーバーのポート番号を指定
			ServerSocket svSock = new ServerSocket(9000);
			
			//アクセスを待ち受け
			Socket sockIn = svSock.accept();
			
			//受信データバッファ
			byte[] data = new byte[1024];
			
			//受信ストリームの取得
			InputStream in = sockIn.getInputStream();
			
			//データを受信
			int readSize = in.read(data);
			
			//受信データを読み込んだサイズまで切り詰め
			data = Arrays.copyOf(data, readSize);
			
            //バイト配列を文字列に変換して表示
            System.out.println("回答を受信しました。");
			System.out.println("「"+new String(data,"UTF-8")+"」");
			
			//受信ストリームの終了
			in.close();
			
			//サーバー終了
			svSock.close();
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}