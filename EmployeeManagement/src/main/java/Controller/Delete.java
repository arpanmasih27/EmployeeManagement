package Controller;
import java.io.IOException;
import java.io.PrintWriter;

import Enteties.Address;
import Enteties.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class Delete extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	resp.setContentType("text/html");
	PrintWriter out = resp.getWriter();
	try {
		
		int id=Integer.parseInt(req.getParameter("id"));
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("arpan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Employee e1 = em.find(Employee.class, id);
		
		if (e1!=null) 
		{
			Address a1 = e1.getAddress();
			et.begin();
			em.remove(a1);
			em.remove(e1);
			RequestDispatcher rd = req.getRequestDispatcher("Welcome.html");
			rd.include(req, resp);			
			et.commit();
		}
		
		
		
}catch (Exception e) {
	// TODO: handle exception
}
}
}
