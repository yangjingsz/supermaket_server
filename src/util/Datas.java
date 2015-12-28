package util;

import java.io.Serializable;
import java.util.List;

import entity.Commodity;
import entity.ServerUser;


/**
 * 序列化传输对象
 * 在客户端与服务器端之间传递的数据对象
 * 
 */
public class Datas implements Serializable {
	private String flag;// 传递的标识
	private String role;//用户用色
	private String userName;//用户名
	private String passWord;//密码
	private String comId;//商品编号
	private Commodity commodity;// 商品对象
	private List<Commodity> comList;//商品集合
	public Datas() {
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public List<Commodity> getComList() {
		return comList;
	}

	public void setComList(List<Commodity> comList) {
		this.comList = comList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}	
	
}
