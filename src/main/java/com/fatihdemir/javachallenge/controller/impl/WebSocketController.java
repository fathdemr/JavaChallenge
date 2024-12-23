package com.fatihdemir.javachallenge.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {

    @GetMapping("/websocket-test")
    public String websocketTestPage(Model model) {
        // WebSocket URL ve dinamik olarak kullanmak istediğiniz Product ID

        return "test"; // templates/test.html dosyasını döndürür
    }

}
