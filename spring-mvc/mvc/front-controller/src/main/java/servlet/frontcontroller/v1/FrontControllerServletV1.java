package servlet.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import servlet.frontcontroller.v1.controller.MemberFormControllerV1;
import servlet.frontcontroller.v1.controller.MemberListControllerV1;
import servlet.frontcontroller.v1.controller.MemberSaveControllerV1;

@Slf4j
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    private final Map<String, ControllerV1> controllerStore = new HashMap<>();

    public FrontControllerServletV1() {
        controllerStore.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerStore.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerStore.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[Connect] : front-controller-v1");

        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerStore.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }
}
