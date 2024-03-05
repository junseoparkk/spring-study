package servlet.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import servlet.frontcontroller.MyView;
import servlet.frontcontroller.v2.controller.MemberFormControllerV2;
import servlet.frontcontroller.v2.controller.MemberListControllerV2;
import servlet.frontcontroller.v2.controller.MemberSaveControllerV2;

@Slf4j
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private final Map<String, ControllerV2> controllerStore = new HashMap<>();

    public FrontControllerServletV2() {
        controllerStore.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerStore.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerStore.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[Connect] : front-controller-v2");

        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerStore.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
