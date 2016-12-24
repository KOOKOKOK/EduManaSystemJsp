package com.edu.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servletAction
 */
@WebServlet("/servletAction")
public class servletAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��������
		int width=112;
		int height=37;
		//�����ڴ�ͼ��
		BufferedImage  image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//��������
		Graphics g=image.getGraphics();
		//ָ���߿����ɫ
		g.setColor(Color.RED);
		//��ͼ��ı߿�
		g.drawRect(0, 0, width, height);
		
		//�����εı���ɫ
		//�趨������ɫ
		g.setColor(Color.YELLOW);
		//�����εı���
		g.fillRect(1, 1, width-2, height-2);
		
		//��������
		g.setFont(new Font("����", Font.BOLD+Font.ITALIC, 24));
		
		//�������
		//�������4�����������ҳ��
		
		Random r=new Random();
		//�趨���ʵ���ɫ
		g.setColor(Color.RED);
		
		//�������4������
		String s="����ְҵ����ѧԺ";
		//�Ƚ�����ת��Ϊunicode����
		s="\u6ec1\u5dde\u804c\u4e1a\u6280\u672f\u5b66\u9662";
		for (int i = 0; i <4; i++) {
		char c=s.charAt(r.nextInt(s.length()));
		int flag=r.nextBoolean()?1:-1;
		g.drawString(c+"", 18+18*i+flag*r.nextInt(3), 24+flag*r.nextInt(5));
		}
		
		/*for (int i = 0; i <=4; i++) {
			int n=r.nextInt(10);
			g.drawString(n+"", 23*i, 22);
			
		}*/
		//��ͼƬ������ͻ���
		ImageIO.write(image, "jpg", response.getOutputStream());
		
		
		
		
		
		
		
		
	}

}
