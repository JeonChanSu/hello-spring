package hello.hellospring.controller;


import hello.hellospring.service.Myservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

    @Autowired
    Myservice myservice;

    private Logger logger = LoggerFactory.getLogger(MemberController.class);







}
