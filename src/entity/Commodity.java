package entity;

import java.io.Serializable;

/**
 * 商品
 */
public class Commodity implements Serializable{
	private String comId;//商品编号
	private String comName;//商品名称
	private double price;//商品单价
	private String unit;//单位
	private int num;//商品库存
	
	public Commodity(){
		
	}
	public Commodity(String comId,String comName,double price,String unit,int num){
		this.comId=comId;
		this.comName=comName;
		this.price=price;
		this.unit=unit;
		this.num=num;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}	
}
