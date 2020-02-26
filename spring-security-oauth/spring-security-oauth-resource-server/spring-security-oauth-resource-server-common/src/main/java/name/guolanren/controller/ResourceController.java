package name.guolanren.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guolanren
 */
@RestController
public class ResourceController {

    @PreAuthorize("#oauth2.hasScope('resource:read')")
    @GetMapping("/resource")
    public String resource() {
        return "resource";
    }

}
