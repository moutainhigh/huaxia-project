package electicBook;

public class MusicThead extends Thread {
	@Override
	public void run(){
		while(true){
			System.out.println("<--音乐播放中-->");
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
class InstallThread extends Thread{
	@Override
	public void run(){
		System.out.println("<--安装开始-->");
	}
}