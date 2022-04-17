package bugs;

import service.Service;

public class AbstractController {

    protected Service service;

    protected AbstractController(Service service){
        this.service = service;
    }
}
