package com.project.servlet;

        import com.project.processing.ProfileFormProcessor;
        import com.project.utils.AuthorizationUtils;
        import com.project.utils.ServletUtils;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("loggedin") == null){
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
        } else {
            AuthorizationUtils.instanse.clearError(request);
            if(request.getParameter("edit") != null){
                if(request.getParameter("edit").equals("true")){
                    request.setAttribute("edit", "true");
                }
                if(request.getParameter("edit").equals("changes")){
                    request.removeAttribute("edit");
                    ProfileFormProcessor.instanse.editUsersProfile(request);
                }
            }

            ProfileFormProcessor.instanse.viewUsersOwnProfile(request);
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/profile.jsp");
        }
    }
}