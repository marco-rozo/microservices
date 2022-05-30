package br.edu.utfpr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping()
public record IndexController() {
    @GetMapping
    public void index(){
        log.info("index default return {}");
    }
}
