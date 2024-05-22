package com.example.frontcontroller;

import com.example.controller.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/* // * HandlerMapping 분리 전 FrontController에 있던 mapping 코드
        if (command.equals("/list.do")) {
            // 리스트 작업(BookListController - Servlet) --> 순수한 일반 클래스(POJO)로 바꿔야함
//            BookListController controller = new BookListController();
//            Controller controller = new BookListController(); // 다형성을 활용해 Controller로 줄이기
            controller = new BookListController(); // 외부에서 컨트롤러 초기화

//            String nextView = controller.requestHandler(req, resp); // 공통 사용으로 외부에서 초기화
            nextView = controller.requestHandler(req, resp);

            // 여기서 포워딩
            //RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + nextView + ".jsp");
//            rd.forward(req, resp); // 포워딩을 FrontController에서 할 수 있도록 변경 - 뷰의 이름만 리턴해준다.
        } else if (command.equals("/delete.do")) {
            // 삭제 작업(BookDeleteController)
            controller = new BookDeleteController();
            nextView = controller.requestHandler(req, resp);
            //resp.sendRedirect("/MF01/" + nextView.split(":/")[1]); // redirect:/list -> /MF01/list
        } else if (command.equals("/update.do")) {
            // 업데이트(수정) 작업(BookUpdatePostController)
        }
*/

// 스프링 내부에 이런 클래스가 존재함. 어떻게 돌아가는지 확인해보자
public class HandlerMapping {
    // Key-Value
    private HashMap<String, Controller> mappings;

    public HandlerMapping() {
        mappings = new HashMap<>();
        try {
            loadMappingFromExternalSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMappingFromExternalSource() throws Exception {
        //? getClass().getClassLoader().getResourceAsStream(""); => resources 폴더의 특정 파일과 연결할 때 사용
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mappingConfig.properties");
        if (inputStream == null) {
            throw new IOException("mappingConfig.properties 파일이 없습니다.");
        }

        Properties properties = new Properties();
        properties.load(inputStream);

        for (String key : properties.stringPropertyNames()) {
            String className = properties.getProperty(key);
            // 리플렉션 기법
            Class<?> clazz = Class.forName(className); //new com.example.controller.BookListController() => 해당 클래스의 바이너리 데이터를 메모리에 올린다.
            Controller controller = (Controller) clazz.getDeclaredConstructor().newInstance();// 메모리에 올라간 바이너리 데이터의 생성자를 호출(인스턴스 생성)
            mappings.put(key, controller);
        }
        inputStream.close();
    }

    public Controller getController(String key) { // /list.do
        return mappings.get(key); // new com.example.controller.BookListController()
    }
}
