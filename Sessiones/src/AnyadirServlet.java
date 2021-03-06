

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/anyadir")
public class AnyadirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnyadirServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Leemos los campos del formulario.
		String atributo = request.getParameter("atributo");
		String valor = request.getParameter("valor");

		// Establecemos el tipo MIME de la respuesta.
		response.setContentType("text/html");

		// Creamos un objeto de tipo PrintWriter para escribir el HTML que
		// devolverá al usuario.
		PrintWriter out = response.getWriter();

		// Añadimos el par atributo/valor a la sesión del usuario.
		HttpSession sesion = request.getSession();

		// Para guardar la clave-valor
		sesion.setAttribute(atributo, valor);

		// Creamos una enumeración para guardar todas las claves de la session
		Enumeration nombresDeAtributos = sesion.getAttributeNames();

		// Vamos enviando el HTML que se mostrará en el navegador como
		// respuesta.
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sesiones</title");
		out.println("<meta charset='utf-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Sesión de usuario</h1>");

		// Recorremos la enumeración y vamos mostrando en pantalla los pares
		// atributo/valor.
		while (nombresDeAtributos.hasMoreElements()) {
			String nombreAtributo = (String) nombresDeAtributos.nextElement();
			String valorAtributo = (String) sesion.getAttribute(nombreAtributo);
			out.println(nombreAtributo + " - " + valorAtributo + "<br>");

		}

		out.println("</body>");
		out.println("</html>");
	}


	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
