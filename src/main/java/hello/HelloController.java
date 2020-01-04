package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @RequestMapping("/")
    public String index() {
	LOGGER.info("Responding to call for index.");
	return "HelloCOntroller Response";
    }
}