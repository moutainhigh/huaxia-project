package electicBook;
/**
 * 统计汉字的个数
 * @author Liuwei
 */
public class ChineseCharacterCounter {
	public static void main(String args[]){
		String message = "明日科技MRSoftasjdflkasdfj案例会计师的福利卡惊世毒妃";
		String regex = "^[\u4e00-\u9fff]$";
		int counter = 0;
		for(int i=0;i<message.length();i++){
			if((""+message.charAt(i)).matches(regex)){
				counter++;
			}
		}
		System.out.println("\""+message+"\"中包含:"+counter+"个汉字！");
	}
}
