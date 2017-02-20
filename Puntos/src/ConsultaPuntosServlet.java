

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/consultapuntos")
public class ConsultaPuntosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaPuntosServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String puntos="0";
		
		Cookie[] cookies=request.getCookies(); 
		if(cookies !=null){
			for (Cookie c : cookies) {
				if(c.getName().equals("cookiepuntos")){
					puntos=c.getValue();
					int p=Integer.parseInt(puntos);
					p++;
					puntos=String.valueOf(p);
				}
				
			}
		}
		Cookie miCookie=new Cookie("cookiepuntos",puntos);
		miCookie.setMaxAge(604800);
		response.addCookie(miCookie);
		
		
		PrintWriter out= response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Puntos</title>");
		out.println("<meta charset='utf-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Puntos</h1>");
		out.println("<p>Cada vez que recargues la página recibirás 1 punto.</p>");
		out.println("ahora tienes: " +puntos + " puntos.");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
