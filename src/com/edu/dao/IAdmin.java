package com.edu.dao;

import java.util.List;

import com.edu.dto.Admin;
import com.edu.dto.Course;
import com.edu.dto.StuScore;
import com.edu.dto.Student;
import com.edu.dto.Teacher;

public interface IAdmin {

	public Admin login(String username, String password) throws Exception;

	public boolean add(Admin a) throws Exception;

	public Admin select(int id) throws Exception;

	public Admin getPsw(String username) throws Exception;

	public boolean delete(int id) throws Exception;

	public boolean modify(Admin ad) throws Exception;

	public List<Admin> list() throws Exception;

	public boolean modisyPsw(Admin ad) throws Exception;

	public List<Admin> recycleBinList() throws Exception;

	public boolean updateStatus(Admin ad) throws Exception;

	public boolean deleteState() throws Exception;
	/*
	 * �޸ĸ�����Ϣ
	 */

	public boolean modifyOwnStudent(Student s) throws Exception;

	/*
	 * ����ѧ����Ų�ѯ
	 */
	public List<StuScore> findByStudent(String num) throws Exception;

	public Course findTeacherByCode(String tea_id) throws Exception;

	/*
	 * �鿴����ѧ����Ϣ ��ͼ
	 */
	public List<StuScore> LispStudentInfo(String code) throws Exception;

	/*
	 * �鿴����ѧ���ɼ� ��ͼ
	 */

	public List<StuScore> LispStudentScore(String code) throws Exception;

	/*
	 * �޸ĸ�����Ϣ
	 */

	public boolean modifyOneTeacher(Teacher tea) throws Exception;
}
