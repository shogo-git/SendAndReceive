import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
 
public class Server {
 
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		try{
			//サーバーのポート番号を指定
			ServerSocket svSock = new ServerSocket(10000);
			
			//アクセスを待ち受け
			Socket sock = svSock.accept();
			
			//受信データバッファ
			byte[] data = new byte[1024];
			
			//受信ストリームの取得
			InputStream in = sock.getInputStream();
			
			//データを受信
			int readSize = in.read(data);
			
			//受信データを読み込んだサイズまで切り詰め
			data = Arrays.copyOf(data, readSize);
			
			//バイト配列を文字列に変換して表示
			System.out.println("「"+new String(data,"UTF-8")+"」を受信しました。");
			
			//受信ストリームの終了
			in.close();
			
			//サーバー終了
            svSock.close();
            
            //送信先のIPアドレス(ドメインなどの名前)とポートを指定
			Socket sockout = new Socket("localhost",9000);
			
			//送信ストリームの取得
			OutputStream out = sockout.getOutputStream();
			
			//送信データ
            //String sendData = "てすとですよ";
            System.out.println("回答を入力してください。");
            String sendData = sc.nextLine();
			
			//文字列をUTF-8形式のバイト配列に変換して送信
			out.write(sendData.getBytes("UTF-8"));
			
            //送信データの表示
            System.out.println("回答を送信しました。");
			System.out.println("「"+sendData+"」");
			
			//送信ストリームを表示
            out.close();

            //終了
            sockout.close();
            sc.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}