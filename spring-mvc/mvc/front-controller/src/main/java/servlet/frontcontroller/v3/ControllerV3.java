package servlet.frontcontroller.v3;

import java.util.Map;
import servlet.frontcontroller.ModelView;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
