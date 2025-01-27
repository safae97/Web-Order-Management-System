//package ma.fstt.controller;
//
//import jakarta.inject.Inject;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import ma.fstt.dao.ClientDAO;
//import ma.fstt.entities.Client;
//
//import java.io.IOException;
//
//@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
//public class LoginServlet extends HttpServlet {
//
//    private ClientDAO clientDAO;  // Declare ClientDAO
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        clientDAO = new ClientDAO();  // Initialize manually
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String email = request.getParameter("email");
//        String nom = request.getParameter("nom");
//
//        Client client = clientDAO.authenticate(email, nom);
//
//        if (client != null) {
//            // Store client information in the session
//            HttpSession session = request.getSession();
//            session.setAttribute("client", client);
//
//            // Redirect to the commandes page
//            response.sendRedirect("commandes");
//        } else {
//            // Authentication failed, redirect to login page with error
//            response.sendRedirect("index.jsp?error=invalid");
//        }
//    }
//}
