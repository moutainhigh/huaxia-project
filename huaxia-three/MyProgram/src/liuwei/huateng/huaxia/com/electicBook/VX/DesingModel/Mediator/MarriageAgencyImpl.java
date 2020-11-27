/**
 * Title: MarriageAgencyImpl.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午9:53:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:MarriageAgencyImpl2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午9:53:37
 */
public class MarriageAgencyImpl implements MarriageAgency {
	//男会员
	List<Man> men = new ArrayList<Man>();
	//女会员
	List<Woman> woman = new ArrayList<Woman>();
	/**
	 * 
	 */
	public MarriageAgencyImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.MarriageAgency#pair(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.Person)
	 */
	@Override
	public void pair(Person person) {
		// TODO Auto-generated method stub
		if(person.sex == Sex.MALE){
			for(Woman w:woman){
				if(w.age == person.requestAge){
					System.out.println(person.name+"和"+w.name+"配对成功");
					return;
				}
			}
		}else if(person.sex == Sex.FEMALE){
			for(Man m:men){
				if(m.age == person.requestAge){
					System.out.println(person.name+"和"+m.name+"配对成功");
					return;
				}
			}
		}
		System.out.println("没有为"+person.name+"找到合适的对象");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.MarriageAgency#register(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.Person)
	 */
	@Override
	public void register(Person person) {
		// TODO Auto-generated method stub
		if(person.sex == Sex.MALE)
			men.add((Man)person);
		else if(person.sex == Sex.FEMALE)
			woman.add((Woman)person);
	}

}
