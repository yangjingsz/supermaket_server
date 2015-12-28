package biz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import entity.Commodity;

import util.Datas;
import util.SysConstants;
import util.Tools;



/**
 * ���������߳��ࡣ ���տͻ�������
 * 
 * @author �
 * 
 */
public class ServerContextListener extends Thread {

	public static void main(String[] args) {
		new ServerContextListener().start();
	}

	public void run() {
		System.out.println("���������");
		// �����������ķ�����
		ServerBiz serverbiz = new ServerBiz();
		// ����������Socket���ʵ��
		ServerSocket serverSocket = null;
		ObjectOutputStream objectOutputStream = null;
		ObjectInputStream objectInputStream = null;
		Socket socket = null;
		try {
			// ����Socket����
			serverSocket = new ServerSocket(Integer.parseInt(Tools
					.getValue(SysConstants.SYS_LISTENERPORT)));
			while (true) {
				// ��ȡ�ͻ���Socket����
				socket = serverSocket.accept();
				objectOutputStream = new ObjectOutputStream(socket
						.getOutputStream());
				objectInputStream = new ObjectInputStream(socket
						.getInputStream());
				
				// ��ȡ�ͻ�������Ķ���
				Datas datas = (Datas) objectInputStream.readObject();
				// �жϿͻ�������Ĳ���
				if (SysConstants.SYS_LOGIN.equals(datas.getFlag())) {
					// ��¼
					String role=serverbiz.userValidate(datas.getUserName(), datas.getPassWord());
					datas.setRole(role);						
				} else {
					if (SysConstants.SYS_QUERY.equals(datas.getFlag())) {// ��ѯ�����Ʒ
						List<Commodity> comList=serverbiz.query();
						datas.setComList(comList);
					} else if (SysConstants.SYS_QUERYBYID.equals(datas.getFlag())) {// ����Ų�ѯ��Ʒ
						String comId = datas.getComId();
						Commodity commodity=serverbiz.queryById(comId);	
						datas.setCommodity(commodity);
					} else if (SysConstants.SYS_INSERT.equals(datas.getFlag())) {//���
						Commodity commodity=datas.getCommodity();
						boolean result=serverbiz.in(commodity);
						if(result)//�������ɹ������óɹ���ʶ
							datas.setFlag(SysConstants.SYS_SUCCESS);
						else //��������ʧ�ܱ�ʶ
							datas.setFlag(SysConstants.SYS_ERROR);
					}else if(SysConstants.SYS_QUERYUPDATEBYID.equals(datas.getFlag())){//����
						Commodity commodity = datas.getCommodity();//�ͻ��˴�����
						//System.out.println("��"+commodity.getNum()+"��棺"+commodity.getNum());
						
						boolean result=serverbiz.out(commodity.getComId(),commodity.getNum());
						
						if(result)//�������ɹ������óɹ���ʶ
							datas.setFlag(SysConstants.SYS_SUCCESS);
						else //��������ʧ�ܱ�ʶ
							datas.setFlag(SysConstants.SYS_ERROR);
					}
					
				}
				objectOutputStream.writeObject(datas);
				objectOutputStream.flush();
				socket.shutdownOutput();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// �ر�
				objectInputStream.close();
				objectOutputStream.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("��������ֹ");
	}

}
