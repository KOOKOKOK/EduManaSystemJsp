package com.edu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.dao.ITeacher;
import com.edu.dao.impl.TeacherImpl;
import com.edu.dto.Teacher;

/**
 * Servlet implementation class TeacherAction
 */
@WebServlet("/TeacherAction")
public class TeacherAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ITeacher teacher = new TeacherImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String operate = request.getParameter("operate");
		if (operate.equals("addTea")) {
			addTea(request, response);
		} else if (operate.equals("deleteTea")) {
			deleteTea(request, response);
		} else if (operate.equals("modifyTea")) {
			modifyTea(request, response);
		} else if (operate.equals("updateTea")) {
			updateTea(request, response);
		} else if (operate.equals("searchTea")) {
			searchTea(request, response);
		} else if (operate.equals("searchStuScorce")) {
			searchStuScorce(request, response);
		} else if (operate.equals("searchAssess")) {
			searchAssess(request, response);
		}else if(operate.equals("listTea")){
			listTea(request,response);
		}
	}

	private void listTea(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		try {
			List<Teacher> tList = teacher.dispAllTeacher();
			request.setAttribute("tList", tList);
			request.getRequestDispatcher("TeacherInfo/listTea.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * ��ʦ�鿴���ۣ�ÿ��ѧ�����Լ������ۣ�
	 */
	private void searchAssess(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/*
	 * ��ѯ����ѧ���ɼ�
	 */
	private void searchStuScorce(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	/*
	 * ���ܣ�����Ա���ҽ�ʦ
	 */
	private void searchTea(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		try {
			Teacher tea = teacher.findTeacher(num);
			if (num == null || num.equals("")) {
				request.setAttribute("msg", "�̹��Ų���Ϊ�գ�");
			}
			if (tea != null) {
				request.setAttribute("TeacherInfo", tea);
				request.getRequestDispatcher("TeacherInfo/listOneTea.jsp").forward(request, response);

			} else {
				request.setAttribute("msg", "�鲻��");
				listTea(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ����Ա�޸Ľ�ʦ��Ϣ���Ȳ�ѯ��������TeacherInfo�� ������modifyTeaҳ��
	 */
	private void updateTea(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		try {
			Teacher tea = teacher.findTeacher(num);
			if (tea != null) {
				request.setAttribute("TeacherInfo", tea);
				request.getRequestDispatcher("TeacherInfo/modifyTea.jsp").forward(request, response);

			} else {
				request.setAttribute("msg", "�鲻��");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ����Ա�޸Ľ�ʦ��Ϣ
	 */
	private void modifyTea(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		StringBuffer sbf = null;
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		String courseid = request.getParameter("courseid");
		String teaTel = request.getParameter("tel");
		Teacher tea;
		try {
			tea = teacher.findTeacher(num);
			sbf = new StringBuffer();
			if (tea != null) {
				if (num == null || num.equals("")) {
					sbf.append("�̹��Ų���Ϊ�գ�");
				} else {
					tea.setNum(num);
				}
				if (name == null || name.equals("")) {
					sbf.append("��������Ϊ�գ�");
				} else {
					tea.setName(name);
				}

				if (courseid == null || courseid.equals("")) {
					sbf.append("�����γ̺Ų���Ϊ�գ�");
				} else {
					tea.setCourse_id(courseid);
				}
				if (teaTel == null || teaTel.equals("")) {
					sbf.append("�ֻ����벻��Ϊ�գ�");
				} else {
					tea.setTeatel(teaTel);
				}

				if (sbf.length() != 0) {
					request.setAttribute("msg", sbf.toString());
					request.getRequestDispatcher("TeacherInfo/modifyea.jsp").forward(request, response);

				} else {
					if (teacher.modifyTeacher(tea)) {
						request.setAttribute("msg", "�޸ĳɹ�!");
						listTea(request, response);				
						} else {
						request.setAttribute("msg", "�޸�ʧ��!");
						request.getRequestDispatcher("TeacherInfo/modifyea.jsp").forward(request, response);

					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * ����Աɾ����ʦ��Ϣ
	 */

	private void deleteTea(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		try {
			if (teacher.deleteTeacher(num)) {
				request.setAttribute("msg", "ɾ���ɹ�!");
				listTea(request, response);
			} else {
				request.setAttribute("msg", "ɾ��ʧ��!");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ����Ա���ӽ�ʦ��Ϣ
	 */

	private void addTea(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer();
		Teacher tea = null;
		try {
			String num = request.getParameter("num");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String courseid = request.getParameter("courseid");
			String teaTel = request.getParameter("tel");
			tea = new Teacher();
			if (num == null || num.equals("")) {
				sbf.append("�̹��Ų���Ϊ�գ�");
			} else {
				tea.setNum(num);
			}
			if (name == null || name.equals("")) {
				sbf.append("��������Ϊ�գ�");
			} else {
				tea.setName(name);
			}

			if (courseid == null || courseid.equals("")) {
				sbf.append("�����γ̺Ų���Ϊ�գ�");
			} else {
				tea.setCourse_id(courseid);
			}
			if (sex.equals("��")) {
				tea.setSex("��");
			} else {
				tea.setSex("Ů");
			}
			if (teaTel == null || teaTel.equals("")) {
				sbf.append("�ֻ����벻��Ϊ�գ�");
			} else {
				tea.setTeatel(teaTel);
			}

			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("TeacherInfo/addTea.jsp").forward(request, response);

			} else {
				if (teacher.addTeacher(tea)) {
					request.setAttribute("msg", "��ӳɹ�!");
					listTea(request, response);
				} else {
					request.setAttribute("msg", "���ʧ��");
					request.getRequestDispatcher("TeacherInfo/addTea.jsp").forward(request, response);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
