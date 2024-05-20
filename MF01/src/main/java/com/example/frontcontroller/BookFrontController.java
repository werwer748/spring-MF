package com.example.frontcontroller;

import com.example.controller.BookDeleteController;
import com.example.controller.BookListController;
import com.example.controller.HelloServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.do") // list.do, delete.do, update.do 같이 .do로 끝나는 모든 요청을 여기서 받는다.
public class BookFrontController extends HelloServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // http://localhost:8081/MF01/list.do
        String reqPath = req.getRequestURI(); // /MF01/list.do => contextPath(/MF01)를 빼면 어떤 요청이 왔는지를 알 수 있다.
        String cpath = req.getContextPath(); // /MF01
        String command = reqPath.substring(cpath.length()); // /list.do

        if (command.equals("/list.do")) {
            // 리스트 작업(BookListController - Servlet) --> 순수한 일반 클래스(POJO)로 바꿔야함
            BookListController controller = new BookListController();
            String nextView = controller.requestHandler(req, resp);

            // 여기서 포워딩
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + nextView + ".jsp");
            rd.forward(req, resp); // 포워딩을 FrontController에서 할 수 있도록 변경 - 뷰의 이름만 리턴해준다.
        } else if (command.equals("/delete.do")) {
            // 삭제 작업(BookDeleteController)
            BookDeleteController controller = new BookDeleteController();
            String nextView = controller.requestHandler(req, resp);
            resp.sendRedirect("/MF01/" + nextView.split(":/")[1]); // redirect:/list -> /MF01/list
        } else if (command.equals("/update.do")) {
            // 업데이트(수정) 작업(BookUpdatePostController)
        }
    }
}
