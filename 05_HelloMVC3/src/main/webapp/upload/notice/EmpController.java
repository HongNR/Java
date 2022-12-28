package com.emp.controller;

import java.util.List;

import com.emp.model.service.EmpService;
import com.emp.model.vo.Employee;
import com.emp.view.MainView;

public class EmpController {
	private static EmpController controller;
	private EmpService service=EmpService.getEmpService();
	private MainView view=MainView.getMainView();
	private EmpController() {}
	
	public static EmpController getEmpController() {
		if(controller==null) controller=new EmpController();
		return controller;
	}
	public void mainMenu() {
		view.mainMenu();
	}
	
	public void searchAll() {
		List<Employee> result=service.searchAll();
		view.printEmployees(result);
	}
	public void searchEmpId() {
		String id=view.inputData("찾을 사원번호");
		Employee e=service.searchEmpId(id);
		view.printEmployees(List.of(e));
	}
	public void insertEmp() {
		Employee e=view.inputEmp();
		int result=service.insertEmp(e);
		view.printMsg(result>0?"입력성공":"입력실패");
	}
	
	
}
