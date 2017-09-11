package servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import izone.adams.communication.jboss.cluster.HANotificationMBean;
import izone.adams.communication.jboss.cluster.infinispan.Producer;

/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/notify"})
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Inject
    private Producer producer; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------------");
		producer.getCache().put("Testing a thing", "WAW");
		if(request.getSession().isNew()) {
			request.getSession().setAttribute("number", 1);
		}else {
			Integer oldNumber = (Integer)request.getSession().getAttribute("number");
			request.getSession().setAttribute("number", ++oldNumber);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("<br/> Number is : " + request.getSession().getAttribute("number"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
