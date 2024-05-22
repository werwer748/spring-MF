package com.example.frontcontroller;

// ViewResolver? spring에서는 기본 제공
public class ViewResolver {
    // 수정되지 않도록! private static final
    private static final String VIEWS_DIRECTORY = "/WEB-INF/views";

    private static final String VIEWS_EXTENSION = ".jsp";

    // 언제든 쓸 수 있게 static
    public static String makeView(String viewName) {
        if (viewName == null || viewName.trim().isEmpty()) {
            throw new IllegalArgumentException("잘못된 뷰의 이름 입니다.");
        }
        return VIEWS_DIRECTORY + "/" + viewName + VIEWS_EXTENSION;
    }
}
