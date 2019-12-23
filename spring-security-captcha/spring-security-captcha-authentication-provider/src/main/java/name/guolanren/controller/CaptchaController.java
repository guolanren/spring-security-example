package name.guolanren.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author guolanren
 */
@Controller
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    @GetMapping(value = "/captcha", produces=MediaType.IMAGE_JPEG_VALUE)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String capText = captchaProducer.createText();
        request.getSession().setAttribute("captcha", capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        try (
            ServletOutputStream out = response.getOutputStream()
        ) {
            ImageIO.write(bi, "jpg", out);
        }
    }

}
