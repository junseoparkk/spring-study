package servlet.frontcontroller.v3.controller;

import java.util.Map;
import servlet.frontcontroller.ModelView;
import servlet.frontcontroller.v3.ControllerV3;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
