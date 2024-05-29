package com.example.controller;

/*
// 스프링에서의 구현 모양
public class BookController {
    // 7개 요청을 처리하는 POJO -> Method 단위로 처리
    @RequestMapping("/list")
    // HttpServletRequest => Model
    public String list(Model model) {
        // DAO 연동
        // 객체 바인딩
        return null;
    }

    @RequestMapping("/delete")
    // HttpServletRequest => Model
    public String delete(int num) {
        // DAO 연동
        return "redirect:/list";
    }

    @RequestMapping("/register-get")
    // HttpServletRequest => Model
    public String registerGet() {
        // DAO 연동
        return "register"; // registerGet.jsp
    }
}
*/
/*\
** HandlerMapping **
@RequestMapping("/list")
- /list = com.example.controller.BookController.list()

@RequestMapping("/delete")
/delete.do = com.example.controller.BookDeleteController.delete()

/register-get.do = com.example.controller.BookRegisterGetController
/register-post.do = com.example.controller.BookRegisterPostController
/update-get.do = com.example.controller.BookUpdateGetController
/update-post.do = com.example.controller.BookUpdatePostController
/view.do = com.example.controller.BookViewController
 */
