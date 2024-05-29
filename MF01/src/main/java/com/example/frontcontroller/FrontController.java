package com.example.frontcontroller;

import com.example.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do") // list.do, delete.do, update.do 같이 .do로 끝나는 모든 요청을 여기서 받는다.
public class FrontController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // http://localhost:8081/MF01/list.do
        String reqPath = req.getRequestURI(); // /MF01/list.do => contextPath(/MF01)를 빼면 어떤 요청이 왔는지를 알 수 있다.
        String cpath = req.getContextPath(); // /MF01
        String command = reqPath.substring(cpath.length()); // /list.do
//        Controller controller = null;// HandlerMapping 분리 전 코드
//        String nextView = null;// HandlerMapping 분리 전 코드
        HandlerMapping mapping = new HandlerMapping();

        Controller controller = mapping.getController(command);

        if (controller == null) {
            // 에러 페이지로 로딩
            return;
        }

        String nextView = controller.requestHandler(req, resp);

        // nextView에 따라서 포워드(뷰처리)인지 리다이렉트 두 경우중 하나 => 메서드를 만들어 처리
        navigate(req, resp, nextView);
    }

    private void navigate(HttpServletRequest req, HttpServletResponse resp, String nextView)
            throws ServletException, IOException {
        if (nextView != null) {
            if (!nextView.contains(":/")) {
                // forward
                RequestDispatcher rd = req.getRequestDispatcher(ViewResolver.makeView(nextView));
                rd.forward(req, resp);
            } else {
                // redirect
                // /까지 잘라서 list.do만 나가기 때문에 contextPath는 유지된다. /가 포함되면 contextPath가 필요함.
                resp.sendRedirect(nextView.split(":/")[1]); // redirect:/[0] | list.do[1]
            }
        }
    }
}
